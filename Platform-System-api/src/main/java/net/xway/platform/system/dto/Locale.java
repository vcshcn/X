package net.xway.platform.system.dto;


public class Locale  extends BaseDTO {

	private static final long serialVersionUID = 1321492020532947364L;
	private Integer localeid;
	private String name;
	private String language;
	private String country;
	
	public String getCode() {
		if (language == null || language.length() == 0) {
			return language;
		}
		if (country == null || country.length()==0) {
			return language;
		}
		return language + "_" + country;
	}
	
	public java.util.Locale getLocale() {
		if (language != null && country == null) {
			return new java.util.Locale(language);
		}
		else if (language != null && country != null) {
			return new java.util.Locale(language, country);
		}
		return null;
	}
	
	public Integer getLocaleid() {
		return localeid;
	}
	public void setLocaleid(Integer localeid) {
		this.localeid = localeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return getCode();
	}

	
}
