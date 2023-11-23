package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScheduleDao {
   public List<Map<String, Object>> selectScheduleByMonth(String memberId, int year, int month) {
      List<Map<String, Object>> list = new ArrayList<>();

      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         Context context = new InitialContext();
         // context.xml에서 커넥션풀 객체 로드
         DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
         conn = ds.getConnection();

         String sql = """
                  SELECT
                     DAY(schedule_date) scheduleDay,
                     COUNT(*) cnt,
                     GROUP_CONCAT(substr(schedule_memo, 1, 5)) memo
                  FROM schedule
                  WHERE member_id = ? 
                  AND YEAR(schedule_date) = ?
                  AND MONTH(schedule_date) = ?
                  GROUP BY schedule_date
                  ORDER BY schedule_date ASC
            """;
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, memberId);
         stmt.setInt(2, year);
         stmt.setInt(3, month);
         System.out.println(stmt + " <-- stmt");
         rs = stmt.executeQuery();
         while(rs.next()) {
            Map<String, Object> m = new HashMap<>();
            m.put("scheduleDay", rs.getString("scheduleDay"));
            m.put("cnt", rs.getInt("cnt"));
            m.put("memo", rs.getString("memo"));
            list.add(m);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            rs.close();
            stmt.close();
            conn.close();
         } catch (SQLException e1) {
            e1.printStackTrace();
         }
      }
      return list;
   }
}