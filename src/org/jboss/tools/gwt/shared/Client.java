package org.jboss.tools.gwt.shared;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Client implements IsSerializable{
	private String clientName;
	private String company;
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}
