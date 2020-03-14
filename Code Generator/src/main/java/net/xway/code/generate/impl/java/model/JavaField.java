package net.xway.code.generate.impl.java.model;

import net.xway.code.model.Field;

public class JavaField {

	private final Field field;
	private final AbstractJavaClass type;
	
	public JavaField(Field field, AbstractJavaClass type) {
		this.field = field;
		this.type = type;
	}
	
	public String getName() {
		return field.getName();
	}
		
	public String getPropertyName() {
		return Character.toLowerCase(getName().charAt(0)) + getName().substring(1);
	}
	
	public String getGetMethodName() {
		return "get"+ getName();
	}
	
	public String getSetMethodName() {
		return "set"+getName();
	}

	public AbstractJavaClass getType() {
		return type;
	}

	public Field getField() {
		return field;
	}

	@Override
	public String toString() {
		return field.getName() + ":TYPE=" + type.getHumanName();
	}

	
}
