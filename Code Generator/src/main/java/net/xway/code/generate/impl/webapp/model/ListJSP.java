package net.xway.code.generate.impl.webapp.model;

import net.xway.code.model.Component;

public class ListJSP extends JSP {
	
	public final static String LISTJSP_TEMPLATE = "/META-INF/templates/listjsp.vm";

	public ListJSP(Component component) {
		super(LISTJSP_TEMPLATE, component);
	}

	@Override
	public String getFileName() {
		return "list-" + component.getName().toLowerCase() + ".jsp";
	}

}
