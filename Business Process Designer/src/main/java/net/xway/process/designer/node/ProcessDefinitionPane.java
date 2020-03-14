package net.xway.process.designer.node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import net.xway.process.designer.EditStatus;
import net.xway.process.designer.IEditStatus;
import net.xway.process.designer.IReloadHandle;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.OperationStatus;
import net.xway.process.designer.ProcessDefinitionGroupPane;
import net.xway.process.designer.command.ICommand;
import net.xway.process.designer.node.activity.UserTaskNode;
import net.xway.process.designer.node.event.end.NoneEndNode;
import net.xway.process.designer.node.event.start.NoneStartNode;
import net.xway.process.designer.node.flow.AbstractFlowNode;
import net.xway.process.designer.node.flow.SequenceFlowNode;

public class ProcessDefinitionPane extends Tab implements IProcessNode, IEditStatus {

	protected Integer databaseId = null ; 
	protected StringProperty key = new SimpleStringProperty();
	protected IntegerProperty version = new SimpleIntegerProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected StringProperty description = new SimpleStringProperty();
	protected StringProperty status = new SimpleStringProperty();

    private ProcessDiagramPane diagramPane;
    private AttributeBox attributeBox = new AttributeBox();
    private ContentPane ctxPane;
    private ResourceBundle bundle;
    private boolean focus = false;
    private IProcessNode lastFocusNode = null;
    private EditStatus editStatus = EditStatus.Created;

	private ProcessAttributePane processAttributePane = new ProcessAttributePane();

