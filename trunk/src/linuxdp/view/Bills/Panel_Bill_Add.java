package Bills;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import hotel.linuxdp.java.controller.BillsController;
import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.model.Bills;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.BillsListener;
import hotel.linuxdp.util.IconButton;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("rawtypes")
public class Panel_Bill_Add extends JDialog implements ActionListener,
		BillsListener {

	private static final long serialVersionUID = 1L;
	private JTextArea txtComment;
	private JComboBox boxReservationID;
	private Integer resID;
	private JButton buttonInsert;
	Hashtable hashRese = new Hashtable();

	public Panel_Bill_Add(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setSize(400, 200);
		setLocation(600, 300);
		setLayout(new MigLayout());
		add(initJPanel());
		allReservation();
		buttonInsert.addActionListener(this);
		BillsController.billController.addAdminListener(this);
	}

	private JPanel initJPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[grow]"));

		txtComment = new JTextArea();
		boxReservationID = new JComboBox();
		buttonInsert = new JButton();
		buttonInsert.setIcon(new IconButton().insertIcon());

		add(new JLabel("Reservation"), "right");
		add(boxReservationID, "width max,height 22");

		add(new JLabel("Comment"), "right");
		add(txtComment, "width max,height 22");
		add(buttonInsert);
		return panel;
	}

	@SuppressWarnings("unchecked")
	private void allReservation() {
		try {
			for (Reservation reservation : ReservationController.ReservationController
					.all()) {
				boxReservationID.addItem(reservation.getExpectedCheckinDate());
				hashRese.put(reservation.getExpectedCheckinDate(),
						reservation.getReservationID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Panel_Bills bills = new Panel_Bills();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				resID = (Integer) hashRese.get(boxReservationID
						.getSelectedItem().toString().trim());
				Bills bill = new Bills(resID, txtComment.getText());
				BillsController.billController.save(bill);
				int c = bills.getModel().getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					bills.getModel().removeRow(i);
					bills.getJtable().revalidate();
				}
				bills.allBill();
				this.dispose();
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void roleadd(EventAll<Bills> event) {
		bills.getModel().insertRow(0, event.getSource().toArray());
	}

}
