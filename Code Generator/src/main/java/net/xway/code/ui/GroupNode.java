package net.xway.code.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class GroupNode extends AbstractTreeNode {

	private Group group;
	private JTree tree;
	private JPopupMenu mnuGroup;
	private JMenuItem mnuNewProject;
	
	public GroupNode(JTree tree) {
		this.tree = tree;
		group = new Group();
		
		ProjectNode node = new ProjectNode(this);
		addNode(node);
		
		initComponents();
	}

	public GroupNode(JTree tree, Group group) {
		this.tree = tree;
		this.group = group;
		
		for (Project project : group.getProjects()) {
			ProjectNode node = new ProjectNode(this, project);
			addNode(node);
		}
		
		initComponents();
	}

	private void initComponents() {
		mnuNewProject = new JMenuItem("New Project");
		mnuNewProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newProject();
			}
		});
		
		// init popup menu
		mnuGroup = new JPopupMenu();
		mnuGroup.add(mnuNewProject);
	}
	
	public void newProject() {
		ProjectNode node = new ProjectNode(this);
		addNode(node);
	}
	
	public void removeProject() {
		
	}

	@Override
	public JPopupMenu getContentMenu() {
		return mnuGroup;
	}

	@Override
	public JPanel getContentPanel() {
		return null;
	}

	@Override
	public JTree getTree() {
		return tree;
	}
	
	public Group getGroup() {
		return group;
	}

	@Override
	public String toString() {
		return "Groups";
	}

}
