package net.xway.code.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.xway.code.model.Component;
import net.xway.code.model.Field;
import net.xway.code.model.Group;
import net.xway.code.model.Project;
import net.xway.code.model.type.ComponentType;
import net.xway.code.ui.cmp.CComponentTransferable;
import net.xway.code.utils.ComponentUtils;

public class DiagramCanvas extends JPanel {

	private final Group group;
	private List<ComponentDiagramRelation> diagramRelations = new ArrayList<>();
	
	public DiagramCanvas(Group group) {
		this.group = group;
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(null);
		setBackground(Color.WHITE);
		setAutoscrolls(true);
		
		int x = 50;
		for (Project project : group.getProjects()) {
			for (Component component : project.getComponents()) {
				List<Component> cmps = ComponentUtils.getComponentAndAllSubComponents(component);
				
				int y = 50;
				int w = -1;
				for (Component cmp : cmps) {
					CComponentDiagram cd = new CComponentDiagram(cmp);
					add(cd);
					cd.setLocation(x, y);
					
					y += cd.getHeight() + 15;
					w = Math.max(w, cd.getWidth());
				}
				
				x += w + 25;
			}
		
			// build diagram relation
			for (Component component : project.getComponents()) {
				List<Component> cmps = ComponentUtils.getComponentAndAllSubComponents(component);
	
				for (Component cmp : cmps) {
					
					for (Field field : cmp.getFields()) {
						if (field.getType() instanceof ComponentType) {
							Component ref = field.getReference();
							
							CComponentDiagram child = findDiagramByComponent(cmp);
							CComponentDiagram parent = findDiagramByComponent(ref);
							if (child != null && parent != null) {
								diagramRelations.add(new ComponentDiagramRelation(parent, child));
							}
						}
					}
					
				}
			}
		
		}
		new DropTarget(this, DnDConstants.ACTION_MOVE,  new DropTargetAdapter() {

			@Override
			public void drop(DropTargetDropEvent dtde) {
				Transferable t = dtde.getTransferable();
				if( t.isDataFlavorSupported( CComponentTransferable.IntFlavor ) ) {
					dtde.acceptDrop(dtde.getDropAction());
					
					try {
						Object[] r = (Object[])t.getTransferData(  CComponentTransferable.IntFlavor);
						int index = (Integer) r[0];
						Point p = (Point) r[1];
						dtde.dropComplete(true);
						
						if (index != -1) {
							CComponentDiagram diagram = (CComponentDiagram) DiagramCanvas.this.getComponents()[index];
							diagram.setLocation(dtde.getLocation().x - p.x, dtde.getLocation().y - p.y);
							
							// get need repaint rectangle
							Rectangle reg2 = getRectangleOfRelation(diagram);
							if (reg2 != null) { // if null the component no line
								DiagramCanvas.this.repaint(reg2.x, reg2.y, reg2.width, reg2.height);
							}
							
							diagram.requestFocus();
						}
					}
					catch (IOException | UnsupportedFlavorException e) {
						e.printStackTrace();
					}
				}
				else {
					dtde.rejectDrop();
				}
				
			}
			
		});
	}

	@Override
	public void paint(Graphics _g) {
		
		Graphics2D g = (Graphics2D) _g;
		g.setColor(getBackground());
		
		// draw background
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		for (int i=0; i<getHeight(); i+= 10) {
			for (int j=0; j<getWidth(); j+=10) {
				g.drawLine(j, i, j+1, i+1);
			}
		}

		// draw relation
		for (ComponentDiagramRelation relation : diagramRelations) {
			Point childLocation = relation.getChildCenterLocation();
			Point parentLocation = relation.getParentCenterLocation();
			g.drawLine(childLocation.x , childLocation.y, parentLocation.x, parentLocation.y);
		}
		
		paintBorder(_g);
		paintChildren(_g);
	}
	
	private CComponentDiagram findDiagramByComponent(Component component) {
		for (java.awt.Component cmp : getComponents()) {
			if (cmp instanceof CComponentDiagram) {
				CComponentDiagram d = (CComponentDiagram) cmp;
				if (d.getModelComponent() == component) {
					return d;
				}
			}
		}
		return null;
	}
	
	/**
	 * return transform rectangle
	 * @param diagram
	 * @return
	 */
	private Rectangle getRectangleOfRelation(CComponentDiagram diagram) {
		Rectangle r= null;
		
		for (ComponentDiagramRelation relation : diagramRelations) {
			if (relation.contains(diagram)) {
				if (r == null) {
					r = relation.getRectangle();
				}
				else {
					r = r.union(relation.getRectangle());
				}
			}
		}
		
		return r;
		
	}
}
