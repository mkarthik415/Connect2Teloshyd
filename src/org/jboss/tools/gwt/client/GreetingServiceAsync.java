package org.jboss.tools.gwt.client;

import java.util.List;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String name, String password,
			AsyncCallback<Boolean> callback);

	void greetLogout(AsyncCallback<Void> callback);

	void createClient(Client client, AsyncCallback<String> callback);
	
	void upgradeClient(Client client, AsyncCallback<String> callback);

	void searchClients(Client client, AsyncCallback<List<Clients>> callback);

	void sendEmail(Client client, AsyncCallback<Boolean> callback);
	
	void sendSms(Client client, AsyncCallback<String> callback);

	void createAgent(Agent agent, AsyncCallback<String> callback);

	void loadAgents(AsyncCallback<List<Agent>> callback);
}
