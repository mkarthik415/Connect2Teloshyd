package org.jboss.tools.gwt.shared;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.tools.gwt.shared.Client;

import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;

public class UserController {

	private String uname;
	Logger logger = Logger.getLogger("logger");
	User userResponse = null;
	Boolean created = false;
	List<Clients> lClients = null;
	ApplicationContext appContext = null;

	// logic to get the data for login from telos DB
	public User getUserResponse(final String user, final String password) {
		// User user = new User();
		System.out.println("Inside spring before appcontext being called");
		appContext = ApplicationContextProvider.getApplicationContext();

		System.out.println("now tUserDAO");

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			userResponse = tUserDAO.selectUser(user, password);
			logger.log(Level.SEVERE,
					"response from DB " + userResponse.getName());
			uname = userResponse.getName();

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
			return null;
		}
		return userResponse;
	}

	// logic to put data for create new client into telos DB
	public Boolean getCreateClientResponse(Client client) {
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			created = tUserDAO.createClient(client);
		} catch (Exception e) {

		}

		return created;

	}
	
	public  List<Clients> getSearchClient(Client client)
	{
		appContext = ApplicationContextProvider.getApplicationContext();
		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");
		try{
			lClients= tUserDAO.searchClient(client);
		}
		catch (Exception e)
		{
			
		}
		return lClients;
		
	}

}
