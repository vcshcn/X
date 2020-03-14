package net.xway.platform.system.dto;


public class TimeZone  extends BaseDTO {

	private static final long serialVersionUID = 4443352822026017203L;
	private Integer timezoneid;
	private String name;
	private String gmt;
	
	public java.util.TimeZone getTimeZone() {
		if (gmt != null) {
			java.util.TimeZone zone = java.util.TimeZone.getTimeZone(gmt);
			return zone;
		}
		return null;
	}
	
	public Integer getTimezoneid() {
		return timezoneid;
	}
	public void setTimezoneid(Integer timezoneid) {
		this.timezoneid = timezoneid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGmt() {
		return gmt;
	}

	public void setGmt(String gmt) {
		this.gmt = gmt;
	}

	@Override
	public String toString() {
		return name + "(" + gmt + ")";
	}

	
}
