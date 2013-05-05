package org.jboss.tools.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	

	private String Name = null;
	private int team;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

}
