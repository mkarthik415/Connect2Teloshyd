package org.jboss.tools.gwt.client;

import org.jboss.tools.gwt.shared.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("quote.rpc")
public interface GreetingService extends RemoteService {
	User[] greetServer(String name,String password) throws IllegalArgumentException;
	void greetLogout() ;
}
