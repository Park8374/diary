package controller.Schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;

@WebServlet("/schedule/removeSchedule")
public class RemoveScheduleController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 세션 유효성
        HttpSession session = request.getSession();
        if(session.getAttribute("loginMember")==null) {
           response.sendRedirect(request.getContextPath()+"/member/loginMember");
           return;
        }   

    	
    	// 스케줄 번호를 가져옵니다.
    	int scheduleNo = Integer.parseInt(request.getParameter("scheduleNo"));
     
        // ScheduleDao를 이용하여 스케줄을 삭제합니다.
        ScheduleDao scheduleDao = new ScheduleDao();
        boolean success = scheduleDao.deleteSchedule(scheduleNo);
        
        if(success) {
            System.out.println("삭제성공");
            request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);
         } else {
        	 System.out.println("삭제실페");
        	 request.getRequestDispatcher("/WEB-INF/view/schedule/scheduleListByDay.jsp").forward(request, response);
         }
         
         
         
      }

      

   }
