package net.xway.code.generate.impl.java.model.dao;

import net.xway.code.generate.impl.java.model.AbstractJavaClass;
import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.model.Project;

public class IDAO extends AbstractJavaClass {

	public final static String IDAO_TEMPLATE = "/META-INF/templates/idao.vm";

	private final DTO dto;
	
	public IDAO(Project project, DTO dto, String packageName) {
		super(IDAO_TEMPLATE, project, dto.getName() + "DAO", packageName, "I" + dto.getName() + "DAO", null, null);
		this.dto = dto;
	}

	public DTO getDto() {
		return dto;
	}

}
