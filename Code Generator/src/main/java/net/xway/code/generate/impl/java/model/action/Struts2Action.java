package net.xway.code.generate.impl.java.model.action;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public abstract class Struts2Action  extends AbstractJavaClass  {

	private final IService iservice;
	
	protected final DTO dto;
	
	public Struts2Action(String template, Project project, IService iservice, DTO dto, String name, String packagename) {
		super(template, project, name, packagename, name + "Action", null, null);
		this.iservice = iservice;
		this.dto = dto;
	}

	public IService getIservice() {
		return iservice;
	}

	public DTO getDto() {
		return dto;
	}
	
	public abstract String getResultType() ;

}
