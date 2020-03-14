package net.xway.base.controller;

import java.util.HashMap;
import java.util.Map;

public class JsonResult<T> implements java.io.Serializable {

	private static final long serialVersionUID = 5950945275080404511L;
	
	private T object;
	private final Map<Integer, Object> errors = new HashMap<>();

	public JsonResult() {
		this.object = null;
	}

	public JsonResult(T object) {
		this.object = object;
	}
	
	public Map<Integer, Object> put(int code, Object message) {
		errors.put(code, message);
		return errors;
	}
	
	public static <T> JsonResult<T> newInstance() {
		return new JsonResult<T>();
	}

	public static <T> JsonResult<T> newInstance(T object) {
		return new JsonResult<T>(object);
	}

	public Map<Integer, Object> getErrors() {
		return errors;
	}

	public JsonResult<T> setObject(T object) {
		this.object = object;
		return this;
	}
	
	public T getObject() {
		return object;
	}

	public boolean getError() {
		return errors.size() > 0;
	}
	
	public boolean hasErrors() {
		return getError();
	}
	
	
}
