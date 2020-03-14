package net.xway.platform.system.dto;

public class RolePrivilege {
	private Integer roleprivilegeid;
	private Role role;
	private Privilege privilege;
	
	public Integer getRoleprivilegeid() {
		return roleprivilegeid;
	}
	public void setRoleprivilegeid(Integer roleprivilegeid) {
		this.roleprivilegeid = roleprivilegeid;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	

}
