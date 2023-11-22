package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;


@WebServlet("/member/memberHome")
public class MemberHomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		// 달력 출력 시 필요한 모델
	     // 1) 출력하고자 하는 년/월/1일
	      
	      Calendar firstD = Calendar.getInstance();      
	      firstD.set(Calendar.DATE, 1);
	      int targetY = firstD.get(Calendar.YEAR);
	      int targetM = firstD.get(Calendar.MONTH);      
	      if(request.getParameter("targetY")!=null && request.getParameter("targetM")!=null) {
	         targetY = Integer.parseInt(request.getParameter("targetY"));
	         targetM = Integer.parseInt(request.getParameter("targetM"));
	         firstD.set(Calendar.YEAR, targetY);
	         firstD.set(Calendar.MONTH, targetM);
	      }
	      
	      // 2) firstD를 통해 마지막 일자 ( ex) 30일,31일,...)
	      int lastD = firstD.getActualMaximum(Calendar.DATE);
	      
	      
	      // 3) firstD를 통해 1일의 요일 -> 시작공백
	      int beginBlank = firstD.get(Calendar.DAY_OF_WEEK)-1; //  요일 맵핑숫자값 -1 ex) 일1 ,월2 , ...
	      
	      // 4) 전체 TD가 7로 나누어 떨어지도록 endBlank 설정
	      int endBlank = 0;
	      if((beginBlank +lastD)% 7 != 0) {
	    	  endBlank =7 - (beginBlank + lastD) % 7 ;
	      }
	      
	      // 5 전체 TD의 개수
	      int totalTd = beginBlank + lastD + endBlank;
	      
	      request.setAttribute("targetY", targetY);
	      request.setAttribute("targetM", targetM);
	      request.setAttribute("totalTd", totalTd);
	      request.setAttribute("beginBlank", beginBlank);
	      request.setAttribute("endBlank", endBlank);
	      request.setAttribute("lastD", lastD);
	      
		Member member = (Member)session.getAttribute("loginmember");
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/member/memberHome.jsp").forward(request, response);
	}
	
	

}