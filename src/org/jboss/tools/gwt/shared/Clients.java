package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Clients extends BaseModel implements IsSerializable{


	public Clients()
	{
		
	}
	public Clients(String name,String company){
	    set("name", name);
	    set("company", company);
	}
	
	  public String getName() {
		    return (String) get("name");
		  }

		  public String getCompany() {
		    return (String) get("company");
		  }
}
