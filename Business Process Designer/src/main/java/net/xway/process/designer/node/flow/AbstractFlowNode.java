package net.xway.process.designer.node.flow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.AbstractNodeContextMenu;
import net.xway.process.designer.node.AbstractNodeContextPane;
import net.xway.process.designer.node.IProcessNode;
import net.xway.process.designer.node.NodeUtils;
import net.xway.process.designer.node.ProcessDefinitionPane;
import net.xway.process.designer.tools.LineTool;

public abstract class AbstractFlowNode extends LineTool implements IProcessNode {
	
    public static final Color NormalColor = Color.DARKBLUE;

    private final ProcessDefinitionPane parent;
    private boolean focus = false;

    protected final AbstractNode source, target;
	protected Integer databaseId = null ; 
	protected StringProperty key = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected StringProperty description = new SimpleStringProperty();
	protected StringProperty conditionExpression = new SimpleStringProperty();

	private FlowNodePane nodePane = new FlowNodePane();
	private FlowNodeMenu nodeMenu = new FlowNodeMenu();

    public AbstractFlowNode(ProcessDefinitionPane parent, AbstractNode source, AbstractNode target) {
        super(source, target);
        this.parent = parent;
        this.source = source;
        this.target = target;
        source.addFromHereLine(this);
        target.addToHereLine(this);
        
        setStrokeWidth(5);
        setStroke(NormalColor);

    	int num = NodeUtils.getDefaultNodeNumber(this);
    	key.set(getDefaultKeyValue() + num);
    	name.set(getDefaultNameValue()+ num);
    	
    	key.addListener(this);
    	name.addListener(this);
    	description.addListener(this);
    	conditionExpression.addListener(this);
    }

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.Flow;
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
	public AbstractNodeContextMenu getNodeContextMenu() {
		return nodeMenu;
	}

	@Override
	public AbstractNodeContextPane getNodeContextPane() {
		return nodePane;
	}

	@Override
	public ProcessDefinitionPane getDefinitionPane() {
		return parent;
	}

	@Override
	public void onReloadFinish(ResourceBundle bundle) {
    	if (getNodeContextPane() != null)
    		getNodeContextPane().onReloadFinish(bundle);
	}

	@Override
    public void onFocus() {
		focus =true;
    	setStroke(Color.LIGHTSKYBLUE);
    }
    
    @Override
    public void onLostFocus() {
    	focus = false;
    	setStroke(NormalColor);
    }
    
    @Override
	public boolean hasFocus() {
		return focus;
	}

	public void onMoveSourceOrTarget() {
        setStart(source.getLayoutX() + source.getWidth() / 2, source.getLayoutY() + source.getHeight() / 2);
        Point2D p = NodeUtils.getNodeCross(source, target);
        setEnd(p.getX(), p.getY());
    }
	    
    public AbstractNode getSource() {
        return source;
    }

    public AbstractNode getTarget() {
        return target;
    }
    
	@Override
	public String getDefaultKeyValue() {
		return "Flow" ;
	}

	@Override
	public String getDefaultNameValue() {
		return "Flow" ;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		parent.changed(observable, oldValue, newValue);
	}

	@Override
	public void save(Connection conn) throws SQLException {
		String sql = "INSERT INTO XT_PROCESSDEFINITIONFLOW(PROCESSDEFINITIONID, SOURCEID, TARGETID, `KEY`, NAME, TYPE, DESCRIPTION) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, getDefinitionPane().getDatabaseId());
		ps.setInt(2, source.getDatabaseId());
		ps.setInt(3, target.getDatabaseId());
		ps.setString(4, key.get());
		ps.setString(5, name.get());
		ps.setString(6, getType().name());
		ps.setString(7, description.get());
		ps.executeUpdate();

	}
	
//	public void insert(Connection conn) throws SQLException {
//		String sql = "INSERT INTO XT_PROCESSDEFINITIONFLOW(PROCESSDEFINITIONID, SOURCEID, TARGETID, `KEY`, NAME, TYPE, DESCRIPTION) VALUES(?,?,?,?,?,?,?)";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, getDefinitionPane().getDatabaseId());
//		ps.setInt(2, source.getDatabaseId());
//		ps.setInt(3, target.getDatabaseId());
//		ps.setString(4, key.get());
//		ps.setString(5, name.get());
//		ps.setString(6, getType().name());
//		ps.setString(7, description.get());
//		ps.executeUpdate();
//
//	}
//
//	public void update(Connection conn) throws SQLException {
//		String sql = "UPDATE XT_PROCESSDEFINITIONFLOW SET SOURCEID, TARGETID, `KEY`, NAME, TYPE, DESCRIPTION) VALUES(?,?,?,?,?,?,?)";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, getDefinitionPane().getDatabaseId());
//		ps.setInt(2, source.getDatabaseId());
//		ps.setInt(3, target.getDatabaseId());
//		ps.setString(4, key.get());
//		ps.setString(5, name.get());
//		ps.setString(6, getType().name());
//		ps.setString(7, description.get());
//		ps.executeUpdate();
//
//	}

	@Override
	public void load(Connection conn, int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	class FlowNodeMenu extends AbstractNodeContextMenu {

		public FlowNodeMenu() {
			super(AbstractFlowNode.this);
		}
		
	}

	class FlowNodePane extends AbstractNodeContextPane {

		private Label lblConditionExpression = new Label();
		private TextField txtConditionExpression = new TextField();
		
		public FlowNodePane() {
			super(AbstractFlowNode.this);
			addTop(lblConditionExpression, txtConditionExpression);
			conditionExpression.bind(txtConditionExpression.textProperty());
		}

		@Override
		public void onReloadFinish(ResourceBundle bundle) {
			super.onReloadFinish(bundle);
			lblConditionExpression.setText(bundle.getString("ConditionExpression"));
		}
		
	}


}
