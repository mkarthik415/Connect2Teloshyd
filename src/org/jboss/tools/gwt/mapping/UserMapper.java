package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.jboss.tools.gwt.shared.User;

public class UserMapper implements RowMapper{
	
	final private static String USER	=	"username";
	
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		System.out.println("inside mapper class");
		user.setName(rs.getString(USER));
		return user;
	}



}
