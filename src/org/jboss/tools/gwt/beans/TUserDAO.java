package org.jboss.tools.gwt.beans;


import java.util.List;

import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;

public interface TUserDAO {
	public Boolean selectUser(final String user,final String password);
	
	public Boolean createClient(Client client);
	
	public List<Clients> searchClient(Client client);

}
