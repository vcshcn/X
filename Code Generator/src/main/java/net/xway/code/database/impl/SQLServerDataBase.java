package net.xway.code.database.impl;

import net.xway.code.database.IDataBase;

public class SQLServerDataBase implements IDataBase {

	public final static String NAME = "SQLServer";
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getJdbcTypeInt() {
		return "int";
	}

	@Override
	public String getJdbcTypeVarChar() {
		return "nvarchar";
	}

	@Override
	public String getJdbcTypeChar() {
		return "nchar";
	}

	@Override
	public String getJdbcTypeDate() {
		return "date";
	}

	@Override
	public String getJdbcTypeDateTime() {
		return "datetime";
	}

	@Override
	public String getPingSQL() {
		return "SELECT 1";
	}

	@Override
	public String[] getReserveWord() {
		return new String[0];
	}

	@Override
	public String getCommentSymbol() {
		return "-- ";
	}

}
