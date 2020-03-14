package net.xway.platform.system.dto;

public class Resource  extends BaseDTO {

	private static final long serialVersionUID = -3883179256342820214L;
	private Integer resourceid;
	private String label;
	private String link;
	private String description;
	private boolean canmenu;
	private boolean stay;
	private Privilege privilege;
	
	public Integer getResourceid() {
		return resourceid;
	}
	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isCanmenu() {
		return canmenu;
	}
	public void setCanmenu(boolean canmenu) {
		this.canmenu = canmenu;
	}
	public boolean isStay() {
		return stay;
	}
	public void setStay(boolean stay) {
		this.stay = stay;
	}
	
}
