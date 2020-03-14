package net.xway.platform.system.dto;

import java.util.Date;

public class LoginStatus {
	private Integer loginstatusid;
	private String lastip;
	private Date lastlogintime;
	private boolean lastsuccess;
	private Date enablelogintime;
	private int loginfailtimes;
	
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public boolean isLastsuccess() {
		return lastsuccess;
	}
	public void setLastsuccess(boolean lastsuccess) {
		this.lastsuccess = lastsuccess;
	}
	public Date getEnablelogintime() {
		return enablelogintime;
	}
	public void setEnablelogintime(Date enablelogintime) {
		this.enablelogintime = enablelogintime;
	}
	public int getLoginfailtimes() {
		return loginfailtimes;
	}
	public void setLoginfailtimes(int loginfailtimes) {
		this.loginfailtimes = loginfailtimes;
	}
	public Integer getLoginstatusid() {
		return loginstatusid;
	}
	public void setLoginstatusid(Integer loginstatusid) {
		this.loginstatusid = loginstatusid;
	}

}
