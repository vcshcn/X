package net.xway.code.model.type;

import net.xway.code.config.Config;

public class IntegerType implements IFieldType {

	private static final long serialVersionUID = 2714104711187694129L;

	@Override
	public String toString() {
		return  NAME;
	}

	@Override
	public String getJdbcType() {
		return Config.getInstance().getCurrentDataBase().getJdbcTypeInt();
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return isNotNull ? "int" : "Integer";
	}

	public final static String NAME = "Integer";

}
