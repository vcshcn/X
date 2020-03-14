package net.xway.code.model.type;

import net.xway.code.config.Config;

public class StringType implements IFieldType {

	private static final long serialVersionUID = 2923786566807231288L;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeVarChar();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return "String";
	}

	public final static String NAME = "String";
	
}
