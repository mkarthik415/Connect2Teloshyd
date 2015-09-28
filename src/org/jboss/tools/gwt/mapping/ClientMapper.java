package org.jboss.tools.gwt.mapping;

import org.jboss.tools.gwt.shared.Clients;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("rawtypes")
public class ClientMapper implements RowMapper {

	final private static String NAME = "INSURED_NAME";
	final private static String COMPANY = "COMPANY";
	final private static String DEPARTMENT = "DEPARTMENT";
	final private static String PHONE_NO	= "PHONE_NO"; 
	final private static String SECONDARY_PHONE_NO	= "SECONDARY_PHONE_NO";
	final private static String DOB = "DOB";
	final private static String GENDER = "GENDER";
	final private static String INDUSTRY = "INDUSTRY";
	final private static String INSURED_ADDRESS ="INSURED_ADDRESS";
	final private static String EMAIL ="EMAIL";
	final private static String SECONDARY_EMAIL ="SECONDARY_EMAIL";
	final private static String POLICY_CERTIFICATE_NO ="POLICY_CERTIFICATE_NO"; 
	final private static String ENDORS_NO ="ENDORS_NO";  
	final private static String INSURANCE_FROM ="INSURANCE_FROM";  
	final private static String INSURANCE_TO ="INSURANCE_TO";  
	final private static String COMPANY_NAME ="COMPANY_NAME";  
	final private static String BRANCH_NAME ="BRANCH_NAME";  
	final private static String OFFICE_CODE ="OFFICE_CODE";  
	final private static String SOURCE ="SOURCE";
	final private static String POLICY_DETAILS ="POLICY_DETAILS";
	final private static String AGENTNAME ="AGENTNAME";
	final private static String VEHICLENO = "VEHICLE_NO";
	final private static String IDV = "IDV";
	final private static String VEHICLE_MAKE = "VEHICLE_MAKE"; 
	final private static String YEAR_OF_MANUFACTURING = "YEAR_OF_MANUFACTURING";
	final private static String NCB_PERCENTAGE = "NCB_PERCENTAGE";
	final private static String POLICY_TYPE = "POLICY_TYPE";  
	final private static String SPECIFIC_POLICY = "SPECIFIC_POLICY"; 
	final private static String OPEN_POLICY = "OPEN_POLICY"; 
	final private static String OPEN_COVER = "OPEN_COVER"; 
	final private static String OTHERS = "OTHERS"; 
	final private static String VOYAGE_FROM = "VOYAGE_FROM"; 
	final private static String VOYAGE_TO = "VOYAGE_TO"; 
	final private static String BASIC_RATE = "BASIC_RATE"; 
	final private static String EARTHQUAKE_PREMIUM = "EARTHQUAKE_PREMIUM"; 
	final private static String ADDITIONAL_PERILS_PREMIUM = "ADDITIONAL_PERILS_PREMIUM";
	final private static String SUM_INSURED = "SUM_INSURED";
	final private static String COLLECTION_DATE = "COLLECTION_DATE";
	final private static String PREMIUM_AMOUNT = "PREMIUM_AMOUNT";
	final private static String TERRORISM_PREMIUM = "TERRORISM_PREMIUM";
	final private static String SERVICE_TAX = "SERVICE_TAX";
	final private static String TOTAL_PREMIUM = "TOTAL_PREMIUM";
	final private static String COMMISSION_RATE = "COMMISSION_RATE";
	final private static String COMMISSION_AMOUNT = "COMMISSION_AMOUNT";
	final private static String SERVICE_TAX_PERCENTAGE ="SERVICE_TAX_PERCENTAGE";
	final private static String ID_CARD ="ID_CARD";
	final private static String DESPATCH_DATE ="DESPATCH_DATE";
	final private static String ID = "id";
    final private static String RENEWAL_AMOUNT ="RENEWAL_AMOUNT";
    final private static String RENEWAL_COMPANY = "RENEWAL_COMPANY";

	public Clients mapRow(ResultSet rs, int arg1) throws SQLException {
		Clients clients = new Clients(rs.getInt(ID),rs.getString(NAME),
				rs.getString(COMPANY), rs.getString(PHONE_NO), rs.getString(SECONDARY_PHONE_NO), rs.getString(EMAIL), rs.getString(SECONDARY_EMAIL), rs.getString(GENDER), rs.getString(INDUSTRY), rs.getString(INSURED_ADDRESS), rs.getDate(DOB),
				rs.getString(POLICY_CERTIFICATE_NO), rs.getString(ENDORS_NO), rs.getDate(INSURANCE_FROM), rs.getDate(INSURANCE_TO), rs.getString(COMPANY_NAME), rs.getString(BRANCH_NAME), rs.getString(OFFICE_CODE), rs.getString(SOURCE), rs.getString(POLICY_DETAILS), rs.getString(AGENTNAME),
				null, rs.getDate(COLLECTION_DATE), rs.getString(POLICY_TYPE), rs.getDouble(BASIC_RATE), rs.getDouble(EARTHQUAKE_PREMIUM), rs.getDouble(ADDITIONAL_PERILS_PREMIUM), rs.getString(SPECIFIC_POLICY), rs.getString(OPEN_POLICY), rs.getString(OPEN_COVER), rs.getString(OTHERS),
				rs.getString(VOYAGE_FROM), rs.getString(VOYAGE_TO), rs.getDouble(PREMIUM_AMOUNT), rs.getDouble(TERRORISM_PREMIUM), rs.getDouble(SERVICE_TAX_PERCENTAGE), rs.getDouble(SERVICE_TAX), rs.getDouble(TOTAL_PREMIUM), rs.getDouble(COMMISSION_RATE), rs.getDouble(COMMISSION_AMOUNT), rs.getDouble(SUM_INSURED),
				rs.getString(VEHICLENO), rs.getString(IDV), rs.getString(VEHICLE_MAKE), rs.getDate(YEAR_OF_MANUFACTURING), rs.getString(NCB_PERCENTAGE),
				rs.getString(DEPARTMENT), rs.getString(POLICY_TYPE),rs.getString(ID_CARD),rs.getDate(DESPATCH_DATE),rs.getDouble(RENEWAL_AMOUNT),rs.getString(RENEWAL_COMPANY),null);
		return clients;
	}

}
