package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class User extends BaseModel implements IsSerializable{
	

	public User()
	{
		
	}
	public User(Integer iD,
			Integer team){
		set("id",iD);
		set("team", team);
	}

	public Integer getId() {
		return (Integer) get("id");
	}

	public Integer getTeam() {
		return (Integer) get("team");
	}

}
