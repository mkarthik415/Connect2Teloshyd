package org.jboss.tools.gwt.scheduler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TriggerScheduler extends HttpServlet{
	
	 public void init() throws ServletException
	    {
	          /// Automatically java script can run here
	          System.out.println("************");
	          System.out.println("*** Servlet Initialized successfully ***..");
	          new ClassPathXmlApplicationContext("Spring-Quartz.xml");
	          System.out.println("***********");

	    }

	public static void main(String[] args) {
		//ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();
		new ClassPathXmlApplicationContext("Spring-Quartz.xml");

	}

}
