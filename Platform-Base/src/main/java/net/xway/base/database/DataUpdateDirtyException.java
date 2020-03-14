package net.xway.base.database;

public class DataUpdateDirtyException extends DAOException {

	private static final long serialVersionUID = -4873202865636604805L;
	private final Object object;

	public DataUpdateDirtyException(String msg) {
		super(msg);
		this.object = null;
	}

	public DataUpdateDirtyException(Object object) {
		super("Data Dirty when insert " + object.getClass().getName());
		this.object = object;
	}

	public Object getObject() {
		return object;
	}
	
}
