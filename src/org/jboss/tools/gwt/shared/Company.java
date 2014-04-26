package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class Company extends BaseModel implements IsSerializable{
	
	private String companyName;
	
	public String getCompnyName() {
		return companyName;
	}
	public void setCompanyName(String name) {
		this.companyName = name;
	}
	

}
