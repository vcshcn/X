package net.xway.process.designer.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.flow.AbstractFlowNode;

public class NodeUtils {

	private static HashMap<NodeType, Integer> defaultIdTypesCount = new HashMap<>();
	
	static public int getDefaultNodeNumber(IProcessNode node) {
		NodeType type = node.getType();
		Integer i = defaultIdTypesCount.get(type);
		if (i == null) {
			i = Integer.valueOf(0);
			defaultIdTypesCount.put(type, i);
		}
		defaultIdTypesCount.put(type, i+1);
		return i.intValue();
	}
	
	static public List<IProcessNode> getSortProcessNode(List<IProcessNode> nodes) {
		ArrayList<IProcessNode> result = new ArrayList<>();
		
    	nodes.stream().filter( node -> NodeCategory.StartEvent.equals(node.getCategory())).findFirst().ifPresent( _start -> {
    		
        	Queue<AbstractNode> queue = new LinkedList<>();
        	queue.add((AbstractNode)_start);
        	
        	while (queue.isEmpty() == false) {
        		AbstractNode p = queue.poll();
        		List<AbstractFlowNode> flows = p.getFromHerelines();
        		
        		if (result.contains(p) == false && NodeCategory.EndEvent.equals(p.getCategory()) == false) {
        			result.add(p);
        		}
        		for (AbstractFlowNode flow : flows) {
        			if (result.contains(flow) == false) {
        				result.add(flow);
        				queue.add(flow.getTarget());
        			}
        		}
        	}

    	});
    	
    	for (IProcessNode node : nodes) {
    		if (result.contains(node) == false) {
    			result.add(node);
    		}
    	}
    	return result;
	}
	
    static public Point2D getNodeCross(AbstractNode start, AbstractNode end) {
        Line line = new Line(start.getLayoutX()+start.getWidth()/2, start.getLayoutY()+start.getHeight()/2, end.getLayoutX()+end.getWidth()/2, end.getLayoutY()+end.getHeight()/2);
        LineModal me = new LineModal(line);
        LineModal left = new LineModal(end.getLayoutX(), end.getLayoutY(), end.getLayoutX(), end.getLayoutY() + end.getHeight());
        LineModal top = new LineModal(end.getLayoutX(), end.getLayoutY(), end.getLayoutX()+end.getWidth(), end.getLayoutY());
        LineModal right = new LineModal(end.getLayoutX()+end.getWidth(), end.getLayoutY(), end.getLayoutX()+end.getWidth(), end.getLayoutY()+end.getHeight());
        LineModal bottom = new LineModal(end.getLayoutX(), end.getLayoutY()+end.getHeight(), end.getLayoutX()+end.getWidth(), end.getLayoutY()+end.getHeight());

        double x00 = top.x1;
        double x11 = top.x2;
        double y00 = left.y1;
        double y11 = left.y2;
        
        Vector<Point2D> v = new Vector<>();
        
        Point2D p =left.getIntersection(me);
        if (p != null && y00 <= p.getY() && p.getY() <= y11) {
            v.add(p);
        }
        
        p = top.getIntersection(me);
        if (p != null && x00 <= p.getX() && p.getX() <= x11) {
            v.add(p);
        }
        
        p = right.getIntersection(me);
        if (p != null && y00 <= p.getY() && p.getY() <= y11) {
            v.add(p);
        }

        p = bottom.getIntersection(me);
        if (p != null && x00 <= p.getX() && p.getX() <= x11) {
            v.add(p);
        }

        if (v.size() > 0) {
            Point2D min = v.get(0);
            for (Point2D pp : v) {
                if (pp.distance(line.getStartX(), line.getStartY()) < min.distance(line.getStartX(), line.getStartY())) {
                    min = pp; 
                }
            }
            return min;
        }
        throw new java.lang.NullPointerException();
    }
}

class LineModal {
	protected double x1, y1, x2, y2;
	protected double a,b,c;

    public LineModal(Line p ) {
        this(p.getStartX(), p.getStartY(), p.getEndX(), p.getEndY());
    }
    
    public LineModal(double x0, double y0, double x1, double y1 ) {
        this.x1 = x0;
        this.y1 = y0;
        this.x2 = x1;
        this.y2 = y1;
        a = y1 - y0;
        b = x0 - x1;
        c = x1 * y0 - x0 * y1;
    }

    public Point2D getIntersection(LineModal _other) {
        LineModal other = (LineModal)_other;
        double x = (other.c * b - c * other.b ) /  ( a * other.b - other.a * b );
        double y = (other.c * a - c * other.a ) /  ( b * other.a - other.b * a );

        return new Point2D(x, y);
    }

	@Override
	public String toString() {
        return "Line[" + x1 + "," + y1 + " - " + x2 + "," + y2 + "]" ;
	}
}
