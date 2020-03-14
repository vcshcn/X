package net.xway.process.designer;

import java.sql.Connection;
import java.sql.SQLException;

public interface IElementSerializable extends java.io.Serializable {

	public void load(Connection conn, int id) throws SQLException;
	
	public void save(Connection conn) throws  SQLException;
}
