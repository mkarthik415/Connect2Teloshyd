package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.EmailedFile;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EmailedFileMapper implements RowMapper {

	final private static String USER = "user";
	final private static String ADDRESS = "address";
	final private static String UPDATEDON = "updated_on";

	@Override
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {

		EmailedFile emailedfile = new EmailedFile(rs.getString(USER),
				rs.getString(ADDRESS), rs.getDate(UPDATEDON));
		return emailedfile;
	}

}
