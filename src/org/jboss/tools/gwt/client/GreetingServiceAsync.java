package org.jboss.tools.gwt.client;

import java.util.List;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input,String pInput, AsyncCallback<User[]> callback)
			throws IllegalArgumentException;

	void greetLogout(AsyncCallback<Void> callback);

	void createClient(Client client, AsyncCallback<Boolean> callback);

	void searchClients(Client client, AsyncCallback<List<Clients>> callback);
}
