package org.jboss.tools.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.tools.gwt.client.GreetingService;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.FieldVerifier;
import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.shared.UserController;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	UserController userController = null;
	List<User> newClients = new ArrayList<User>();
	List<Clients> foundClients = null;
	List<Clients> foundClientsArray = new ArrayList<Clients>();
	Logger logger = Logger.getLogger("logger");

	public Boolean greetServer(String input, String pInput)
			throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		Boolean user = false;
		String userAgent = "from this blockcs";

		userController = new UserController();
		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		try {
			user = userController.getUserResponse(input, pInput);
			logger.log(Level.SEVERE, "response After DB and controller ");
			userAgent = escapeHtml(userAgent);
			// storeUserInSession(user);
			/*if (user != null) {
				newClients.add(user);
			}*/
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());
			return user;
		}
		return user;

	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	private User storeUserInSession(User user) {
		HttpSession session = this.getThreadLocalRequest().getSession(true);
		if (session.getAttribute("user") != null) {
			return (User) session.getAttribute("user");
		} else {
			session.setAttribute("user", user);
		}
		return null;

	}

	@Override
	public void greetLogout() {
		// TODO Auto-generated method stub
		deleteUserFromSession();
	}

	private void deleteUserFromSession() {
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("user");
	}

	@Override
	public Boolean createClient(Client client) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		userController = new UserController();
		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		try {
			create = userController.getCreateClientResponse(client);
			logger.log(Level.SEVERE, "response After DB and controller ");

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return create;
	}

	Boolean create = false;

	// implementation to search created clients in telos database
	@Override
	public List<Clients> searchClients(Client client)
			throws IllegalArgumentException {
		// Verify that the input is valid.
				if (!FieldVerifier.isValidName(client.getClientName())) {
					// If the input is not valid, throw an IllegalArgumentException back
					// to
					// the client.
					throw new IllegalArgumentException(
							"Name must be at least 4 characters long");
				}
				userController = new UserController();
				try{
					foundClients = userController.getSearchClient(client);
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE,
							"Inside emailing service " + e.toString());
				}
		//return (Clients[]) foundClients.toArray(new Clients[0]);
				return foundClients;
	}

	@Override
	public Boolean sendEmail(Client client) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Boolean sent = false;
		try{
			sent = userController.getEmailClient(client);
			
		}
		catch(Exception e)
		{
			return sent;
		}
		return sent;
	}

	@Override
	public String sendSms(Client client) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String response = null;
		try{
			 response = userController.getSMSClient(client);
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,
					"Inside sms service " + e.toString());
		}
		return response;
	}
}
