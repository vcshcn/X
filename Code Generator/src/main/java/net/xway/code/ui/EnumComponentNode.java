package net.xway.code.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import net.xway.code.model.EnumComponent;

public class EnumComponentNode  extends AbstractTreeNode {

	private static int EnumComponentIndex = 0;

	private EnumComponent enumComponent;
	
	public EnumComponentNode(ProjectNode parentNode) {
		super(parentNode);
		enumComponent = new EnumComponent(parentNode.getProject(), "Enum"+EnumComponentIndex++);
		parentNode.getProject().addEnumComponent(enumComponent);
		
		initComponents();
	}
	
	public EnumComponentNode(ProjectNode parentNode, EnumComponent c) {
		super(parentNode);
		this.enumComponent = c;
		
		initComponents();
	}
	
	private void initComponents() {
		mnuNewEnumComponent = new JMenuItem("New Enum");
		mnuNewEnumComponent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((ProjectNode)getParent()).newEnumComponent();
			}
			
		});

		// init popup menu
		mnuEnumComponent = new JPopupMenu();
		mnuEnumComponent.add(mnuNewEnumComponent);
	
		// init content
		pnlEnumComponent = new EnumComponentPanel(this, enumComponent);

	}

	@Override
	public JPopupMenu getContentMenu() {
		return mnuEnumComponent;
	}

	@Override
	public JPanel getContentPanel() {
		return pnlEnumComponent;
	}

	@Override
	public JTree getTree() {
		return getParent().getTree();
	}

	@Override
	public String toString() {
		return enumComponent.getName();
	}

	private JPopupMenu mnuEnumComponent;
	private JMenuItem mnuNewEnumComponent;

	private EnumComponentPanel pnlEnumComponent;
}
