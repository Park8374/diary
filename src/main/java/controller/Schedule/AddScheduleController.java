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

/**
 * Servlet implementation class AddScheduleController
 */
@WebServlet("/schedule/addSchedule")
public class AddScheduleController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/loginMember");
			return;
		}
		
		Member member = (Member)session.getAttribute("loginMember");
        request.setCharacterEncoding("utf-8");
        
    	String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String emoji = request.getParameter("emoji");
        String scheduleMemo = request.getParameter("scheduleMemo");

        // 위에서 받은 데이터를 Schedule 객체에 설정
        Schedule schedule = new Schedule();
        schedule.setMemberId(memberId);
        schedule.setScheduleDate(year + "-" + month + "-" + day); // 날짜 형식에 따라 적절히 설정
        schedule.setScheduleEmoji(emoji);
        schedule.setScheduleMemo(scheduleMemo);

        // ScheduleDao를 사용하여 일정을 추가하는 메서드 호출
        ScheduleDao scheduleDao = new ScheduleDao();
        boolean success = scheduleDao.addSchedule(schedule);

        // 일정 추가가 성공했는지에 따라 다른 페이지로 리다이렉트할 수 있도록 설정
        if (success) {
            request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);
        }
    }
}