	public ProcessDefinitionPane(ProcessDefinitionGroupPane groupPane) {
		diagramPane = new ProcessDiagramPane(this);
		ctxPane = new ContentPane();
		setContent(ctxPane);
		textProperty().bind(name);
		
    	int num = NodeUtils.getDefaultNodeNumber(this);
    	key.set(getDefaultKeyValue() + num);
    	name.set(getDefaultNameValue()+ num);
    	version.set(1);
    	status.set("Draft");

    	key.addListener( this);
    	name.addListener( this); 
    	description.addListener( this); 
    	requestFocus(this);
	} 
	
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		editStatus = EditStatus.Editing;
	}

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.ProcessDefinition;
	}

	@Override
	public NodeType getType() {
		return NodeType.ProcessDefinition;
	}

	@Override
	public void onReloadFinish(ResourceBundle bundle) {
		this.bundle =  bundle;
		ctxPane.onReloadFinish(bundle);
		processAttributePane.onReloadFinish(bundle);

        for (Node node : diagramPane.getChildren()) {
        	((IReloadHandle)node).onReloadFinish(bundle);
        }
	}
	
	public void changeOperationStatus(OperationStatus status) {
		diagramPane.setOperationStatus(status);
		if (status.equals(OperationStatus.Point))
			ctxPane.btnPoint.setSelected(true);
		else
			ctxPane.btnLine.setSelected(true);
	}

	public AbstractNodeContextMenu showNodeContextMenu(IProcessNode node, double x, double y) {
		AbstractNodeContextMenu menu = node.getNodeContextMenu();
		if (menu != null)
			menu.show(diagramPane, x, y, bundle);
		return menu;
	}
	
	public void requestFocus(IProcessNode node) {
		if (lastFocusNode == node) {
			return ;
		}
		if (lastFocusNode != null)
			lastFocusNode.onLostFocus();
		node.onFocus();
		showNodeContextPane(node);
		lastFocusNode = node;
	}

	@Override
	public Integer getDatabaseId() {
		return databaseId;
	}

	@Override
	public StringProperty getKeyProperty() {
		return key;
	}

	@Override
	public StringProperty getNameProperty() {
		return name;
	}

	@Override
	public StringProperty getDescriptionProperty() {
		return description;
	}

	@Override
	public ProcessDefinitionPane getDefinitionPane() {
		return this;
	}

	@Override
	public AbstractNodeContextMenu getNodeContextMenu() {
		return null;
	}

	@Override
	public AbstractNodeContextPane getNodeContextPane() {
		return processAttributePane;
	}

	@Override
	public void onFocus() {
		focus = true;
	}
	
	@Override
	public void onLostFocus() {
		focus = false;
	}
	
	@Override
	public boolean hasFocus() {
		return focus;
	}
	
	@Override
	public EditStatus getStatus() {
		return editStatus;
	}

	public void addNode(AbstractNode node, double x, double y) {
		diagramPane.getChildren().add(node);
        node.setLayoutX(x);
        node.setLayoutY(y);
        requestFocus(node);
        node.onReloadFinish(bundle);
	}
	
	public void removeNode(AbstractNode node) {
		diagramPane.getChildren().remove(node);
		for (AbstractFlowNode flow : node.getFromHerelines()) {
			flow.getTarget().removeToHereLine(flow);
			diagramPane.getChildren().remove(flow);
		}
		for (AbstractFlowNode flow : node.getToHereLines()) {
			flow.getSource().removeFromHereLine(flow);
			diagramPane.getChildren().remove(flow);
		}
		requestFocus(this);
	}
	
	public void moveNode(AbstractNode node, double x, double y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
        node.onMove();
	}

	public void addFlow(AbstractFlowNode flow) {
		diagramPane.getChildren().add(flow);
		flow.toBack();
		requestFocus(flow);
		flow.onReloadFinish(bundle);
	}
	
	public void removeFlow(AbstractFlowNode flow) {
		flow.getSource().removeFromHereLine(flow);
		flow.getTarget().removeToHereLine(flow);
		diagramPane.getChildren().remove(flow);
		requestFocus(this);
	}
	
	public void validation() {
		HashSet<String> unique = new HashSet<>();
		for (Node _node : diagramPane.getChildren()) {
			IProcessNode node = (IProcessNode) _node;
			if (unique.contains(node.getKeyProperty().get()) == true) {
				throw new NodeDefinitionRepeatException(bundle, node);
			}
			else {
				unique.add(node.getKeyProperty().get());
			}
		}
	}
	
	public void executeCommand(ICommand command) {
		editStatus = EditStatus.Editing;
		command.execute();
	}

	@Override
	public String getDefaultKeyValue() {
		return "Process" ;
	}

	@Override
	public String getDefaultNameValue() {
		return "Process" ;
	}

	@Override
	public void save(Connection conn) throws SQLException {
		if (EditStatus.Editing.equals(editStatus) == false)
			return ;
		
		if (databaseId == null) {
			insert(conn);
		}
		else {
			update(conn);
			
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM XT_PROCESSDEFINITIONNODE WHERE PROCESSDEFINITIONID=" + databaseId);
			stmt.close();
		}
		
		LinkedList<IProcessNode> r = new LinkedList<>();
		for (IProcessNode node : diagramPane.getProcessSortChildren()) {
			if (node instanceof AbstractNode)
				r.addFirst(node);
			else if (node instanceof SequenceFlowNode)
				r.addLast(node);
		}
		for (IProcessNode node : r) {
			node.save(conn);
		}
		editStatus = EditStatus.Complete;
	}
	
	public void insert(Connection conn) throws SQLException {
		String sql = "INSERT INTO XT_PROCESSDEFINITION(`KEY`, NAME, VERSION, DESCRIPTION, STATUS, CREATETIME, MODIFIEDTIME) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, key.get());
		ps.setString(2, name.get());
		ps.setInt(3, version.get());
		if (description.get() == null)
			ps.setNull(4, java.sql.Types.VARCHAR);
		else
			ps.setString(4, description.get());
		ps.setString(5, status.get());
		ps.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
		ps.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
		
		if (ps.executeUpdate() == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				databaseId = rs.getInt(1);
			}
			rs.close();
		}
		ps.close();
	}

	public void update(Connection conn) throws SQLException {
		String sql = "UPDATE XT_PROCESSDEFINITION SET `KEY`=? , NAME=?, VERSION=?, DESCRIPTION=?, MODIFIEDTIME=? WHERE PROCESSDEFINITIONID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, key.get());
		ps.setString(2, name.get());
		ps.setInt(3, version.get());
		if (description.get() == null)
			ps.setNull(4, java.sql.Types.VARCHAR);
		else
			ps.setString(4, description.get());
		ps.setTimestamp(5, new java.sql.Timestamp(new Date().getTime()));
		ps.setInt(6, databaseId);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void load(Connection conn, int id) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM XT_PROCESSDEFINITION WHERE PROCESSDEFINITIONID= " + id);
		if (rs.next()) {
			databaseId = id;
			key.set(rs.getString("KEY"));
			version.set(rs.getInt("VERSION"));
			name.set(rs.getString("NAME"));
			description.set(rs.getString("DESCRIPTION"));
			status.set(rs.getString("STATUS"));
			if ("Deploy".equals(status.get())) {
				version.add(1);
			}
		}
		rs.close();
		stmt.close();
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM XT_PROCESSDEFINITIONNODE WHERE PROCESSDEFINITIONID=" + id);
		while (rs.next()) {
			AbstractNode node = null;
			int x = rs.getInt("COORDINATEX");
			int y = rs.getInt("COORDINATEY");
			
			String type = rs.getString("TYPE");
			switch (type) {
				case "NoneStartEvent":	node = new NoneStartNode(this); break;
				case "NoneEndEvnet":	node = new NoneEndNode(this); break;
				case "UserTask":		node = new UserTaskNode(this); break;
				default: throw new java.lang.IllegalArgumentException(type);
			}
			addNode(node, x, y);
			node.load(conn, rs.getInt("PROCESSDEFINITIONNODEID"));
		}
		rs.close();
		stmt.close();
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM XT_PROCESSDEFINITIONFLOW WHERE PROCESSDEFINITIONID=" + id);
		while (rs.next()) {
			AbstractFlowNode flow;
			AbstractNode source = findNodeByNodeDatabaseID(rs.getInt("SOURCEID"));
			AbstractNode target= findNodeByNodeDatabaseID(rs.getInt("TARGETID"));
			
			String type = rs.getString("TYPE");
			switch (type) {
				case "SequenceFlow": flow = new SequenceFlowNode(this, source, target ); break;
				default: throw new java.lang.IllegalArgumentException(type);
			}
			
			addFlow(flow);
		}
		rs.close();
		stmt.close();
	}
	
	
	private AbstractNode findNodeByNodeDatabaseID(int id) {
		for (Node _node : diagramPane.getChildren()) {
			if (_node instanceof AbstractNode) {
				AbstractNode node = (AbstractNode) _node;
				if (node.getDatabaseId().intValue() == id )
					return node;
			}
		}
		return null;
	}

	@Override
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<process id=\"" + key.get() + "\" name=\"" + name.get() + "\">\r\n");
		for (IProcessNode node : diagramPane.getProcessSortChildren()) {
			sb.append("\t\t");
			sb.append( node.toXML() );
		}
		sb.append("\t</process>\r\n");
		return sb.toString();
	}

	private void showNodeContextPane(IProcessNode node) {
		attributeBox.removePane();
		if (node.getNodeContextPane() != null) {
			AbstractNodeContextPane p = node.getNodeContextPane();
			attributeBox.putPane(p);
		}
	}

	class ProcessAttributePane extends AbstractNodeContextPane  {
		
		public ProcessAttributePane() {
			super(ProcessDefinitionPane.this);
		}
		
	}


	class AttributeBox extends BorderPane {
		
		public void putPane(AbstractNodeContextPane pane) {
			getChildren().clear();
			setCenter(pane);
		}
		
		public void removePane() {
			getChildren().clear();
			setCenter(null);
		}
	}

	class ContentPane extends BorderPane implements IReloadHandle, EventHandler<ActionEvent> {
		private ToolBar barTools;
	    private ToggleButton btnPoint;
	    private ToggleButton btnLine;
	    private Button btnValidation;

		public ContentPane() {
	        barTools = new ToolBar();

	        ToggleGroup tg = new ToggleGroup ();
	        btnPoint = new ToggleButton(null, new ImageView("pointer.png"));
	        btnPoint.setToggleGroup(tg);
	        btnPoint.setSelected(true);
	        btnPoint.setOnAction(this);
	        
	        btnLine = new ToggleButton(null, new ImageView("pen.png"));
	        btnLine.setToggleGroup(tg);
	        btnLine.setOnAction(this);
	        
	        btnValidation = new Button();
	        btnValidation.setOnAction(this);
	        
	        barTools = new ToolBar();
	        barTools.getItems().addAll(btnPoint, btnLine, new Separator(), btnValidation );

	        ScrollPane sp = new ScrollPane(diagramPane);
	        sp.setPannable(true);
	        sp.setFitToWidth(true);
	        sp.setFitToHeight(true);
			SplitPane p = new SplitPane(sp, attributeBox);
			p.setOrientation(Orientation.VERTICAL);
	        p.setDividerPositions(0.8f);
	        
	        setTop(barTools);
	        setCenter(p);
		}

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == btnPoint) {
				changeOperationStatus(OperationStatus.Point);
			}
			else if (event.getSource() == btnLine) {
				changeOperationStatus(OperationStatus.Line);
			}
			else if (event.getSource() == btnValidation) {
				try {
					validation();
					Alert a = new Alert (Alert.AlertType.INFORMATION);
					a.setTitle( bundle.getString("ToolBar.Validation"));
					a.setHeaderText(bundle.getString("ToolBar.Validation") + " " +bundle.getString("Success"));
					a.showAndWait();
				}
				catch (NodeDefinitionRepeatException e) {
					IProcessNode node = e.getNode();
					ProcessDefinitionPane.this.requestFocus(node);
					Alert a = new Alert (Alert.AlertType.ERROR );
					a.setTitle(bundle.getString("Exception.ProcessError"));
					a.setHeaderText(e.getMessage());
					a.showAndWait();
				}
			}
			
		}
		
		@Override
		public void onReloadFinish(ResourceBundle rb) {
	        btnPoint.setText( rb.getString("ToolBar.Point") );
	        btnLine.setText(  rb.getString("ToolBar.Line") );
	        btnValidation.setText( rb.getString("ToolBar.Validation"));
		}

	}
	
	class ProcessDefinitionException extends java.lang.RuntimeException {
		
		protected final IProcessNode node;
		protected final ResourceBundle bundle;
		
		public ProcessDefinitionException(ResourceBundle bundle, IProcessNode node) {
			this.node = node;
			this.bundle = bundle;
		}

		public IProcessNode getNode() {
			return node;
		}
	}
	
	class NodeDefinitionRepeatException extends ProcessDefinitionException {

		public NodeDefinitionRepeatException(ResourceBundle bundle, IProcessNode node) {
			super(bundle, node);
		}

		@Override
		public String getMessage() {
			return bundle.getString("Exception.NodeDefinitionRepeatException");
		}
		
	}
}
