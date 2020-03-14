package net.xway.process.designer.node;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import net.xway.process.designer.command.RemoveFlowCommand;
import net.xway.process.designer.command.RemoveNodeCommand;
import net.xway.process.designer.node.flow.SequenceFlowNode;

public abstract class AbstractNodeContextMenu extends ContextMenu implements EventHandler<ActionEvent> {

	private IProcessNode node;
	private MenuItem mnuDelete = new MenuItem();
	
	public AbstractNodeContextMenu(IProcessNode node) {
		this.node = node;
		getItems().addAll(mnuDelete);
		mnuDelete.setOnAction(this);
	}

	public void show(ProcessDiagramPane diagram, double screenX, double screenY, ResourceBundle bundle) {
		mnuDelete.setText(bundle.getString("Menu.Delete"));
		super.show(diagram, screenX, screenY);
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == mnuDelete) {
			if (node instanceof AbstractNode)
				node.getDefinitionPane().executeCommand(new RemoveNodeCommand((AbstractNode)node));
			if (node instanceof SequenceFlowNode) 
				node.getDefinitionPane().executeCommand(new RemoveFlowCommand((SequenceFlowNode)node));
		}
	}

}
