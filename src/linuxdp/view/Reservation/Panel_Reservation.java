package Reservation;

import hotel.linuxdp.java.controller.*;
import hotel.linuxdp.java.model.Customer;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.model.RoomType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.ReservationListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Reservation extends JPanel implements ActionListener,
		MouseListener, ReservationListener {

	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtArrivalTime, txtNumberGuests, txtRoomRate;
	private static JDateChooser txtCheckIn;
	private JDateChooser txtCheckOut;
	private JComboBox boxCustomer, boxRoomType;
	private JTextArea txtComments;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	JSeparator separator1 = new JSeparator();
	private Integer ID;
	Calendar calendarStar, calendarStop;
	Hashtable hashtableCus = new Hashtable();
	Hashtable hashtableRoomType = new Hashtable();
	private Integer roomID;
	private static Integer arrivalTime;

	public Panel_Reservation() {
		initComponents();
		all();
		loadRoomType();
		loadCus();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		ReservationController.ReservationController.addDiscountListener(this);
		jTable.addMouseListener(this);
		dateChooser();
		txtCheckIn.addMouseListener(this);
		txtCheckOut.addMouseListener(this);

	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jTable(), "span,width max,gap top 2");
		add(separator, "span,width max, height 5 , gap top 7, gap bottom 7");
		add(createForm(), "span,grow");
		add(separator1, "span,width max, height 5");
		add(createButtons(), "right,dock south");
	}

	@SuppressWarnings("unchecked")
	public JPanel jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane = new JScrollPane();
		jTable = new JTable();

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
		tableTitle.add("Comments");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);
		return panel;
	}

	private void all() {
		try {
			for (Reservation reservation : ReservationController.ReservationController
					.all()) {
				model.insertRow(0, reservation.toArray());
			}
		} catch (Exception EX) {

		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		LC layC = new LC().fill().wrap();
		AC colC = new AC().align("NORTH", 0).fill(4, 0).grow(100, 1, 3)
				.align("right", 2).gap("15", 1);
		AC rowC = new AC().align("bottom", 0).fill(4, 0);
		titleData = new JPanel();
		titleData.setLayout(new MigLayout(layC, colC, rowC));
		titleData.setBorder(BorderFactory.createTitledBorder(""));
		txtArrivalTime = new JTextField();

		calendarStar = Calendar.getInstance();
		txtCheckIn = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtCheckIn.setCalendar(calendarStar);

		calendarStop = Calendar.getInstance();
		txtCheckOut = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtCheckOut.setCalendar(calendarStop);

		txtComments = new JTextArea();
		txtNumberGuests = new JTextField();
		boxCustomer = new JComboBox();
		txtRoomRate = new JTextField();
		boxRoomType = new JComboBox();
		/**
		 * Create Clear 2 column
		 */
		titleData.add(new JLabel("Check In"), "right");
		titleData.add(txtCheckIn, "grow,width 250,height 25,gap top 8");

		titleData.add(new JLabel("Check Out:"), "right");
		titleData.add(txtCheckOut, "grow,width 250,height 25,gap top 8");
		/**
		 * Create Clear 2 column
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * Create Clear 2 column
		 */
		titleData.add(new JLabel("Room Rate"), "right");
		titleData.add(txtRoomRate, "grow,width 250,height 25,gap top 10");

		titleData.add(new JLabel("Arrival Time:"), "right");
		titleData.add(txtArrivalTime, "grow,width 250,height 25,gap top 10");
		/**
		 * Create Clear 2 column
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("CustomerName"), "right");
		titleData.add(boxCustomer, "grow,width 250,height 25,gap top 10");

		titleData.add(new JLabel("Number Guests"), "right");
		titleData.add(txtNumberGuests, "grow,width 250,height 25,gap top 10");
		/**
		 * 
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("RoomType"), "right");
		titleData.add(boxRoomType,
				"grow,width 250,height 25,gap top 8,gap bottom 8");

		titleData.add(new JLabel("Comments"), "right");
		titleData.add(txtComments,
				"grow,width 250,height 25,gap top 8,gap bottom 8");

		panel.add(titleData);
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

	@SuppressWarnings("unchecked")
	private void loadCus() {
		try {
			for (Customer customer : CustomerController.customerController
					.all()) {
				boxCustomer.addItem(customer.getFirstName());
				hashtableCus.put(customer.getFirstName(),
						customer.getCustomerID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadRoomType() {
		try {
			for (RoomType roomType : RoomTypeController.roomTypeController
					.all()) {
				boxRoomType.addItem(roomType.getRoomType());
				hashtableRoomType.put(roomType.getRoomType(),
						roomType.getRoomTypeID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dateChooser() {

		Calendar checkInCalendar = txtCheckIn.getCalendar();
		Calendar checkOutCalendar = txtCheckIn.getCalendar();

		int checkInDay = checkInCalendar.get(Calendar.DAY_OF_MONTH);
		int checkInMonth = checkInCalendar.get(Calendar.MONTH);
		int checkInYear = checkInCalendar.get(Calendar.YEAR);

		int checkOutDay = checkOutCalendar.get(Calendar.DAY_OF_MONTH);
		int checkOutMonth = checkOutCalendar.get(Calendar.MONTH);
		int checkOutYear = checkOutCalendar.get(Calendar.YEAR);

		if (checkInDay == checkOutDay && checkInMonth == checkOutMonth
				&& checkInYear == checkOutYear) {
			arrivalTime = checkOutDay - checkInDay + 1;
			txtArrivalTime.setText(arrivalTime.toString());
		}
		if (checkInDay < checkOutDay && checkInMonth == checkOutMonth
				&& checkInYear == checkOutYear) {
			arrivalTime = checkOutDay - checkInDay;
			txtArrivalTime.setText(arrivalTime.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			if (txtArrivalTime.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "txtArrivalTime");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dateIn = sdf.format(txtCheckIn.getDate());
				String dateOut = sdf.format(txtCheckIn.getDate());

				Integer cus = (Integer) (hashtableCus.get(boxCustomer
						.getSelectedItem().toString().trim()));
				roomID = (Integer) (hashtableRoomType.get(boxRoomType
						.getSelectedItem().toString().trim()));

				Integer arr = Integer.parseInt(txtArrivalTime.getText().trim());
				Integer numberGuests = Integer.parseInt(txtNumberGuests
						.getText().trim());
				try {
					Reservation reservation = new Reservation(dateIn, dateOut,
							arr, numberGuests, txtRoomRate.getText(),
							txtComments.getText(), cus, roomID);
					ReservationController.ReservationController
							.save(reservation);
					JOptionPane.showMessageDialog(this, "d");
					int c = model.getRowCount();
					for (int i = c - 1; i >= 0; i--) {
						model.removeRow(i);
						jTable.revalidate();
					}
					all();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		if (e.getSource() == buttonUpdate) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String dateIn = sdf.format(txtCheckIn.getDate());
			String dateOut = sdf.format(txtCheckIn.getDate());

			Integer cus = (Integer) (hashtableCus.get(boxCustomer
					.getSelectedItem().toString().trim()));
			Integer arr = Integer.parseInt(txtArrivalTime.getText().trim());
			Integer numberGuests = Integer.parseInt(txtNumberGuests.getText()
					.trim());
			roomID = (Integer) (hashtableRoomType.get(boxRoomType
					.getSelectedItem().toString().trim()));
			try {
				Reservation reservation = new Reservation(dateIn, dateOut, arr,
						numberGuests, txtRoomRate.getText(), txtComments
								.getText().trim(), cus, roomID);
				reservation.setReservationID(ID);
				ReservationController.ReservationController.update(reservation);
				JOptionPane.showMessageDialog(this, "true");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			try {
				ReservationController.ReservationController.delete(ID);
				JOptionPane.showMessageDialog(this, "true");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
		}
		if (e.getSource() == txtCheckIn && e.getSource() == txtCheckOut) {
			dateChooser();
		}

	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "ban nen chon 1 row");
			} else {
				try {
					DateFormat formatterIn = new SimpleDateFormat("MM/dd/yyyy");
					buttonInsert.setEnabled(false);
					buttonUpdate.setEnabled(true);
					buttonDelete.setEnabled(true);
					Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);

					ID = Integer.parseInt(vDongDaChon.get(0).toString());
					String cusID = vDongDaChon.get(1).toString();
					String cusPhone = vDongDaChon.get(2).toString();
					String cusEmail = vDongDaChon.get(3).toString();
					String checkIn = vDongDaChon.get(4).toString();
					String checkOut = vDongDaChon.get(5).toString();
					String arrivalTime = vDongDaChon.get(6).toString();
					String numbedGuest = vDongDaChon.get(7).toString();
					String roomRate = vDongDaChon.get(8).toString();
					String roomID = vDongDaChon.get(9).toString();
					String comment = vDongDaChon.get(10).toString();

					Date dateIn = formatterIn.parse(checkIn);
					Date dateOut = formatterIn.parse(checkOut);

					txtCheckIn.setDate(dateIn);
					txtCheckOut.setDate(dateOut);
					txtArrivalTime.setText(arrivalTime);
					txtNumberGuests.setText(numbedGuest);
					txtRoomRate.setText(roomRate);
					txtComments.setText(comment);
					boxCustomer.setSelectedItem(cusID.toString());
					boxRoomType.setSelectedItem(roomID.toString());

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void roleadd(EventAll<Reservation> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
