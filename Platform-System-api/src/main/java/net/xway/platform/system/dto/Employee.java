package net.xway.platform.system.dto;

public class Employee extends User  {

	private static final long serialVersionUID = -3897503344309999979L;
	private Department department;
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
