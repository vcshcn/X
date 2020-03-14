package net.xway.code.generate.impl.java.model.action;

import java.util.HashMap;
import java.util.Map;

import net.xway.code.generate.impl.java.model.dto.DTO;
import net.xway.code.generate.impl.java.model.service.IService;
import net.xway.code.model.Project;

public class ListStruts2Action extends Struts2Action {

	public final static String LIST_ACTION_TEMPLATE = "/META-INF/templates/listaction.vm";
	private Map<String, String> param = new HashMap<>(1);

	public ListStruts2Action(Project project, IService iService, DTO dto, String packagename) {
		super(LIST_ACTION_TEMPLATE, project, iService, dto, "List" + dto.getName(), packagename);
		String left = ("/" + project.getName() + "/" + dto.getName()+ "/search-" + dto.getName() + ".jsp").toLowerCase();
		param.put("left", left);
		String center = ("/" + project.getName() + "/" + dto.getName()+ "/list-" + dto.getName() + ".jsp").toLowerCase();
		param.put("center", center);
	}

	@Override
	public String getResultType() {
		return "frame";
	}
	
	public Map<String, String> getParam() {
		return param;
	}
}
