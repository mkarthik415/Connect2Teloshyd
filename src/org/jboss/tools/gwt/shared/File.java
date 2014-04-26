package org.jboss.tools.gwt.shared;

import java.util.Date;
import java.util.List;

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
			String fileDescption,
			Integer sent,
			Date updatedOn,
			String uploadedBy
			){
		set("id",iD);
	    set("name", fileName);
	    set("description", fileDescption);
	    set("sent", sent);
	    set("updated_on", updatedOn);
	    set("uploaded_by",uploadedBy);
		
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
	
	public Integer getSize() {
		return (Integer) get("sent");
	}
	
	public Date getUpdatedOn() {
		return (Date) get("updated_on");
	}
	
	public List<EmailedFile> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailedFile> emails) {
		this.emails = emails;
	}
	
	public String getUploadedBy() {
		return (String) get("uploaded_by");
	}

	private List<EmailedFile> emails;

}
