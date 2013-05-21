package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.OfficeCode;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class OfficeCodeMapper implements RowMapper {
	
	final private String office_Code = "office_code";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		OfficeCode officeCode = new OfficeCode();
		officeCode.setCompanyOfficeCode(rs.getString(office_Code));
		return officeCode;
	}

}
