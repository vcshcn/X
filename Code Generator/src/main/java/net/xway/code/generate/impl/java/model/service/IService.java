package net.xway.code.generate.impl.java.model.service;

import java.util.List;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.model.Project;

public class IService extends AbstractJavaClass {

	public final static String ISERVICE_TEMPLATE = "/META-INF/templates/iservice.vm";
	
	private final List<DTO> dtos;
	
	public IService(Project project, String componentName, List<DTO> dtos, String packageName) {
		super(ISERVICE_TEMPLATE, project, componentName+"Service", packageName, "I" + componentName+"Service", null, null);
		this.dtos = dtos;
	}

	public List<DTO> getDtos() {
		return dtos;
	}

}
