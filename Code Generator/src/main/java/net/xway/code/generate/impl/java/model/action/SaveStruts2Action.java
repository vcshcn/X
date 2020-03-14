package net.xway.code.generate.impl.java.model.action;

import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public class SaveStruts2Action extends Struts2Action {

	public final static String SAVE_ACTION_TEMPLATE = "/META-INF/templates/saveaction.vm";
	
	public SaveStruts2Action(Project project, IService iService, DTO dto, String packagename) {
		super(SAVE_ACTION_TEMPLATE, project, iService, dto, "Save" + dto.getName(),  packagename);
	}

	@Override
	public String getResultType() {
		return "redirectAction";
	}
	
	public String getlocation() {
		return "List" + dto.getName();
	}

}
