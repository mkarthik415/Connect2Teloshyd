package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.File;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class FileMapper implements RowMapper {
	
	final private static String ID = "id";
	final private static String NAME = "name";
	final private static String DESCRIPTION = "description";

	@Override
	public File mapRow(ResultSet rs, int arg1) throws SQLException {
		File flies = new File(rs.getInt(ID),rs.getString(NAME),rs.getString(DESCRIPTION));
		return flies;
	}

}
