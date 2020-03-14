package net.xway.code.model.type;

public interface IFieldType extends java.io.Serializable {
	
	public String getJdbcType();
	
	public String getJavaType(boolean isNotNull);
	
	
}
