package net.xway.base.database.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * java.util.Date convert to long store to database
 * read java.util.Date from database long
 * @author cc
 *
 */
public class DateTypeHandler implements TypeHandler<Date> {
	
	@Override
	public void setParameter(PreparedStatement ps, int index, Date parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setNull(index, java.sql.Types.INTEGER);
		}
		else {
			ps.setLong(index, parameter.getTime());
		}
	}

	@Override
	public Date getResult(ResultSet rs, String columnName) throws SQLException {
		long l = rs.getLong(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return new Date(l);
	}

	@Override
	public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
		long l = rs.getLong(columnIndex);
		if (rs.wasNull()) {
			return null;
		}
		return new Date(l);
	}

	@Override
	public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
		long l = cs.getLong(columnIndex);
		if (cs.wasNull()) {
			return null;
		}
		return new Date(l);
	}

}
