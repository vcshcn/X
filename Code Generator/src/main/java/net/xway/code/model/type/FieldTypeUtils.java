package net.xway.code.model.type;

import java.util.HashMap;

public class FieldTypeUtils {

	public final static StringType TYPE_STRING = new StringType();
	public final static NameType TYPE_NAME = new NameType();
	public final static IntegerType TYPE_INTEGER = new IntegerType();
	public final static BooleanTimeType TYPE_BOOLEAN = new BooleanTimeType();
	public final static PrimaryKeyType TYPE_PRIMARYKEY = new PrimaryKeyType();
	public final static DateType TYPE_DATE = new DateType();
	public final static DateTimeType TYPE_DATETIME = new DateTimeType();
	public final static ComponentType TYPE_COMPONENT = new ComponentType();
	public final static EnumType TYPE_ENUM = new EnumType();
	
	private static HashMap<String, IFieldType> TYPES = new HashMap<>();
	static {
		TYPES.put(StringType.NAME, TYPE_STRING);
		TYPES.put(NameType.NAME, TYPE_NAME);
		TYPES.put(IntegerType.NAME , TYPE_INTEGER);
		TYPES.put(BooleanTimeType.NAME, TYPE_BOOLEAN);
		TYPES.put(PrimaryKeyType.NAME, TYPE_PRIMARYKEY);
		TYPES.put(DateType.NAME , TYPE_DATE);
		TYPES.put(DateTimeType.NAME ,TYPE_DATETIME);
		TYPES.put(ComponentType.NAME , TYPE_COMPONENT);
		TYPES.put(EnumType.NAME , TYPE_ENUM);
	}
	
	public static IFieldType getType(String name) {
		return TYPES.get( name);
	}

	public static IFieldType DefaultPrimaryKeyType = getType(PrimaryKeyType.NAME);
	
	public static IFieldType[] UseFieldType = new IFieldType[] {TYPE_STRING, TYPE_INTEGER, TYPE_BOOLEAN, TYPE_DATE, TYPE_DATETIME, TYPE_NAME, TYPE_COMPONENT, TYPE_ENUM};
	
	public static IFieldType DefaultNameType = getType(NameType.NAME);

}
