package net.xway.code.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import net.xway.code.model.Component;
import net.xway.code.model.EnumComponent;
import net.xway.code.model.Field;
import net.xway.code.model.Project;
import net.xway.code.model.type.BooleanTimeType;
import net.xway.code.model.type.ComponentType;
import net.xway.code.model.type.DateTimeType;
import net.xway.code.model.type.DateType;
import net.xway.code.model.type.EnumType;
import net.xway.code.model.type.FieldTypeUtils;
import net.xway.code.model.type.IFieldType;
import net.xway.code.model.type.IntegerType;
import net.xway.code.model.type.NameType;
import net.xway.code.model.type.PrimaryKeyType;
import net.xway.code.model.type.StringType;

public class ComponentPanel extends JPanel {

	private int FieldIndex = 0;
	
	public ComponentPanel(ComponentNode leftNode, Component component) {
		this.leftNode = leftNode;
		this.component = component;
		initComponents();
		updateModelToUI();
	}
	
	private void initComponents() {
		// top component info panel
		lblComponentGenerate = new JLabel("Generate Component");
		chkComponentGenerate = new JCheckBox();
		lblComponentName = new JLabel("Component Name");
		txtComponentName = new JTextField(20);
		lblComponentComment = new JLabel("Component Comment");
		txtComponentComment = new JTextArea(3,20);
		txtComponentComment.setBorder(txtComponentName.getBorder());
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUIToModel();
			}
			
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateModelToUI();
			}
			
		});
		
		componentInfoPanel = new JPanel();
		GroupLayout layout = new GroupLayout(componentInfoPanel);
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
		componentInfoPanel.setLayout(layout);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup().addComponent(lblComponentGenerate).addComponent(chkComponentGenerate))
					.addGroup(layout.createParallelGroup().addComponent(lblComponentName).addComponent(txtComponentName))
					.addGroup(layout.createParallelGroup().addComponent(lblComponentComment).addComponent(txtComponentComment))
					.addGroup(layout.createParallelGroup().addComponent(btnOK).addComponent(btnCancel))
		);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup().addComponent(lblComponentGenerate).addComponent(lblComponentName).addComponent(lblComponentComment))
					.addGroup(layout.createParallelGroup().addComponent(chkComponentGenerate).addComponent(txtComponentName).addComponent(txtComponentComment)
					.addGroup(layout.createSequentialGroup().addComponent(btnOK).addComponent(btnCancel)))
		);
		
		// toolbar
		btnNewField = new JButton("New Field");
		btnNewField.addActionListener(e->{	tableModel.appendField(); });
		
		btnDeleteField = new JButton("Delete Field");
		btnDeleteField.addActionListener(e-> { tableModel.deleteField(tabFields.getSelectedRow());	});
		
		btnValidField = new JButton("Valid Field");
		
		
		pnlToolBar = new JToolBar();
		pnlToolBar.add(btnNewField);
		pnlToolBar.add(btnDeleteField);
		pnlToolBar.add(btnValidField);
		
		tableModel = new FieldsTableModel();
		tabFields = new JTable(tableModel);
//		tabFields.setCellEditor(new FieldCellEditor(tableModel));
		DefaultTableCellRenderer render0 = new DefaultTableCellRenderer();
		render0.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tabFields.getColumnModel().getColumn(0).setCellRenderer(render0);
		tabFields.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabFields.getColumnModel().getColumn(0).setMaxWidth(100);
		
		tabFields.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<IFieldType>(FieldTypeUtils.UseFieldType)));
		tabFields.getColumnModel().getColumn(4).setCellEditor(new CboComponentTableCellEditor());
