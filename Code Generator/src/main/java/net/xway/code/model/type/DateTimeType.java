package net.xway.code.model.type;

import net.xway.code.config.Config;

public class DateTimeType implements  IFieldType {
	
	private static final long serialVersionUID = -4801261785444551634L;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeDateTime();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return "java.util.Date";
	}

	public final static String NAME = "DateTime";

}
