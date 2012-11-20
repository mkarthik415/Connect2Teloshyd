package org.jboss.tools.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	
	public User() {
		super();
	}

	private String Name;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
