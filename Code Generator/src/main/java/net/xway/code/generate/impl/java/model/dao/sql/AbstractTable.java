package net.xway.code.generate.impl.java.model.dao.sql;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable {

	public static final char SPACE = ' ';
	public static final char COMMA = ',';
	public static final char DOT = '.';
	
	protected String tableName;
	protected String tableAlias;
	protected List<SQLField> fields = new ArrayList<>();
	
	public AbstractTable(String tableName, String tableAlias) {
		this.tableName = tableName;
		this.tableAlias = tableAlias;
	}
	
	public String getTableName() {
		return tableName;
	}
	public String getTableAlias() {
		return tableAlias;
	}
	public List<SQLField> getFields() {
		return fields;
	}

	public String getSelectFieldsSQL() {
		StringBuilder sb = new StringBuilder() ;
		
		for (int i=0; i<fields.size(); i++) {
			SQLField field = fields.get(i);
			sb.append(field.getTable().getTableAlias());
			sb.append('.');
			sb.append(field.getName());
			sb.append(SPACE);
			sb.append(field.getAlias());
			
			if (i != fields.size()-1) {
				sb.append(COMMA);
			}
		}
		
		return sb.toString();
	}
	
	public abstract List<SQLField> getAllSQLFields();
	
}
