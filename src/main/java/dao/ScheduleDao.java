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

import vo.Schedule;

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
	
	public List<Schedule> selectScheduleByDay(
			String memberId, int year, int month, int day) {
		List<Schedule> list = new ArrayList<>();
		
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
							schedule_no scheduleNo, 
							schedule_date scheduleDate,
							schedule_memo scheduleMemo, 
							schedule_emoji scheduleEmoji
							FROM schedule
							WHERE member_id = ?
									AND YEAR(schedule_date) = ?
									AND MONTH(schedule_date) = ?
									AND DAY(schedule_date) = ?
													""";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, year);
			stmt.setInt(3, month);
			stmt.setInt(4, day);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Schedule s = new Schedule();
				s.setScheduleNo(rs.getInt("scheduleNo"));
				s.setScheduleDate(rs.getString("scheduleDate"));
				s.setScheduleMemo(rs.getString("scheduleMemo"));
				s.setScheduleEmoji(rs.getString("scheduleEmoji"));
				list.add(s);
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
	
	
	public boolean deleteSchedule(int scheduleNo) {
		boolean success = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();

			String sql = """
						DELETE 
						FROM schedule
						WHERE schedule_no = ?
					""";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scheduleNo);

			System.out.println(stmt + " <-- stmt");
			int row = stmt.executeUpdate();
			
			if (row > 0) {
	            success = true;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return success;
	}
	
	public boolean addSchedule(Schedule schedule) {
	    boolean success = false;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        Context context = new InitialContext();
	        // context.xml에서 커넥션풀 객체 로드
	        DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
	        conn = ds.getConnection();
	        String sql = """
	                    INSERT INTO schedule (
	                    member_id,
	                    schedule_date,
	                    schedule_emoji,
	                    schedule_memo
	                    )
	                    VALUES (?, ?, ?, ?)
	                """;
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, schedule.getMemberId());
	        stmt.setString(2, schedule.getScheduleDate());
	        stmt.setString(3, schedule.getScheduleEmoji());
	        stmt.setString(4, schedule.getScheduleMemo());
	        System.out.println(stmt + " <-- stmt");
	        int row = stmt.executeUpdate();

	        if (row > 0) {
	            success = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	    }
	    return success;
	}

	// modify(수정)
		public int modifySchedule (String scheduleMemo , String scheduleEmoji , int scheduleNo) {
			int row = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				// Tomcat context.xml 설정을 로드!
				Context context = new InitialContext(); 
					// context.xml에서 커넥션풀 객체를 로드!
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				String sql="""
							UPDATE schedule SET schedule_memo=? ,
												schedule_emoji=?
							WHERE schedule_no = ?
						""";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, scheduleMemo);
				stmt.setString(2, scheduleEmoji);
				stmt.setInt(3, scheduleNo);			

				System.out.println(stmt + "<-stmt");
				row = stmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try {				
					stmt.close();
					conn.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return row;
		}
}