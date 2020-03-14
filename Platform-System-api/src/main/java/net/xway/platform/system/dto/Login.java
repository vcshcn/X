package net.xway.platform.system.dto;


public class Login  extends BaseDTO {

	private static final long serialVersionUID = 4725115590429255390L;
	private Integer loginid;
	private String loginname;
	private String password;
	private String cookie;
	private boolean lock;

	public Integer getLoginid() {
		return loginid;
	}
	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	public boolean getLock() {
		return lock;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}
