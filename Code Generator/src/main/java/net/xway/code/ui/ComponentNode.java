package net.xway.code.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import net.xway.code.model.Component;

public class ComponentNode extends AbstractTreeNode {

	private static int ComponentIndex = 0;
	
	private ComponentPanel pnlComponent;
	private Component component;
	
	public  ComponentNode(ProjectNode parentNode) {
		super(parentNode);
		component = new Component(parentNode.getProject(), "Component"+ ComponentIndex++ );
		parentNode.getProject().addComponent(component);
		initComponents();
	}
	
	public  ComponentNode(ProjectNode parentNode, Component c) {
		super(parentNode);
		this.component = c;
		initComponents();
		for (Component sub : c.getSubcomponent()) {
			ComponentNode node = new ComponentNode(this, sub);
			addNode(node);
		}
	}
	
	public ComponentNode(ComponentNode parentNode) {
		super(parentNode);
		component = new Component(parentNode.getComponent().getProject(), "Component"+ ComponentIndex++, parentNode.getComponent() );
		parentNode.getComponent().addSubComponent(component);
		initComponents();
	}
	
	public ComponentNode(ComponentNode parentNode, Component c) {
		super(parentNode);
		this.component = c;
		
		initComponents();
		
		for (Component sub : c.getSubcomponent()) {
			ComponentNode node = new ComponentNode(this, sub);
			addNode(node);
		}
	}
	
	public Component getComponent() {
		return component;
	}
	
	private void initComponents() {
		mnuNewComponent = new JMenuItem("New Component");
		mnuNewComponent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractTreeNode node = getParent();
				while (node instanceof ProjectNode == false) {
					node = node.getParent();
				}				
				((ProjectNode)node).newComponent();
			}
		});
		
		mnuNewSubComponent = new JMenuItem("New SubComponenet");
		mnuNewSubComponent.addActionListener(e-> { newSubComponent(); });
		
		mnuDeleteComponent = new JMenuItem("Delete Component");
		mnuDeleteComponent.addActionListener(e->{ 
			AbstractTreeNode node = getParent();
			if (node instanceof ComponentNode) {
				((ComponentNode)node).deleteSubComponent(this);
			}
			else if (node instanceof ProjectNode) {
				((ProjectNode)node).deleteComponent(this);
			}
		});
		
		// init popup menu
		mnuComponenet = new JPopupMenu();
		mnuComponenet.add(mnuNewComponent);
		mnuComponenet.add(mnuNewSubComponent);
		mnuComponenet.add(mnuDeleteComponent);

		// init content
		pnlComponent = new ComponentPanel(this, component);
	}

	public void newSubComponent() {
		ComponentNode node = new ComponentNode(this);
		addNode(node);
	}
	
	public void deleteSubComponent(ComponentNode node) {
		component.removeSubComponent(node.getComponent());
		deleteNode(node);
	}
	
	@Override
	public JPopupMenu getContentMenu() {
		return mnuComponenet;
	}

	@Override
	public JPanel getContentPanel() {
		return pnlComponent;
	}

	@Override
	public JTree getTree() {
		return getParent().getTree();
	}

	@Override
	public String toString() {
		return component.getName();
	}

	private JPopupMenu mnuComponenet;
	private JMenuItem mnuNewComponent;
	private JMenuItem mnuNewSubComponent;
	private JMenuItem mnuDeleteComponent;

}
