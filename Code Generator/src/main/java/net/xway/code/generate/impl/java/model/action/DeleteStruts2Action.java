package net.xway.code.generate.impl.java.model.action;

import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public class DeleteStruts2Action extends Struts2Action {

	public final static String DELETE_ACTION_TEMPLATE = "/META-INF/templates/deleteaction.vm";

	public DeleteStruts2Action(Project project, IService iService, DTO dto, String packagename) {
		super(DELETE_ACTION_TEMPLATE, project, iService,  dto, "Delete" + dto.getName(), packagename);
	}

	@Override
	public String getResultType() {
		return "redirectAction";
	}

	public String getlocation() {
		return "List" + dto.getName();
	}
}
