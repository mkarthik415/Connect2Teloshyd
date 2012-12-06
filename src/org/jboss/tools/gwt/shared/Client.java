package org.jboss.tools.gwt.shared;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Client  extends BaseModel implements IsSerializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientName;
	private String company;
	private String phoneNumber;
	private String email;
	private String gender;
	private String industry;
	private String address;
	private Date dob;
	private String policyNumber;
	private String endrsNumber;
	private Date policyStartdate;
	private Date policyEndDate;
	private String InsCompanyName;
	private String InsBranchName;
	private String officeCode;
	private String source;
	private Date collectionDate;
	private String fireTypeOfPolicy;
	private Number basicRate;
	private Number earthQuakePremium;
	private Number anyAdditionalPremium;
	private String marineTypeOfPolicy;
	private String marineOpenPolicy;
	private String marineOpenCover;
	private String marineOtherPolicies;
	private String marineVoyageFrom;
	private String marineVoyageTo;
	private Number premiumAmount;
	private Number terrorismPremiumAmount;
	private Number serviceTax;
	private Number serviceTaxAmount;
	private Number totalPremiumAmount;
	private Number commionRate;
	private Number commionRateAmount;
	private Double sumInsured;
	private String vehicleNumber;
	private String iDV;
	private String vehicleMake;
	private Date vehicleManufactureYear;
	private String nBC;
	private String department;
	private String miscTypeOfPolicy;
	
	
	public String getMiscTypeOfPolicy() {
		return miscTypeOfPolicy;
	}
	public void setMiscTypeOfPolicy(String miscTypeOfPolicy) {
		this.miscTypeOfPolicy = miscTypeOfPolicy;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getiDV() {
		return iDV;
	}
	public void setiDV(String iDV) {
		this.iDV = iDV;
	}
	public String getVehicleMake() {
		return vehicleMake;
	}
	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}
	public Date getVehicleManufactureYear() {
		return vehicleManufactureYear;
	}
	public void setVehicleManufactureYear(Date vehicleManufactureYear) {
		this.vehicleManufactureYear = vehicleManufactureYear;
	}
	public String getnBC() {
		return nBC;
	}
	public void setnBC(String nBC) {
		this.nBC = nBC;
	}
	public Double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getEndrsNumber() {
		return endrsNumber;
	}
	public void setEndrsNumber(String endrsNumber) {
		this.endrsNumber = endrsNumber;
	}
	public Date getPolicyStartdate() {
		return policyStartdate;
	}
	public void setPolicyStartdate(Date policyStartdate) {
		this.policyStartdate = policyStartdate;
	}
	public Date getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getInsCompanyName() {
		return InsCompanyName;
	}
	public void setInsCompanyName(String insCompanyName) {
		InsCompanyName = insCompanyName;
	}
	public String getInsBranchName() {
		return InsBranchName;
	}
	public void setInsBranchName(String insBranchName) {
		InsBranchName = insBranchName;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	public String getFireTypeOfPolicy() {
		return fireTypeOfPolicy;
	}
	public void setFireTypeOfPolicy(String fireTypeOfPolicy) {
		this.fireTypeOfPolicy = fireTypeOfPolicy;
	}
	public Number getBasicRate() {
		return basicRate;
	}
	public void setBasicRate(Number number) {
		this.basicRate = number;
	}
	public Number getEarthQuakePremium() {
		return earthQuakePremium;
	}
	public void setEarthQuakePremium(Number number) {
		this.earthQuakePremium = number;
	}
	public Number getAnyAdditionalPremium() {
		return anyAdditionalPremium;
	}
	public void setAnyAdditionalPremium(Number number) {
		this.anyAdditionalPremium = number;
	}
	public String getMarineTypeOfPolicy() {
		return marineTypeOfPolicy;
	}
	public void setMarineTypeOfPolicy(String marineTypeOfPolicy) {
		this.marineTypeOfPolicy = marineTypeOfPolicy;
	}
	public String getMarineOpenPolicy() {
		return marineOpenPolicy;
	}
	public void setMarineOpenPolicy(String marineOpenPolicy) {
		this.marineOpenPolicy = marineOpenPolicy;
	}
	public String getMarineOpenCover() {
		return marineOpenCover;
	}
	public void setMarineOpenCover(String marineOpenCover) {
		this.marineOpenCover = marineOpenCover;
	}
	public String getMarineOtherPolicies() {
		return marineOtherPolicies;
	}
	public void setMarineOtherPolicies(String marineOtherPolicies) {
		this.marineOtherPolicies = marineOtherPolicies;
	}
	public String getMarineVoyageFrom() {
		return marineVoyageFrom;
	}
	public void setMarineVoyageFrom(String marineVoyageFrom) {
		this.marineVoyageFrom = marineVoyageFrom;
	}
	public String getMarineVoyageTo() {
		return marineVoyageTo;
	}
	public void setMarineVoyageTo(String marineVoyageTo) {
		this.marineVoyageTo = marineVoyageTo;
	}
	public Number getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(Number number) {
		this.premiumAmount = number;
	}
	public Number getTerrorismPremiumAmount() {
		return terrorismPremiumAmount;
	}
	public void setTerrorismPremiumAmount(Number number) {
		this.terrorismPremiumAmount = number;
	}
	public Number getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Number number) {
		this.serviceTax = number;
	}
	public Number getServiceTaxAmount() {
		return serviceTaxAmount;
	}
	public void setServiceTaxAmount(Number number) {
		this.serviceTaxAmount = number;
	}
	public Number getTotalPremiumAmount() {
		return totalPremiumAmount;
	}
	public void setTotalPremiumAmount(Number number) {
		this.totalPremiumAmount = number;
	}
	public Number getCommionRate() {
		return commionRate;
	}
	public void setCommionRate(Number number) {
		this.commionRate = number;
	}
	public Number getCommionRateAmount() {
		return commionRateAmount;
	}
	public void setCommionRateAmount(Number number) {
		this.commionRateAmount = number;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}
