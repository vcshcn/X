package net.xway.platform.app.system.service;

public class NoSuchAccountException extends LoginException {
	private static final long serialVersionUID = 5015029686356242556L;
	private String loginname;
	
	public NoSuchAccountException(String loginname) {
		super();
		this.loginname  = loginname;
	}

	public String getLoginname() {
		return loginname;
	}

}
