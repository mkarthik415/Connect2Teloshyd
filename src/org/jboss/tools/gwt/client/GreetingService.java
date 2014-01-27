package org.jboss.tools.gwt.client;

import java.util.List;
import java.util.Map;


import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.CompanyDetails;
import org.jboss.tools.gwt.shared.EmailList;
import org.jboss.tools.gwt.shared.EmailedFile;
import org.jboss.tools.gwt.shared.File;
import org.jboss.tools.gwt.shared.Insurance;
import org.jboss.tools.gwt.shared.OfficeCode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("quote.rpc")
public interface GreetingService extends RemoteService {
	
	long getUserSessionTimeout();

	// used to login
	Integer greetServer(String name, String password)
			throws IllegalArgumentException;

	String createClient(Client client);

	// used to log out user
	void greetLogout();

	// find created client in telos
	List<Clients> searchClients(Client client) throws IllegalArgumentException;
	
	Boolean sendEmail(Client client, List<File> files);
	
	// used to dispatch sms to client
	String sendSms(Client client) throws IllegalArgumentException;

	// used to upgrade client policy
	String upgradeClient(Client client);
	
	String createAgent(Agent agent);
	
	List<Agent> loadAgents();

	String getPdfReport(String fileName, Map<String, Object> param);

	List<OfficeCode> loadOfficeCode();
	
	String getExcel(String fileName, Map<String, Object> param);

	List<Clients> searchClientsByCarNum(Client client);

	List<Clients> searchClientsByPolicyDates(Client client);

	List<Clients> searchClientsBySrialNo(Client client);

	List<Clients> searchClientsByPolicyNo(Client client);

	String createInsuranceCompony(Insurance insurance);

	List<Insurance> loadInsurance();

	List<Company> loadComapny();

	String getPdfReportForClient(String fileName, Map<String, Object> param);

	String getExcelForClient(String fileName, Map<String, Object> param);

	String getPdfReportForPendingPolicy(String fileName,
			Map<String, Object> param);

	String getExcelForPendingPolicy(String fileName, Map<String, Object> param);

	Boolean isSessionStillAlive();

	String getFilePath(String fileName);

	List<File> getUploadedDocumentsForClient(Client client);

	List<EmailedFile> getEmails(File file);

	List<Clients> searchClientsByPhoneNum(Client client);

	List<EmailList> loadEmails();

	CompanyDetails loadCompanyDetails(Company company);
}
