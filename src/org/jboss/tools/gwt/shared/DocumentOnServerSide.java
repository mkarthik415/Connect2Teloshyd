package org.jboss.tools.gwt.shared;

import java.sql.Blob;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class DocumentOnServerSide extends BaseModel implements IsSerializable{

	private static final long serialVersionUID = 1L;

	public DocumentOnServerSide()
	{
		
	}
	
	public DocumentOnServerSide(
			Integer iD,
			String fileName,
			String fileDescption,
			Blob scanned
			){
		set("id",iD);
	    set("name", fileName);
	    set("description", fileDescption);
	    set("scanned", scanned);
		
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
	
	public Blob getScanned() {
		return get("scanned");
	}

}
