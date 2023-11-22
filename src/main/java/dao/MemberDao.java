package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import vo.Member;

public class MemberDao {
	// 로그인
	public Member loginMember(Member paramMember) {
		Member resultMember = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 로그인 쿼리 구현
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			String sql = "SELECT member_no memberNo, member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();

			if (rs.next()) {
				resultMember = new Member();
				resultMember.setMemberNo(rs.getInt("memberNo"));
				resultMember.setMemberId(rs.getString("memberId")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
	            if (rs != null) {
	                rs.close();
	            }
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
		return resultMember;
	}
	
	
	// 회원가입
	public int insertMember(Member paramMember) {
		int row = 0;
		// 커넥션풀
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// Tomcat context.xml 설정을 로드
			Context context = new InitialContext();
			// context.xml에서 커넥션풀 객체 로드
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
			conn = ds.getConnection();
			// conn 디버깅
			System.out.println(conn +" <-- conn");
			String sql = "INSERT INTO member(member_id, member_pw) VALUES(?,PASSWORD(?))";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			row = stmt.executeUpdate();
			
		} catch(Exception e) {
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
	
	
	// 회원 탈퇴 메서드
	public boolean deleteMember(int memberNo, String memberPw) throws NamingException {
	    boolean success = false;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    Context context = new InitialContext();
	    
	    DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
	    try {
	        conn = ds.getConnection();
	        String sql = "DELETE FROM member WHERE member_no = ? AND member_pw = PASSWORD(?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, memberNo);
	        stmt.setString(2, memberPw);
	        
	        int row = stmt.executeUpdate();
	        
	        if (row > 0) {
	            success = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    } finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            
	        }
	    }
	    
	    return success;
	}

	// MemberDao 클래스 내에 비밀번호 업데이트를 위한 메서드
	public boolean updatePwMember(int memberNo, String currentPassword, String newPassword) throws NamingException {
	    boolean success = false;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    Context context = new InitialContext();
	    DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
	    
	    try {
	        conn = ds.getConnection(); // getConnection() 메서드는 커넥션을 가져오는 로직으로 대체해야 합니다.
	        
	        // 기존 비밀번호 확인
	        String checkSql = "SELECT member_no FROM member WHERE member_no = ? AND member_pw = PASSWORD(?)";
	        stmt = conn.prepareStatement(checkSql);
	        stmt.setInt(1, memberNo);
	        stmt.setString(2, currentPassword);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) { // 현재 비밀번호가 일치하는 경우
	            rs.close();
	            stmt.close();
	            
	            // 새로운 비밀번호 업데이트
	            String updateSql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_no = ?";
	            stmt = conn.prepareStatement(updateSql);
	            stmt.setString(1, newPassword);
	            stmt.setInt(2, memberNo);
	            int row = stmt.executeUpdate();
	            
	            // 업데이트 성공 시 success 값을 true로 설정
	            if (row > 0) {
	                success = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 예외 처리 필요
	    } finally {
	        // 리소스 해제 필요
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // 예외 처리 필요
	        }
	    }
	    
	    return success;
	}
}