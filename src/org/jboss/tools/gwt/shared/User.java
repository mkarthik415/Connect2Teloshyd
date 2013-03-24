package org.jboss.tools.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	

	private String Name = null;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
