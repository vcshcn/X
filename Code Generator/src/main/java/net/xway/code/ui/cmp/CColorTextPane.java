package net.xway.code.ui.cmp;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class CColorTextPane extends JTextPane implements CaretListener {

	private static ArrayList<String> SPLIT_STRING = new ArrayList<>();
	static {
		SPLIT_STRING.add(" ");
		SPLIT_STRING.add("\r");
	}
	
	private static ArrayList<String> RESERVE_STRING = new ArrayList<>();
	static {
		RESERVE_STRING.add("SELECT");
		RESERVE_STRING.add("FROM");
		RESERVE_STRING.add("WHERE");
	}
	
	public CColorTextPane() {
		super();	
		addCaretListener(this);
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		try {
			Group group = findWord(e.getDot());
			
			if (group != null && RESERVE_STRING.indexOf(group.word.toUpperCase()) >= 0) {
				this.getHighlighter().addHighlight(group.left, group.right, new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE));
			}
//			SimpleAttributeSet set = new SimpleAttributeSet ();
//			StyleConstants.setForeground(set, Color.BLUE);
			
//			this.getDocument().remove(group.left, group.right- group.left);
//			this.getDocument().insertString(group.left, group.word, set);
		} 
		catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	private Group findWord(final int P) throws BadLocationException {
		int left, right;
		int pos = P;
		
		while (true) {
			pos -- ;
			if (pos < 0) {
				left = 0;
				break ;
			}
			
			String s = getDocument().getText(pos, 1);
			if (SPLIT_STRING.indexOf(s) >= 0) {
				left = pos + 1;
				break ;
			}
			
		}
		
		pos = P;
		while (true) {
			if (pos >= getDocument().getLength()) {
				right = getDocument().getLength();
				break ;
			}
			
			String s = getDocument().getText(pos, 1);
			if (SPLIT_STRING.indexOf(s) >= 0) {
				right = pos - 1;
				break ;
			}
			pos ++;
		}
		
		if (right > left) {
			String word = getDocument().getText(left, right-left);
			return new Group(word, left, right);
		}
		return null;
	}

	
	class Group {
		
		public Group(String word, int left, int right) {
			this.word = word;
			this.left = left;
			this.right = right;
		}
		
		public String word;
		public int left;
		public int right;
	}
}
