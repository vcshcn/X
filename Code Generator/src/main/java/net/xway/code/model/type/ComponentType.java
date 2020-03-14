package net.xway.code.model.type;

import net.xway.code.config.Config;

public class ComponentType implements IFieldType {
	
	private static final long serialVersionUID = -5963773039554790014L;

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
		return isNotNull ? "int" : "Integer";
	}

	public final static String NAME = "Component";

}
