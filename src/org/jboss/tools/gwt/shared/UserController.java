package org.jboss.tools.gwt.shared;



import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;

public class UserController {
	
	private String uname;
	Logger logger = Logger.getLogger("logger");
	public User getUserResponse(final String user,final String password) {
		//User user = new User();
		System.out.println("Inside spring before appcontext being called");
		 ApplicationContext appContext = ApplicationContextProvider
				.getApplicationContext();
		 
		 System.out.println("now tUserDAO");

		final TUserDAO tUserDAO = (TUserDAO) appContext
				.getBean("tUserDAO");

		try {
			userResponse= tUserDAO.selectUser(user,password);
			logger.log(Level.SEVERE, "response from DB "+userResponse.getName());
                    uname = userResponse.getName();


		} catch (Exception e) {

			logger.log(Level.SEVERE,"Inside UserController "+ e.toString());
			return null;
		}
		return userResponse;
	}

	User userResponse = null;
}
