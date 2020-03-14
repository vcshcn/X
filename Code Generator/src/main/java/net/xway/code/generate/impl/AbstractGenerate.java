package net.xway.code.generate.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.xway.code.generate.impl.java.model.dao.sql.SQLTool;
import net.xway.code.model.Component;
import net.xway.code.model.Group;
import net.xway.code.model.Project;
import net.xway.code.utils.ComponentUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public abstract class AbstractGenerate {

	public abstract void generate(Group group, Project project, Component component) throws IOException;
	
	protected List<Component> getComponentAndAllSubComponents(Component component) {
		return ComponentUtils.getComponentAndAllSubComponents(component);
	}
	
	protected List<Component> getSubComponent(Component component) {
		return ComponentUtils.getSubComponent(component);
	}
	
	protected void write(AbstractFile file, Writer writer) throws IOException {
		Map<String, Object> context = new HashMap<>();
		context.put("context",file);
		context.put("StringTool", new StringTool());
		context.put("SQLTool", new SQLTool());
		generate(file.getTemplate(), context, writer);
	}
	
	protected File writeToFile(AbstractFile file, File directory) throws IOException {
		if (directory.isDirectory() == false) {
			directory.mkdirs();
		}
		
		String filename = file.getFileName();
		File target = new File(directory, filename);
		try (
				FileOutputStream fos = new FileOutputStream(target);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
		) {
				write(file, bw);
				System.out.println("-- Generate "  + filename);
		}
		return target;
	}

	protected static void generate(String template, Map<String,Object> context, Writer writer) throws IOException {		
		Template t = ve.getTemplate(template);
		
		VelocityContext vc = new VelocityContext();
		for (String key : context.keySet()) {
			vc.put(key, context.get(key));
		}
		
		try (
				BufferedWriter bw = new BufferedWriter(writer);
		) {
			t.merge(vc, bw);
		}
		
		
	}
	
	/**
	 * Get Init Velocity 
	 * @return
	 */
	private static VelocityEngine ve = new VelocityEngine();;
	static {
		Properties p = new Properties();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		ve.init(p);
	}

}
