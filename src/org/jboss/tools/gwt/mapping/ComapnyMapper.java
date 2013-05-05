package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Company;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class ComapnyMapper implements RowMapper{
	
	final private static String COMPANY_NAME	=	"COMPANY";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Company company = new Company();
		company.setCompanyName(rs.getString(COMPANY_NAME));
		return company;
	}

}
