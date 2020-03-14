package net.xway.process.designer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ProjectDialog  extends Dialog<Integer> {

	private final int PAGESIZE = 15;
	private ResourceBundle bundle;
	private TableView<ProcessProject> tabProject;
	
	public ProjectDialog(Stage stage, ResourceBundle bundle) {
		this.bundle = bundle;
		setTitle(bundle.getString("Project.list"));
		
		int projectNum = 0;
		
		try (
			Connection conn = AppUtils.getConnection();
		)
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM XT_PROCESSDEFINITION");
			if (rs.next()) {
				projectNum = rs.getInt(1);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IOException e) {
			Alert a = new Alert (Alert.AlertType.ERROR );
			a.setTitle(bundle.getString("Project.list"));
			a.setHeaderText(e.toString());
			a.showAndWait();
		}
		

		TableColumn<ProcessProject, Boolean> colId = new TableColumn<>(bundle.getString("Choose"));
		colId.setCellFactory(CheckBoxTableCell.forTableColumn(colId));
		colId.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, Boolean>, ObservableValue<Boolean>>() {
		     public ObservableValue<Boolean> call(CellDataFeatures<ProcessProject, Boolean> p) {
		         return p.getValue().getChoose();
		     }
		});

		TableColumn<ProcessProject, String> colName = new TableColumn<>(bundle.getString("Process.name"));
		colName.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ProcessProject, String> p) {
		         return p.getValue().getName();
		     }
		});
		
		TableColumn<ProcessProject, String> colKey = new TableColumn<>(bundle.getString("Process.key"));
		colKey.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ProcessProject, String> p) {
		         return p.getValue().getKey();
		     }
		});
		
		TableColumn<ProcessProject, Number> colVersion = new TableColumn<>(bundle.getString("Process.version"));
		colVersion.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, Number>, ObservableValue<Number>>() {
		     public ObservableValue<Number> call(CellDataFeatures<ProcessProject, Number> p) {
		         return p.getValue().getVersion();
		     }
		});
		
		TableColumn<ProcessProject, String> colDescription = new TableColumn<>(bundle.getString("Process.description"));
		colDescription.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ProcessProject, String> p) {
		         return p.getValue().getDescription();
		     }
		});

		TableColumn<ProcessProject, ProcessStatus> colStatus = new TableColumn<>(bundle.getString("Process.status"));
		colStatus.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, ProcessStatus>, ObservableValue<ProcessStatus>>() {
		     public ObservableValue<ProcessStatus> call(CellDataFeatures<ProcessProject, ProcessStatus> p) {
		         return p.getValue().getStatus();
		     }
		});

		TableColumn<ProcessProject, String> colCreateTime = new TableColumn<>(bundle.getString("Process.createTime"));
		colCreateTime.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ProcessProject, String> p) {
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	 String val = sdf.format(p.getValue().getCreateTime().get());
		         return new SimpleStringProperty(val);
		     }
		});

		TableColumn<ProcessProject, String> colModifiedTime = new TableColumn<>(bundle.getString("Process.modifiedTime"));
		colModifiedTime.setCellValueFactory(new Callback<CellDataFeatures<ProcessProject, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ProcessProject, String> p) {
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	 String val = sdf.format(p.getValue().getModifiedTime().get());
		         return new SimpleStringProperty(val);
		     }
		});

		tabProject = new TableView<>();
		tabProject.setEditable(true);
		tabProject.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tabProject.getColumns().addAll(colId, colName, colKey, colVersion, colDescription, colStatus, colCreateTime, colModifiedTime);
		load(projectNum % PAGESIZE == 0 ? projectNum / PAGESIZE : projectNum / PAGESIZE + 1, 1);

		//
		Pagination p = new Pagination(projectNum);

		
		//
		VBox box = new VBox();
		box.getChildren().addAll(tabProject, p);
		
		getDialogPane().setContent(box);
		
		//
		ButtonType _btnLoad = new ButtonType(bundle.getString("Load"), ButtonData.YES);
		getDialogPane().getButtonTypes().addAll( _btnLoad, ButtonType.CLOSE);
		
		Button btnLoad = (Button)getDialogPane().lookupButton(_btnLoad);
		btnLoad.setDefaultButton(false);
		btnLoad.addEventFilter(ActionEvent.ACTION, l -> {
			for (ProcessProject project : tabProject.getItems()) {
				if (project.getChoose().get() == true) {
					setResult(project.getProcessDefinitionId().get());
				}
			}
			if (getResult() == null)
				l.consume();
		});
		setResultConverter(new Callback<ButtonType, Integer>() {

			@Override
			public Integer call(ButtonType param) {
				return getResult();
			}
			
		});
	}
	
	private void load(int pageIndex, int pageSize) {
		ArrayList<ProcessProject> projects = new ArrayList<>();
		
		try (
			Connection conn = AppUtils.getConnection();
		)
		{				
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM XT_PROCESSDEFINITION ORDER BY MODIFIEDTIME DESC ");
			while (rs.next()) {
				ProcessProject project = new ProcessProject ();
				project.setProcessDefinitionId(rs.getInt("PROCESSDEFINITIONID"));
				project.setName(rs.getString("NAME"));
				project.setKey(rs.getString("KEY"));
				project.setDescription(rs.getString("DESCRIPTION"));
				project.setVersion(rs.getInt("VERSION"));
				project.setStatus(ProcessStatus.valueOf(rs.getString("STATUS")));
				project.setCreateTime(rs.getTimestamp("CREATETIME"));
				project.setModifiedTime(rs.getTimestamp("MODIFIEDTIME"));
				projects.add(project);
			}
			rs.close();
			stmt.close();
			tabProject.setItems(FXCollections.observableArrayList(projects));
		}
		catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | IOException e) {
			Alert a = new Alert (Alert.AlertType.ERROR );
			a.setTitle(bundle.getString("Project.list"));
			a.setHeaderText(e.toString());
			a.showAndWait();
		}
	}
}

enum ProcessStatus {
	Draft, Deploy;
}

class ProcessProject {
	
	private SimpleIntegerProperty processDefinitionId = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty key = new SimpleStringProperty();
	private SimpleIntegerProperty version = new SimpleIntegerProperty();
	private SimpleStringProperty description = new SimpleStringProperty();
	private SimpleObjectProperty<ProcessStatus> status = new SimpleObjectProperty<>();
	private SimpleObjectProperty<Date> createTime = new SimpleObjectProperty<>();
	private SimpleObjectProperty<Date> modifiedTime = new SimpleObjectProperty<>();
	private SimpleBooleanProperty choose = new SimpleBooleanProperty(true);
	
	public SimpleIntegerProperty getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(int processDefinitionId) {
		this.processDefinitionId.set(processDefinitionId);
	}
	public SimpleStringProperty getName() {
		return name;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public SimpleStringProperty getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key.set(key);
	}
	public SimpleIntegerProperty getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version.set(version);
	}
	public SimpleStringProperty getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description.set(description);
	}
	public SimpleObjectProperty<ProcessStatus> getStatus() {
		return status;
	}
	public void setStatus(ProcessStatus status) {
		this.status.set(status);
	}
	public SimpleObjectProperty<Date> getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime.set(createTime);
	}
	public SimpleObjectProperty<Date> getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime.set(modifiedTime);
	}
	public SimpleBooleanProperty getChoose() {
		return choose;
	}
	public void setChoose(SimpleBooleanProperty choose) {
		this.choose = choose;
	}
	

	
}
