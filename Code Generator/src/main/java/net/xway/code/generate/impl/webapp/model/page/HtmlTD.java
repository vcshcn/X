package net.xway.code.generate.impl.webapp.model.page;

import java.util.ArrayList;
import java.util.List;

public class HtmlTD {

	private String label;
	private List<HtmlInput> inputs = new ArrayList<>();
	
	public HtmlTD(String label, HtmlInput... inputs) {
		this.label = label;
		for (int i=0; i<inputs.length; i++) {
			this.inputs.add(inputs[i]);
		}
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<HtmlInput> getInputs() {
		return inputs;
	}
	public void setInputs(List<HtmlInput> inputs) {
		this.inputs = inputs;
	}
	
	
}
