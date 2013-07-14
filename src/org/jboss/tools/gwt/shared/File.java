package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class File extends BaseModel implements IsSerializable{
	
	public File()
	{
		
	}
	
	public File(
			Integer iD,
			String fileName,
			String fileDescption
			){
		set("id",iD);
	    set("name", fileName);
	    set("description", fileDescption);
		
	}
	
	public Integer getId() {
		return (Integer) get("id");
	}
	
	public String getName() {
		return (String) get("name");
	}
	
	public String getDescription() {
		return (String) get("description");
	}

}
