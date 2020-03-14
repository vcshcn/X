package net.xway.code.generate.impl.java.model.dto;

import java.util.List;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.JavaField;
import net.xway.code.model.Component;
import net.xway.code.model.Project;
import net.xway.code.model.type.NameType;
import net.xway.code.model.type.PrimaryKeyType;

public class DTO extends AbstractJavaClass {

	public final static String DTO_TEMPLATE = "/META-INF/templates/dto.vm";

	protected final Component component;
	protected List<JavaField> fields;
	protected JavaField primaryKey;
	protected JavaField nameField;

	public DTO(Project project, Component component, String packageName) {
		super(DTO_TEMPLATE, project, component.getName(), packageName, component.getName(), null, null);
		this.component = component;
	}
	
	public void setJavaFields(List<JavaField> fields) {
		this.fields = fields;
		for (JavaField jf : fields) {
			if (jf.getField().getType() instanceof PrimaryKeyType) {
				primaryKey = jf;
			}
			if (jf.getField().getType() instanceof NameType) {
				nameField = jf;
			}
			
			addImport(jf.getType());
		}
	}
	
	public String getName() {
		return humanName;
	}

	public List<JavaField> getFields() {
		return fields;
	}

	public Component getComponent() {
		return component;
	}


	public JavaField getPrimaryKey() {
		return primaryKey;
	}


	public JavaField getNameField() {
		return nameField;
	}

}
