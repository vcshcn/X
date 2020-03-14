package net.xway.code.model.type;

import net.xway.code.config.Config;

public class NameType implements IFieldType {

	private static final long serialVersionUID = 8056133517601577855L;

	@Override
	public String toString() {
		return NAME;
	}

	public final static String NAME = "Name";

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeVarChar();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return "String";
	}
	
}
