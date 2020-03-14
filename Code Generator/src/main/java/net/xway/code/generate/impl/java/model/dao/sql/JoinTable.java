package net.xway.code.generate.impl.java.model.dao.sql;

import java.util.ArrayList;
import java.util.List;

public class JoinTable extends AbstractTable {
	
	protected Type type;
	protected SQLTable childTable;
	protected SQLField childKey;
	
	public  JoinTable(Type type, SQLTable childTable, SQLField childKey, String tableAlias) {
		super(null, tableAlias.toUpperCase());
		this.type = type;
		this.childTable = childTable;
		this.childKey = childKey;
		
		for (SQLField field : getAllSQLFields()) {
			SQLField f = new SQLField(this, field);
			fields.add(f);
		}
	}
	
	public SQLTable getChildTable() {
		return childTable;
	}
	
	public SQLField getChildKey() {
		return childKey;
	}

	public SQLField findFieldByChildField() {
		SQLField p = childKey;
		while (p!=null && p.getTable() != this) {
			p = p.getUp();
		}
		return p;
	}

	public List<SQLField> getAllSQLFields() {
		List<SQLField> fs = new ArrayList<>();
		
		fs.addAll(childTable.getFields());
		if (childTable.getJoinTable() != null) {
			fs.addAll(childTable.getJoinTable().getFields());
		}
		
		return fs;
	}
	
	public String getJoinSQL() {
		return type.name() + " JOIN";
	}

	public enum Type {
		INNER, LEFT, RIGHT;
	}
}
