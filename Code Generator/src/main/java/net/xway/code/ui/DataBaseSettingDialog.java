package net.xway.code.ui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.xway.code.config.Config;
import net.xway.code.database.impl.MySQLDataBase;
import net.xway.code.database.impl.SQLServerDataBase;

public class DataBaseSettingDialog extends JDialog {

	private JLabel lblType;
	private JLabel lblJar;
	private JLabel lblClass;
	private JLabel lblUrl;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JComboBox<String> cboType;
	private JTextField txtJar;
	private JTextField txtClass;
	private JTextField txtUrl;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnReset;
	private JButton btnDirectory;
	private JButton btnTest;
	private JButton btnOK;
	private JButton btnCancel;
	
	public DataBaseSettingDialog(JFrame frame) {
		super(frame, "DataBase Setting", true);
		
		initComponents();
		updateModelToUI();
	}
	
	private void initComponents() {
		lblType = new JLabel("DataBase");
		lblJar = new JLabel("Jdbc JAR");
		lblClass = new JLabel("Jdbc Driver");
		lblUrl = new JLabel("Jdbc URL");
		lblUser = new JLabel("User Name");
		lblPassword = new JLabel("Password");
		cboType = new JComboBox<String>(new String[] {MySQLDataBase.NAME, SQLServerDataBase.NAME});
		txtJar = new JTextField(20);
		txtClass = new JTextField(20);
		txtUrl = new JTextField(20);
		txtUser = new JTextField(20);
		txtPassword = new JPasswordField(20);
		btnDirectory = new JButton("...");
		btnDirectory.addActionListener((ActionEvent e)->{
			FileDialog dlgOpen = new FileDialog(this, "Find " + cboType.getSelectedItem() + " JAR", FileDialog.LOAD);
			dlgOpen.setFile("*.jar");
			dlgOpen.setVisible(true);
			String filename = dlgOpen.getFile();
			if (filename != null) {
				txtJar.setText(dlgOpen.getDirectory() + dlgOpen.getFile());
			}

		});
		btnReset = new JButton("Reset");
		btnReset.addActionListener((ActionEvent e)->{
			if (cboType.getSelectedItem().equals(MySQLDataBase.NAME)) {
				txtClass.setText("com.mysql.jdbc.Driver");
				txtUrl.setText("jdbc:mysql://localhost:3306/XWAY");
				txtUser.setText("xway");
				txtPassword.setText("xway");
			}
			else if (cboType.getSelectedItem().equals(SQLServerDataBase.NAME)) {
				txtClass.setText("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				txtUrl.setText("jdbc:sqlserver://localhost:1433;databaseName=XWAY;");
				txtUser.setText("xway");
				txtPassword.setText("xway");
			}
		});
		btnTest = new JButton("Test");
		btnTest.addActionListener((ActionEvent e)-> {test();});
		btnOK = new JButton("OK");
		btnOK.addActionListener((ActionEvent e)-> {
			updateUIToModel();
			dispose();
			
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener((ActionEvent e)->{ dispose(); });
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		
		layout.setHorizontalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup().addComponent(lblType).addComponent(lblJar).addComponent(lblClass).addComponent(lblUrl).addComponent(lblUser).addComponent(lblPassword)
			).addGroup(
				layout.createParallelGroup().addGroup(layout.createSequentialGroup().addComponent(cboType).addComponent(btnReset)).addGroup(layout.createSequentialGroup().addComponent(txtJar).addComponent(btnDirectory)).addComponent(txtClass).addComponent(txtUrl).addComponent(txtUser).addComponent(txtPassword)
				.addGroup(layout.createSequentialGroup().addComponent(btnTest).addComponent(btnOK).addComponent(btnCancel))
			)
		);
		
		layout.setVerticalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup().addComponent(lblType).addComponent(cboType).addComponent(btnReset)
			).addGroup(
				layout.createParallelGroup().addComponent(lblJar).addComponent(txtJar).addComponent(btnDirectory)
			).addGroup(
				layout.createParallelGroup().addComponent(lblClass).addComponent(txtClass)
			).addGroup(
				layout.createParallelGroup().addComponent(lblUrl).addComponent(txtUrl)
			).addGroup(
				layout.createParallelGroup().addComponent(lblUser).addComponent(txtUser)
			).addGroup(
				layout.createParallelGroup().addComponent(lblPassword).addComponent(txtPassword)
			).addGroup(
				layout.createParallelGroup().addComponent(btnTest).addComponent(btnOK).addComponent(btnCancel)
			)
		);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void updateModelToUI() {
		Config config = Config.getInstance();
		cboType.setSelectedItem(config.getCurrentDataBase().getName());
		txtJar.setText(config.getJar());
		txtClass.setText(config.getDriver());
		txtUrl.setText(config.getUrl());
		txtUser.setText(config.getUsername());
		txtPassword.setText(config.getPassword());
	}
	
	private void updateUIToModel() {
		Config config = Config.getInstance();
		config.setCurrentDataBase((String)cboType.getSelectedItem()); 
		config.setJar(txtJar.getText());
		config.setDriver(txtClass.getText());
		config.setUrl(txtUrl.getText());
		config.setUsername(txtUser.getText());
		config.setPassword(new String(txtPassword.getPassword()));
	}

	
	private void test() {
		
		try {
			Config.testConnection(txtJar.getText(), txtClass.getText(), txtUrl.getText(), txtUser.getText(), new String(txtPassword.getPassword()));
			JOptionPane.showMessageDialog(this, "Connection Success.", "Success",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Fail",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
