/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer;

import java.sql.Connection;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 *
 * @author c
 */
public class ProcessDiagramApp extends Application implements IReloadHandle, EventHandler<ActionEvent> {
	
    private Scene scene;
    private Stage stage;
    
    private MenuBar menuBar ;
    private Menu mnuFile;
    private MenuItem itmNew;
    private MenuItem itmOpen;
    private MenuItem itmSave;
    private MenuItem itmImport;
    private MenuItem itmExport;
    private MenuItem itmExportXML;
    private MenuItem itmExit;
    private Menu mnuLanguage;
    private CheckMenuItem mnuEnglish;
    private CheckMenuItem mnuChinese;
    private CheckMenuItem mnuTraditional;
    private CheckMenuItem mnuJapanese;
    private Menu mnuOptions;
    private MenuItem itmDatabase;
    private Menu mnuHelp;
    private MenuItem itmAbout;
    
    private ToolBar barTools;
    private Button btnNew;
    private Button btnOpen;
    private Button btnSave;
    private Button btnImport;
    private Button btnExport;
    private Button btnXMLExport;
    
    private ToolsPane toolsPane;
    private ProcessDefinitionGroupPane groupPane; 
    private ResourceBundle bundle;
    
    @Override
    public void init() {
        itmNew = new MenuItem();
        itmOpen = new MenuItem();
        itmSave = new MenuItem();
        itmImport = new MenuItem();
        itmExport = new MenuItem();
        itmExportXML = new MenuItem();
        itmExit = new MenuItem();
        itmDatabase = new MenuItem();
        itmAbout = new MenuItem();
        mnuFile = new Menu();
        mnuFile.getItems().addAll(itmNew, itmOpen, itmSave, new SeparatorMenuItem(),itmImport,itmExport,itmExportXML, new SeparatorMenuItem(), itmExit );
        mnuEnglish = new CheckMenuItem();
        mnuEnglish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	GlobalConfigure.setLocale(Locale.US);
                triggerOnLocaleChange(GlobalConfigure.getLocale());
           }
        });
        mnuChinese = new CheckMenuItem();
        mnuChinese.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	GlobalConfigure.setLocale(Locale.SIMPLIFIED_CHINESE);
                triggerOnLocaleChange(GlobalConfigure.getLocale());
           }
        });
        mnuTraditional = new CheckMenuItem();
        mnuTraditional.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	GlobalConfigure.setLocale(Locale.TRADITIONAL_CHINESE);
                triggerOnLocaleChange(GlobalConfigure.getLocale());
           }
        });
        mnuJapanese = new CheckMenuItem();
        mnuJapanese.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	GlobalConfigure.setLocale(Locale.JAPAN);
                triggerOnLocaleChange(GlobalConfigure.getLocale());
           }
        });
        itmDatabase.setOnAction(this);

        mnuLanguage = new Menu();
        mnuLanguage.getItems().addAll(mnuChinese,mnuTraditional,mnuEnglish,mnuJapanese );
        mnuHelp = new Menu();
        mnuOptions = new Menu();
        mnuOptions.getItems().addAll(itmDatabase);
        mnuHelp.getItems().add(itmAbout);
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(mnuFile,mnuLanguage,mnuOptions,mnuHelp );
        
        btnNew = new Button();
        btnNew.setOnAction(this);
        btnOpen = new Button();
        btnOpen.setOnAction(this);
        btnSave = new Button();
        btnSave.setOnAction(this);
        btnExport = new Button();
        btnImport = new Button();
        btnXMLExport = new Button("XML");
        btnXMLExport.setOnAction(this);
        barTools = new ToolBar(btnNew, btnOpen, btnSave, new Separator(), btnImport, btnExport,  btnXMLExport);
        
        toolsPane = new ToolsPane();
        groupPane = new ProcessDefinitionGroupPane();
                
        SplitPane cetPane = new SplitPane(toolsPane, groupPane);
        cetPane.setDividerPositions(0.1f);
        
        Pane satPane = new Pane();
        
        BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar, barTools));
        root.setCenter(cetPane);
        root.setBottom(satPane);
        
        scene = new Scene(root);

    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(scene);
        stage.setWidth(Screen.getPrimary().getBounds().getWidth() * 2 /3);
        stage.setHeight(Screen.getPrimary().getBounds().getHeight() *2/3);
        triggerOnLocaleChange( GlobalConfigure.getLocale() );
        stage.show();
    }
    
    @Override
	public void handle(ActionEvent event) {
    	if (event.getSource() == btnNew) {
    		groupPane.createDiagramPane();
    		groupPane.onReloadFinish(bundle);
    	}
    	else if (event.getSource() == btnXMLExport) {
			String xml = groupPane.toXML();
			Dialog<Void> dlg = new XMLDialog(xml, bundle);
			dlg.showAndWait();
		}
    	else if (event.getSource() == itmDatabase) {
    		Dialog<Void> dlg = new DatabaseDialog(stage, bundle);
    		dlg.showAndWait();
    	}
    	else if (event.getSource() == btnOpen ) {
    		ProjectDialog dlg = new ProjectDialog(stage, bundle);
    		Optional<Integer> id = dlg.showAndWait();
    		
    		if (id.isPresent()) {
        		Connection conn = null;
        		try {
    	    		try {
    	    			conn = AppUtils.getConnection();
    	       			groupPane.load(conn, id.get());
    	    		}
    	    		catch (Exception e) {
    	    			e.printStackTrace();
    	    			if (conn != null)
    	    				conn.rollback();
    	    			throw e;
    				} 
    	    		finally {
    	    			if (conn != null)
    	    				conn.close();
    	    		}
        		}
        		catch (Exception e) {
    				Alert a = new Alert (Alert.AlertType.ERROR );
    				a.setTitle(bundle.getString("Load"));
    				a.setHeaderText(e.toString());
    				a.showAndWait();
        		}
     		}
    	}
    	else if (event.getSource() == btnSave) {
    		
    		Connection conn = null;
    		try {
	    		try {
	    			conn = AppUtils.getConnection();
	    			conn.setAutoCommit(false);
	    			groupPane.save(conn);
	    			conn.commit();
					Alert a = new Alert (Alert.AlertType.INFORMATION);
					a.setTitle( bundle.getString("Save"));
					a.setHeaderText(bundle.getString("Save") + " " +bundle.getString("Success"));
					a.showAndWait();
	    		}
	    		catch (Exception e) {
	    			if (conn != null)
	    				conn.rollback();
	    			throw e;
				} 
	    		finally {
	    			if (conn != null)
	    				conn.close();
	    		}
    		}
    		catch (Exception e) {
    			e.printStackTrace();
				Alert a = new Alert (Alert.AlertType.ERROR );
				a.setTitle(bundle.getString("Save"));
				a.setHeaderText(e.toString());
				a.showAndWait();
    		}
    	}
	}

	private void triggerOnLocaleChange(Locale locale) {
        mnuEnglish.setSelected(false);
        mnuChinese.setSelected(false);
        mnuTraditional.setSelected(false);
        mnuJapanese.setSelected(false);
        if (Locale.US.equals(locale)) {
            mnuEnglish.setSelected(true);
        }
        else if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
            mnuChinese.setSelected(true);
        }
        else if (Locale.TRADITIONAL_CHINESE.equals(locale)) {
        	mnuTraditional.setSelected(true);
        }
        else if (Locale.JAPAN.equals(locale)) {
        	mnuJapanese.setSelected(true);
        }
        
        ResourceBundle rb = ResourceBundle.getBundle("META-INF/resource", locale);
        onReloadFinish(rb);
    }
    
    @Override
    public void onReloadFinish(ResourceBundle rb) {
    	this.bundle = rb;
        stage.setTitle( rb.getString("Stage.Title") );
        mnuFile.setText( rb.getString("Menu.Project") );
        mnuHelp.setText( rb.getString("Menu.Help") );
        mnuOptions.setText( rb.getString("Menu.Options"));
        mnuLanguage.setText( rb.getString("Menu.Language"));
        itmNew.setText( rb.getString("Menu.New") );
        itmOpen.setText( rb.getString("Menu.Open") );
        itmSave.setText( rb.getString("Menu.Save") );
        itmImport.setText( rb.getString("Menu.Import") );
        itmExport.setText( rb.getString("Menu.Export") );
        itmExportXML.setText(rb.getString("Menu.Export") + " XML");
        itmExit.setText( rb.getString("Menu.Exit") );
        mnuEnglish.setText( rb.getString("Menu.English") );
        mnuChinese.setText( rb.getString("Menu.Chinese") );
        mnuTraditional.setText( rb.getString("Menu.Traditional") );
        mnuJapanese.setText(rb.getString("Menu.Japanese"));
        itmDatabase.setText(rb.getString("Menu.Database"));
        itmAbout.setText( rb.getString("Menu.About") );

        btnNew.setText(  rb.getString("Menu.New") );
        btnOpen.setText(  rb.getString("Menu.Open") );
        btnSave.setText(  rb.getString("Menu.Save") );
        btnExport.setText(  rb.getString("Menu.Export") );
        btnImport.setText(  rb.getString("Menu.Import") );
        
        toolsPane.onReloadFinish(rb);
        groupPane.onReloadFinish(rb);
    }

}
