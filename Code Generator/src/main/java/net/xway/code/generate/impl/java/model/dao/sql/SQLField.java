package net.xway.code.generate.impl.java.model.dao.sql;

import net.xway.code.generate.impl.java.model.JavaField;
import net.xway.code.model.type.PrimaryKeyType;

public class SQLField {

	private final JavaField jField; 
	private final String name;
	private final AbstractTable table;
	
	private SQLField up = null;
	private SQLField down = null;
		
	public SQLField(AbstractTable table, SQLField field) {
		this(field.jField, table, field.getAlias());
		this.down = field;
		field.up = this;
	}
	
	public SQLField(JavaField jField, AbstractTable table, String name) {
		this.jField = jField;
		this.table = table;
		this.name = name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlias() {
		return table.getTableAlias() +"_"+name;
	}
	
	public boolean isPrimaryKey() {
		return jField.getField().getType() instanceof PrimaryKeyType;
	}
		
	public SQLField getUp() {
		return up;
	}

	public SQLField getDown() {
		return down;
	}

	public AbstractTable getTable() {
		return table;
	}

	public JavaField getJavaField() {
		return jField;
	}

	@Override
	public String toString() {
		return name;
	}

}
