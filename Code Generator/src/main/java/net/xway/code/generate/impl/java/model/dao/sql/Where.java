package net.xway.code.generate.impl.java.model.dao.sql;

public class Where {

	private final SQLField field;
	private final String op;
	private final String val;
	
	public Where(SQLField field, String op, String val) {
		this.field = field;
		this.op = op;
		this.val = val;
	}

	public SQLField getField() {
		return field;
	}

	public String getOp() {
		return op;
	}

	public String getVal() {
		return val;
	}
	
	
}
