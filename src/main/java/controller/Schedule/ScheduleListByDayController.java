package controller.Schedule;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import vo.Member;
import vo.Schedule;

@WebServlet("/schedule/scheduleListByDay") 
public class ScheduleListByDayController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 코드
			HttpSession session = request.getSession();
			if(session.getAttribute("loginMember") == null) {
				response.sendRedirect(request.getContextPath()+"/member/loginMember");
				return;
			}
		Member member = (Member)session.getAttribute("loginMember");
		
		int targetY = Integer.parseInt(request.getParameter("targetY"));
		int targetM = Integer.parseInt(request.getParameter("targetM"));
		int targetD = Integer.parseInt(request.getParameter("targetD"));
		
		ScheduleDao scheduleDao = new ScheduleDao();
		List<Schedule> list = scheduleDao.selectScheduleByDay(member.getMemberId(), targetY, targetM+1, targetD);
		
		request.setAttribute("list", list);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);
	}
}