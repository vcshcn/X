package net.xway.platform.system.dto;

public class Country  extends BaseDTO {

	private static final long serialVersionUID = 7616954231321094386L;
	
	private Integer countryid;
	private String name;
	private String code2;
	private String code3;
	private String telphonecode;
	private String currency;
	private Continent continent;

	public Integer getCountryid() {
		return countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getTelphonecode() {
		return telphonecode;
	}

	public void setTelphonecode(String telphonecode) {
		this.telphonecode = telphonecode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	
}
