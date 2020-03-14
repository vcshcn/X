package net.xway.base.database.mybatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import net.xway.base.utils.IPUtil;

public class IPTypeHandler implements TypeHandler<String> {
	
	@Override
	public void setParameter(PreparedStatement ps, int index, String ip, JdbcType jdbcType) throws SQLException {
		if (ip == null) {
			ps.setNull(index, java.sql.Types.INTEGER);
		}
		else {
			ps.setInt(index, IPUtil.ip2int(ip));
		}
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		int l = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return IPUtil.int2ip(l);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		int l = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		}
		return IPUtil.int2ip(l);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		int l = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		}
		return IPUtil.int2ip(l);
	}

}
