package net.xway.code.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import net.xway.code.config.Config;
import net.xway.code.generate.G;
import net.xway.code.model.Group;

public class CodeDialog extends JDialog {
	
	public CodeDialog(JFrame frame, Group group) {
		super(frame, "Code", true);
		this.group = group;
		initComponents();
	}
	
	private void initComponents() {
		txtSql = new JTextArea(30,100);
		txtJava = new JTextArea(30,100);
		txtJsp =  new JTextArea(30, 100);

		btnJava = new JButton("Run");
		btnJava.addActionListener(e->{generateJava(); });
		btnJsp = new JButton("Run");
		btnJsp.addActionListener(e->{generateJSP(); }); 
		btnSql = new JButton("Run");
		btnSql.addActionListener(e->{generateSQL();});
		btnSqlExecute = new JButton("Execute");
		btnSqlExecute.addActionListener(e->{ executeSQL(); });

		pnlBottomJava = new JPanel();
		pnlBottomJava.add(btnJava);
		pnlBottomSql = new JPanel();
		pnlBottomSql.add(btnSql);
		pnlBottomSql.add(btnSqlExecute);
		pnlBottomJsp = new JPanel();
		pnlBottomJsp.add(btnJsp);

		pnlSQL = new JPanel();
		pnlSQL.setLayout(new BorderLayout());
		pnlSQL.add(new JScrollPane(txtSql), BorderLayout.CENTER);
		pnlSQL.add( pnlBottomSql, BorderLayout.SOUTH);
		pnlJava = new JPanel();
		pnlJava.setLayout(new BorderLayout());
		pnlJava.add(new JScrollPane(txtJava), BorderLayout.CENTER);
		pnlJava.add(pnlBottomJava, BorderLayout.SOUTH);
		pnlJsp = new JPanel();
		pnlJsp.setLayout(new BorderLayout());
		pnlJsp.add(new JScrollPane(txtJsp), BorderLayout.CENTER);
		pnlJsp.add(pnlBottomJsp, BorderLayout.SOUTH);

		pnlTabbed = new JTabbedPane();
		pnlTabbed.addTab("Java", pnlJava);
		pnlTabbed.addTab("JSP", pnlJsp);
		pnlTabbed.addTab("SQL", pnlSQL);
		
		btnOK = new JButton("Run All");
		btnOK.addActionListener(e->{generate();});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e->{ dispose();} );
		pnlBottom = new JPanel();
		pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlBottom.add(btnOK);
		pnlBottom.add(btnCancel);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlTabbed, BorderLayout.CENTER);
		getContentPane().add(pnlBottom, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
	}

	private void generate() {
		generateJava();
		generateJSP();
		generateSQL();
	}

	private void generateSQL() {
		generateSQL(new PrintStream(new RedirectOutputStream(txtSql)));
	}
	
	private void executeSQL() {
		Connection conn  = null;
		
		int startLine = 1, endLine = 1;
		try { 
			conn = Config.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			LineNumberReader lineReader =new LineNumberReader(new BufferedReader(new StringReader(txtSql.getText())));
			String line;
			StringBuilder sb = new StringBuilder(); 
			while ( (line = lineReader.readLine()) != null) {
				if (line.length() == 0) {
					endLine = startLine = lineReader.getLineNumber();
					continue ;
				}
				if (line.startsWith("-- ")) {
					endLine = startLine = lineReader.getLineNumber();
					continue ;
				}
				sb.append(line);
				if (line.endsWith(";")) {
					conn.createStatement().execute(sb.toString());
					sb.setLength(0);
					startLine = endLine;
					endLine = lineReader.getLineNumber();
				}
			}
			
			conn.commit();
			JOptionPane.showMessageDialog(this, "Success", "Success",JOptionPane.INFORMATION_MESSAGE);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e, "Error",JOptionPane.ERROR_MESSAGE);
			try {
				txtSql.requestFocus();
				int start = txtSql.getLineStartOffset(startLine);
				int end = txtSql.getLineEndOffset(endLine);
				txtSql.select(start, end);
				
			}
			catch (BadLocationException e1) {
				JOptionPane.showMessageDialog(this, e1, "Error",JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1, "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Error",JOptionPane.ERROR_MESSAGE);
		}
		finally {
			try {
				conn.close();
			}
			catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, e1, "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void generateJava() {
		generateJava(new PrintStream(new RedirectOutputStream(txtJava)));
	}	
	
	private void generateJSP() {
		generateJSP(new PrintStream(new RedirectOutputStream(txtJsp)));
	}

	private void generateSQL(PrintStream writer) {
		txtSql.setText("");
		G.g("sql",group, writer);
	}
	
	
	private void generateJava(PrintStream writer) {
		txtJava.setText("");
		G.g("java", group, writer);
	}
	
	public void generateJSP(PrintStream writer) {
		txtJsp.setText("");
		G.g("webapp", group, writer);
	}

	private Group group;
	
	private JTabbedPane pnlTabbed;
	private JPanel pnlJava;
	private JPanel pnlJsp;
	private JPanel pnlSQL;
	private JTextArea txtSql;
	private JTextArea txtJava;
	private JTextArea txtJsp;
	private JPanel pnlBottomJava;
	private JPanel pnlBottomSql;
	private JPanel pnlBottomJsp;
	private JButton btnJava;
	private JButton btnSql;
	private JButton btnSqlExecute;
	private JButton btnJsp;
	private JButton btnOK;
	private JButton btnCancel;
	private JPanel pnlBottom;
	

	class RedirectOutputStream extends OutputStream {

		private JTextArea area;
		
		public RedirectOutputStream(JTextArea area) {
			this.area = area;
		}
		
		@Override
		public void write(int b) throws IOException {
			SwingUtilities.invokeLater( ()->{area.append(new String(new char[] {(char)b}));});
			
		}

	}
}
