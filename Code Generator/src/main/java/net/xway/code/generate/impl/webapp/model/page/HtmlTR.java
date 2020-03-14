package net.xway.code.generate.impl.webapp.model.page;

import java.util.ArrayList;
import java.util.List;

public class HtmlTR {

	private List<HtmlTD> htmlTDs = new ArrayList<>();

	public List<HtmlTD> getColumns() {
		return htmlTDs;
	}

	public void setColumns(List<HtmlTD> htmlTDs) {
		this.htmlTDs = htmlTDs;
	}
	
	public void addHtmlTD(HtmlTD td) {
		htmlTDs.add(td);
	}
	
}
