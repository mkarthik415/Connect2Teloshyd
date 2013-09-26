package org.jboss.tools.gwt.scheduler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TriggerScheduler {

	public static void main(String[] args) {
		//ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();
		new ClassPathXmlApplicationContext("Spring-Quartz.xml");

	}

}
