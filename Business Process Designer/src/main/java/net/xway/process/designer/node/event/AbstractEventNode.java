package net.xway.process.designer.node.event;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.AbstractNodeContextMenu;
import net.xway.process.designer.node.AbstractNodeContextPane;
import net.xway.process.designer.node.ProcessDefinitionPane;

public abstract class AbstractEventNode extends AbstractNode {

	protected StringProperty listener = new SimpleStringProperty();

	private EventNodePane nodePane = new EventNodePane();
	private EventNodeMenu nodeMenu = new EventNodeMenu();
	
	public AbstractEventNode(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public AbstractNodeContextMenu getNodeContextMenu() {
		return nodeMenu;
	}

	@Override
	public AbstractNodeContextPane getNodeContextPane() {
		return nodePane;
	}

	class EventNodePane extends AbstractNodeContextPane {

		private Label lblListener;
		private TextField txtListener;
		
		public EventNodePane() {
			super(AbstractEventNode.this);
			lblListener = new Label();
			txtListener = new TextField();
			listener.bind(txtListener.textProperty());
			addTop(lblListener, txtListener);
		}

		@Override
		public void onReloadFinish(ResourceBundle bundle) {
			super.onReloadFinish(bundle);
			lblListener.setText(bundle.getString("Listener"));
		}
		
	}

	class EventNodeMenu extends AbstractNodeContextMenu {

		public EventNodeMenu() {
			super(AbstractEventNode.this);
		}
		
	}

}
