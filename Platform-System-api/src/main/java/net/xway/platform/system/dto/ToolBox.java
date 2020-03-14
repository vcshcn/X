package net.xway.platform.system.dto;

public class ToolBox  extends BaseDTO {

	private static final long serialVersionUID = -6619360792087135616L;
	private Integer toolboxid;
	private String icon;
	private String name;
	private String description;
	private Resource resource;
	private boolean fix;
	private int order;

	public Integer getToolboxid() {
		return toolboxid;
	}
	public void setToolboxid(Integer toolboxid) {
		this.toolboxid = toolboxid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isFix() {
		return fix;
	}
	public void setFix(boolean fix) {
		this.fix = fix;
	}
	
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	@Override
	public int hashCode() {
		return toolboxid.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		ToolBox box = (ToolBox)obj;
		if (box == null) {
			return false;
		}
		if (toolboxid == null && box.toolboxid == null) {
			return true;
		}
		if (toolboxid != null && box.toolboxid != null ) {
			return toolboxid.equals(box.toolboxid);
		}
		return false;
	}
	
	
}
