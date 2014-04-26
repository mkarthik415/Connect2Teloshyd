package org.jboss.tools.gwt.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.tools.gwt.client.GreetingService;
import org.jboss.tools.gwt.shared.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService,ServletContextAware {

    @Autowired
    private UserControllerInterface userController;

    GreetingServiceImpl()
    {
        //this.userController = getUserController();

    }
    List<User> newClients = new ArrayList<User>();
    List<Clients> foundClients = null;
    List<Company> foundCompany= null;
    List<File> foundDocuments = null;
    List<EmailedFile> sentEmails = null;
    List<EmailList> foundEmails = null;
    List<Clients> foundClientsArray = new ArrayList<Clients>();
    Clients insuranceCompanyDetails = null;
    CompanyDetails companydetails = null;
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
            //this.userController = getUserController();
            user = this.userController.getUserResponse(input, pInput);
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
            create = this.userController.getCreateClientResponse(client);
            logger.log(Level.SEVERE, "response After DB and controller ");

        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Inside GreetingServiceImpl " + e.toString());

        }
        return create;
    }

    String create = null;

    @Override
    public String policyRenewal(Client client) {

        try {
            create = this.userController.getrenewlientResponse(client);
            logger.log(Level.SEVERE, "response After DB and controller when renewing a policy");

        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Inside GreetingServiceImpl exceptin when renewing a policy" + e.toString());

        }


        return create;
    }

    // implementation to search created clients in telos database
    @Override
    public List<Clients> searchClients(Client client)
            throws IllegalArgumentException {
        try{
            foundClients = this.userController.getSearchClient(client);
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
            foundClients = this.userController.getSearchClientByCarNum(client);
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
    public List<Clients> searchClientsByPhoneNum(Client client)
            throws IllegalArgumentException {
        try{
            foundClients = this.userController.getSearchClientByPhoneNum(client);
            logger.log(Level.SEVERE,
                    "Inside service ");
        }
        catch(Exception e)
        {
            logger.log(Level.SEVERE,
                    "Inside ClientsByPhoneNum service " + e.toString());
        }
        return foundClients;
    }

    @Override
    public Boolean sendEmail(Client client, List<File> files) throws IllegalArgumentException {
        Boolean sent = false;
        try{
            sent = this.userController.getEmailClient(client,files);

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
            response = this.userController.getSMSClient(client);

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
            update = this.userController.updateClientResponse(client);
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
            update = this.userController.createAgentResponse(agent);
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
            update = this.userController.createInsuranceResponse(insurance);
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
            lAgent = this.userController.getSearchAgent();
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
        try {
            lInsurance = this.userController.getSearchInsuranceCompany();
            logger.log(Level.SEVERE, "response After DB and controller for update "+lInsurance.get(0).getScreenName());

        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Inside GreetingServiceImpl " + e.toString());

        }
        return lInsurance;
    }

    @Override
    public List<Company> loadComapny() {
        try {
            foundCompany = this.userController.getListOfCompanies();
            logger.log(Level.SEVERE, "Inside service ");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside get companies list in DB "
                    + e.toString());
        }
        return foundCompany;
    }

    @Override
    public String getPdfReport(String fileName, Map<String, Object> param) {
        try
        {
            String filePath = this.getServletContext().getRealPath(fileName);
            logger.log(Level.SEVERE,
                    "inside getPdfReport and path is  " +filePath);
            String response = this.userController.getPdfReport(filePath, param);
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
            String response = this.userController.getPdfReportForPendingPolicy(filePath, param);
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
            lOfficeCode = this.userController.getSearchOfficeCode();
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
            String response = this.userController.getExcelReport(filePath, param);
            return response;

        }
        catch (Exception ex)
        {
            logger.log(Level.SEVERE,
                    "inside getPdfReport and path is  " +ex.toString());
        }
        return "resources/Reports/reportForPending.xls";
    }

    @Override
    public String getExcelForPendingPolicy(String fileName, Map<String, Object> param) {

        try
        {
            String filePath = this.getServletContext().getRealPath(fileName);
            logger.log(Level.SEVERE,
                    "inside getPdfReport and path is  " +filePath);
            String response = this.userController.getExcelReportForPendingPolicy(filePath, param);
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
            foundClients = this.userController.getSearchClientByPolicyDates(client);
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
            foundClients = this.userController.getSearchClientBySerialNo(client);
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
            foundClients = this.userController.getSearchClientByPolicyNo(client);
            logger.log(Level.SEVERE, "Inside service ");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside saerch service by policy/certificate Number "
                    + e.toString());
        }
        return foundClients;
    }

    @Override
    public String getPdfReportForClient(String fileName,
                                        Map<String, Object> param) {
        try
        {
            String filePath = this.getServletContext().getRealPath(fileName);
            logger.log(Level.SEVERE,
                    "inside getPdfReport and path is  " +filePath);
            String response = this.userController.getPdfReportForClient(filePath, param);
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
            String response = this.userController.getExcelReportForClient(filePath, param);
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
            foundDocuments = this.userController.getUploadedDocuments(client);
            logger.log(Level.SEVERE, "Inside service ");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside saerch service by policy/certificate Number "
                    + e.toString());
        }
        return foundDocuments;
    }

    @Override
    public List<EmailedFile> getEmails(File file) {
        try {
            sentEmails = this.userController.getEmails(file);
            logger.log(Level.SEVERE, "Inside service ");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside getEmails service to find out email sent for the file "
                    + e.toString());
        }
        return sentEmails;
    }

    @Override
    public List<EmailList> loadEmails() {
        try {
            foundEmails = this.userController.getListOfEmails();
            logger.log(Level.SEVERE, "Inside service ");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside get companies list in DB "
                    + e.toString());
        }
        return foundEmails;
    }

    @Override
    public CompanyDetails loadCompanyDetails(Company company) {
        try {
            companydetails = this.userController.getCompanyDetails(company);
            logger.log(Level.SEVERE, "Inside service when selected from the drop dowm box"+companydetails.getPhoneNumber());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Inside get companies list in DB "
                    + e.toString());
        }
        return companydetails;

    }

    @Override
    public Clients searchInsuranceCompanyDetails(Client client) {
        try{
            insuranceCompanyDetails = this.userController.searchInsuranceDetailsByOfficeCode(client);
        }catch (Exception e) {
            logger.log(Level.SEVERE, "Inside get companiesdetails from office code list in DB "
                    + e.toString());
        }
        return insuranceCompanyDetails;
    }

    @Override
    public boolean deleteDocumentsForClient(Client client, List<File> files) {
        // TODO Auto-generated method stub
        Boolean sent = false;
        try{
            sent = this.userController.deleteClientDocuments(client,files);

        }
        catch(Exception e)
        {
            logger.log(Level.SEVERE,
                    "Inside mail service " + e.toString());
            return sent;
        }
        return sent;
    }


}
