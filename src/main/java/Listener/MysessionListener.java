package Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.CounterDao;
import vo.Counter;

/**
 * Application Lifecycle Listener implementation class MysessionListener
 *
 */
@WebListener
public class MysessionListener implements HttpSessionListener {

	// vire에서 application.getAttribute("currentCnt") 값을 호출 하면 현재 접속자수를 출력가능.
    public void sessionCreated(HttpSessionEvent se)  { 
    	 
    	 
    	 // 현재 접속자
    	 int n = (Integer)se.getSession().getServletContext().getAttribute("currentCnt");
    	 se.getSession().getServletContext().setAttribute("currentCnt" , n+1);
    	 
    	 
    	 // 오늘의 누적 접속자
    	 CounterDao counterDao = new CounterDao();
    	 Counter counter = counterDao.selecctCounterByToday();
    	 if (counter == null) { // 오늘의 첫 접속자
    		 counterDao.insertCounter();
    	 } else {
    		 counterDao.updateCounter();
    	 }
    	 
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
     // -1    
    	 int n = (Integer)se.getSession().getServletContext().getAttribute("currentCnt");
    	 se.getSession().getServletContext().setAttribute("currentCnt" , n-1);
    	
    }
	
}
