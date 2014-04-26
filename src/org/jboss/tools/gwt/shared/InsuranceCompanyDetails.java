package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class InsuranceCompanyDetails extends BaseModel implements IsSerializable{
	
	public InsuranceCompanyDetails(String officeCode,
			String insCompanyName,
			String insBranchName
			)
	{
		set("officeCode",officeCode);
		set("insCompanyName", insCompanyName);
		set("insBranchName", insBranchName);
	}
	
	public String getInsCompanyName(){
		return (String) get("InsCompanyName");
		
	}
	
	public String getInsBranchName(){
		return (String) get("InsBranchName");
		
	}
	
	public String getOfficeCode(){
		return (String) get("officeCode");
		
	}

}
