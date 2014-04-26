package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Clients;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EmailClientMapper implements RowMapper {

	final private static String EMAIL ="EMAIL";
	final private static String ID = "id";
	final private static String PHONE_NO ="PHONE_NO";
	final private static String SECONDARY_PHONE_NO ="SECONDARY_PHONE_NO";
	final private static String POLICY_NO ="POLICY_CERTIFICATE_NO";

	public Clients mapRow(ResultSet rs, int arg1) throws SQLException {
		Clients clients = new Clients(rs.getInt(ID), rs.getString(EMAIL),rs.getString(PHONE_NO),rs.getString(SECONDARY_PHONE_NO),rs.getString(POLICY_NO));
		return clients;
	}

}
