package net.xway.base.database;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -1036135043985827016L;

	public DAOException() { }
	
	public DAOException(Throwable cause) {
		super(cause);
	}
	
	public DAOException(String cause) {
		super(cause);
	}
}
