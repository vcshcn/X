package net.xway.code.generate.impl.sql.model.ddl;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.config.Config;
import net.xway.code.generate.impl.AbstractFile;
import net.xway.code.generate.impl.sql.model.ForeignKey;
import net.xway.code.generate.impl.sql.model.Table;
import net.xway.code.model.Component;
import net.xway.code.model.Project;

public class CreateScript extends AbstractFile {

	public final static String CREATE_TEMPLATE = "/META-INF/templates/createsql.vm";
	private final Project project;
	private final Component component;
	private List<Table> tables = new ArrayList<>();
	private List<ForeignKey> foreignkeys = new ArrayList<>();

	public CreateScript(Project project, Component component, List<Component> components) {
		super(CREATE_TEMPLATE);
		this.project = project;
		this.component = component;
		
		for (Component cmp : components) {
			Table table = new Table(cmp);
			tables.add(table);
			foreignkeys.addAll(table.getForeignkey());
		}
	}

	@Override
	public String getFileName() {
		String filename = project.getName() + "-" + component.getName() + "-" + Config.getInstance().getCurrentDataBase().getName() + "-create.sql"; 
		return filename.toLowerCase();
	}

	public List<Table> getTables() {
		return tables;
	}

	public List<ForeignKey> getForeignkeys() {
		return foreignkeys;
	}
	
	public String getComponentPackageName() {
		return (project.getPackageName() + "." + component.getName()).toLowerCase();
	}
	
}
