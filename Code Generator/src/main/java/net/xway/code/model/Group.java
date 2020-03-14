package net.xway.code.model;

import java.util.ArrayList;
import java.util.List;

public class Group implements java.io.Serializable {

	private static final long serialVersionUID = -263628169192289675L;
	
	private List<Project> projects = new ArrayList<>();
	
	public List<Project> getProjects() {
		return projects;
	}

	public void addProjects(Project project) {
		projects.add(project);
	}
	
	public void merge(Group group) {
		for (Project project : group.getProjects()) {
			projects.add(project);
		}
	}
	
}
