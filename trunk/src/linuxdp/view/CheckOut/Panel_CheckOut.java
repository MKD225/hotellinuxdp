package CheckOut;

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
public class Panel_CheckOut extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable tableRole;
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

	public Panel_CheckOut() {
		initComponents();

	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(tableRole(), "grow, span,width max");
		add(createForm(), "grow,span,gap bottom 20");
	}

	@SuppressWarnings("unchecked")
	private JComponent tableRole() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		tableRole = new JTable();
		tableTitle.add("RoleID");
		tableTitle.add("RoleName");
		tableTitle.add("Description");

		model = new DefaultTableModel(tableRecords, tableTitle);
		tableRole.setModel(model);
		tableRole.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableRole);
		panel.add(scrollPane);

		return panel;
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
