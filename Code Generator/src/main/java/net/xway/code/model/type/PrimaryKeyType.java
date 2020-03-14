package net.xway.code.model.type;

public class PrimaryKeyType extends IntegerType implements IFieldType{

	private static final long serialVersionUID = -2044367467464626667L;

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public String getJavaType(boolean isNotNull) {
		return "Integer";
	}

	public final static String NAME = "PrimaryKey";

}
