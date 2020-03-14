package net.xway.base.database.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;


public class BooleanTypeHandler implements TypeHandler<Boolean> {
	
	@Override
	public Boolean getResult(ResultSet rs, String name) throws SQLException {
		int i = rs.getInt(name);
		if (rs.wasNull()) {
			return null;
		}
		else {
			return i==0 ? Boolean.FALSE : Boolean.TRUE;
		}
	}

	@Override
	public Boolean getResult(ResultSet rs, int index) throws SQLException {
		int i = rs.getInt( index);
		if (rs.wasNull()) {
			return null;
		}
		else {
			return i==0 ? Boolean.FALSE : Boolean.TRUE;
		}
	}

	@Override
	public Boolean getResult(CallableStatement cs, int index) throws SQLException {
		int i = cs.getInt( index);
		if (cs.wasNull()) {
			return null;
		}
		else {
			return i==0 ? Boolean.FALSE : Boolean.TRUE;
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int index, Boolean value, JdbcType type) throws SQLException {
		if (value == null) {
			ps.setNull(index, java.sql.Types.INTEGER);
		}
		else {
			ps.setInt(index, value == true ? 1 : 0);
		}
	}

}
