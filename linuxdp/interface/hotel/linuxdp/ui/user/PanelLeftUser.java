package hotel.linuxdp.ui.user;

import hotel.linuxdp.ui.UserFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.*;
import Payment.Panel_Payment;
import PaymentType.Panel_PaymentType;
import Report.Panel_Report;
import Reservation.Panel_Reservation;
import Roles.Panel_Roles;
import RoomStatus.Panel_RoomStatus;
import RoomType.Panel_RoomType;
import Rooms.Panel_Rooms;
import Administrator.Panel_Administrator;
import BillItemType.Panel_BillItemType;
import BillItem_Discount.Panel_BillItem;
import Bills.Panel_Bills;
import CheckIn.Panel_CheckIn;
import CheckOut.Panel_CheckOut;
import Customer.Panel_Customer;
import Discount.Panel_Discount;
import Foods.Panel_Foods;

/**
 * 
 * @author linuxdp
 * 
 */
public class PanelLeftUser extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6574991583881650911L;

	private JXTaskPaneContainer tpc;
	JButton buttonAdmin, buttonBillItem, buttonBillItemType, buttonBills,
			buttonCheckIn, buttonCheckOut, buttonCustomer, buttonDiscount,
			buttonFoods, buttonPayment, buttonPaymentType, buttonReport,
			buttonReservation, buttonRoles, buttonRooms, buttonRoomStatus,
			buttonRoomsType;

	public PanelLeftUser() {
		super(new BorderLayout());
		add(JXTaskPane());
	}

	private JComponent JXTaskPane() {
		JPanel panel = new JPanel();
		MigLayout layout = new MigLayout();
		panel.setLayout(new BorderLayout());
		tpc = new JXTaskPaneContainer();

		JXTaskPane systemGroup = new JXTaskPane();
		// systemGroup.setLayout(layout);
		systemGroup.setTitle("System");
		systemGroup.setCollapsed(true);
		//
		buttonAdmin = new JButton("Admin");
		buttonBillItem = new JButton("Bill Item");
		buttonBillItemType = new JButton("Bill Item Type");
		buttonBills = new JButton("Bills");
		buttonCheckIn = new JButton("Check In");
		buttonCheckOut = new JButton("Check Out");
		buttonCustomer = new JButton("Customer");
		buttonDiscount = new JButton("Discount");
		buttonFoods = new JButton("Foods");
		buttonPayment = new JButton("Payment");
		buttonPaymentType = new JButton("PaymentType");
		buttonReport = new JButton("Report");
		buttonReservation = new JButton("Reservation");
		buttonRoles = new JButton("Role");
		buttonRooms = new JButton("Rooms");
		buttonRoomStatus = new JButton("Room Status");
		buttonRoomsType = new JButton("Rooms Type");
		//
		// systemGroup.add(buttonRoles, "width 185,span,dock north");
		// buttonRoles.addActionListener(this);
		// systemGroup.add(buttonAdmin, "width 185");
		// buttonAdmin.addActionListener(this);
		// tpc.add(systemGroup);

		// "Office" GROUP
		JXTaskPane officeGroup = new JXTaskPane();
		officeGroup.setLayout(layout);
		officeGroup.setTitle("Customer & Rooms");
		officeGroup.add(buttonReservation, "width 180,span,dock north");
		buttonReservation.addActionListener(this);
		officeGroup.add(buttonCustomer, "width 180,span");
		buttonCustomer.addActionListener(this);
		officeGroup.add(buttonCheckIn, "width 180,span");
		buttonCheckIn.addActionListener(this);
		officeGroup.add(buttonCheckOut, "width 180,span");
		buttonCheckOut.addActionListener(this);
		officeGroup.add(buttonDiscount, "width 180,span");
		buttonDiscount.addActionListener(this);
		officeGroup.add(buttonRooms, "width 180,span");
		buttonRooms.addActionListener(this);
		officeGroup.add(buttonRoomStatus, "width 180,span");
		buttonRoomStatus.addActionListener(this);
		officeGroup.add(buttonRoomsType, "width 180");
		buttonRoomsType.addActionListener(this);
		officeGroup.setCollapsed(false);
		// officeGroup.add(buttonFoods);
		// buttonFoods.addActionListener(this);
		tpc.add(officeGroup);
		JXTaskPane paymentJxTaskPane = new JXTaskPane("Payment");
		paymentJxTaskPane.add(buttonPayment);
		buttonPayment.addActionListener(this);
		paymentJxTaskPane.add(buttonPaymentType);
		buttonPaymentType.addActionListener(this);

		tpc.add(paymentJxTaskPane);

		JXTaskPane billJxTaskPane = new JXTaskPane("Bills");
		billJxTaskPane.add(buttonBills);
		buttonBills.addActionListener(this);
		billJxTaskPane.add(buttonBillItemType);
		buttonBillItemType.addActionListener(this);
		billJxTaskPane.add(buttonBillItem);
		buttonBillItem.addActionListener(this);
		billJxTaskPane.setCollapsed(false);
		tpc.add(billJxTaskPane);

		JXTaskPane report = new JXTaskPane();
		report.setTitle("Report & Summarize");
		report.add(buttonReport);
		buttonReport.addActionListener(this);

		report.setCollapsed(true);
		tpc.add(report);

		panel.add(new JScrollPane(tpc));

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdmin) {
			Panel_Administrator administrator = new Panel_Administrator();
			UserFrame.callJPanel(administrator);
		}
		if (e.getSource() == buttonRoles) {
			Panel_Roles roles = new Panel_Roles();
			UserFrame.callJPanel(roles);
		}
		if (e.getSource() == buttonCustomer) {
			Panel_Customer customer = new Panel_Customer();
			UserFrame.callJPanel(customer);
		}
		if (e.getSource() == buttonBills) {
			Panel_Bills bills = new Panel_Bills();
			UserFrame.callJPanel(bills);
		}

		if (e.getSource() == buttonBillItem) {
			Panel_BillItem contractPanel = new Panel_BillItem();
			UserFrame.callJPanel(contractPanel);
		}
		if (e.getSource() == buttonBillItemType) {
			Panel_BillItemType billItemType = new Panel_BillItemType();
			UserFrame.callJPanel(billItemType);
		}
		if (e.getSource() == buttonCheckIn) {
			Panel_CheckIn checkIn = new Panel_CheckIn();
			UserFrame.callJPanel(checkIn);
		}
		if (e.getSource() == buttonCheckOut) {
			Panel_CheckOut checkOut = new Panel_CheckOut();
			UserFrame.callJPanel(checkOut);
		}

		if (e.getSource() == buttonRooms) {
			Panel_Rooms rooms = new Panel_Rooms();
			UserFrame.callJPanel(rooms);
		}
		if (e.getSource() == buttonRoomStatus) {
			Panel_RoomStatus roomStatus = new Panel_RoomStatus();
			UserFrame.callJPanel(roomStatus);
		}
		if (e.getSource() == buttonRoomsType) {
			Panel_RoomType type = new Panel_RoomType();
			UserFrame.callJPanel(type);
		}
		if (e.getSource() == buttonReport) {
			Panel_Report report = new Panel_Report();
			UserFrame.callJPanel(report);
		}
		if (e.getSource() == buttonPayment) {
			Panel_Payment panel = new Panel_Payment();
			UserFrame.callJPanel(panel);
		}
		if (e.getSource() == buttonPaymentType) {
			Panel_PaymentType paymentType = new Panel_PaymentType();
			UserFrame.callJPanel(paymentType);
		}
		if (e.getSource() == buttonReservation) {
			Panel_Reservation reservation = new Panel_Reservation();
			UserFrame.callJPanel(reservation);
		}
		if (e.getSource() == buttonDiscount) {
			Panel_Discount discount = new Panel_Discount();
			UserFrame.callJPanel(discount);
		}
		if (e.getSource() == buttonFoods) {
			Panel_Foods foods = new Panel_Foods();
			UserFrame.callJPanel(foods);
		}

	}
}
