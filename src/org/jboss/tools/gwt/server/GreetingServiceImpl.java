package org.jboss.tools.gwt.server;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.jboss.tools.gwt.client.GreetingService;
import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.FieldVerifier;
import org.jboss.tools.gwt.shared.OfficeCode;
import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.shared.UserController;
import org.springframework.web.context.ServletContextAware;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService,ServletContextAware {

	UserController userController = new UserController();
	List<User> newClients = new ArrayList<User>();
	List<Clients> foundClients = null;
	List<Clients> foundClientsArray = new ArrayList<Clients>();
	Logger logger = Logger.getLogger("logger");
	private ServletContext servletContext;

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

		//userController = new UserController();
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
	public String createClient(Client client) throws IllegalArgumentException {

		//userController = new UserController();
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

	String create = null;

	// implementation to search created clients in telos database
	@Override
	public List<Clients> searchClients(Client client)
			throws IllegalArgumentException {
		// Verify that the input is valid.
				//userController = new UserController();
				try{
					foundClients = userController.getSearchClient(client);
					logger.log(Level.SEVERE,
							"Inside service ");
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE,
							"Inside emailing service " + e.toString());
				}
		//return (Clients[]) foundClients.toArray(new Clients[0]);
				return foundClients;
	}
	
	// implementation to search created clients in telos database
	@Override
	public List<Clients> searchClientsByCarNum(Client client)
			throws IllegalArgumentException {
		// Verify that the input is valid.
				//userController = new UserController();
				try{
					foundClients = userController.getSearchClientByCarNum(client);
					logger.log(Level.SEVERE,
							"Inside service ");
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

	@Override
	public String upgradeClient(Client client) {
		
		//userController = new UserController();
		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		try {
			update = userController.updateClientResponse(client);
			logger.log(Level.SEVERE, "response After DB and controller for update");

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return update;
	}

	String update = null;

	@Override
	public String createAgent(Agent agent) {
		try {
			update = userController.createAgentResponse(agent);
			logger.log(Level.SEVERE, "response After DB and controller for update");

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return update;
	}

	List<Agent> lAgent= null;
	@Override
	public List<Agent> loadAgents() {
		// TODO Auto-generated method stub
		try {
			lAgent = userController.getSearchAgent();
			logger.log(Level.SEVERE, "response After DB and controller for update "+lAgent.get(0).getScreenName());

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return lAgent;
	}

	@Override
	public String getPdfReport(String fileName, Map<String, Object> param) {
        try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getPdfReportForIRDA(filePath, param);
        //	return "resources/Reports/report.pdf";
        	return response;

        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +ex.toString());
        }
        return null;
    }

	@Override
	 public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

	private List<OfficeCode>lOfficeCode = null;
    
    @Override
	public List<OfficeCode> loadOfficeCode() {
		try {
			lOfficeCode = userController.getSearchOfficeCode();
			logger.log(Level.SEVERE, "response After DB and controller for update "+lAgent.get(0).getScreenName());

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return lOfficeCode;
	}

	@Override
	public String getExcel(String fileName, Map<String, Object> param) {
		
        try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getExcelReportForIRDA(filePath, param);
        	return response;

        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +ex.toString());
        }
        return "resources/Reports/report.xls";
	}
}
