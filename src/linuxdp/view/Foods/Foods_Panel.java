package Foods;

import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Foods_Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable jTable;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtRoleName, txtRoleID;
	private JTextArea txtRoleDes;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator;

	public Foods_Panel() {
		initComponents();
		all();
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout("wrap 3", "[] [grow] []",
				"[] [grow] [grow] [grow] []");
		setLayout(layout);
		add(getTable(), "grow, span,gap top 1,height 250");
		add(createForm(), "grow,span,gap bottom 20");
	}

	public JPanel getTable() {
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		panelTable.add(jTable());
		return panelTable;
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("ID");
		tableTitle.add("BillItemType");
		tableTitle.add("Description");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {

		} catch (Exception ex) {

		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("Role ID:"), "right");
		txtRoleID = new JTextField();
		txtRoleID.setEnabled(false);
		txtRoleID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtRoleID, "hmin 23,width 450,span");

		txtRoleName = new JTextField();
		titleData.add(new JLabel("Role Name:"), "right");
		titleData.add(txtRoleName, "hmin 23,width max,span");

		txtRoleDes = new JTextArea();
		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtRoleDes, "span, growx,hmin 120");

		panel.add(titleData, "span");
		separator = new JSeparator();
		panel.add(separator, "width max, hmin 5,span,gap top 10,gap bottom 10");

		panel.add(createButtons(), "dock south");
		return panel;
	}

	private JPanel createButtons() {
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new MigLayout("right", "[]"));
		buttonInsert = new JButton("New");
		buttonInsert.setIcon(new IconButton().insertIcon());
		panelButton.add(buttonInsert);

		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new IconButton().updateIcon());
		buttonUpdate.setEnabled(false);
		panelButton.add(buttonUpdate);

		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
		buttonDelete.setEnabled(false);
		panelButton.add(buttonDelete);

		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new IconButton().refreshIcon());
		panelButton.add(buttonRefresh);

		return panelButton;
	}
}
