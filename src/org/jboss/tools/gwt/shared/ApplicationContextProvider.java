package org.jboss.tools.gwt.shared;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(final ApplicationContext applicationContext)
			throws BeansException {
		// Assign the ApplicationContext into a static variable
		ApplicationContextProvider.applicationContext = applicationContext;
	}

}
