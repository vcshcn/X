package net.xway.code.generate.impl;


public abstract class AbstractFile {

	protected final String template;
	
	public AbstractFile(String template) {
		this.template = template;
	}
	
	public abstract String getFileName();

	public String getTemplate() {
		return template;
	}


}
