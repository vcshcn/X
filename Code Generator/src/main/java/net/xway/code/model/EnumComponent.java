package net.xway.code.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.xway.code.model.type.FieldTypeUtils;

public class EnumComponent extends Component implements java.io.Serializable {
	
	private static final long serialVersionUID = 5138318908745491782L;
	
	private List<EnumValue> values = new ArrayList<>();

	public EnumComponent(Project project, String name) {
		super(project, name);

		Field value = new Field("VALUE");
		value.setType(FieldTypeUtils.TYPE_INTEGER);
		value.setNotNull(true);
		super.addField(value);

		Field desc = new Field("DESCRIPTION");
		desc.setType(FieldTypeUtils.TYPE_STRING);
		desc.setLength(16);
		desc.setNotNull(true);
		super.addField(desc);
	}

	@Override
	public boolean addField(Field field) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public List<Field> getFields() {
		return Collections.unmodifiableList(super.getFields());
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean addValue(EnumValue value) {
		return values.add(value);
	}

	public List<EnumValue> getValues() {
		return values;
	}

}
