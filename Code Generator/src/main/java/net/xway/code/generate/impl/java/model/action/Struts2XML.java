package net.xway.code.generate.impl.java.model.action;

import java.util.List;

import net.xway.code.generate.impl.AbstractFile;

public class Struts2XML extends AbstractFile {

	public final static String ACTION_XML_TEMPLATE = "/META-INF/templates/actionxml.vm";

	private final List<Struts2Action> actions;
	
	private final String name;
	
	public Struts2XML(String name, List<Struts2Action> actions) {
		super(ACTION_XML_TEMPLATE);
		this.name = name;
		this.actions = actions;
	}

	@Override
	public String getFileName() {
		return "struts-" + name + ".xml";
	}

	public String getName() {
		return name;
	}
	
	public List<Struts2Action> getActions() {
		return actions;
	}

}
