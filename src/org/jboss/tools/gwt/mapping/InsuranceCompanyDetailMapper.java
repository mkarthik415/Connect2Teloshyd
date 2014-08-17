package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Clients;
import org.jboss.tools.gwt.shared.InsuranceCompanyDetails;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class InsuranceCompanyDetailMapper implements RowMapper {

	 
	final private static String COMPANY_NAME ="COMPANY_NAME";  
	final private static String BRANCH_NAME ="BRANCH_NAME";  
	final private static String OFFICE_CODE ="OFFICE_CODE";  

	public Clients mapRow(ResultSet rs, int arg1) throws SQLException {
		Clients clients = new Clients( rs.getString(OFFICE_CODE), rs.getString(COMPANY_NAME), rs.getString(BRANCH_NAME) );
		return clients;
	}

}