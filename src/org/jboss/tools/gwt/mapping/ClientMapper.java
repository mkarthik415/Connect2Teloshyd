package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.Clients;
import org.springframework.jdbc.core.RowMapper;

public class ClientMapper implements RowMapper{
	
	final private static String NAME	=	"INSURED_NAME";
	final private static String COMPANY = "POLICY_TYPE";
	final private static String DEPARTMENT = "DEPARTMENT";
	final private static String VEHICLENO ="VEHICLE_NO";
	
	public Clients mapRow(ResultSet rs, int arg1) throws SQLException {
		System.out.println("inside mapper class");
		Clients clients = new Clients(rs.getString(NAME),rs.getString(COMPANY), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, rs.getString(VEHICLENO), null, null, null, null, rs.getString(DEPARTMENT), null);
		//user.setUserName(rs.getString(USER));
		return clients;
	}



}
