package net.xway.code.generate.impl.webapp.model;

import net.xway.code.model.Component;

public class SearchJSP extends JSP {
	
	public final static String SEARCHJSP_TEMPLATE = "/META-INF/templates/searchjsp.vm";

	public SearchJSP( Component component) {
		super(SEARCHJSP_TEMPLATE, component);
	}

	@Override
	public String getFileName() {
		return "search-" + component.getName().toLowerCase() + ".jsp";
	}

}
