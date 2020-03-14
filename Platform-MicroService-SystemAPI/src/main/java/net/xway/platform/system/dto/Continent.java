package net.xway.platform.system.dto;

public class Continent  extends BaseDTO {
	private static final long serialVersionUID = 6695132357255876512L;
	
	private Integer continentid;
	private String name;
	private String code;
	
	public Integer getContinentid() {
		return continentid;
	}
	public void setContinentid(Integer continentid) {
		this.continentid = continentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
