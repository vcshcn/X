/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.tools;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.NodeUtils;


/**
 *
 * @author cc
 */
public class LineTool extends Path  {
    
	private SimpleDoubleProperty startX, startY;
	private SimpleDoubleProperty endX, endY;
    
    private MoveTo moveTo = new MoveTo(),  m1 = new MoveTo(), m2 = new MoveTo();
    private LineTo lineTo = new LineTo(),  l1 = new LineTo(), l2 = new LineTo();

    public LineTool(AbstractNode source, AbstractNode target) {
        this(source, NodeUtils.getNodeCross(source, target));
    }

    public LineTool(AbstractNode source, Point2D p) {
        this(source, p.getX(), p.getY());
    }
        
    public LineTool(AbstractNode source, double x, double y) {
        startX = new SimpleDoubleProperty(source.getLayoutX() + source.getWidth() / 2);
        startY = new SimpleDoubleProperty(source.getLayoutY() + source.getHeight() / 2);
        endX =  new SimpleDoubleProperty(x);
        endY =  new SimpleDoubleProperty(y);
        startX.addListener( l-> {update();});
        startY.addListener(l-> {update();});
        endX.addListener(l-> {update();});
        endY.addListener(l-> {update();});
        
        getElements().addAll(new PathElement[] { moveTo, lineTo, m1, l1, m2, l2 }); 
        moveTo.xProperty().bind(startX);
        moveTo.yProperty().bind(startY);
        lineTo.setX(endX.doubleValue());
        lineTo.setY(endY.doubleValue());
        m1.xProperty().bind(endX);
        m1.yProperty().bind(endY);
        m2.xProperty().bind(endX);
        m2.yProperty().bind(endY);
        
        update();
    }
    
    public void setStart(double x , double y) {
        startX.set(x);
        startY.set(y);
    }
    
    public void setEnd(double x , double y) {
        endX.set(x);
        endY.set(y);
    }

    private void update() {
    	lineTo.setX(endX.doubleValue());
        lineTo.setY(endY.doubleValue());
        
        double alpha = Math.atan2( endY.doubleValue() - startY.doubleValue()  , endX.doubleValue() - startX.doubleValue() );
        double beta = Math.PI / 2 - alpha;
        
        double beta1 = beta - Math.PI / 6;
        double xl = Math.sin(beta1) * 15;
        double y1 = Math.cos(beta1) * 15;	
        double deltaX = endX.doubleValue() - xl;
        double deltaY = endY.doubleValue() - y1;
        l1.setX(deltaX);
        l1.setY(deltaY);
    
        beta1 = beta + Math.PI / 6;
        xl = Math.sin(beta1) * 15;
        y1 = Math.cos(beta1) * 15;
        deltaX = endX.doubleValue() - xl;
        deltaY = endY.doubleValue() - y1;   
        l2.setX(deltaX);
        l2.setY(deltaY);
    
    }
}
