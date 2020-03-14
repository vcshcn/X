package net.xway.platform.app.system.service;

public class PasswordNotMatchException extends LoginException {

	private static final long serialVersionUID = -7934103833796103022L;
	private String loginname;
	private String password;
	
	public PasswordNotMatchException(String loginname, String password) {
		super();
		this.loginname = loginname;
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public String getPassword() {
		return password;
	}
	
	
}
