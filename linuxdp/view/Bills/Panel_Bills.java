package Bills;

import hotel.linuxdp.java.controller.BillItemController;
import hotel.linuxdp.java.controller.BillsController;
import hotel.linuxdp.java.model.BillItem;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import View_Packer.Cli_Reservation;
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
	private int error = 1;
	public boolean rootPaneCheckingEnabled = true;
	private Integer id;

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
		tableTitle.add("Reservation");

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
		panelButton.add(buttonUpdate);

		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
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
		taskPane.add(new Cli_Reservation());
		tpc.add(taskPane);
		panel.add(tpc);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			Insert_Bills bills = new Insert_Bills();
			bills.setVisible(true);
		}
		if (e.getSource() == buttonUpdate) {
			Update_Bills bills = new Update_Bills();
			int iDongDaChon = jtable.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "choose 1 row");
			} else {
				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
				Integer idBills = Integer.parseInt(vDongDaChon.get(0)
						.toString());
				String idReservation = vDongDaChon.get(9).toString();
				String comment = vDongDaChon.get(8).toString();

				bills.setIdBill(idBills);
				bills.setIdReservation(idReservation);
				bills.setComment(comment);
				bills.setVisible(true);
			}
		}
		if (e.getSource() == buttonDelete) {
			int lsRemote = jtable.getSelectedRow();
			if (lsRemote == -1) {
				JOptionPane.showMessageDialog(this, "choose 1 row");
			} else {
				id = Integer.valueOf(jtable.getValueAt(lsRemote, 0).toString());
				try {
					List<BillItem> temp = BillItemController.billItemController
							.all();
					for (int i = 0; i < temp.size(); i++) {
						if (id == temp.get(i).getBillID()) {
							this.setError(1);
						} else {
							this.setError(0);
							BillsController.billController.deleteAdmin(id);
							int c = model.getRowCount();
							for (int ii = c - 1; ii >= 0; ii--) {
								model.removeRow(ii);
								jtable.revalidate();
							}
							allBill();
						}
					}
					JOptionPane.showMessageDialog(this, "Delete to succeed !");
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(this,
							"can't delete then bill Item still");
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			int c = model.getRowCount();
			for (int ii = c - 1; ii >= 0; ii--) {
				model.removeRow(ii);
				jtable.revalidate();
			}
			allBill();
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

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

}
