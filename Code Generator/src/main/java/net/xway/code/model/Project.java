package net.xway.code.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Project implements java.io.Serializable {

	private static final long serialVersionUID = -2805461118192246540L;
	
	private String name;
	private String basePackage;
	private String author;
	private String createdate;
	private String tablePrefix;
	private String tableSuffix;
	private List<Component> components = new ArrayList<>();
	private List<EnumComponent> enumComponents = new ArrayList<>();
	private String comment;

	public Project(String name) {
		this.name = name;
		basePackage = "net.xway";
		author = System.getenv("USERNAME");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		createdate = sdf.format(new java.util.Date());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getPackageName() {
		return (basePackage + "." + name).toLowerCase();
	}

	public boolean addComponent(Component c) {
		return components.add(c);
	}
	
	public boolean removeComponent(Component c) {
		return components.remove(c);
	}

	public List<Component> getComponents() {
		return components;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean addEnumComponent(EnumComponent c) {
		return enumComponents.add(c);
	}

	public List<EnumComponent> getEnumComponents() {
		return enumComponents;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getTablePrefix() {
		return tablePrefix == null ? "" : tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getTableSuffix() {
		return tableSuffix == null ? "" : tableSuffix;
	}

	public void setTableSuffix(String tableSuffix) {
		this.tableSuffix = tableSuffix;
	}

}
