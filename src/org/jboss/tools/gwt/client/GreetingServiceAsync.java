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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String name, String password,
			AsyncCallback<Integer> callback);

	void greetLogout(AsyncCallback<Void> callback);

	void createClient(Client client, AsyncCallback<String> callback);
	
	void upgradeClient(Client client, AsyncCallback<String> callback);

	void searchClients(Client client, AsyncCallback<List<Clients>> callback);
	
	void searchClientsByCarNum(Client client, AsyncCallback<List<Clients>> callback);
	
	void searchClientsByPhoneNum(Client client, AsyncCallback<List<Clients>> callback);
	
	void searchClientsByPolicyDates(Client client, AsyncCallback<List<Clients>> callback);
	
	void searchClientsBySrialNo(Client client, AsyncCallback<List<Clients>> callback);
	
	void getUploadedDocumentsForClient(Client client, AsyncCallback<List<File>> callback);
	
	void deleteDocumentsForClient(Client client, List<File> files, AsyncCallback<Boolean> callback);
	
	void searchClientsByPolicyNo(Client client, AsyncCallback<List<Clients>> callback);

	void sendEmail(Client client, List<File> files, AsyncCallback<Boolean> callback);
	
	void sendSms(Client client, AsyncCallback<String> callback);

	void createAgent(Agent agent, AsyncCallback<String> callback);

	void loadAgents(AsyncCallback<List<Agent>> callback);
	
	void loadInsurance(AsyncCallback<List<Insurance>> callback);
	
	void loadComapny(AsyncCallback<List<Company>> callback);
	
	void loadEmails(AsyncCallback<List<EmailList>> callback);
	
	void loadOfficeCode(AsyncCallback<List<OfficeCode>> callback);
	
	void getPdfReport(String fileName, Map<String, Object> param,
			AsyncCallback<String> asyncCallback);

	void getExcel(String fileName, Map<String, Object> param,
			AsyncCallback<String> callback);

	void createInsuranceCompony(Insurance insurance,
			AsyncCallback<String> callback);

	void getPdfReportForClient(String fileName, Map<String, Object> param,
			AsyncCallback<String> asyncCallback);
	
	void getExcelForClient(String fileName, Map<String, Object> param,
			AsyncCallback<String> callback);

	void getPdfReportForPendingPolicy(String fileName,
			Map<String, Object> param, AsyncCallback<String> callback);

	void getExcelForPendingPolicy(String fileName, Map<String, Object> param,
			AsyncCallback<String> callback);

	void getUserSessionTimeout(AsyncCallback<Long> asyncCallback);

	void isSessionStillAlive(AsyncCallback<Boolean> callback);

	void getFilePath(String fileName, AsyncCallback<String> callback);
	
	void getEmails(File file, AsyncCallback<List<EmailedFile>> callback);

	void loadCompanyDetails(Company company,
			AsyncCallback<CompanyDetails> callback);
	
	void searchInsuranceCompanyDetails(Client client, AsyncCallback<Clients> callback);

}
