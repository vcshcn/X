package net.xway.code.generate.impl.webapp.model;

import net.xway.code.model.Component;

public class ViewJSP extends JSP {
	
	public final static String VIEWJSP_TEMPLATE = "/META-INF/templates/viewjsp.vm";

	public ViewJSP( Component component) {
		super(VIEWJSP_TEMPLATE, component);
	}

	@Override
	public String getFileName() {
		return "view-" + component.getName().toLowerCase() + ".jsp";
	}

}
