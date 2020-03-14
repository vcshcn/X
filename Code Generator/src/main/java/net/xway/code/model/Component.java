package net.xway.code.model;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.model.type.FieldTypeUtils;
import net.xway.code.model.type.PrimaryKeyType;

public class Component implements java.io.Serializable {

	private static final long serialVersionUID = 1735613862366625219L;
	private static final String ID = "id";
	
	private String name;
	private List<Field> fields = new ArrayList<>();
	private String comment;
	private List<Component> subcomponent = new ArrayList<>();
	private boolean generate = true;
	private Component parent;
	private final Project project;

	public Component(Project project, String name) {
		this.project = project;
		this.name = name;

		Field primaryKey = new Field(name + ID);
		primaryKey.setType(FieldTypeUtils.DefaultPrimaryKeyType);
		primaryKey.setNotNull(true);

		fields.add(primaryKey);
	}
	
	public Component(Project project, String name, Component parentComponent) {
		this(project, name);
		this.parent = parentComponent;
		
		Field parent = new Field(parentComponent.getName());
		parent.setType (FieldTypeUtils.TYPE_COMPONENT);
		parent.setReference(parentComponent);
		parent.setNotNull(true);
		fields.add(parent);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		for (Field field : fields) {
			if (field.getType().equals(FieldTypeUtils.TYPE_PRIMARYKEY)) {
				field.setName(name+ID);
				break ;
			}
		}
	}
	
	public boolean isSubComponent() {
		return parent != null;
	}

	public boolean addField(Field field) {
		return fields.add(field);
	}
	
	public boolean deleteField(Field field) {
		return fields.remove(field);
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public Field getPrimaryKey() {
		for (Field field : fields) {
			if (field.getType() instanceof PrimaryKeyType) {
				return field;
			}
		}
		return null;
	}

	public boolean addSubComponent(Component c) {
		return subcomponent.add(c);
	}
	
	public boolean removeSubComponent(Component c) {
		return subcomponent.remove(c);
	}

	public List<Component> getSubcomponent() {
		return subcomponent;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isGenerate() {
		return generate;
	}

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}
	
	public Project getProject() {
		return project;
	}

	@Override
	public String toString() {
		return name;
	}
	

}
