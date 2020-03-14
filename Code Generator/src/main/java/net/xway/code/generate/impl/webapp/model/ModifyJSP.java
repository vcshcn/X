package net.xway.code.generate.impl.webapp.model;

import net.xway.code.model.Component;

public class ModifyJSP extends JSP {
	
	public final static String MODIFYJSP_TEMPLATE = "/META-INF/templates/modifyjsp.vm";

	public ModifyJSP( Component component) {
		super(MODIFYJSP_TEMPLATE, component);
	}

	@Override
	public String getFileName() {
		return "modify-" + component.getName().toLowerCase() + ".jsp";
	}

}