//		tabFields.setRowHeight(30);
//		tabFields.setFont(new Font("ËÎÌו", Font.PLAIN, 24));
		pnlTable = new JScrollPane(tabFields);
		
		// 
		fieldsInfoPanel = new JPanel();
		fieldsInfoPanel.setLayout(new BorderLayout());
		
		setBorder(new EmptyBorder(5, 10, 5, 10));
		setLayout(new BorderLayout());
		add(componentInfoPanel, BorderLayout.NORTH);
		add(fieldsInfoPanel, BorderLayout.CENTER);

		fieldsInfoPanel.add(pnlToolBar, BorderLayout.NORTH);
		fieldsInfoPanel.add(pnlTable, BorderLayout.CENTER);
		
	}
	
	private void updateModelToUI() {
		txtComponentName.setText(component.getName());
		txtComponentComment.setText(component.getComment());
		chkComponentGenerate.setSelected(component.isGenerate());
	}
	
	private void updateUIToModel() {
		component.setName(txtComponentName.getText());
		component.setComment(txtComponentComment.getText());
		component.setGenerate(chkComponentGenerate.isSelected());
		
		tableModel.fireUpdateFieldEvent(0);
		leftNode.nodeChanged();
	}
	
	private Project getProject() {
		AbstractTreeNode node = leftNode.getParent();
		while (node instanceof ProjectNode == false) {
			node = node.getParent();
		}
		return ((ProjectNode)node).getProject();
	}
	
	private final ComponentNode leftNode;
	private Component component;
	
	private JPanel componentInfoPanel;
	private JLabel lblComponentGenerate;
	private JCheckBox chkComponentGenerate;
	private JLabel lblComponentName;
	private JTextField txtComponentName;
	private JLabel lblComponentComment;
	private JTextArea txtComponentComment;
	private JButton btnOK;
	private JButton btnCancel;
	
	private JPanel fieldsInfoPanel;
	private JToolBar pnlToolBar;
	private JScrollPane pnlTable;
	private JTable tabFields;
	private FieldsTableModel tableModel;
	private JButton btnNewField;
	private JButton btnDeleteField;
	private JButton btnValidField;

	
	private class CboComponentTableCellEditor extends AbstractCellEditor implements TableCellEditor  {

		private JComboBox<Component> cboComponenets = new JComboBox<>();
		
		public CboComponentTableCellEditor() {
			cboComponenets.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
		}
		
		@Override
		public Object getCellEditorValue() {
			return cboComponenets.getSelectedItem();
		}

		@Override
		public java.awt.Component getTableCellEditorComponent(JTable table,	Object value, boolean isSelected, int row, int column) {
			DefaultComboBoxModel aModel = null;
			
			IFieldType type = (IFieldType) table.getModel().getValueAt(row, 2);
			if (type instanceof ComponentType) {
				Vector<Component> cs = findAllReferenceComponent();
				aModel = new DefaultComboBoxModel<>(cs);
			}
			else if (type instanceof EnumType) {
				Vector<EnumComponent> cs = findAllReferenceEnum();
				aModel = new DefaultComboBoxModel<>(cs);
			}
			
			if (aModel != null) {
				// fill combo model
				cboComponenets.setModel(aModel);
				if (value != null) {
					cboComponenets.setSelectedItem(value);
				}
			}
			
			return cboComponenets;
		}

	
	}
	
	/**
	 * Find All Component for reference
	 * @return
	 */
	private Vector<Component> findAllReferenceComponent() {
		Vector<Component> cs = new Vector<>();
		
		// add all comonent
		Stack<Component> stack = new Stack<>();
		JTree tree = leftNode.getTree();
		GroupNode root = (GroupNode) tree.getModel().getRoot();
		for (Project project : root.getGroup().getProjects()) {
			stack.addAll(project.getComponents());
			while (stack.empty() == false) {
				Component cmp = stack.pop();
				cs.add(cmp);
				stack.addAll(cmp.getSubcomponent());
			}
			
		}
		return cs;
	}
	
	/**
	 *  find all enum component
	 */
	private Vector<EnumComponent> findAllReferenceEnum() {
		ProjectNode root = (ProjectNode) leftNode.getTree().getModel().getRoot();
		return new Vector<EnumComponent>(root.getProject().getEnumComponents());
	}
	
	private class FieldsTableModel implements TableModel {

		private List<TableModelListener> listeners = new ArrayList<>();
		
		private String[] columnNames = new String[] {"NO.", "Name", "Type", "Length", "Reference", "Format", "Not Null", "Default", "Search"} ;

		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			Field f = component.getFields().get(rowIndex);
			
			switch (columnIndex) {
				case 1: f.setName((String) aValue);
						break ;
				case 2: f.setType((IFieldType)aValue);
						break ;
				case 3: Integer v = (Integer) aValue;
						if (v != null && v > 0) {
							f.setLength((Integer)aValue);
						}
						break ;
				case 4: Component ref = (Component)aValue;
						f.setReference(ref);
						break ;
				case 5: f.setFormat((String) aValue);
						break ;
				case 6: f.setNotNull((Boolean)aValue);
						break ;
				case 7: f.setDefaultValue((String)aValue);
						break ;
				case 8: f.setSearch((Boolean)aValue);
						break;
			}
			
			if (columnIndex == 2 && f.getType() != null && f.getType().equals(aValue) == false) {
				if (f.getType() instanceof StringType || f.getType() instanceof NameType) {
					f.setDefaultValue(null);
					f.setFormat(null);
					f.setLength(16);
					f.setNotNull(false);
					f.setReference(null);
				}
				else if (f.getType() instanceof IntegerType) {
					f.setDefaultValue(null);
					f.setFormat(null);
					f.setLength(null);
					f.setNotNull(false);
					f.setReference(null);
				}
				else if (f.getType() instanceof BooleanTimeType) {
					f.setDefaultValue(Boolean.FALSE);
					f.setFormat(null);
					f.setLength(null);
					f.setNotNull(true);
					f.setReference(null);
				}
				else if (f.getType() instanceof DateType || f.getType() instanceof DateTimeType) {
					f.setDefaultValue(null);
					f.setFormat(null);
					f.setLength(null);
					f.setNotNull(false);
					f.setReference(null);
				}
				else if (f.getType() instanceof ComponentType || f.getType() instanceof EnumType) {
					f.setDefaultValue(null);
					f.setFormat(null);
					f.setLength(null);
					f.setNotNull(true);
					f.setReference(null);
				}
			}
			
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			Field f = component.getFields().get(rowIndex);
			if (f.getType() instanceof PrimaryKeyType) {
				return false;
			}
			
			if (columnIndex == 0) {
				return false;
			}
			else if (columnIndex ==1) {
				return true;
			}
			else if (columnIndex == 2) {
				return true;
			}
			else if (columnIndex == 3 && (f.getType() instanceof StringType || f.getType() instanceof NameType)) {
				return true;
			}
			else if (columnIndex == 4 && (f.getType() instanceof ComponentType || f.getType() instanceof EnumType)) {
				return true;
			}
			else if (columnIndex == 6 ) {
				return true;
			}
			else if (columnIndex == 7 && (f.getType() instanceof StringType || f.getType() instanceof IntegerType) || f.getType() instanceof DateType || f.getType() instanceof DateTimeType) {
				return true;
			}
			else if (columnIndex == 8) {
				return true;
			}
			
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Field f = component.getFields().get(rowIndex);
			switch (columnIndex) {
				case 0: return rowIndex+1;
				case 1: return f.getName();
				case 2: return f.getType();
				case 3: return f.getLength();
				case 4: return f.getReference();
				case 5: return f.getFormat();
				case 6: return f.isNotNull();
				case 7: return f.getDefaultValue();
				case 8: return f.isSearch();
			}

			return null;
		}
		
		public Field appendField() {
			Field field = new Field("Field"+FieldIndex++);
			component.addField(field);
			fireAddFieldEvent(component.getFields().size());
			return field;
		}
		
		public Field deleteField(int row) {
			Field f = component.getFields().get(row);
			component.deleteField(f);
			fireDeleteFieldEvent(row);
			return f;
		}
		
		@Override
		public int getRowCount() {
			return component.getFields().size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return columnNames[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 3) {
				return Integer.class;
			}
			else if (columnIndex == 6) {
				return Boolean.class;
			}
			else if (columnIndex == 8) {
				return Boolean.class;
			}
			return String.class;
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			listeners.add(l);
			
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			listeners.remove(l);
		}
		
		private void fireAddFieldEvent(int rowIndex) {
			TableModelEvent event = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
			fireRowsChange(event);
		}
		
		private void fireUpdateFieldEvent(int rowIndex) {
			TableModelEvent event = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
			fireRowsChange(event);
		}
		
		private void fireDeleteFieldEvent(int rowIndex) {
			TableModelEvent event = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
			fireRowsChange(event);
		}
		
		private void fireRowsChange(TableModelEvent event) {
			for (TableModelListener l : listeners) {
				l.tableChanged(event);
			}
		}
		
	}
	
}
