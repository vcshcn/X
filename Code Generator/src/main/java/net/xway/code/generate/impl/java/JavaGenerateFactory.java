package net.xway.code.generate.impl.java;

import net.xway.code.generate.AbstractGenerateFactory;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class JavaGenerateFactory extends AbstractGenerateFactory {
	
	@Override
	public String getID() {
		return "java";
	}

	public void generate(Group group)  throws Exception {
		JavaGenerate g = new JavaGenerate();
		for (Project project : group.getProjects()) {
			for (Component component : project.getComponents()) {
	
				System.out.println();
				System.out.println("Generate " + component.getName() +" Files.");
				
				if (component.isGenerate()) {
					g.generate(group, project, component);
				}
			}
		}
	}
	

}
