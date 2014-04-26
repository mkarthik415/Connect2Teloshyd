package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.CompanyDetails;
import org.jboss.tools.gwt.shared.EmailedFile;
import org.springframework.jdbc.core.RowMapper;

public class CompanyDetailsMapper implements RowMapper {

	final private static String COMPANY = "company";
	final private static String PHONE_NO = "phone_no";
	final private static String SECONDARY_PHONE_NO = "secondary_phone_no";
	final private static String EMAIL = "email";
	final private static String SECONDARY_EMAIL = "secondary_email";
	final private static String INSURED_ADDRESS = "insured_address";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyName(rs.getString(COMPANY));
		companyDetails.setPhoneNumber(rs.getString(PHONE_NO));
		companyDetails.setSecondaryPhoneNumber(rs.getString(SECONDARY_PHONE_NO));
		companyDetails.setEmail(rs.getString(EMAIL));
		companyDetails.setSecondaryEmail(rs.getString(SECONDARY_EMAIL));
		companyDetails.setAddress(rs.getString(INSURED_ADDRESS));
		return companyDetails;
	}

}
