package net.xway.code.generate.impl.sql;

import net.xway.code.generate.AbstractGenerateFactory;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class SQLGenerateFactory extends AbstractGenerateFactory {

	@Override
	public String getID() {
		return "sql";
	}

	@Override
	public void generate(Group group) throws Exception {
		SQLGenerate g = new SQLGenerate();
		
		for (Project project : group.getProjects()) {
			for (Component component : project.getComponents()) {
				if (component.isGenerate()) {
					g.generate(group, project, component);
				}
			}
		}
	}

}
