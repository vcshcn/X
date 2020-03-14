package net.xway.code.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import net.xway.code.model.EnumComponent;
import net.xway.code.model.EnumValue;

public class EnumComponentPanel extends JPanel {

	private EnumComponentNode leftNode;
	private EnumComponent enumComponent;
	
	public EnumComponentPanel(EnumComponentNode node, EnumComponent enumComponent) {
		this.leftNode = node;
		this.enumComponent = enumComponent;
		
		initComponents();
		updateComponentToUI();
	}
	
	private void initComponents() {
		lblEnumComponentName = new JLabel("Enum Component Name");
		txtEnumComponentName = new JTextField(20);
		lblEnumComponentComment = new JLabel("Enum Component Comment");
		txtEnumComponentComment = new JTextArea(3,20);
		txtEnumComponentComment.setBorder(txtEnumComponentName.getBorder());
		btnOK = new JButton("OK");
		btnOK.addActionListener((ActionEvent e) -> {
			updateUIToComponent();
		});
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener((ActionEvent e) -> {
			updateComponentToUI();
		});
		
		pnlEnumPanel = new JPanel();
		GroupLayout layout = new GroupLayout(pnlEnumPanel);
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    pnlEnumPanel.setLayout(layout);
	    
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup().addComponent(lblEnumComponentName).addComponent(txtEnumComponentName))
					.addGroup(layout.createParallelGroup().addComponent(lblEnumComponentComment).addComponent(txtEnumComponentComment))
					.addGroup(layout.createParallelGroup().addComponent(btnOK).addComponent(btnCancel))
		);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup().addComponent(lblEnumComponentName).addComponent(lblEnumComponentComment))
					.addGroup(layout.createParallelGroup().addComponent(txtEnumComponentName).addComponent(txtEnumComponentComment)
					.addGroup(layout.createSequentialGroup().addComponent(btnOK).addComponent(btnCancel)))
		);

		//
		tableModel = new EnumValueTableModel();
		tabFields = new JTable(tableModel);
		tabFields.getColumnModel().getColumn(0).setMaxWidth(100);
		tabFields.getColumnModel().getColumn(1).setMaxWidth(200);
		pnlTable = new JScrollPane(tabFields);
		
		btnAddValue = new JButton("Add Enum");
		btnAddValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableModel.insertEnumValue();
			}
			
		});
		btnDeleteValue = new JButton("Delete Enum");
		btnDeleteValue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabFields.getSelectedRow() != -1) {
					tableModel.deleteEnumValue(tabFields.getSelectedRow());
				}
			}
			
		});
		
		barValuesButtons = new JToolBar();
		barValuesButtons.add(btnAddValue);
		barValuesButtons.add(btnDeleteValue);
		
		pnlValuesPanel = new JPanel();
		pnlValuesPanel.setLayout(new BorderLayout());
		pnlValuesPanel.add(barValuesButtons, BorderLayout.NORTH);
		pnlValuesPanel.add(pnlTable, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(pnlEnumPanel, BorderLayout.NORTH);
		add(pnlValuesPanel, BorderLayout.CENTER);
	}
	
	private void updateComponentToUI() {
		txtEnumComponentName.setText(enumComponent.getName());
		txtEnumComponentComment.setText(enumComponent.getComment());
	}
	
	private void updateUIToComponent() {
		enumComponent.setName(txtEnumComponentName.getText());
		enumComponent.setComment(txtEnumComponentComment.getText());
		
		leftNode.nodeChanged();
	}

	private JPanel pnlEnumPanel;
	private JPanel pnlValuesPanel;
	
	private JLabel lblEnumComponentName;
	private JTextField txtEnumComponentName;
	private JLabel lblEnumComponentComment;
	private JTextArea txtEnumComponentComment;
	private JButton btnOK;
	private JButton btnCancel;
	
	private JToolBar barValuesButtons;
	private JButton btnAddValue;
	private JButton btnDeleteValue;
	private JScrollPane pnlTable;
	private JTable tabFields;
	private EnumValueTableModel tableModel;
	
	class EnumValueTableModel implements TableModel {
		private List<TableModelListener> listeners = new ArrayList<>();

		public EnumValue insertEnumValue() {
			EnumValue ev = new EnumValue(enumComponent.getValues().size()+1, "Value" + enumComponent.getValues().size(), "");
			enumComponent.addValue(ev);
			fireAddFieldEvent(enumComponent.getValues().size());
			return ev;
		}
		
		public EnumValue deleteEnumValue(int index) {
			EnumValue ev = enumComponent.getValues().remove(index);
			fireDeleteFieldEvent(index);
			return ev;
		}
		
		@Override
		public int getRowCount() {
			return enumComponent.getValues().size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "Key";
			}
			else if (columnIndex == 1) {
				return "Value";
			}
			else {
				return "Description";
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 0) {
				return Integer.TYPE;
			}
			else {
				return String.class;
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return enumComponent.getValues().get(rowIndex).key;
			}
			else if (columnIndex == 1) {
				return enumComponent.getValues().get(rowIndex).value;
			}
			else {
				return enumComponent.getValues().get(rowIndex).description;
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			EnumValue ev = enumComponent.getValues().get(rowIndex);
			if (columnIndex == 0) {
				ev.key = (Integer) aValue;
			}
			else if (columnIndex == 1) {
				ev.value = (String) aValue;
			}
			else if (columnIndex == 2) {
				ev.description = (String) aValue;
			}
			
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
