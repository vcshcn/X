package net.xway.code.ui;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.xway.code.config.Config;

public class ProjectSettingDialog extends JDialog {

	private JLabel lblStereotype;
	private JLabel lblBasetPath;
	private JLabel lblSourcePath;
	private JLabel lblWebappPath;
	private JLabel lblSqlPath;
	private JLabel lblDocPath;
	private JComboBox<String> cboStereotype;
	private JTextField txtBasePath;
	private JTextField txtSourcePath;
	private JTextField txtWebappPath;
	private JTextField txtSqlPath;
	private JTextField txtDocPath;
	private JButton btnBasePath;
	private JButton btnSourcePath;
	private JButton btnWebappPath;
	private JButton btnSqlPath;
	private JButton btnDocPath;
	private JButton btnOK;
	private JButton btnClear;
	private JButton btnCancel;
	
	public ProjectSettingDialog(JFrame frame) {
		super(frame, "Project Setting", true);
		
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		lblStereotype = new JLabel("Project Stereotype");
		lblBasetPath = new JLabel("Project Base Path");
		lblSourcePath = new JLabel("Project Java Source Path");
		lblWebappPath = new JLabel("Project Webapp Path");
		lblSqlPath = new JLabel("Project SQL Path");
		lblDocPath = new JLabel("Project Docment Path");
		cboStereotype = new JComboBox<>(new String[] {"maven", "netbean"});
		txtBasePath = new JTextField(20);
		txtBasePath.setEditable(false);
		txtBasePath.setText(Config.getInstance().getBaseDirectory());
		txtSourcePath = new JTextField(20);
		txtSourcePath.setEditable(false);
		txtSourcePath.setText(Config.getInstance().getJavaDirectory());
		txtWebappPath = new JTextField(20);
		txtWebappPath.setEditable(false);
		txtWebappPath.setText(Config.getInstance().getWebDirectory());
		txtSqlPath = new JTextField(20);
		txtSqlPath.setEditable(false);
		txtSqlPath.setText(Config.getInstance().getSqlDirectory());
		txtDocPath = new JTextField(20);
		txtDocPath.setEditable(false);
		txtDocPath.setText(Config.getInstance().getDocDirectory());
		txtWebappPath.setText(Config.getInstance().getWebDirectory());
		btnBasePath = new JButton("...");
		btnBasePath.addActionListener((ActionEvent e)->{
			JFileChooser chooser = new JFileChooser ();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if ( chooser.showDialog(this,"Choose Directory") == JFileChooser.APPROVE_OPTION) {
				String basePath = chooser.getSelectedFile().getAbsolutePath();
				txtBasePath.setText( basePath );
				if ("maven".equals(cboStereotype.getSelectedItem())) {
					
					if (txtSourcePath.getText().length() == 0) {
						txtSourcePath.setText(basePath + File.separator + "src" + File.separator + "main"+ File.separator + "java");
					}
					if (txtWebappPath.getText().length() == 0) {
						txtWebappPath.setText(basePath + File.separator + "src" + File.separator + "main"+ File.separator + "webapp");
					}
					if (txtSqlPath.getText().length() == 0) {
						txtSqlPath.setText(basePath + File.separator + "src" + File.separator + "dev" + File.separator + "sql");
					}
					if (txtDocPath.getText().length() == 0) {
						txtDocPath.setText(basePath + File.separator + "src" + File.separator + "dev" + File.separator + "doc");
					}
				}
			}
		});
		btnSourcePath = new JButton("...");
		btnSourcePath.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser (txtBasePath.getText());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if ( chooser.showDialog(this,"Choose Directory") == JFileChooser.APPROVE_OPTION) {
				txtSourcePath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnWebappPath = new JButton("...");
		btnWebappPath.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser (txtBasePath.getText());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if ( chooser.showDialog(this,"Choose Directory") == JFileChooser.APPROVE_OPTION) {
				txtWebappPath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnSqlPath = new JButton("...");
		btnSqlPath.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser (txtBasePath.getText());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if ( chooser.showDialog(this,"Choose Directory") == JFileChooser.APPROVE_OPTION) {
				txtSqlPath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnDocPath = new JButton("...");
		btnDocPath.addActionListener(e-> {
			JFileChooser chooser = new JFileChooser (txtBasePath.getText());
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if ( chooser.showDialog(this,"Choose Directory") == JFileChooser.APPROVE_OPTION) {
				txtDocPath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnOK = new JButton("OK");
		btnOK.addActionListener(e->{
			Config.getInstance().setBaseDirectory(txtBasePath.getText());
			Config.getInstance().setJavaDirectory(txtSourcePath.getText());
			Config.getInstance().setWebDirectory(txtWebappPath.getText());
			Config.getInstance().setSqlDirectory(txtSqlPath.getText());
			Config.getInstance().setDocDirectory(txtDocPath.getText());
			 dispose();
		});
		btnClear = new JButton("Clear");
		btnClear.addActionListener(e->{
			txtBasePath.setText("");
			txtSourcePath.setText("");
			txtWebappPath.setText("");
			txtSqlPath.setText("");
			txtDocPath.setText("");
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener((ActionEvent e)->{ dispose(); });
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup().addComponent(lblStereotype).addComponent(lblBasetPath).addComponent(lblSourcePath).addComponent(lblWebappPath).addComponent(lblSqlPath).addComponent(lblDocPath)
			).addGroup(
				layout.createParallelGroup().addComponent(cboStereotype).addComponent(txtBasePath).addComponent(txtSourcePath).addComponent(txtWebappPath).addComponent(txtSqlPath).addComponent(txtDocPath)
				.addGroup(
					layout.createSequentialGroup().addComponent(btnOK).addComponent(btnClear).addComponent(btnCancel)
				)
			).addGroup(
				layout.createParallelGroup().addComponent(btnBasePath).addComponent(btnSourcePath).addComponent(btnWebappPath).addComponent(btnSqlPath).addComponent(btnDocPath)
			)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup()
			.addGroup(
				layout.createParallelGroup().addComponent(lblStereotype).addComponent(cboStereotype)
			).addGroup(
				layout.createParallelGroup().addComponent(lblBasetPath).addComponent(txtBasePath).addComponent(btnBasePath)
			).addGroup(
				layout.createParallelGroup().addComponent(lblSourcePath).addComponent(txtSourcePath).addComponent(btnSourcePath)
			).addGroup(
				layout.createParallelGroup().addComponent(lblWebappPath).addComponent(txtWebappPath).addComponent(btnWebappPath)
			).addGroup(
				layout.createParallelGroup().addComponent(lblSqlPath).addComponent(txtSqlPath).addComponent(btnSqlPath)
			).addGroup(
				layout.createParallelGroup().addComponent(lblDocPath).addComponent(txtDocPath).addComponent(btnDocPath)
			).addGroup(
				layout.createParallelGroup().addComponent(btnOK).addComponent(btnClear).addComponent(btnCancel)
			)
		);
		
		pack();
		setLocationRelativeTo(null);
	}
}
