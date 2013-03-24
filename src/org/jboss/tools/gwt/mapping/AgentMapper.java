package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Agent;
import org.springframework.jdbc.core.RowMapper;

public class AgentMapper implements RowMapper{
	
	final private static String USER	=	"screenname";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Agent agent = new Agent();
		agent.setScreenName(rs.getString(USER));
		return agent;
	}

}
