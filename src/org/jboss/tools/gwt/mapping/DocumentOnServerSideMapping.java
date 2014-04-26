package org.jboss.tools.gwt.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.tools.gwt.shared.DocumentOnServerSide;
import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class DocumentOnServerSideMapping implements RowMapper {

	final private static String ID = "id";
	final private static String NAME = "name";
	final private static String DESCRIPTION = "description";
	final private static String SCANNED = "scanned";

	@Override
	public DocumentOnServerSide mapRow(ResultSet rs, int arg1)
			throws SQLException {
		DocumentOnServerSide flies = new DocumentOnServerSide(rs.getInt(ID),
				rs.getString(NAME), rs.getString(DESCRIPTION),
				rs.getBlob(SCANNED));
		return flies;
	}

}
