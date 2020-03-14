package net.xway.process.designer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatabaseDialog  extends Dialog<Void> implements ChangeListener<Object> {

	private Label lblPath;
	private TextField txtPath;
	private Button btnPath;
	private Label lblType;
	private ComboBox<String> cboType;
	private Label lblDriver;
	private TextField txtDriver;
	private Label lblHost;
	private TextField txtHost;
	private Label lblPort;
	private Spinner<Integer> txtPort;
	private Label lblUser;
	private TextField txtUser;
	private Label lblPassword;
	private TextField txtPassword;
	private Label lblName;
	private TextField txtName;
	private Label lblURL;
	private TextField txtURL;
	
	public DatabaseDialog(Stage stage, ResourceBundle bundle) {
		setTitle(bundle.getString("Database") + " "+ bundle.getString("Config"));

		lblType = new Label(bundle.getString("Type"));
		cboType = new ComboBox<>(FXCollections.observableArrayList(GlobalConfigure.getJdbcAllTypes()));
		cboType.setOnAction( l -> {
			populate();
		});
		cboType.getSelectionModel().select(0);
		lblPath = new Label("JAR "+bundle.getString("Path"));
		txtPath = new TextField();
		txtPath.setPrefColumnCount(25);
		btnPath = new Button("...");
		btnPath.setOnAction(l-> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(cboType.getValue() + " JAR");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR", "*.jar"));
			File file = fileChooser.showOpenDialog(stage);
			if (file != null)
				txtPath.setText(file.getAbsolutePath());
			l.consume();
		});
		lblDriver = new Label( bundle.getString("Driver") );
		txtDriver = new TextField();
		lblHost = new Label(bundle.getString("Host"));
		txtHost = new TextField();
		lblPort = new Label(bundle.getString("Port"));
		txtPort = new Spinner<Integer>(0, 65535, 0);
		txtPort.setEditable(true);
		lblUser = new Label(bundle.getString("User"));
		txtUser = new TextField();
		lblPassword = new Label(bundle.getString("Password"));
		txtPassword = new TextField();
		lblName = new Label(bundle.getString("Database"));
		txtName = new TextField();
		lblURL = new Label("URL");
		txtURL = new TextField();
		txtURL.setEditable(false);

		txtHost.textProperty().addListener((ChangeListener<? super String>) this);
		txtPort.valueProperty().addListener((ChangeListener<? super Integer>) this);
		txtName.textProperty().addListener((ChangeListener<? super String>) this);
		
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.add(lblType, 0, 0);
		pane.add(cboType, 1, 0);
		pane.add(lblPath, 0, 1);
		pane.add(txtPath, 1, 1);
		pane.add(btnPath, 2, 1);
		pane.add(lblDriver, 0, 2);
		pane.add(txtDriver, 1, 2);
		pane.add(lblHost, 0, 3);
		pane.add(txtHost, 1, 3);
		pane.add(lblPort, 0, 4);
		pane.add(txtPort, 1, 4);
		pane.add(lblUser, 0, 5);
		pane.add(txtUser, 1, 5);
		pane.add(lblPassword, 0, 6);
		pane.add(txtPassword, 1, 6);
		pane.add(lblName, 0, 7);
		pane.add(txtName, 1, 7);
		pane.add(lblURL, 0, 8);
		pane.add(txtURL, 1, 8);

		getDialogPane().setContent(pane);
		
		ButtonType _btnTest = new ButtonType("Ping", ButtonData.YES);
		ButtonType _btnSave = new ButtonType(bundle.getString("Save"), ButtonData.YES);
		ButtonType _btnClose = new ButtonType(bundle.getString("Close"), ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(_btnTest, _btnSave, _btnClose);
		
		Button btnSave = (Button)getDialogPane().lookupButton(_btnSave);
		btnSave.setDefaultButton(false);
		btnSave.addEventFilter(ActionEvent.ACTION, l -> {
			String jdbcType = cboType.getValue();
			
			GlobalConfigure.setJdbcType(jdbcType);
			GlobalConfigure.setJdbcDriverPath(jdbcType, txtPath.getText());
			GlobalConfigure.setJdbcDriver(jdbcType, txtDriver.getText());
			GlobalConfigure.setJdbcDriverHost(jdbcType, txtHost.getText());
			GlobalConfigure.setJdbcDriverPort(jdbcType, txtPort.getValue());
			GlobalConfigure.setJdbcDriverUser(jdbcType, txtUser.getText());
			GlobalConfigure.setJdbcDriverPassword(jdbcType, txtPassword.getText());
			GlobalConfigure.setJdbcDriverDatabase(jdbcType, txtName.getText());
			try {
				GlobalConfigure.saveFile();
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle( bundle.getString("Save"));
				a.setHeaderText(bundle.getString("Save") + " " +bundle.getString("Success"));
				a.showAndWait();
			} 
			catch (IOException e) {
				Alert a = new Alert (Alert.AlertType.ERROR );
				a.setTitle(bundle.getString("Save"));
				a.setHeaderText(e.toString());
				a.showAndWait();
			}
			l.consume();
		});
		
		Button btnTest = (Button)getDialogPane().lookupButton(_btnTest);
		btnTest.setDefaultButton(false);
		btnTest.addEventFilter(ActionEvent.ACTION, l -> {
			try (
				Connection conn = AppUtils.getConnection(txtPath.getText(), txtDriver.getText(), txtURL.getText(), txtUser.getText(), txtPassword.getText());
			)
			{
				Statement stmt = conn.createStatement();
				stmt.executeQuery(GlobalConfigure.getJdbcDriverTestSQL(GlobalConfigure.getJdbcType()));
				stmt.close();
				stmt = null;
				Alert a = new Alert (Alert.AlertType.INFORMATION);
				a.setTitle("Ping");
				a.setHeaderText(bundle.getString("Success"));
				a.showAndWait();
			}
			catch (Exception e) {
				Alert a = new Alert (Alert.AlertType.ERROR );
				a.setTitle("Ping");
				a.setHeaderText(e.toString());
				a.showAndWait();
			}
			l.consume();
		});
		Button btnClose = (Button)getDialogPane().lookupButton(_btnClose);
		btnClose.setDefaultButton(true);
		btnClose.setCancelButton(true);
		populate();
	}

	private void populate() {
		String type = cboType.getValue();
		txtPath.setText(GlobalConfigure.getJdbcDriverPath(type));
		txtDriver.setText(GlobalConfigure.getJdbcDriver(type));
		txtHost.setText(GlobalConfigure.getJdbcDriverHost(type));
		txtPort.getValueFactory().setValue(GlobalConfigure.getJdbcDriverPort(type));
		txtUser.setText(GlobalConfigure.getJdbcDriverUser(type));
		txtPassword.setText(GlobalConfigure.getJdbcDriverPassword(type));
		txtName.setText(GlobalConfigure.getJdbcDriverDatabase(type));
		String url = String.format(GlobalConfigure.getJdbcDriverURLTemplate(cboType.getValue()), txtHost.getText(), txtPort.getValue(), txtName.getText());
		txtURL.setText(url);
	}
	
	
	@Override
	public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
		String url = String.format(GlobalConfigure.getJdbcDriverURLTemplate(cboType.getValue()), txtHost.getText(), txtPort.getValue(), txtName.getText());
		txtURL.setText(url);
	}

}
