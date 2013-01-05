package org.jboss.tools.gwt.shared;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jboss.tools.gwt.shared.Client;

import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.beans.TUserDAO;
import org.springframework.context.ApplicationContext;

public class UserController {

	private String uname;
	Logger logger = Logger.getLogger("logger");
	Boolean userResponse = false;
	Boolean created = false;
	List<Clients> lClients = null;
	ApplicationContext appContext = null;

	// logic to get the data for login from telos DB
	public Boolean getUserResponse(final String user, final String password) {
		// User user = new User();
		appContext = ApplicationContextProvider.getApplicationContext();

		final TUserDAO tUserDAO = (TUserDAO) appContext.getBean("tUserDAO");

		try {
			userResponse = tUserDAO.selectUser(user, password);
			logger.log(Level.SEVERE,
					"response from DB ");

		} catch (Exception e) {

			logger.log(Level.SEVERE, "Inside UserController " + e.toString());
			return userResponse;
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
	
	public Boolean getEmailClient(Client client) throws AddressException, MessagingException
	{
		SendEmail sendEmail = new SendEmail();
		Boolean sent = sendEmail.emailSent(client);
		return sent;
		
	}
	
	public String getSMSClient(Client client)
	{
		SmsLane smsLane = new SmsLane();
		String response = smsLane.SMSSender(client.getPhoneNumber(), "\n\n Your documents have been dispatched. Thank you for doing business with us.\n\n With Regards, \n Telos");
		return response;
		
	}

}
