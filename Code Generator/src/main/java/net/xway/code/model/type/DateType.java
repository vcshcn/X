package net.xway.code.model.type;

import net.xway.code.config.Config;

public class DateType implements  IFieldType {

	private static final long serialVersionUID = 1426430445364155581L;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeDate();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return "java.util.Date";
	}

	public final static String NAME = "Date";

}
