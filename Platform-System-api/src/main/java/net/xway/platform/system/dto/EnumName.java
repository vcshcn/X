package net.xway.platform.system.dto;

import java.util.List;

public class EnumName {

	private Integer enumnameid;
	private String name;
	private String description;
	
	private List<EnumValue> values;


	public Integer getEnumnameid() {
		return enumnameid;
	}

	public void setEnumnameid(Integer enumnameid) {
		this.enumnameid = enumnameid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EnumValue> getValues() {
		return values;
	}

	public void setValues(List<EnumValue> values) {
		this.values = values;
	}
	
	public EnumValue value(int value) {
		if (values != null) {
			for (EnumValue v : values) {
				if (v.getValue() == value) {
					return v;
				}
			}
		}
		
		return null;
	}
	
}
