package org.jboss.tools.gwt.beans;


import java.util.List;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.User;

public interface TUserDAO {
	public User selectUser(final String user,final String password);
	
	public Boolean createClient(Client client);
	
	public List<Clients> searchClient(Client client);

}
