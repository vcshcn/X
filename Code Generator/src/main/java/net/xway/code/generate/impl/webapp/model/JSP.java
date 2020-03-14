package net.xway.code.generate.impl.webapp.model;

import java.util.ArrayList;
import java.util.List;

import net.xway.code.generate.impl.AbstractFile;
import net.xway.code.generate.impl.webapp.model.page.HtmlInput;
import net.xway.code.generate.impl.webapp.model.page.HtmlTD;
import net.xway.code.generate.impl.webapp.model.page.HtmlTR;
import net.xway.code.model.Component;
import net.xway.code.model.Field;
import net.xway.code.model.type.ComponentType;
import net.xway.code.model.type.PrimaryKeyType;

public abstract class JSP extends  AbstractFile {

	protected final Component component;
	private List<HtmlTR> trs;

	public JSP(String template, Component component) {
		super(template);
		this.component = component;
	
		trs = initRows();
	}

	public Component getComponent() {
		return component;
	}
	
	public List<HtmlTR> getRows() {
		return trs;
	}

	private List<HtmlTR> initRows() {
		ArrayList<HtmlTR> htmlTR = new ArrayList<>();
		
		HtmlTR tr = new HtmlTR();
		for (Field field : component.getFields()) {
			HtmlTD td = null;
			
			if (field.getType() instanceof PrimaryKeyType == false) {
				String name;
				if (field.getType() instanceof ComponentType) {
					HtmlInput hidden = new HtmlInput("hidden", field.getName()+"id");
					HtmlInput text = new HtmlInput("text", field.getName()+"name");
					
					 td = new HtmlTD(field.getName(), hidden, text);
				}
				else {
					HtmlInput text = new HtmlInput("text", field.getName()+"name");
					td = new HtmlTD(field.getName(), text );
				}
			}
			
			if (td != null) {
				tr.addHtmlTD(td);
				if (tr.getColumns().size() == 1) {
					htmlTR.add(tr);
				}
				else if (tr.getColumns().size() == 3) {
					tr = new HtmlTR();
				}
			}
		}
		
		return htmlTR;
	}
}
