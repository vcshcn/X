package net.xway.code.database;


public interface IDataBase {

	public String getName();
	
	public String getJdbcTypeInt();
	
	public String getJdbcTypeVarChar();
	
	public String getJdbcTypeChar();

	public String getJdbcTypeDate();
	
	public String getJdbcTypeDateTime();
	
	public String getPingSQL();

	public String[] getReserveWord();
	
	public String getCommentSymbol();
}
