package net.xway.platform.app.system.service;

public class LoginException extends Exception {

	private static final long serialVersionUID = 8480958993971486238L;

	public LoginException() {
		super();
	}
	
	public LoginException(String message) {
		super(message);
	}
	
	public LoginException(Throwable cause) {
		super(cause);
	}
}
