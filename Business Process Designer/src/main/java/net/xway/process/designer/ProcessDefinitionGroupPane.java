package net.xway.process.designer;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import net.xway.process.designer.node.ProcessDefinitionPane;

public class ProcessDefinitionGroupPane extends BorderPane  implements IReloadHandle, IXMLString, IElementSerializable {
	
	public final String XMLNS_XSI = "http://www.w3.org/2001/XMLSchema-instance";
	public final String XSI_SCHEMALOCATION = "http://schema.omg.org/spec/BPMN/2.0 BPMN20.xsd";
	public final String XMLNS = "http://schema.omg.org/spec/BPMN/2.0";
	public final String TYPELANGUAGE = "http://www.w3.org/2001/XMLSchema";
	public final String EXPRESSIONLANGUAGE = "http://www.w3.org/1999/XPath";
	public final String TARGETNAMESPACE = "http://xway.org/bpmn";

	protected StringProperty id = new SimpleStringProperty("ProcessDefinition0");
	private ResourceBundle bundle;
	private TabPane tabPane;
	
	public ProcessDefinitionGroupPane() {    
		tabPane = new TabPane(); 
		setCenter(tabPane);
		createDiagramPane();
	}

	public ProcessDefinitionPane createDiagramPane() {
		ProcessDefinitionPane tab = new ProcessDefinitionPane(this);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
        return tab;
	}

	@Override
	public void onReloadFinish(ResourceBundle bundle) {
		this.bundle = bundle;
		for (Tab tab : tabPane.getTabs()) {
			ProcessDefinitionPane p = (ProcessDefinitionPane) tab;
			p.onReloadFinish(bundle);
		}
	}
	
	@Override
	public void save(Connection conn) throws SQLException {
		for (Tab tab : tabPane.getTabs()) {
			ProcessDefinitionPane p = (ProcessDefinitionPane) tab;
			p.save(conn);
		}
	}

	@Override
	public void load(Connection conn, int id) throws SQLException {
		ProcessDefinitionPane pane = createDiagramPane();
		pane.onReloadFinish(bundle);
		pane.load(conn, id);
		pane.requestFocus(pane);
	}

	@Override
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<definitions id=\"" + id.get() + "\"");
		sb.append(" xmlns:xsi=\"" + XMLNS_XSI+ "\"");
		sb.append(" xsi:schemaLocation=\""+XSI_SCHEMALOCATION+ "\"");
		sb.append(" xmlns=\""+XMLNS+ "\"");
		sb.append(" typeLanguage=\""+TYPELANGUAGE+ "\"");
		sb.append(" expressionLanguage=\""+EXPRESSIONLANGUAGE+ "\"");
		sb.append(" targetNamespace=\""+TARGETNAMESPACE+ "\"");
		sb.append(">\r\n");
		for (Tab tab : tabPane.getTabs()) {
			ProcessDefinitionPane p = (ProcessDefinitionPane) tab;
			sb.append(p.toXML());
		}
		sb.append("</definitions>\r\n");
		return sb.toString();
	}
}
