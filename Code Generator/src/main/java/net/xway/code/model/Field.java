package net.xway.code.model;

import net.xway.code.model.type.ComponentType;
import net.xway.code.model.type.DateTimeType;
import net.xway.code.model.type.IFieldType;

public class Field implements java.io.Serializable {

	private static final long serialVersionUID = -5597143905942104838L;
	
	private String name;
	private IFieldType type;
	private Integer length;
	private String format;
	private boolean notNull = false;
	private Object defaultValue;
	private Component reference;
	private String comment;
	private boolean search = false;

	public Field(String name) {
		this.name = name;
	}
	
	public Field(String name, IFieldType type, Integer length, String format, boolean notNull, Object defaultValue) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.format = format;
		this.notNull = notNull;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IFieldType getType() {
		return type;
	}

	public void setType(IFieldType type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Component getReference() {
		return reference;
	}

	public void setReference(Component reference) {
		this.reference = reference;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}
	
	public String getJdbcName() {
		if (type instanceof ComponentType) {
			return (name + "ID").toUpperCase();
		}
		else {
			return name.toUpperCase();
		}
	}
	
	public static Field CreatedTime  = new Field("createdtime",  new DateTimeType(), null, null, false, null); 
	public static Field ModifiedTime = new Field("modifiedtime", new DateTimeType(), null, null, false, null);

}
