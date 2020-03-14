/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.node;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import net.xway.process.designer.GlobalConfigure;
import net.xway.process.designer.OperationStatus;
import net.xway.process.designer.command.CreateFlowCommand;
import net.xway.process.designer.command.CreateNodeCommand;
import net.xway.process.designer.command.MoveNodeCommand;
import net.xway.process.designer.tools.AbstractTool;
import net.xway.process.designer.tools.LineTool;

/**
 * @author cc
 */
public class ProcessDiagramPane extends Pane  {

	private final ProcessDefinitionPane parent ;
	private OperationStatus status = OperationStatus.Point;
	private LineTool dragLine = null;

    public ProcessDiagramPane(ProcessDefinitionPane parent) {
    	this.parent = parent;
    	this.backgroundProperty().bind(GlobalConfigure.diagramPaneBackground);
        
        setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (OperationStatus.Point.equals(status)) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                else {
                    event.acceptTransferModes(TransferMode.LINK);
                    Object _source = event.getGestureSource();
                    if (_source instanceof AbstractNode) {
                    	AbstractNode source = (AbstractNode)_source;
                        if (dragLine == null) {
                            dragLine = new LineTool(source, event.getX(), event.getY());
                            getChildren().add(dragLine);
                        }
                        else {
                            dragLine.setEnd( event.getX(), event.getY());
                        }
                    }
                   
                }
            }
        });
        setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (OperationStatus.Point.equals(status)) {

                    if (TransferMode.COPY.equals(event.getTransferMode())) {
                        AbstractTool tool = (AbstractTool) event.getGestureSource();                        
                        double x = event.getX() - tool.getWidth() / 2;
                        double y = event.getY() - tool.getHeight() / 2;
                        parent.executeCommand(new CreateNodeCommand(parent, tool, x, y));
                    }
                    else if (TransferMode.MOVE.equals(event.getTransferMode())) {
                        AbstractNode node = (AbstractNode) event.getGestureSource();
                        double x = event.getX() - node.getWidth() / 2;
                        double y = event.getY() - node.getHeight() / 2;
                        parent.executeCommand(new MoveNodeCommand(node, x, y));
                     }

                }
                else if (OperationStatus.Line.equals(status)) {
                    AbstractNode start = (AbstractNode) event.getGestureSource();
                    Node _end = find(event.getX(), event.getY());
                    
                    if (start != null && _end != null && start != _end && _end instanceof AbstractNode )  {
                    	AbstractNode end = (AbstractNode) _end;
                    	
                        if (start.judgelinkTO(end.getType()) && start.getLineByTarget(end) == null) {
                        	parent.executeCommand(new CreateFlowCommand(parent, start, end));
                        }
                    }
                    getChildren().remove(dragLine);
                    dragLine = null;
                 }
             event.setDropCompleted(true);
             event.consume();
           }
        });
        
        setOnMouseClicked( new EventHandler<MouseEvent>() {
			AbstractNodeContextMenu menu;

			@Override
			public void handle(MouseEvent l) {
				IProcessNode currentNode = (IProcessNode) find(l.getX(), l.getY());

				if (status == OperationStatus.Line && l.getButton().equals(MouseButton.SECONDARY) ) {
					if (currentNode == null)
						parent.changeOperationStatus(OperationStatus.Point);
				}
				else {
					if (menu != null) {
						menu.hide();
						menu = null;
					}
					
					if (currentNode == null) 
						currentNode = parent;
					parent.requestFocus(currentNode);
						
					if (currentNode != null && l.getButton().equals(MouseButton.SECONDARY)) {
						menu = parent.showNodeContextMenu(currentNode, l.getScreenX(), l.getScreenY());
					}
				}
				l.consume();
			}
        	
        });
     }

    public void setOperationStatus(OperationStatus status) {
        this.status = status;
         if (OperationStatus.Point.equals(status)) {
            setCursor(Cursor.DEFAULT);
        }
        else if (OperationStatus.Line.equals(status)) {
            this.setCursor(Cursor.CROSSHAIR);
        }
        for (Node node : getChildren()) {
            if (node instanceof AbstractNode)
                ((AbstractNode)node).setOperationStatus(status);
        }
    }

    public OperationStatus getOperationStatus() {
        return status;
    }
    
    public List<IProcessNode> getProcessSortChildren() {
    	ArrayList<IProcessNode> r = new ArrayList<>();
    	for (Node n : getChildren() ) {
    		r.add((IProcessNode) n);
    	}
    	return NodeUtils.getSortProcessNode(r);
    	
    }

    private Node find(double x, double y) {
      for (Node node : getChildren()) {
          if (node.contains(x, y)) {
        	  return node;
          }
      }
      return null;
    }

}
