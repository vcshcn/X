package net.xway.platform.system.dto;


import net.xway.base.Constant;

public class Customize  extends BaseDTO {

	private static final long serialVersionUID = 6322193347628333195L;
	
	private Integer customizeid;
	private String dateformat;
	private String timeformat;
	private String datetimeformat;
	private Integer pagesize;
	private String style;
	
	public static Customize getDefaultCustomize() {
		Customize c = new Customize();
		c.setDateformat(Constant.USER_DEFAULT_DATE_FORMAT);
		c.setTimeformat(Constant.USER_DEFAULT_TIME_FORMAT);
		c.setDatetimeformat(Constant.USER_DEFAULT_DATETIME_FORMAT);
		c.setPagesize(Constant.USER_DEFAULT_PAGINATION_PAEGSIZE);
		c.setStyle(Constant.USER_DEFAULT_UI_STYLE);
		return c;
	}

	public Integer getCustomizeid() {
		return customizeid;
	}
	public void setCustomizeid(Integer customizeid) {
		this.customizeid = customizeid;
	}
	public String getDateformat() {
		return dateformat;
	}
	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public String getDatetimeformat() {
		return datetimeformat;
	}
	public void setDatetimeformat(String datetimeformat) {
		this.datetimeformat = datetimeformat;
	}
	public String getTimeformat() {
		return timeformat;
	}
	public void setTimeformat(String timeformat) {
		this.timeformat = timeformat;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
