package net.xway.base.service;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = -6099332050225623920L;

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}
}
