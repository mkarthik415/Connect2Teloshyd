package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.jboss.tools.gwt.shared.User;

public class ClientMapper implements RowMapper{
	
	
	public Boolean mapRow(ResultSet rs, int arg1) throws SQLException {
		System.out.println("inside mapper class");
		//user.setUserName(rs.getString(USER));
		rs.insertRow();
		return true;
	}



}
