package de.tum.in.fedsparql.inference.framework.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.tum.in.fedsparql.inference.framework.DatabaseID;
import de.tum.in.fedsparql.inference.framework.Script;

public class ScriptForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3891748313611987879L;
	private JTextField textField;
	private JTable inputTable;
	private JTable outputTable;
	private JTextArea textArea;
	private static int scriptCounter=0;

	public ScriptForm(GUI gui) {
		this(gui, new Script("NewScript"+(++scriptCounter)));
	}

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public ScriptForm(final GUI gui, final Script script) {
		setMinimumSize(new Dimension(0, 0));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(50dlu;min):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow(3)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,}));

		JPanel panel = new JPanel();
		add(panel, "2, 2, 3, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		JLabel lblScriptName = new JLabel("Script name:");
		panel.add(lblScriptName, "1, 1, right, default");

		textField = new JTextField();
		panel.add(textField, "3, 1, fill, default");
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 4");

		inputTable = new JTable();
		inputTable.setRowHeight(18);
		inputTable.setBackground(UIManager.getColor("Button.background"));
		inputTable.setShowGrid(false);
		inputTable.setRowSelectionAllowed(false);
		Object[][] objectArray1 = new Object[gui.getDatabaseList().size()][2];
		for (int i = 0; i < gui.getDatabaseList().size(); i++) {
			boolean bool = false;
			if (script.inputDatabases != null) {
				if (script.inputDatabases.contains(gui.getDatabaseList().get(i))) {
					bool = true;
				}
			}
			objectArray1[i] = new Object[] {bool, gui.getDatabaseList().get(i).getName()};
		}
		inputTable.setModel(new DefaultTableModel(objectArray1, new String[] {"", "Input Databases"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6948798906173992975L;
			Class<?>[] columnTypes = new Class[] {
					Boolean.class, String.class
			};
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		inputTable.getColumnModel().getColumn(0).setResizable(false);
		inputTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		inputTable.getColumnModel().getColumn(0).setMinWidth(20);
		inputTable.getColumnModel().getColumn(0).setMaxWidth(20);
		inputTable.getColumnModel().getColumn(1).setMinWidth(75);
		scrollPane.setViewportView(inputTable);

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "4, 4");

		outputTable = new JTable();
		outputTable.setRowHeight(18);
		outputTable.setBackground(UIManager.getColor("Button.background"));
		outputTable.setShowGrid(false);
		outputTable.setRowSelectionAllowed(false);
		Object[][] objectArray2 = new Object[gui.getDatabaseList().size()][3];
		for (int i = 0; i < gui.getDatabaseList().size(); i++) {
			boolean bool = false;
			boolean freshDB = false;
			if (script.outputDatabases != null) {
				if (script.outputDatabases.contains(gui.getDatabaseList().get(i))) {
					bool = true;
					for (DatabaseID db: script.outputDatabases) {
						if (db.equals(gui.getDatabaseList().get(i))) {
							freshDB = db.isFresh();
						}
					}
				}
			}
			objectArray2[i] = new Object[] {bool, freshDB, gui.getDatabaseList().get(i).getName()};
		}
		outputTable.setModel(new DefaultTableModel(objectArray2, new String[] {"", "*", "Output Databases"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6300798795385609709L;
			Class<?>[] columnTypes = new Class[] {
					Boolean.class, Boolean.class, Object.class
			};
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		outputTable.getColumnModel().getColumn(0).setResizable(false);
		outputTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		outputTable.getColumnModel().getColumn(0).setMinWidth(20);
		outputTable.getColumnModel().getColumn(0).setMaxWidth(20);
		outputTable.getColumnModel().getColumn(1).setResizable(false);
		outputTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		outputTable.getColumnModel().getColumn(1).setMinWidth(20);
		outputTable.getColumnModel().getColumn(1).setMaxWidth(20);
		outputTable.getColumnModel().getColumn(2).setMinWidth(75);
		scrollPane_1.setViewportView(outputTable);

		JLabel lblNewLabel = new JLabel("Script code:");
		add(lblNewLabel, "2, 6, 3, 1");

		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "2, 8, 3, 1, fill, fill");

		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				script.id = textField.getText();
				script.jScript = textArea.getText();
				script.inputDatabases = createDatabaseSet(inputTable.getModel());
				script.outputDatabases = createDatabaseSet(outputTable.getModel());
				gui.refreshList();
			}
		});
		add(btnNewButton, "2, 10, 3, 1, left, default");

		textField.setText(script.id);
		textArea.setText(script.jScript);

	}

	String getTextField() {
		return textField.getText();
	}

	JTable getInputTable() {
		return inputTable;
	}

	JTable getOutputTable() {
		return outputTable;
	}

	JTextArea getTextArea() {
		return textArea;
	}

	Set<DatabaseID> createDatabaseSet(TableModel model) {
		HashSet<DatabaseID> set = new HashSet<DatabaseID>();
		for (int i = 0; i < model.getRowCount() - 1; i++) {
			if ((boolean)model.getValueAt(i, 0)) {
				if (model.getColumnCount() > 2) { // if 2 columns -> input DB, if 3 columns -> output DB
					System.out.println((boolean)model.getValueAt(i, 1));
					set.add(new DatabaseID(((String)model.getValueAt(i, 2)), (boolean)model.getValueAt(i, 1)));
				} else {
					set.add(new DatabaseID(((String)model.getValueAt(i, 1))));
				}
			}
		}
		return set;
	}

}
