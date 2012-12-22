package org.jboss.tools.gwt.shared;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Clients extends BaseModel implements IsSerializable{


	public Clients()
	{
		
	}
	public Clients(
	String clientName,
	String company,
	String phoneNumber,
	String email,
	String gender,
	String industry,
	String address,
	Date dob,
	String policyNumber,
	String endrsNumber,
	Date policyStartdate,
	Date policyEndDate,
	String InsCompanyName,
	String InsBranchName,
	String officeCode,
	String source,
	String policyDetails,
	String agent,
	String policyType,
	Date collectionDate,
	String fireTypeOfPolicy,
	Double basicRate,
	Double earthQuakePremium,
	Double anyAdditionalPremium,
	String marineTypeOfPolicy,
	String marineOpenPolicy,
	String marineOpenCover,
	String marineOtherPolicies,
	String marineVoyageFrom,
	String marineVoyageTo,
	Double premiumAmount,
	Double terrorismPremiumAmount,
	Double serviceTax,
	Double serviceTaxAmount,
	Double totalPremiumAmount,
	Double commionRate,
	Double commionRateAmount,
	Double sumInsured,
	String vehicleNumber,
	String iDV,
	String vehicleMake,
	Date vehicleManufactureYear,
	String nBC,
	String department,
	String miscTypeOfPolicy
	 ){
		
		
	    set("name", clientName);
	    set("company", company);
	    set("phoneNumber", phoneNumber);
	    set("email", email);
	    set("gender", gender);
	    set("industry", industry);
	    set("address", address);
	    set("dob", dob);
	    set("policyNumber", policyNumber);
	    set("endrsNumber", endrsNumber);
	    set("policyStartdate", policyStartdate);
	    set("policyEndDate", policyEndDate);
	    set("InsCompanyName", InsCompanyName);
	    set("InsBranchName", InsBranchName);
	    set("officeCode",officeCode);
	    set("source", source);
	    set("policyDetails", policyDetails);
	    set("agent", agent);
	    set("policyType", policyType);
	    set("collectionDate", collectionDate);
	    set("fireTypeOfPolicy", fireTypeOfPolicy);
	    set("basicRate", basicRate);
	    set("earthQuakePremium", earthQuakePremium);
	    set("anyAdditionalPremium", anyAdditionalPremium);
	    set("marineTypeOfPolicy", marineTypeOfPolicy);
	    set("marineOpenPolicy", marineOpenPolicy);
	    set("marineOpenCover", marineOpenCover);
	    set("marineOtherPolicies", marineOtherPolicies);
	    set("marineVoyageFrom", marineVoyageFrom);
	    set("marineVoyageTo", marineVoyageTo);
	    set("premiumAmount", premiumAmount);
	    set("terrorismPremiumAmount", terrorismPremiumAmount);
	    set("serviceTax", serviceTax);
	    set("serviceTaxAmount", serviceTaxAmount);
	    set("totalPremiumAmount", totalPremiumAmount);
	    set("commionRate", commionRate);
	    set("commionRateAmount", commionRateAmount);
	    set("sumInsured", sumInsured);
	    set("vehicleNumber", vehicleNumber);
	    set("iDV", iDV);
	    set("vehicleMake", vehicleMake);
	    set("vehicleManufactureYear", vehicleManufactureYear);
	    set("nBC", nBC);
	    set("department", department);
	    set("miscTypeOfPolicy", miscTypeOfPolicy);
	    
	}
	
	public String getName() {
		return (String) get("name");
	}

	public String getCompany() {
		return (String) get("company");
	}

	public String getPhoneNumber(){
		return (String) get("phoneNumber");
		
	}
	
	public String getEmail(){
		return (String) get("email");
		
	}
	
	public String getGender(){
		return (String) get("gender");
		
	}
	
	public String getIndustry(){
		return (String) get("industry");
		
	}
	
	public String getAddress(){
		return (String) get("address");
		
	}
	public Date getDob(){
		return (Date) get("dob");
		
	}
	
	public String getPolicyNumber(){
		return (String) get("policyNumber");
		
	}
	public String getEndrsNumber(){
		return (String) get("endrsNumber");
		
	}
	
	public Date getPolicyStartdate(){
		return (Date) get("policyStartdate");
		
	}
	public Date getPolicyEndDate(){
		return (Date) get("policyEndDate");
		
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
	
	public String getSource(){
		return (String) get("source");
		
	}
	
	public String getPolicyDetails() {
		return (String) get("policyDetails");
	}

	public String getAgent() {
		return (String) get("agent");
	}

	public String getPolicyType(){
		return (String) get("policyType");
		
	}
	
	public Date getCollectionDate(){
		return (Date) get("collectionDate");
		
	}
	
	public String getFireTypeOfPolicy(){
		return (String) get("fireTypeOfPolicy");
		
	}
	
	public Double getBasicRate(){
		return (Double) get("basicRate");
		
	}
	
	public Double getEarthQuakePremium(){
		return (Double) get("earthQuakePremium");
		
	}
	public Double getAnyAdditionalPremium(){
		return (Double) get("anyAdditionalPremium");
		
	}
	
	public String getMarineTypeOfPolicy(){
		return (String) get("marineTypeOfPolicy");
		
	}
	public String getMarineOpenPolicy(){
		return (String) get("marineOpenPolicy");
		
	}
	
	public String getMarineOpenCover(){
		return (String) get("marineOpenCover");
		
	}
	public String getMarineOtherPolicies(){
		return (String) get("marineOtherPolicies");
		
	}
	
	public String getMarineVoyageTo(){
		return (String) get("marineVoyageTo");
		
	}
	
	public String getMarineVoyageFrom(){
		return (String) get("marineVoyageFrom");
		
	}
	
	public Double getPremiumAmount() {
		return (Double) get("premiumAmount");
	}

	public Double getTerrorismPremiumAmount() {
		return (Double) get("terrorismPremiumAmount");
	}

	public Double getServiceTax(){
		return (Double) get("serviceTax");
		
	}
	
	public Double getServiceTaxAmount(){
		return (Double) get("serviceTaxAmount");
		
	}
	
	public Double getTotalPremiumAmount(){
		return (Double) get("totalPremiumAmount");
		
	}
	
	public Double getCommionRate(){
		return (Double) get("commionRate");
		
	}
	
	public Double getCommionRateAmount(){
		return (Double) get("commionRateAmount");
		
	}
	public Double getSumInsured(){
		return (Double) get("sumInsured");
		
	}
	
	public String getVehicleNumber(){
		return (String) get("vehicleNumber");
		
	}
	public String getIDV(){
		return (String) get("iDV");
		
	}
	
	public String getVehicleMake(){
		return (String) get("vehicleMake");
		
	}
	public Date getVehicleManufactureYear(){
		return (Date) get("vehicleManufactureYear");
		
	}
	
	public String getNBC(){
		return (String) get("nBC");
		
	}
	public String getDepartment(){
		return (String) get("department");
		
	}
	
	public String getMiscTypeOfPolicy(){
		return (String) get("miscTypeOfPolicy");
		
	}

}
