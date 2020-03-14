package net.xway.code.generate.impl.webapp;

import net.xway.code.generate.AbstractGenerateFactory;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class WebappGenerateFactory extends AbstractGenerateFactory {

	private JspGenerate jspGenerate = new JspGenerate();
	
	@Override
	public void generate(Group group) throws Exception  {
		for (Project project : group.getProjects()) {
			for (Component component : project.getComponents()) {
				if (component.isGenerate()) {
					jspGenerate.generate(group, project, component);
				}
			}
		}
	}

	@Override
	public String getID() {
		return "webapp";
	}

}
