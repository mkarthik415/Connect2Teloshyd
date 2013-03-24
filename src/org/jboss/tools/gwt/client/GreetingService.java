package org.jboss.tools.gwt.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.OfficeCode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("quote.rpc")
public interface GreetingService extends RemoteService {

	// used to login
	Boolean greetServer(String name, String password)
			throws IllegalArgumentException;

	String createClient(Client client);

	// used to log out user
	void greetLogout();

	// find created client in telos
	List<Clients> searchClients(Client client) throws IllegalArgumentException;
	
	// used to dispatch mail to client
	Boolean sendEmail(Client client) throws IllegalArgumentException;
	
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
}
