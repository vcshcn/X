package net.xway.code.generate.impl.java.model.action;

import java.util.HashMap;
import java.util.Map;

import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public class ViewStruts2Action extends Struts2Action {

	public final static String VIEW_ACTION_TEMPLATE = "/META-INF/templates/viewaction.vm";
	private Map<String, String> param = new HashMap<>(1);

	public ViewStruts2Action(Project project, IService iService, DTO dto, String packagename) {
		super(VIEW_ACTION_TEMPLATE, project, iService, dto, "View" + dto.getName(), packagename);
		String jsp = ("/" + project.getName() + "/" + dto.getName()+ "/view-" + dto.getName() + ".jsp").toLowerCase();
		param.put("center", jsp);
	}

	@Override
	public String getResultType() {
		return "frame";
	}
	
	public Map<String, String> getParam() {
		return param;
	}

}
