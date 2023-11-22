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

@WebServlet("/member/loginMember")
public class LoginMemberController extends HttpServlet {
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/WEB-INF/view/member/memberHome");
			return;
		}
			request.getRequestDispatcher("/WEB-INF/view/member/loginMember.jsp").forward(request, response);
	}
	
	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/memberHome");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
        String memberPw = request.getParameter("memberPw");
		
		// request.getParameter....
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
        paramMember.setMemberPw(memberPw);
        
        
		MemberDao memberDao = new MemberDao();
		Member resultMember = memberDao.loginMember(paramMember);
		
		if(resultMember == null) {
			// 로그인 실패 -> 로그인폼으로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/loginMember");
            return;
		}
		
		// 로그인 성공시
		session.setAttribute("loginMember", resultMember);
		response.sendRedirect(request.getContextPath()+"/member/memberHome"); 
	}

}