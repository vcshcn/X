package net.xway.code.generate.impl.java.model.action;

import java.util.HashMap;
import java.util.Map;

import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public class AddStruts2Action extends Struts2Action {
	
	public final static String ADD_ACTION_TEMPLATE = "/META-INF/templates/addaction.vm";
	
	private Map<String, String> param = new HashMap<>(1);

	public AddStruts2Action(Project project, IService iService, DTO dto, String packagename) {
		super(ADD_ACTION_TEMPLATE, project, iService, dto, "Add" + dto.getName(), packagename);
		String jsp = ("/" + project.getName() + "/" + dto.getName()+ "/modify-" + dto.getName() + ".jsp").toLowerCase();
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
