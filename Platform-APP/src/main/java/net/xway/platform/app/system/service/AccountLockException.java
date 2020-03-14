package net.xway.platform.app.system.service;


public class AccountLockException extends LoginException {

	private static final long serialVersionUID = -7019911146426474190L;
	private String loginname;
	
	public AccountLockException(String loginname) {
		super();
		this.loginname = loginname;
	}

	public String getLoginname() {
		return loginname;
	}
	
	
}
