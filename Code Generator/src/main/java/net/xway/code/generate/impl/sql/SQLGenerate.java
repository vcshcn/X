package net.xway.code.generate.impl.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import net.xway.code.config.Config;
import net.xway.code.generate.Env;
import net.xway.code.generate.impl.AbstractGenerate;
import net.xway.code.generate.impl.sql.model.ddl.CreateScript;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class SQLGenerate extends AbstractGenerate {

	@Override
	public void generate(Group group, Project project, Component component) throws IOException {
		String databaseType = Config.getInstance().getCurrentDataBase().getName().toLowerCase();
		File baseSQLDirectory = Env.getBaseSQLDirectory();
		File sqlDirectory = new File(baseSQLDirectory, databaseType);

		List<Component> components = getComponentAndAllSubComponents(component);
		
		CreateScript script = new CreateScript(project, component, components);
		File file = writeToFile(script, sqlDirectory);
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line ;
		while ( (line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		
	}

}
