package net.xway.base.database;

import java.util.HashMap;

public class Parameters extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 5678978080841945759L;

	public Parameters set(String key, Object value) {
		put(key, value);
		return this;
	}

}
