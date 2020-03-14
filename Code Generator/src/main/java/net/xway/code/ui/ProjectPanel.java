package net.xway.code.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.xway.code.model.Project;

public class ProjectPanel extends JPanel {

	private JPanel pnlTop;
	private JLabel lblProjectName;
	private JTextField txtProjectName;
	private JLabel lblBasePackage;
	private JTextField txtBasePackage;
	private JLabel lblPackageName;
	private JTextField txtPackageName;
	private JLabel lblAuthor;
	private JTextField txtAuthor;
	private JLabel lblCreateDate;
	private JTextField txtCreateDate;
	private JLabel lblTablePrefix;
	private JTextField txtTablePrefix;
	private JLabel lblTableSuffix;
	private JTextField txtTableSuffix;
	private JLabel lblProjectComment;
	private JTextArea txtProjectComment;
	private JButton btnOK;
	private JButton btnCancel;
	
	private final ProjectNode leftNode;
	private Project project;

	public ProjectPanel(ProjectNode leftNode, Project project) {
		this.leftNode = leftNode;
		this.project = project;
		initComponents();
		updateUIFromModel();
	}
	
	private void initComponents() {
		lblProjectName = new JLabel("Project Name");
		lblBasePackage = new JLabel("Base Package");
		lblPackageName = new JLabel("Package Name");
		lblAuthor = new JLabel("Author");
		lblCreateDate = new JLabel("Create Date");
		lblTablePrefix = new JLabel("Table Prefix");
		lblTableSuffix = new JLabel("Table Suffix");
		lblProjectComment = new JLabel("Project Comment");
		txtProjectName = new JTextField(20);
		txtBasePackage = new JTextField(20);
		txtPackageName = new JTextField(20);
		txtPackageName.setEnabled(false);
		txtAuthor = new JTextField(20);
		txtCreateDate = new JTextField(20);
		txtTablePrefix = new JTextField(20);
		txtTableSuffix = new JTextField(20);
		txtProjectComment = new JTextArea(5, 20);
		txtProjectComment.setBorder(txtPackageName.getBorder());
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateModelFromUI();
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUIFromModel();
			}
		});
		
		pnlTop = new JPanel();
		GroupLayout layout = new GroupLayout(pnlTop);
		pnlTop.setLayout(layout);
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.setHorizontalGroup(
	    	layout.createSequentialGroup()
	    	.addGroup(
	    		layout.createParallelGroup().addComponent(lblProjectName).addComponent(lblBasePackage).addComponent(lblPackageName).addComponent(lblProjectComment).addComponent(lblAuthor).addComponent(lblCreateDate).addComponent(lblTablePrefix).addComponent(lblTableSuffix)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(txtProjectName).addComponent(txtBasePackage).addComponent(txtPackageName).addComponent(txtProjectComment).addComponent(txtAuthor).addComponent(txtCreateDate).addComponent(txtTablePrefix).addComponent(txtTableSuffix)
	    		.addGroup(layout.createSequentialGroup().addComponent(btnOK).addComponent(btnCancel)	)
	    	)
	    );
	    
	    layout.setVerticalGroup(
	    	layout.createSequentialGroup().addGroup(
	    		layout.createParallelGroup().addComponent(lblProjectName).addComponent(txtProjectName)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblBasePackage).addComponent(txtBasePackage)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblPackageName).addComponent(txtPackageName)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblAuthor).addComponent(txtAuthor)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblCreateDate).addComponent(txtCreateDate)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblTablePrefix).addComponent(txtTablePrefix)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblTableSuffix).addComponent(txtTableSuffix)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(lblProjectComment).addComponent(txtProjectComment)
	    	).addGroup(
	    		layout.createParallelGroup().addComponent(btnOK).addComponent(btnCancel)
	    	)
	    );

		setLayout(new FlowLayout());
		add(pnlTop);
		
	}
	
	private void updateModelFromUI() {
		project.setName(txtProjectName.getText());
		project.setBasePackage(txtBasePackage.getText());
		project.setComment(txtProjectComment.getText());
		project.setAuthor(txtAuthor.getText());
		project.setCreatedate(txtCreateDate.getText());
		project.setTablePrefix(txtTablePrefix.getText());
		project.setTableSuffix(txtTableSuffix.getText());
		
		txtPackageName.setText(project.getPackageName());
		leftNode.nodeChanged();
	}
	
	private void updateUIFromModel() {
		txtProjectName.setText(project.getName());
		txtBasePackage.setText(project.getBasePackage());
		txtPackageName.setText(project.getPackageName());
		txtProjectComment.setText(project.getComment());
		txtAuthor.setText(project.getAuthor());
		txtCreateDate.setText(project.getCreatedate());
		txtTablePrefix.setText(project.getTablePrefix());
		txtTableSuffix.setText(project.getTableSuffix());
	}

}
