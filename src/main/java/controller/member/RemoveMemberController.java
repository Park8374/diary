package controller.member;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
            return;
        }
        Member member = (Member)session.getAttribute("loginmember");
		request.setAttribute("member", member);
        request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        // session 유효성 검사
        HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
            return;
        }
        
        // 사용자가 입력한 비밀번호
        String memberPw = request.getParameter("currentPassword");

        // DAO를 사용하여 회원 삭제 시도
        MemberDao memberDao = new MemberDao();
        Member loginMember = (Member) session.getAttribute("loginMember");

        boolean deletionSuccess = false;
        try {
            deletionSuccess = memberDao.deleteMember(loginMember.getMemberNo(), memberPw);
        } catch (NamingException e) {
            e.printStackTrace();
            // NamingException 처리
        }

        if (deletionSuccess) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/member/loginMember");
        } else {
            // 삭제 실패 시 처리
            // 실패 메시지를 전달하거나 적절한 처리를 수행
            request.setAttribute("errorMessage", "정확한 암호를 입력해주세요.");
            request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
        }
    }

}
