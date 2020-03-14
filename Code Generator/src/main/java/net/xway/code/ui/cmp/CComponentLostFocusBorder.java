package net.xway.code.ui.cmp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class CComponentLostFocusBorder extends AbstractBorder {

	private final Insets insets = new Insets(5,5,5,5);
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,int height) {
		Color oldColor = g.getColor();
		g.setColor(Color.GRAY);
		g.drawRect(x+insets.left, y+insets.top, width-insets.left-insets.right-1, height-insets.top-insets.bottom-1);
		g.setColor(oldColor);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.top = insets.left = insets.bottom = insets.right = 5;
		return insets;
	}

}
