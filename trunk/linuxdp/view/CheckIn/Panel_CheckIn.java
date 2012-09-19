package CheckIn;

import hotel.linuxdp.java.controller.CheckInController;
import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CheckInListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_CheckIn extends JPanel implements ActionListener,
		CheckInListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private JXTaskPaneContainer tpc;
	private DefaultTableModel model, model2;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	private Integer id;

	public Panel_CheckIn() {
		initComponents();
		all();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		CheckInController.checkInController.addCheckInListener(this);

	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(jTable(), "span,width max,gap top 5,height max");
		add(separator, "span,width max,height 5,gap top 5");
		add(createButtons(), "span,right");
		add(JXTaskPane(), "dock south,width max,hmax 250");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		setjTable(new JTable());
		tableTitle.add("ID");
		tableTitle.add("ResID");
		tableTitle.add("FirstName");
		tableTitle.add("BirthDay");
		tableTitle.add("ExpCheckInDate");
		tableTitle.add("ExpCheckOut");
		tableTitle.add("CheckInDate");
		tableTitle.add("RoomNumber");
		tableTitle.add("NumGuests");
		tableTitle.add("RoomType");
		tableTitle.add("NumBeds");
		tableTitle.add("<html><font color=red>RoomStatus</font></html>");

		setModel(new DefaultTableModel(tableRecords, tableTitle));
		getjTable().setModel(getModel());
		getjTable().getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(getjTable());
		panel.add(scrollPane);

		return panel;

	}

	public void all() {
		try {
			for (CheckIn checkIn : CheckInController.checkInController.all()) {
				getModel().insertRow(0, checkIn.toArray());
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

	private JComponent JXTaskPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tpc = new JXTaskPaneContainer();

		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setTitle("Reservation");
		taskPane.add(panelReservation());
		taskPane.setCollapsed(false);
		tpc.add(taskPane);
		panel.add(tpc);
		return panel;
	}

	@SuppressWarnings("unchecked")
	public JPanel panelReservation() {
		Vector tableRecord = new Vector();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		model2 = new DefaultTableModel();

		Vector tableTitle = new Vector();
		JTable tableCustomer1 = new JTable();

		tableTitle.add("ID");
		tableTitle.add("CusName");
		tableTitle.add("CusPhone");
		tableTitle.add("CusMail");
		tableTitle.add("CheckIn");
		tableTitle.add("CheckOut");
		tableTitle.add("ArrivalTime");
		tableTitle.add("NumberGuests");
		tableTitle.add("RoomRate");
		tableTitle.add("RoomType");

		model2 = new DefaultTableModel(tableRecord, tableTitle);
		tableCustomer1.setModel(model2);
		tableCustomer1.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableCustomer1);
		panel.add(scrollPane);
		reservation();
		return panel;

	}

	private void reservation() {
		try {
			for (Reservation reservation : ReservationController.ReservationController
					.all()) {
				model2.insertRow(0, reservation.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean rootPaneCheckingEnabled = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			Insert_CheckIn out = new Insert_CheckIn(null,
					rootPaneCheckingEnabled);
			out.setVisible(true);
		}
		if (e.getSource() == buttonUpdate) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "hay chon 1 ");
			} else {
				id = Integer.parseInt(jTable.getValueAt(iDongDaChon, 0)
						.toString());
				String reservation = jTable.getValueAt(iDongDaChon, 1)
						.toString();
				String checkInDate = jTable.getValueAt(iDongDaChon, 4)
						.toString();
				String roomNumber = jTable.getValueAt(iDongDaChon, 6).toString();
				Update_CheckIn checkIn = new Update_CheckIn();
				checkIn.setCheckInID(id);
				checkIn.setResevration(reservation);
				checkIn.setCheckDate(checkInDate);
				checkIn.setRoomNumber(roomNumber);
				System.out.println(roomNumber);
				checkIn.setVisible(true);
			}
		}
		if (e.getSource() == buttonDelete) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "hay chon 1 ");
			} else {
				id = Integer.parseInt(jTable.getValueAt(iDongDaChon, 0)
						.toString());
				//CheckInController.checkInController.deleteCheckIn(id);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
				JOptionPane.showMessageDialog(this, "ok");
			}
		}
		if (e.getSource() == buttonRefresh) {
			int c = model.getRowCount();
			for (int i = c - 1; i >= 0; i--) {
				model.removeRow(i);
				jTable.revalidate();
			}
			all();
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int iDongDaChon = jTable.getSelectedRow();
		if (iDongDaChon != 0) {
			buttonDelete.setEnabled(true);
			buttonUpdate.setEnabled(true);
			Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
			id = Integer.parseInt(vDongDaChon.get(0).toString());
			String resv = vDongDaChon.get(1).toString();
			String firstname = vDongDaChon.get(2).toString();
			String checkIn = vDongDaChon.get(3).toString();
			String checkOut = vDongDaChon.get(4).toString();
			String checkIndate = vDongDaChon.get(5).toString();
			String roomNumber = vDongDaChon.get(6).toString();
			String nugues = vDongDaChon.get(7).toString();
		}

	}

	public JTable getjTable() {
		return jTable;
	}

	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	@Override
	public void roleadd(EventAll<CheckIn> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
