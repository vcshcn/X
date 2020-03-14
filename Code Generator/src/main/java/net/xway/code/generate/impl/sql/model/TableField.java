package net.xway.code.generate.impl.sql.model;

import net.xway.code.model.Field;
import net.xway.code.model.type.PrimaryKeyType;


public class TableField {

	private final String fieldname;
	private final String type;
	private final boolean notnull;
	private final Object defaultvalue;
	private final String comment;
	private final Field field;
	
	public TableField(Field field) {
		this.field = field;
		fieldname = field.getJdbcName();
		String t = field.getType().getJdbcType();
		if (field.getLength() != null) {
			t += "(" + field.getLength() + ")";
		}
		type = t.toUpperCase();
		notnull = field.isNotNull();
		defaultvalue =  field.getDefaultValue();
		comment = field.getComment();
	}

	public String getFieldname() {
		return fieldname;
	}

	public String getType() {
		return type;
	}

	public String getNotnull() {
		return notnull ? "NOT NULL" : "";
	}

	public String getDefault() {
		return defaultvalue == null ? "" : defaultvalue.toString();
	}

	public String getComment() {
		return comment;
	}
	
	public String getPrimarykey() {
		return (field.getType() instanceof PrimaryKeyType) ? "PRIMARY KEY AUTO_INCREMENT" : "";
	}
	
	
}
