package Bills;

import hotel.linuxdp.java.controller.BillsController;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import View_Packer.cli_Reservation;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Bills extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtable;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	public static JXTaskPaneContainer tpc;
	public JXTaskPane taskPane;

	public boolean rootPaneCheckingEnabled = true;
	
	public Panel_Bills() {
		initComponents();
		allBill();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jtable(), "grow, span,gap top 1,height max,width max");
		add(separator, "span,width max,height 5");
		add(createButtons(), "right,span");
		add(JXTaskPane(), "dock south,width max,hmax 300");
	}

	@SuppressWarnings("unchecked")
	private JComponent jtable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane = new JScrollPane();
		setJtable(new JTable());

		tableTitle.add("BillID");
		tableTitle.add("FirstName");
		tableTitle.add("RoomType");
		tableTitle.add("CheckIn");
		tableTitle.add("CheckOut");
		tableTitle.add("ArrivalTime");
		tableTitle.add("NumberGuests");
		tableTitle.add("RoomRate");
		tableTitle.add("Comments");

		setModel(new DefaultTableModel(tableRecords, tableTitle));
		getJtable().setModel(getModel());
		getJtable().getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(getJtable());
		panel.add(scrollPane);

		return panel;
	}

	public void allBill() {
		try {
			for (hotel.linuxdp.java.model.Bills bills : BillsController.billController
					.all()) {
				getModel().insertRow(0, bills.toArray());
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

	public JComponent JXTaskPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tpc = new JXTaskPaneContainer();
		taskPane = new JXTaskPane();
		taskPane.setTitle("Reservation");
		taskPane.setCollapsed(false);
		taskPane.add(new cli_Reservation());
		tpc.add(taskPane);
		panel.add(tpc);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
		}
		if (e.getSource() == buttonUpdate) {

		}
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getJtable() {
		return jtable;
	}

	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}
}
