package org.jboss.tools.gwt.client;

import java.util.List;

import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.User;
import org.jboss.tools.gwt.shared.Client;

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

	// used to create new client
	Boolean createClient(Client client) throws IllegalArgumentException;

	// used to log out user
	void greetLogout();

	// find created client in telos
	List<Clients> searchClients(Client client) throws IllegalArgumentException;
	
	// used to dispatch mail to client
	Boolean sendEmail(Client client) throws IllegalArgumentException;
	
	// used to dispatch sms to client
	String sendSms(Client client) throws IllegalArgumentException;
}
