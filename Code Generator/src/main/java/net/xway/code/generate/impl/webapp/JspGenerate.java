package net.xway.code.generate.impl.webapp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.xway.code.generate.Env;
import net.xway.code.generate.impl.AbstractGenerate;
import net.xway.code.generate.impl.webapp.model.ListJSP;
import net.xway.code.generate.impl.webapp.model.ModifyJSP;
import net.xway.code.generate.impl.webapp.model.SearchJSP;
import net.xway.code.generate.impl.webapp.model.ViewJSP;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class JspGenerate extends AbstractGenerate {

	@Override
	public void generate(Group group, Project project, Component component) throws IOException {
		File directory = new File(Env.getBaseWebappDirectory(), component.getName().toLowerCase());
		
		List<Component> components = getComponentAndAllSubComponents(component);
		for (Component c : components) {
			ListJSP listJsp = new ListJSP(c);
			writeToFile(listJsp, directory);
	
			SearchJSP searchJsp = new SearchJSP(c);
			writeToFile(searchJsp, directory);
			
			ViewJSP viewJsp = new ViewJSP(c);
			writeToFile(viewJsp, directory);
			
			ModifyJSP modifyJsp = new ModifyJSP(c);
			writeToFile(modifyJsp,directory);
		}
	}
}
