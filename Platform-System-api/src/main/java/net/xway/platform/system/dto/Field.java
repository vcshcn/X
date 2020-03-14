package net.xway.platform.system.dto;

public class Field  extends BaseDTO {

	private static final long serialVersionUID = 7185526571311000110L;
	public final static String TYPE_PRIMARYKEY 	= "pk";
	public final static String TYPE_NAME 		= "name";
	public final static String TYPE_STRING		= "string";
	public final static String TYPE_CHAR 		= "char";
	public final static String TYPE_INT 		= "int";
	public final static String TYPE_LONG 		= "long";
	public final static String TYPE_FLOAT 		= "float";
	public final static String TYPE_DOUBLE 		= "double";
	public final static String TYPE_DECIMAL 	= "decimal";
	public final static String TYPE_BOOL 		= "bool";
	public final static String TYPE_DATE 		= "date";
	public final static String TYPE_TIME 		= "time";
	public final static String TYPE_DATETIME 	= "datetime";
	public final static String TYPE_ENUM 		= "enum";
	
	private Integer fieldid;
	private View view;
	private String name;
	private String label;
	private String foreign;
	private String type;	// pk, name, string char, int, long, float, double, decimal, bool, date, datetime, enum
	private String ognl;
	private String format;	
	private Object value;
	private String enumname;
	private int displayIndex;
	private String width;
	private boolean isSearchField;
	private Integer sortIndex;
	
	private Integer size;	// database field length
	private Boolean nullable;
	
	public Integer getFieldid() {
		return fieldid;
	}
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getDisplayIndex() {
		return displayIndex;
	}
	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}
	public boolean isSearchField() {
		return isSearchField;
	}
	public void setSearchField(boolean isSearchField) {
		this.isSearchField = isSearchField;
	}
	public Integer getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getOgnl() {
		return ognl;
	}
	public void setOgnl(String ognl) {
		this.ognl = ognl;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getEnumname() {
		return enumname;
	}
	public void setEnumname(String enumname) {
		this.enumname = enumname;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return name + ":" + type;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Boolean isNullable() {
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}


	
}
