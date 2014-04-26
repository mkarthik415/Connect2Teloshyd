package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Company;
import org.jboss.tools.gwt.shared.EmailList;
import org.springframework.jdbc.core.RowMapper;

public class EmailIdsMapper implements RowMapper{
	
	final private static String EMAIL_ADDRESS	=	"EMAIL_ADDRESS";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		EmailList emailList = new EmailList();
		emailList.setEmailId(rs.getString(EMAIL_ADDRESS));
		return emailList;
	}

}

