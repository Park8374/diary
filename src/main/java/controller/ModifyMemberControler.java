package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/member/modifyPwMember") 
public class ModifyMemberControler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // 세션 유효성 검사
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
            return;
        }
    	
    	// 해당 기능의 화면을 보여주기 위한 처리
        // 비밀번호 수정을 위한 폼을 보여줄 수 있습니다.
        request.getRequestDispatcher("/WEB-INF/view/member/modifyMemberPw.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 세션 유효성 검사
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
            return;
        }

        // 사용자가 입력한 비밀번호 정보 가져오기
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        MemberDao memberDao = new MemberDao();
        Member loginMember = (Member) session.getAttribute("loginMember");

        // 새로운 비밀번호로 회원 정보 업데이트 시도
        boolean updateSuccess = false;
        try {
            updateSuccess = memberDao.updatePwMember(loginMember.getMemberNo(), currentPassword, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 처리
        }

        if (updateSuccess) {
        	session.invalidate();
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
        } else {
            // 실패 시 처리
            // 실패 메시지를 전달하거나 적절한 처리를 수행
            request.setAttribute("errorMessage", "비밀번호 수정에 실패했습니다. 다시 시도해주세요.");
            request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp").forward(request, response);
        }
    }
}
