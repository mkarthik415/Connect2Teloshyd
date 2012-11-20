package org.jboss.tools.gwt.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.tools.gwt.client.GreetingService;
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
	List<User> stockclients = new ArrayList<User>();
	Logger logger = Logger.getLogger("logger");
	public User[] greetServer(String input, String pInput) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = "reply from server side";
		String userAgent = "from this blockcs";

		userController = new UserController();
		// Escape data from the client to avoid cross-site script vulnerabilities.
		try{
			User user = userController.getUserResponse(input, pInput);
			logger.log(Level.SEVERE, "response After DB and controller ");
			input = escapeHtml(input);
			userAgent = escapeHtml(userAgent);
			//storeUserInSession(user);
			if(user !=null)
			{
				stockclients.add(user);
			}
		}
			catch(Exception e){
				logger.log(Level.SEVERE, "Inside GreetingServiceImpl "+e.toString());
				return (User[]) stockclients.toArray(new User[0]);
			}
			return (User[]) stockclients.toArray(new User[0]);

	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
				">", "&gt;");
	}
	
	private User storeUserInSession(User user) {
		HttpSession session = this.getThreadLocalRequest().getSession(true);
	    if (session.getAttribute("user") != null)
	    {
	        return (User) session.getAttribute("user");
	    }
	    else 
	    {
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
}
