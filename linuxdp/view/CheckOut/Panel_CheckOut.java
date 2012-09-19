package CheckOut;

import hotel.linuxdp.java.controller.CheckOutController;
import hotel.linuxdp.java.model.CheckOut;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_CheckOut extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable jTable;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator;

	public Panel_CheckOut() {
		initComponents();
		all();
		buttonInsert.addActionListener(this);
	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jTable(), "grow, span,width max,height max");
		add(new JSeparator(), "width max, height 5,span");
		add(createButtons(), "right,dock south");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("ID");
		tableTitle.add("FirstName");
		tableTitle.add("WorkPhone");
		tableTitle.add("Email");
		tableTitle.add("Arrival");
		tableTitle.add("NumberGuest");
		tableTitle.add("RoomRate");
		tableTitle.add("CheckOutDate");
		tableTitle.add("ReservationID");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (CheckOut checkOut : CheckOutController.checkOutController
					.all()) {
				model.insertRow(0, checkOut.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

	public static Boolean autoscroll = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			Insert_CheckOut checkOut = new Insert_CheckOut(null, autoscroll);
			checkOut.setVisible(true);
		}
		if (e.getSource() == buttonRefresh) {
			int c = model.getRowCount();
			for (int ii = c - 1; ii >= 0; ii--) {
				model.removeRow(ii);
				jTable.revalidate();
			}
			all();
		}
	}

}
