package net.xway.code.generate.impl.sql.model;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.model.Component;
import net.xway.code.model.Field;
import net.xway.code.model.Project;
import net.xway.code.model.type.ComponentType;

public class Table {

	private final String tablename;
	private Component component;
	private List<TableField> fields = new ArrayList<>();
	private List<ForeignKey> foreignkeys = new ArrayList<>();
	
	public Table(Component component) {
		this.component = component;
		tablename = component.getName().toUpperCase();
		for (Field field : component.getFields()) {
			TableField tf = new TableField(field);
			fields.add(tf);
			
			if (field.getType() instanceof ComponentType) {
				Table primaryTable = new Table(field.getReference());
				TableField primaryKey = new TableField(field.getReference().getPrimaryKey());
				TableField childKey = new TableField(field);
				ForeignKey key = new ForeignKey(primaryTable,  primaryKey, this, childKey);
				foreignkeys.add(key);
			}
		}
	}
	
	public String getTablename() {
		return component.getProject().getTablePrefix() +  tablename + component.getProject().getTableSuffix();
	}

	public List<TableField> getFields() {
		return fields;
	}

	public List<ForeignKey> getForeignkey() {
		return foreignkeys;
	}

	public Component getComponent() {
		return component;
	}
	
	
}
