package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Insurance;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class InsuranceMapper implements RowMapper{
	
	final private static String USER	=	"screenname";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		Insurance insurance = new Insurance();
		insurance.setScreenName(rs.getString(USER));
		return insurance;
	}

}
