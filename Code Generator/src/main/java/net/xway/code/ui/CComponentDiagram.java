package net.xway.code.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;

import net.xway.code.model.Component;
import net.xway.code.model.Field;
import net.xway.code.ui.cmp.CComponentFocusBorder;
import net.xway.code.ui.cmp.CComponentLostFocusBorder;
import net.xway.code.ui.cmp.CComponentTransferable;

public class CComponentDiagram extends JComponent {

	private static final String uiClassID = "CComponentDiagramUI";

	private static final String FIELD_SPACE_TYPE = "     ";

	private final Component component;
	private Font titleFont;
	private int titlePixelWidth;
	private int titlePixelHeight;

	private Font fieldFont;
	
	private Border focusBorder = new CComponentFocusBorder();
	private Border lostFocusBorder = new CComponentLostFocusBorder();

	public CComponentDiagram(Component component) {
		this.component = component;
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(null);
		
		Font defFont = UIManager.getDefaults().getFont("Label.font");
		FontMetrics fontMetrics = getFontMetrics(defFont);

		titleFont = new Font(Font.SERIF, Font.BOLD | Font.ITALIC, defFont.getSize());
		FontMetrics titleMetrics = this.getFontMetrics(titleFont);
		titlePixelWidth = titleMetrics.stringWidth(component.getName());
		titlePixelHeight = titleMetrics.getHeight();
		
		fieldFont = new Font(Font.SERIF, Font.PLAIN, defFont.getSize());

		Color background = new Color(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue());
		setBackground(background);

		// compute size
		int height = Math.max(fontMetrics.getHeight(), titleMetrics.getHeight()) * (component.getFields().size() + 1);
		int width = titlePixelWidth;
		for (Field field : component.getFields()) {
			width = Math.max(titlePixelWidth,  fontMetrics.stringWidth(field.getName() + FIELD_SPACE_TYPE + "<PRIMARYKEY>"));
		}
		
		setSize( (int)( width * 1.5), height + 20);

		// enable get focus
		setFocusable(true);
		// set border
		setBorder(lostFocusBorder);
		
		setOpaque(false);
		
		// support drag
		 DragSource dragSource = DragSource.getDefaultDragSource();
		 dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, new DragGestureListener() {

			@Override
			public void dragGestureRecognized(DragGestureEvent dge) {
				int index = -1;
				DiagramCanvas pnlParent = (DiagramCanvas)( CComponentDiagram.this.getParent());
				for (int i=0; i<pnlParent.getComponents().length; i++) {
					java.awt.Component c = pnlParent.getComponents()[i];
					if (c == CComponentDiagram.this) {
						index = i;
						break ;
					}
				}
				
				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
				CComponentDiagram.this.print(img.getGraphics());
				
				dge.startDrag(DragSource.DefaultMoveDrop, img, dge.getDragOrigin(), new CComponentTransferable(index, dge.getDragOrigin()), null);
			}
		 });

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
			}			
		});
		
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				setBorder(focusBorder);
				repaint();
			}

			@Override
			public void focusLost(FocusEvent e) {
				setBorder(lostFocusBorder);
				repaint();
			}
			
		});
		
	}

	@Override
	public Insets getInsets() {
		return getBorder().getBorderInsets(this);
	}

	@Override
	public Dimension getPreferredSize() {
		Insets insets = getInsets();
		Dimension d = new Dimension(getWidth(), getHeight());
		d.width += insets.left + insets.right;
		d.height += insets.top + insets.bottom;
		
		return d;
	}

	@Override
	protected void paintComponent(Graphics _g) {
		super.paintComponent(_g);
		
		Insets insets = getInsets();
		Rectangle r = new Rectangle(insets.left, insets.top, getWidth()-insets.left-insets.right-1, getHeight() - insets.top - insets.bottom-1);
		
		Graphics2D g = (Graphics2D)_g;
		Color oldColor = g.getColor();
		
		g.setColor(getBackground());
		g.fillRect(r.x, r.y, r.width, r.height);

		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString(component.getName(), (r.width-titlePixelWidth) /2, r.y+titlePixelHeight-2);
		g.drawLine(r.x,r.y+titlePixelHeight , r.width, r.y+titlePixelHeight);
		
		int y = r.y+titlePixelHeight + 2;
		g.setFont(fieldFont);
		FontMetrics fieldMetrics = this.getFontMetrics(fieldFont);
		for (Field field : component.getFields()) {
			y += fieldMetrics.getHeight();
			g.drawString(field.getName(), r.x+5 , y);
			if (field.getType() != null) {
				g.drawString("<"+field.getType().toString()+">", r.width-fieldMetrics.stringWidth("<PrimaryKey>")-5, y);
			}
		}
		g.setColor(oldColor);
	}


	@Override
	public Container getParent() {
		// TODO Auto-generated method stub
		return super.getParent();
	}

	@Override
	public String getUIClassID() {
		return uiClassID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CComponentDiagram) {
			return ((CComponentDiagram)obj).component == component;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Diagram of " + component.getName();
	}
	
	public Component getModelComponent() {
		return component;
	}
}
