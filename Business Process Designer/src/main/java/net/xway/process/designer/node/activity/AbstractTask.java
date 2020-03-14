package net.xway.process.designer.node.activity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.AbstractNodeContextMenu;
import net.xway.process.designer.node.AbstractNodeContextPane;
import net.xway.process.designer.node.ProcessDefinitionPane;

public abstract class AbstractTask extends AbstractNode {

	protected StringProperty cadidateUsers = new SimpleStringProperty();
	protected StringProperty cadidateRoles = new SimpleStringProperty();

	private TaskNodePane nodePane = new TaskNodePane();
	private TaskNodeMenu nodeMenu = new TaskNodeMenu();

	
	public AbstractTask(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.Activity;
	}

	@Override
	public boolean judgelinkTO(NodeType type) {
		return type.isStartEvent() == false;
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
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.setFill(Color.LIGHTYELLOW);
    	ctx.setStroke(Color.BLACK);
    	ctx.fillRoundRect(0, 0, getIconWidth(), getIconHeight(), 30, 30);
    	ctx.strokeRoundRect(0, 0, getIconWidth(), getIconHeight(), 30, 30);
    	return canvas;
	}

	@Override
	public String getDefaultKeyValue() {
		return "UserTask";
	}

	@Override
	public String getDefaultNameValue() {
		return "User Task";
	}



	class TaskNodePane extends AbstractNodeContextPane {

		private Label lblCadidateUsers = new Label();
		private TextField txtCadidateUsers = new TextField();
		private Label lblCadidateRoles = new Label();
		private TextField txtCadidateRoles = new TextField();

		public TaskNodePane() {
			super(AbstractTask.this);
			addCenter(lblCadidateUsers, txtCadidateUsers, lblCadidateRoles, txtCadidateRoles);
			cadidateUsers.bind(txtCadidateUsers.textProperty());
			cadidateRoles.bind(txtCadidateRoles.textProperty());
		}
		
		@Override
		public void onReloadFinish(ResourceBundle bundle) {
			super.onReloadFinish(bundle);
			lblCadidateUsers.setText(bundle.getString("CadidateUsers"));
			lblCadidateRoles.setText(bundle.getString("CadidateRoles"));
		}

	}

	class TaskNodeMenu extends AbstractNodeContextMenu {

		public TaskNodeMenu() {
			super(AbstractTask.this);
		}
		
	}


}
