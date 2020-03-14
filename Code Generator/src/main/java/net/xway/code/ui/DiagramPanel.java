package net.xway.code.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import net.xway.code.model.Group;

public class DiagramPanel extends JPanel {

	private final Group group;
	
	public DiagramPanel(Group group) {
		this.group = group;
		
		initComponents();
	}
	
	public void initComponents() {
		
		setLayout(new BorderLayout());
		
		btnLayout = new JButton("Auto Layout");
		btnSave = new JButton("Save As Image");
		
		barTools = new JToolBar();
		barTools.add(btnLayout);
		barTools.add(btnSave);
		
		barTools.setFloatable(false);
		add(barTools, BorderLayout.NORTH);
		
		canvas = new DiagramCanvas(group);
		add(canvas, BorderLayout.CENTER);
	}
	
	private DiagramCanvas canvas;
	private JButton btnLayout;
	private JButton btnSave;
	private JToolBar barTools;
}
