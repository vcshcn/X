/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.OperationStatus;
import net.xway.process.designer.node.flow.AbstractFlowNode;
import net.xway.process.designer.tools.AbstractTool;

/**
 *
 * @author cc
 */
public abstract class AbstractNode extends AbstractTool implements IProcessNode {
	
	private final double SCALE = 1;
	
	protected ProcessDefinitionPane parent;
    private boolean focus = false;
	protected OperationStatus currentOperationStatus = OperationStatus.Point;
    
    protected Vector<AbstractFlowNode> fromHerelines = new Vector<>();
    protected Vector<AbstractFlowNode> toHereLines = new Vector<>();
    
	protected Integer databaseId = null ; 
	protected StringProperty key = new SimpleStringProperty();
	protected StringProperty name = new SimpleStringProperty();
	protected StringProperty description = new SimpleStringProperty();

    public AbstractNode(ProcessDefinitionPane parent) {
    	this.parent = parent;
    	int num = NodeUtils.getDefaultNodeNumber(this);
    	key.set(getDefaultKeyValue() + num);
    	name.set(getDefaultNameValue()+ num);
    	lblTitle.textProperty().bind(name);
    	key.addListener(this);
    	name.addListener(this);
    	description.addListener(this);

        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (OperationStatus.Point.equals(currentOperationStatus)) {
                    Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(iconBox.getAsImage());
                    dragboard.setContent(content);
                }
                else if (OperationStatus.Line.equals(currentOperationStatus)) {
                    Dragboard dragboard = startDragAndDrop(TransferMode.LINK);
                    ClipboardContent content = new ClipboardContent();
                    content.putString("move");
                    dragboard.setContent(content);
                }
                event.consume();
            }
        });
    }

    abstract public boolean judgelinkTO(NodeType type);
    abstract public AbstractNodeContextMenu getNodeContextMenu();
    abstract public AbstractNodeContextPane getNodeContextPane();

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		parent.changed(observable, oldValue, newValue);
	}

	@Override
	public void onReloadFinish(ResourceBundle bundle) {
    	if (getIcon() != null)
    		iconBox.setIcon(getIcon());
    	if (getNodeContextPane() != null)
    		getNodeContextPane().onReloadFinish(bundle);
	}

	@Override
	public String getTitle() {
		return name.get();
	}
	
	@Override
	public Integer getDatabaseId() {
		return databaseId;
	}

	@Override
	public StringProperty getKeyProperty() {
		return key;
	}

	public StringProperty getNameProperty() {
		return name;
	}

	public StringProperty getDescriptionProperty() {
		return description;
	}

	@Override
	public ProcessDefinitionPane getDefinitionPane() {
		return parent;
	}

    public AbstractFlowNode getLineByTarget(AbstractNode target) {
    	for (AbstractFlowNode n : fromHerelines) {
    		if (target == n.getTarget()) return n;
    	}
    	return null;
    }

    public void setOperationStatus(OperationStatus status) {
        currentOperationStatus = status;
    }

    
    public List<AbstractFlowNode> getFromHerelines() {
		return fromHerelines;
	}

    public void addFromHereLine(AbstractFlowNode flow) {
        fromHerelines.add(flow);
    }
    
    public void removeFromHereLine(AbstractFlowNode flow) {
    	fromHerelines.remove(flow);
    }

	public List<AbstractFlowNode> getToHereLines() {
		return toHereLines;
	}

    public void addToHereLine(AbstractFlowNode flow) {
        toHereLines.add(flow);
    }

    public void removeToHereLine(AbstractFlowNode flow) {
        toHereLines.remove(flow);
    }

	public void onMove() {
        for (AbstractFlowNode line : fromHerelines) {
            line.onMoveSourceOrTarget();
        }
        for (AbstractFlowNode line : toHereLines) {
            line.onMoveSourceOrTarget();
        }
    }
    
	@Override
    public void onFocus() {
		focus = true;
    	setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

	@Override
    public void onLostFocus() {
		focus = false;
    	setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
    }

	@Override
	public boolean hasFocus() {
		return focus;
	}

	@Override
	public int getIconWidth() {
		return (int)(super.getIconWidth() * SCALE);
	}

	@Override
	public int getIconHeight() {
		return (int)(super.getIconHeight()* SCALE);
	}

	@Override
    public boolean contains(double localX,double localY) {
        return localX > getLayoutX() && localX < getLayoutX()+getWidth() && localY > getLayoutY() && localY < getLayoutY() + getHeight();
    }

	@Override
    public String toString() {
        return getType() + "Node[" + (this.getLayoutX() + this.getWidth()/2) + "," + (this.getLayoutY() + this.getHeight()/2) + "]" ;
    }
    
	protected Integer saveProperties(Connection conn, String key, String value) throws SQLException {
		Integer id = null;
		String sql = "INSERT INTO XT_PROCESSDEFINITIONNODEPROPERTIES(PROCESSDEFINITIONNODEID, `KEY`, VALUE) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, databaseId);
		ps.setString(2, key);
		ps.setString(3, value);
		if (ps.executeUpdate() == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			ps.close();

		}
		return id;
	}

	@Override
	public void save(Connection conn) throws SQLException {
		String sql = "INSERT INTO XT_PROCESSDEFINITIONNODE(PROCESSDEFINITIONID, TYPE, `KEY`, NAME, DESCRIPTION, COORDINATEX, COORDINATEY) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, parent.getDatabaseId());
		ps.setString(2, getType().name());
		ps.setString(3, key.get());
		ps.setString(4, name.get());
		ps.setString(5, description.get());
		ps.setInt(6, (int)getLayoutX());
		ps.setInt(7, (int)getLayoutY());

		if (ps.executeUpdate() == 1) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				databaseId = rs.getInt(1);
			}
			rs.close();
			ps.close();
		}
	}
//
//	public void update(Connection conn) throws SQLException {
//		String sql = "UPDATE XT_PROCESSDEFINITIONNODE SET `KEY`=?, NAME=?, DESCRIPTION=?, COORDINATEX=?, COORDINATEY=? WHERE PROCESSDEFINITIONID=?";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setString(1, key.get());
//		ps.setString(2, name.get());
//		if (description.get() == null)
//			ps.setNull(3, java.sql.Types.VARCHAR);
//		else
//			ps.setString(3, description.get());
//		ps.setInt(4, (int)(getLayoutX() + getWidth() / 2));
//		ps.setInt(5, (int)(getLayoutY() + getHeight() /2 ));
//		ps.setInt(6, databaseId);
//		ps.executeUpdate();
//		ps.close();
//	}

	@Override
	public void load(Connection conn, int id) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM XT_PROCESSDEFINITIONNODE WHERE PROCESSDEFINITIONNODEID= " + id);
		if (rs.next()) {
			databaseId = id ; 
			key.set(rs.getString("KEY"));
			name.set(rs.getString("NAME")); 
			description.set(rs.getString("DESCRIPTION"));
		}
		rs.close();
		stmt.close();
	}
    
}
