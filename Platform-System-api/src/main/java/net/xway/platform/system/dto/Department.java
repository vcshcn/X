package net.xway.platform.system.dto;

public class Department  extends BaseDTO {
	private static final long serialVersionUID = 8696055446224363219L;
	
	private Integer departmentid;
	private Org org;
	private Department parent;
	private String name;
	private String tel;
	private String description;
	

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
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

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
