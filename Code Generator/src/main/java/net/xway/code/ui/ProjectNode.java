package net.xway.code.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import net.xway.code.model.Component;
import net.xway.code.model.EnumComponent;
import net.xway.code.model.Project;

public class ProjectNode extends AbstractTreeNode {
	
	private static int ProjectIndex = 0;

	public ProjectNode(GroupNode parentNode) {
		super(parentNode);
		project = new Project("Project"+ ProjectIndex++ );
		parentNode.getGroup().addProjects(project);
		initComponents();
	}
	
	public ProjectNode(GroupNode parent, Project project) {
		super(parent);
		this.project= project;
		for (Component c : project.getComponents()) {
			ComponentNode node = new ComponentNode(this, c);
			addNode(node);
		}
		
		for (EnumComponent c : project.getEnumComponents()) {
			EnumComponentNode node = new EnumComponentNode(this, c);
			addNode(node);
		}
		
		initComponents();
	}
	
	private void initComponents() {
		mnuNewComponent = new JMenuItem("New Component");
		mnuNewComponent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newComponent();				
				
			}
		});
		
		mnuNewEnumComponent = new JMenuItem("New Enum");
		mnuNewEnumComponent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newEnumComponent();
			}
			
		});		
		
		// init popup menu
		mnuTree = new JPopupMenu();
		mnuTree.add(mnuNewComponent);
		mnuTree.add(mnuNewEnumComponent);
	
		// init content
		pnlContent = new ProjectPanel(this, project);
		
	}
	
	public void newComponent() {
		ComponentNode node = new ComponentNode(this);
		addNode(node);
	}
	
	public void deleteComponent(ComponentNode node) {
		project.removeComponent(node.getComponent());
		deleteNode(node);
	}
	
	public void newEnumComponent() {
		EnumComponentNode node = new EnumComponentNode(this);
		addNode(node);
		
	}
	
	@Override
	public JPopupMenu getContentMenu() {
		return mnuTree;
	}

	@Override
	public JPanel getContentPanel() {
		return pnlContent;
	}

	@Override
	public JTree getTree() {
		return getParent().getTree();
	}

	@Override
	public String toString() {
		return project.getName();
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	private JPopupMenu mnuTree;
	private JMenuItem mnuNewComponent;
	private JMenuItem mnuNewEnumComponent;
	
	private ProjectPanel pnlContent;
	private Project project;

}
