package org.jboss.tools.gwt.client;

import org.jboss.tools.gwt.shared.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input,String pInput, AsyncCallback<User[]> callback)
			throws IllegalArgumentException;

	void greetLogout(AsyncCallback<Void> callback);
}
