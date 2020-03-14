package net.xway.platform.app.system.exception;

import net.xway.platform.system.dto.User;

public class SafeException extends java.lang.IllegalArgumentException {

	private static final long serialVersionUID = 9185632256952623992L;

	private final User source, target;
	
	public SafeException(String message, User source, User target) {
		super(message);
		this.source = source;
		this.target = target;
	}

	public User getSource() {
		return source;
	}

	public User getTarget() {
		return target;
	}
	
	
}
