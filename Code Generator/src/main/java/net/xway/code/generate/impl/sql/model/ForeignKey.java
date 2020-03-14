package net.xway.code.generate.impl.sql.model;

public class ForeignKey {

	private final String name;
	private final Table primarytable;
	private final TableField primarykey;
	private final Table childtable;
	private final TableField childkey;
	
	public ForeignKey(Table primaryTable, TableField primaryKey, Table childTable, TableField childKey) {
		primarytable = primaryTable;
		primarykey = primaryKey;
		childtable = childTable;
		childkey = childKey;
		name = primaryTable.getTablename() + "_" + childTable.getTablename() + "_" + primaryKey.getFieldname() + "_" + childKey.getFieldname();
	}

	public Table getPrimarytable() {
		return primarytable;
	}

	public TableField getPrimarykey() {
		return primarykey;
	}

	public Table getChildtable() {
		return childtable;
	}

	public TableField getChildkey() {
		return childkey;
	}

	public String getName() {
		return name;
	}
	
	
}
