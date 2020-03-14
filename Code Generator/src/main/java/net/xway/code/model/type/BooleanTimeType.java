package net.xway.code.model.type;

import net.xway.code.config.Config;

public class BooleanTimeType implements  IFieldType {
	private static final long serialVersionUID = -3799266549198807164L;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeInt();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return isNotNull ? "boolean" : "Boolean";
	}

	public final static String NAME = "Boolean";

}
