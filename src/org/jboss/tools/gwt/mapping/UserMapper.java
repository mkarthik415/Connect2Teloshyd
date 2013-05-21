package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.jboss.tools.gwt.shared.User;

@SuppressWarnings("rawtypes")
public class UserMapper implements RowMapper{
	
	final private static String TEAM	=	"team";
	
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		//user.setName(rs.getString(USER));
		user.setTeam(rs.getInt(TEAM));
		return user;
	}



}
