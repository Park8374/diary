package Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  {      
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	// application.setAttribute()
    	sce.getServletContext().setAttribute("currentCnt", 0);
    	System.out.println("MyContextListener currentCnt : "
    			+ sce.getServletContext().getAttribute("currentCnt"));
    }
	
}