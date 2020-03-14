package net.xway.platform.system.dto;

import java.util.Date;

public class UserAccessLog {

	private String userAccessLogId;
	private String userLoginLogId;
	private String page;
	private Date when;
	private long duration;

	public String getUserLoginLogId() {
		return userLoginLogId;
	}
	public void setUserLoginLogId(String userLoginLogId) {
		this.userLoginLogId = userLoginLogId;
	}
	public String getUserAccessLogId() {
		return userAccessLogId;
	}
	public void setUserAccessLogId(String userAccessLogId) {
		this.userAccessLogId = userAccessLogId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

	
}
