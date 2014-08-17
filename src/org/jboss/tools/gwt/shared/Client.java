package org.jboss.tools.gwt.shared;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Client extends BaseModel implements IsSerializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String clientName;
    private String company;
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private String email;
    private String secondaryEmail;
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
    private String policyDetails;
    private String agent;
    private String policyType;
    private Date collectionDate;
    private String fireTypeOfPolicy;
    private Double basicRate;
    private Double earthQuakePremium;
    private Double anyAdditionalPremium;
    private String marineTypeOfPolicy;
    private String marineOpenPolicy;
    private String marineOpenCover;
    private String marineOtherPolicies;
    private String marineVoyageFrom;
    private String marineVoyageTo;
    private Double premiumAmount;
    private Double terrorismPremiumAmount;
    private Double serviceTax;
    private Double serviceTaxAmount;
    private Double totalPremiumAmount;

    private Double commionRate;

    private Double commionRateAmount;
    private Double sumInsured;
    private String vehicleNumber;
    private String iDV;
    private String vehicleMake;
    private Date vehicleManufactureYear;
    private String nBC;
    private String department;
    private String miscTypeOfPolicy;
    private String note;
    private String smsLane;
    private Integer id;

    private Date fromDate;

    private Date toDate;
    private String miscIdCard;
    private Date miscDispatchDate;
    private Integer expiredId;
    private Double renewalAmount;
    private String renewalCompany;
    public Date getMiscDispatchDate() {
        return miscDispatchDate;
    }
    public void setMiscDispatchDate(Date dispatchDate) {
        this.miscDispatchDate = dispatchDate;
    }

    public String getMiscIdCard() {
        return miscIdCard;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public Integer getExpiredId() {
        return expiredId;
    }

    public void setExpiredId(String expiredId) {
        this.expiredId = Integer.parseInt(expiredId);
    }

    public String getSmsLane() {
        return smsLane;
    }

    public void setSmsLane(String smsLane) {
        this.smsLane = smsLane;
    }

    public String getMiscTypeOfPolicy() {
        return miscTypeOfPolicy;
    }

    public void setMiscTypeOfPolicy(String miscTypeOfPolicy) {
        this.miscTypeOfPolicy = miscTypeOfPolicy;
    }

    public void setMiscIdCard(String misIdCard) {
        this.miscIdCard = misIdCard;
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

    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double number) {
        this.basicRate = number;
    }

    public Double getEarthQuakePremium() {
        return earthQuakePremium;
    }

    public void setEarthQuakePremium(Double number) {
        this.earthQuakePremium = number;
    }

    public Double getAnyAdditionalPremium() {
        return anyAdditionalPremium;
    }

    public void setAnyAdditionalPremium(Double number) {
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

    public Double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(Double number) {
        this.premiumAmount = number;
    }

    public Double getTerrorismPremiumAmount() {
        return terrorismPremiumAmount;
    }

    public void setTerrorismPremiumAmount(Double number) {
        this.terrorismPremiumAmount = number;
    }

    public Double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(Double number) {
        this.serviceTax = number;
    }

    public Double getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(Double number) {
        this.serviceTaxAmount = number;
    }

    public Double getTotalPremiumAmount() {
        return totalPremiumAmount;
    }

    public void setTotalPremiumAmount(Double number) {
        this.totalPremiumAmount = number;
    }

    public Double getCommionRate() {
        return commionRate;
    }

    public void setCommionRate(Double number) {
        this.commionRate = number;
    }

    public Double getCommionRateAmount() {
        return commionRateAmount;
    }

    public void setCommionRateAmount(Double number) {
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

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
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

    public String getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(String policyDetails) {
        this.policyDetails = policyDetails;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getNote() {
        return note;
    }

    public Double getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(Double renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public String getRenewalCompany() {
        return renewalCompany;
    }

    public void setRenewalCompany(String renewalCompany) {
        this.renewalCompany = renewalCompany;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
