package net.xway.code.generate.impl.java.model.dao.sql;

public class OrderBy {

	private final SQLField field;
	private final String order;
	
	public OrderBy(SQLField field, String order) {
		this.field = field;
		this.order = order;
	}

	public SQLField getField() {
		return field;
	}

	public String getOrder() {
		return order;
	}

	
	
}
