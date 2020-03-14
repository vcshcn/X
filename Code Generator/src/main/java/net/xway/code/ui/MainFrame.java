package net.xway.code.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.FileDialog;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import net.xway.code.config.Config;
import net.xway.code.model.Group;
import net.xway.code.model.Project;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("XWAY Coder");
		initComponents();
	}
	
	private void initComponents() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setBounds( env.getMaximumWindowBounds());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		// init menu 
		barMainMenu = new JMenuBar();
		
		mnuNewProject = new JMenuItem("New");
		mnuNewProject.setMnemonic('N');
		mnuNewProject.setAccelerator(KeyStroke.getKeyStroke('N', java.awt.Event.CTRL_MASK, false));
		mnuNewProject.addActionListener((ActionEvent e) -> { newProject(); });
		
		mnuOpenProject = new JMenuItem("Open");
		mnuOpenProject.setMnemonic('O');
		mnuOpenProject.setAccelerator(KeyStroke.getKeyStroke('O', java.awt.Event.CTRL_MASK, false));
		mnuOpenProject.addActionListener((ActionEvent e) -> { openProject(); });
		
		mnuSaveProject = new JMenuItem("Save");
		mnuSaveProject.setMnemonic('S');
		mnuSaveProject.setAccelerator(KeyStroke.getKeyStroke('S', java.awt.Event.CTRL_MASK, false));
		mnuSaveProject.addActionListener( (ActionEvent e)-> { saveProject(); });
		
		mnuSaveAsProject = new JMenuItem("Save as");
		mnuSaveAsProject.addActionListener( (ActionEvent e) -> { saveasProject(); });
		
		mnuImportProject = new JMenuItem("Import");
		mnuImportProject.addActionListener((ActionEvent e)-> {importProject(); } );
		
		mnuExitItem = new JMenuItem("Exit");
		mnuExitItem.setMnemonic('X');
		mnuExitItem.setAccelerator(KeyStroke.getKeyStroke('X', java.awt.Event.ALT_MASK, false));
		mnuExitItem.addActionListener((ActionEvent e) -> { dispose();});
		
		mnuFile = new JMenu("File");
		mnuFile.add(mnuNewProject);
		mnuFile.add(mnuOpenProject);
		mnuFile.add(mnuSaveProject);
		mnuFile.add(mnuSaveAsProject);
		mnuFile.add(mnuImportProject);
		mnuFile.addSeparator();
		mnuFile.add(mnuExitItem);
		
		mnuGenerate = new JMenuItem("Generate Code");
		mnuGenerate.addActionListener((ActionEvent e) -> {
			CodeDialog dlg = new CodeDialog(this, root.getGroup());
			dlg.setVisible(true);
		});
		mnuCode = new JMenu("Code");
		mnuCode.add(mnuGenerate);
		
		mnuProject = new JMenuItem("Project");
		mnuProject.setMnemonic('P');
		mnuProject.setAccelerator(KeyStroke.getKeyStroke('P', java.awt.Event.CTRL_MASK, false));
		mnuProject.addActionListener((ActionEvent e)-> {
			ProjectSettingDialog dlg = new ProjectSettingDialog(this);
			dlg.setVisible(true);
		});
		mnuDataBase = new JMenuItem("DataBase");
		mnuDataBase.addActionListener((ActionEvent e)-> {
			DataBaseSettingDialog dlg = new DataBaseSettingDialog(this);
			dlg.setVisible(true);
		});
		mnuEnglish = new JRadioButtonMenuItem("English", true);
		ButtonGroup myGroup = new ButtonGroup();
		myGroup.add(mnuEnglish);
		
		mnuOption = new JMenu("Option");
		mnuOption.add(mnuProject);
		mnuOption.add(mnuDataBase);
		mnuOption.addSeparator();
		mnuOption.add(mnuEnglish);

		mnuLicense = new JMenuItem("License");
		menuHomePage = new JMenuItem("Home Page");
		menuHomePage.addActionListener(e->{
			if (Desktop.isDesktopSupported()) {
				if (Desktop.getDesktop().isSupported(Action.BROWSE)) {
					try {
						Desktop.getDesktop().browse(new URI("https://code.csdn.net/vcshcn"));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnuMyself = new JMenuItem("MY");
		mnuMyself.addActionListener(e->{
			JOptionPane.showMessageDialog(this, "Author: vcshcn\r\nEmail: vcshcn@126.com", "MY", JOptionPane.INFORMATION_MESSAGE );
		});
		
		mnuAbout = new JMenu("About");
		mnuAbout.add(mnuLicense);
		mnuAbout.add(mnuMyself);
		mnuAbout.add(menuHomePage);
		
		barMainMenu.add(mnuFile);
		barMainMenu.add(mnuCode);
		barMainMenu.add(mnuOption);
		barMainMenu.add(mnuAbout);
		
		setJMenuBar(barMainMenu);
		
		// init toolbar
		btnNewProject = new JButton("New");
		btnNewProject.addActionListener((ActionEvent e)->{ newProject(); });
		btnOpenProject = new JButton("Open");
		btnOpenProject.addActionListener((ActionEvent e)->{ openProject(); });
		btnSaveProject = new JButton("Save");
		btnSaveProject.addActionListener((ActionEvent e)->{ saveProject(); });
		btnGenerateProject = new JButton("Generate");
		btnGenerateProject.addActionListener(e->{ new CodeDialog(this, root.getGroup()).setVisible(true); });
		btnDiagram = new JButton("Diagram");
		btnDiagram.addActionListener(e->{ updateMainPanel(new DiagramPanel( root.getGroup()));  });
		
		barMainTools = new JToolBar();
		barMainTools.add(btnNewProject);
		barMainTools.add(btnOpenProject);
		barMainTools.add(btnSaveProject);
		barMainTools.addSeparator();
		barMainTools.add(btnGenerateProject);
		barMainTools.add(btnDiagram);
		
		// init panel
		treLeftNavigator = new JTree();
		treLeftNavigator.setCellRenderer(new DefaultTreeCellRenderer() {

			
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,	row, hasFocus);
				if (value instanceof GroupNode) {
					setIcon(ImageCache.groupIcon);
				}
				else if (value instanceof ProjectNode) {
					setIcon(ImageCache.projectIcon);
				}
				else if (value instanceof ComponentNode) {
					setIcon(ImageCache.componentIcon);
				}
				else if (value instanceof EnumComponentNode) {
					setIcon(ImageCache.enumIcon);
				}
				return this;
			}
			
		});

		pnlLeftScroll = new JScrollPane(treLeftNavigator);
		pnlRight = new JPanel();
		pnlRight.setLayout(new BorderLayout());
		
		pnlSplit = new JSplitPane();
		pnlSplit.setLeftComponent(pnlLeftScroll);
		pnlSplit.setRightComponent(pnlRight);
		
		getContentPane().add(pnlSplit, BorderLayout.CENTER);
		getContentPane().add(barMainTools, BorderLayout.NORTH);
		

		newProject();
		treLeftNavigator.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				TreePath path = treLeftNavigator.getPathForLocation(e.getX(), e.getY());
				if (path == null) {
					return ;
				}

				AbstractTreeNode node = (AbstractTreeNode) path.getLastPathComponent();
				
				if (e.getButton()  == 3) {
					JPopupMenu m = node.getContentMenu();
					if (m != null) {
						m.show(treLeftNavigator,e.getX(), e.getY());
					}
				}
				else {
					JPanel pnl = node.getContentPanel();
					updateMainPanel(pnl);
				}
			}

			
		});

		setVisible(true);
		pnlSplit.setDividerLocation(.25);
	}
	
	public void updateMainPanel(JPanel pnl) {
		pnlRight.removeAll();
		if (pnl != null) {
			pnlRight.add(pnl, BorderLayout.CENTER);
		}
		pnlRight.doLayout();
		pnlRight.updateUI();
		
	}
	
	public void newProject() {
		loadProject(new Group());
		projectDiskFile = null;
	}
	
	private void loadProject(Group group) {
		root = new GroupNode(treLeftNavigator ,group);
		DefaultTreeModel model = new DefaultTreeModel(root);
		treLeftNavigator.setModel(model);
		treLeftNavigator.updateUI();
		
		pnlRight.removeAll();
		pnlRight.doLayout();
		pnlRight.updateUI();
	}
	
	private void importProject(Group group) {
		Group g = root.getGroup();
		
		for (Project project : group.getProjects()) {
			for (net.xway.code.model.Component component : project.getComponents()) {
				{
					Stack<net.xway.code.model.Component> stack = new Stack<net.xway.code.model.Component>();
					stack.push(component);
					while (stack.empty() == false) {
						net.xway.code.model.Component c = stack.pop();
						c.setGenerate(false);
						stack.addAll(c.getSubcomponent());
					}
				}
			}
			
			g.addProjects(project);
			root.addNode(new ProjectNode(root, project) );
		}
		
		root.nodeChanged();
	}
	
	public void openProject() {
		FileDialog dlgOpen = new FileDialog(this, "Open a Project", FileDialog.LOAD);
		dlgOpen.setFile("*.xway.project");
		dlgOpen.setVisible(true);
		String filename = dlgOpen.getFile();
		if (filename != null) {
			projectDiskFile = new File(dlgOpen.getDirectory(), filename);
			try (
				FileInputStream fis = new FileInputStream(projectDiskFile);
				BufferedInputStream bis =new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);
			) {
				Group group = (Group) ois.readObject();
				loadProject(group);
				
				Config.getInstance().load(dlgOpen.getDirectory());
			}
			catch (ClassNotFoundException e) {
				System.err.println(e);
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}
	}
	
	public void saveProject() {
		if (projectDiskFile == null) {
			FileDialog dlgSave = new FileDialog(this, "Save Project", FileDialog.SAVE);
			dlgSave.setFile("*.xway.project");
			dlgSave.setVisible(true);
			String filename = dlgSave.getFile();
			if (filename != null) {
				
				if (filename.endsWith(".xway.project") == false) {
					filename += ".xway.project";
				}

				File file =new File(dlgSave.getDirectory(), filename);
				if (file.exists() == true) {
					if (JOptionPane.showConfirmDialog(this, "Override " + filename + "?", "Warn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) ==JOptionPane.CANCEL_OPTION) {
						return ;
					}
				}
				projectDiskFile = file;
			}
		}
		
		if (projectDiskFile != null) {
			try (
					FileOutputStream fos = new FileOutputStream(projectDiskFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
				oos.writeObject(root.getGroup());
				Config.getInstance().save(projectDiskFile.getParentFile().getAbsolutePath());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveasProject() {
		FileDialog dlgSave = new FileDialog(this, "Save As Project", FileDialog.SAVE);
		dlgSave.setFile("*.xway.project");
		dlgSave.setVisible(true);
		String filename = dlgSave.getFile();
		if (filename != null) {
			
			if (filename.endsWith(".xway.project") == false) {
				filename += ".xway.project";
			}

			File file =new File(dlgSave.getDirectory(), filename);
			if (file.exists() == true) {
				if (JOptionPane.showConfirmDialog(this, "Override " + filename + "?", "Warn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) ==JOptionPane.CANCEL_OPTION) {
					return ;
				}
			}
			projectDiskFile = file;
			saveProject();
		}
	}
	
	public void importProject() {
		FileDialog dlgOpen = new FileDialog(this, "Import a Project", FileDialog.LOAD);
		dlgOpen.setFile("*.xway.project");
		dlgOpen.setVisible(true);
		String filename = dlgOpen.getFile();
		if (filename != null) {
			File projectDiskFile = new File(dlgOpen.getDirectory(), filename);
			try (
				FileInputStream fis = new FileInputStream(projectDiskFile);
				BufferedInputStream bis =new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);
			) {
				Group group = (Group) ois.readObject();
				importProject(group);
			}
			catch (ClassNotFoundException e) {
				System.err.println(e);
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}

	}
	
	private GroupNode root;
	private JTree treLeftNavigator;
	private JScrollPane pnlLeftScroll;
	private JPanel pnlRight;
	private JSplitPane pnlSplit ;
	
	private JToolBar barMainTools;
	private JButton btnNewProject;
	private JButton btnOpenProject;
	private JButton btnSaveProject;
	private JButton btnGenerateProject;
	private JButton btnDiagram;
	private JMenuBar barMainMenu;
	private JMenu mnuFile;
	private JMenuItem mnuExitItem;
	private JMenuItem mnuNewProject;
	private JMenuItem mnuOpenProject;
	private JMenuItem mnuSaveProject;
	private JMenuItem mnuSaveAsProject;
	private JMenuItem mnuImportProject;
	private JMenu mnuCode;
	private JMenuItem mnuGenerate;
	private JMenu mnuOption;
	private JMenuItem mnuProject;
	private JMenuItem mnuDataBase;
	private JRadioButtonMenuItem mnuEnglish;
	private JMenu mnuAbout;
	private JMenuItem mnuLicense;
	private JMenuItem mnuMyself;
	private JMenuItem menuHomePage;
	
	// project save location
	private File projectDiskFile = null;
}
