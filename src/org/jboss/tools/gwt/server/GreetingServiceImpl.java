package org.jboss.tools.gwt.server;


import java.util.ArrayList;
import java.util.Date;
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
import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.FieldVerifier;
import org.jboss.tools.gwt.shared.File;
import org.jboss.tools.gwt.shared.Insurance;
import org.jboss.tools.gwt.shared.OfficeCode;
import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.shared.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	List<Company> foundCompany= null;
	List<File> foundDocuments = null;
	List<Clients> foundClientsArray = new ArrayList<Clients>();
	Logger logger = Logger.getLogger("logger");
	private ServletContext servletContext;
	@Autowired
	private HttpServletRequest request;
	int timeout;

	public Integer greetServer(String input, String pInput)
			throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		Integer user = null;
		String userAgent = "from this blockcs";
		try {
			user = userController.getUserResponse(input, pInput);
			logger.log(Level.SEVERE, "response After DB and controller ");
			userAgent = escapeHtml(userAgent);
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());
			return null;
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

	@SuppressWarnings("unused")
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
				try{
					foundClients = userController.getSearchClient(client);
					logger.log(Level.SEVERE,
							"Inside service after execution of the query the start date is :::: "+foundClients.get(0).getPolicyStartdate());
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE,
							"Inside emailing service " + e.toString());
				}
				return foundClients;
	}
	
	@Override
	public List<Clients> searchClientsByCarNum(Client client)
			throws IllegalArgumentException {
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
				return foundClients;
	}

	@Override
	public Boolean sendEmail(Client client) throws IllegalArgumentException {
		Boolean sent = false;
		try{
			sent = userController.getEmailClient(client);
			
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,
					"Inside mail service " + e.toString());
			return sent;
		}
		return sent;
	}

	@Override
	public String sendSms(Client client) throws IllegalArgumentException {
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
	
	@Override
	public String createInsuranceCompony(Insurance insurance) {
		try {
			update = userController.createInsuranceResponse(insurance);
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
	
	List<Insurance> lInsurance= null;
	@Override
	public List<Insurance> loadInsurance() {
		// TODO Auto-generated method stub
		try {
			lInsurance = userController.getSearchInsuranceCompany();
			logger.log(Level.SEVERE, "response After DB and controller for update "+lInsurance.get(0).getScreenName());

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Inside GreetingServiceImpl " + e.toString());

		}
		return lInsurance;
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
	public String getPdfReportForPendingPolicy(String fileName, Map<String, Object> param) {
        try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getPdfReportForPendingPolicy(filePath, param);
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
	
	@Override
	public String getExcelForPendingPolicy(String fileName, Map<String, Object> param) {
		
        try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getExcelReportForPendingPolicy(filePath, param);
        	return response;

        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +ex.toString());
        }
        return "resources/Reports/policy.xls";
	}

	@Override
	public List<Clients> searchClientsByPolicyDates(Client client)
			throws IllegalArgumentException {
		try {
			foundClients = userController.getSearchClientByPolicyDates(client);
			logger.log(Level.SEVERE, "Inside service ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside saerch service by policy dates " + e.toString());
		}
		return foundClients;
	}

	@Override
	public List<Clients> searchClientsBySrialNo(Client client)
			throws IllegalArgumentException {
		try {
			foundClients = userController.getSearchClientBySerialNo(client);
			logger.log(Level.SEVERE, "Inside service ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside saerch service by policy dates "
					+ e.toString());
		}
		return foundClients;
	}

	@Override
	public List<Clients> searchClientsByPolicyNo(Client client)
			throws IllegalArgumentException {
		try {
			foundClients = userController.getSearchClientByPolicyNo(client);
			logger.log(Level.SEVERE, "Inside service ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside saerch service by policy/certificate Number "
					+ e.toString());
		}
		return foundClients;
	}

	@Override
	public List<Company> loadComapny() {
		try {
			foundCompany = userController.getListOfCompanies();
			logger.log(Level.SEVERE, "Inside service ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside get companies list in DB "
					+ e.toString());
		}
		return foundCompany;
	}

	@Override
	public String getPdfReportForClient(String fileName,
			Map<String, Object> param) {
        try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getPdfReportForClient(filePath, param);
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
	public String getExcelForClient(String fileName, Map<String, Object> param) {
		try
        {
        	String filePath = this.getServletContext().getRealPath(fileName);
        	logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +filePath);
        	String response = userController.getExcelReportForClient(filePath, param);
        	return response;

        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE,
					"inside getPdfReport and path is  " +ex.toString());
        }
        return "resources/Reports/client.xls";
	}
	
	public Boolean setUserInfoFromHeader(String userName) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest req = requestAttributes.getRequest();
			req.getSession().setAttribute("userName", userName);
			return true;
		}
		return false;

	}
	
	@Override
	public String getFilePath(String fileName)
	{
		String filePath = this.getServletContext().getRealPath(fileName);
		return filePath;
	}

	@Override
	public long getUserSessionTimeout() {
		ServletRequestAttributes sra = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes());
		System.out.println("it comes here"+sra.toString());
		timeout= sra.getRequest().getSession().getMaxInactiveInterval()*1000;
		System.out.println("it comes here"+timeout);
		return Long.valueOf(timeout);
	}
	
	@Override
	public Boolean isSessionStillAlive() {
		ServletRequestAttributes sra = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes());
		System.out.println("system last accesed at "+sra.getRequest().getSession().getLastAccessedTime());
		Date currentDate = new Date();
		System.out.println("Current Date: "+currentDate.getTime());
		//return new Boolean((currentDate.getTime() - sra.getRequest().getSession().getLastAccessedTime()) < 12000);
		return false;
	}

	@Override
	public List<File> getUploadedDocumentsForClient(Client client) 		
			throws IllegalArgumentException {
		try {
			foundDocuments = userController.getUploadedDocuments(client);
			logger.log(Level.SEVERE, "Inside service ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Inside saerch service by policy/certificate Number "
					+ e.toString());
		}
		return foundDocuments;
	}
}
