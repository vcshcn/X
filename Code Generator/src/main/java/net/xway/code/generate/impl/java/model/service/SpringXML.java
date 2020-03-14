package net.xway.code.generate.impl.java.model.service;

import java.util.List;

import net.xway.code.generate.impl.AbstractFile;
import net.xway.code.generate.impl.java.model.action.Struts2Action;
import net.xway.code.generate.impl.java.model.dao.IDAO;

public class SpringXML extends AbstractFile {

	public final static String SPRING_XML_TEMPLATE = "/META-INF/templates/springxml.vm";

	private final String componentName;
	private final List<Struts2Action> actions;
	private final ServiceImpl serviceImpl;
	private final List<IDAO> daos;
	
	public SpringXML(String componentName, List<Struts2Action> actions, ServiceImpl serviceImpl, List<IDAO> daos) {
		super(SPRING_XML_TEMPLATE);
		this.componentName = componentName;
		this.actions = actions;
		this.serviceImpl = serviceImpl;
		this.daos = daos;
	}

	@Override
	public String getFileName() {
		return "spring-" + componentName + ".xml";
	}

	public String getComponentName() {
		return componentName;
	}

	public List<Struts2Action> getActions() {
		return actions;
	}

	public ServiceImpl getServiceImpl() {
		return serviceImpl;
	}

	public List<IDAO> getDaos() {
		return daos;
	}



}
