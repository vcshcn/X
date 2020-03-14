package net.xway.code.database.impl;

import net.xway.code.database.IDataBase;

public class MySQLDataBase implements IDataBase {

	public final static String NAME = "MySQL";

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
		return "varchar";
	}

	@Override
	public String getJdbcTypeChar() {
		return "char";
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
