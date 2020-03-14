package net.xway.platform.system.dto;

public class Privilege  extends BaseDTO {

	private static final long serialVersionUID = 1913957429542485548L;
	private Integer privilegeid;
	private String name;
	private String description;
	
	public Integer getPrivilegeid() {
		return privilegeid;
	}
	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return name;
	}
	
}
