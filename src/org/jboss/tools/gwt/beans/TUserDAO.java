package org.jboss.tools.gwt.beans;


import java.util.List;

import org.jboss.tools.gwt.shared.Agent;
import org.jboss.tools.gwt.shared.Client;
import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.OfficeCode;

public interface TUserDAO {
	public Boolean selectUser(final String user,final String password);
	
	public String createClient(Client client);
	
	public String updateClient(Client client);
	
	public List<Clients> searchClient(Client client);
	
	public String createAgent(Agent agent);
	
	public List<Agent> searchAgent();
	
	public List<OfficeCode> searchOfficeCode();

}
