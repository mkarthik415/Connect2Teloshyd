package org.jboss.tools.gwt.shared;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class OfficeCode extends BaseModel implements IsSerializable {

	private String companyName;
	private String companyBranch;
	private String companyOfficeCode;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyBranch() {
		return companyBranch;
	}
	public void setCompanyBranch(String companyBranch) {
		this.companyBranch = companyBranch;
	}
	public String getCompanyOfficeCode() {
		return companyOfficeCode;
	}
	public void setCompanyOfficeCode(String companyOfficeCode) {
		this.companyOfficeCode = companyOfficeCode;
	}
	
}
