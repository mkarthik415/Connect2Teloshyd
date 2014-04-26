package org.jboss.tools.gwt.shared;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class EmailedFile extends BaseModel implements IsSerializable{

	public EmailedFile()
	{
		
	}
	
	public EmailedFile(
			String user,
			String emailAddress,
			Date updatedOn
			){
		set("user",user);
	    set("address", emailAddress);
	    set("updated_on", updatedOn);
	}
	
	public String getUser() {
		return (String) get("user");
	}
	
	public String getEmailAddress() {
		return (String) get("address");
	}
	
	public Date getUpdatedOn() {
		return (Date) get("updated_on");
	}
}
