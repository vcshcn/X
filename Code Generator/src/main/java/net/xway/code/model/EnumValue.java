package net.xway.code.model;

public class EnumValue implements java.io.Serializable {

	private static final long serialVersionUID = 1201405459287339387L;
	
	public Integer key;
	public String value;
	public String description;

	public EnumValue() {
	}

	public EnumValue(Integer key, String value, String description) {
		this.key = key;
		this.value = value;
		this.description = description;
	}

}
