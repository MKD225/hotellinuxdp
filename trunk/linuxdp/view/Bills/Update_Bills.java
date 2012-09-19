package Bills;

import hotel.linuxdp.java.controller.BillsController;
import hotel.linuxdp.java.controller.CheckInController;
import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.model.Bills;
import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CheckInListener;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class Update_Bills extends JFrame implements ActionListener,
		CheckInListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private JComboBox boxUserName;
	private JButton buttonUpdate;
	private Integer idBill;
	private String comment;
	private String idReservation;
	private JTextArea txtCommend;
	JTextField txtReservationID, textFieldFirstName, textFieldMiddleName,
			textFieldLastName, textFieldSex, textFieldAddStreet,
			textFieldBirthDay, textFieldAddCity, textFieldStatus, textFieldZip,
			textFieldCountry, textFieldHomePhone, textFieldWorkPhone,
			textFieldEmail, textFieldCCNumber, txtCusID, txtArrivalTime,
			txtCheckIn, txtCheckOut, txtNumberGuests, txtRoomRate, txtRoomType,
			txtID;
	private static JPanel titleData;
	Panel_Bills bills = new Panel_Bills();
	public Update_Bills() {
		setLayout(new MigLayout());
		setSize(1100, 550);
		setLocation(180, 90);
		add(initComponent(), "span,width max,gap top 5");
		add(createForm(), "span,width max");
		add(getCustomer(), "span,grow,width max");
		add(new JSeparator(), "width max,height 5");
		add(buttonUpdate, "right");
		allReservation();
		buttonUpdate.addActionListener(this);
		boxUserName.addItemListener(this);
		CheckInController.checkInController.addCheckInListener(this);
	}

	public boolean rootPaneCheckingEnabled = true;

	private JPanel initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[grow]"));
		panel.setBorder(BorderFactory.createTitledBorder(""));

		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new IconButton().insertIcon());
		boxUserName = new JComboBox();
		txtCommend = new JTextArea();
		txtReservationID = new JTextField();
		txtReservationID.setForeground(new Color(252, 4, 4));
		txtID = new JTextField();

		panel.add(new JLabel("UserName"), "right,gap left 100");
		panel.add(boxUserName, "width max");

		panel.add(new JLabel(), "span,grow");

		panel.add(new JLabel("Reservation"), "right");
		panel.add(txtReservationID, "width max");

		panel.add(new JLabel(), "span,grow");

		panel.add(new JLabel("Comments"), "right");
		panel.add(txtCommend, "width max,height 140");

		return panel;
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout("insets 0", "[grow]"));
		titleData.setBorder(BorderFactory
				.createTitledBorder("Reservation Data"));
		txtArrivalTime = new JTextField();
		txtCheckIn = new JTextField();
		txtCheckIn.setEditable(false);
		txtCheckOut = new JTextField();
		txtCheckOut.setEditable(false);
		txtNumberGuests = new JTextField();
		txtRoomRate = new JTextField();
		txtRoomType = new JTextField();

		titleData.add(new JLabel("Check In"), "right");
		titleData.add(txtCheckIn,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		titleData.add(new JLabel("Check Out:"), "right");
		titleData.add(txtCheckOut,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		titleData.add(new JLabel("Room Rate"), "right");
		titleData.add(txtRoomRate,
				"grow,width max,height 22,gap top 10,gap bottom 10");
		/**
	   * 
	   */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("Arrival Time:"), "right");
		titleData.add(txtArrivalTime,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		titleData.add(new JLabel("Number Guests"), "right");
		titleData.add(txtNumberGuests,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		titleData.add(new JLabel("RoomType"), "right");
		titleData.add(txtRoomType,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		panel.add(titleData);
		return panel;
	}

	private JPanel getCustomer() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Customer Data"));
		txtCusID = new JTextField();
		txtCusID.setVisible(false);
		textFieldFirstName = new JTextField();
		textFieldMiddleName = new JTextField();
		textFieldLastName = new JTextField();
		textFieldBirthDay = new JTextField();

		textFieldSex = new JTextField();
		textFieldAddStreet = new JTextField();
		textFieldAddCity = new JTextField();
		textFieldStatus = new JTextField();
		textFieldZip = new JTextField();
		textFieldCountry = new JTextField();
		textFieldHomePhone = new JTextField();
		textFieldWorkPhone = new JTextField();
		textFieldEmail = new JTextField();
		textFieldCCNumber = new JTextField();

		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel("FirstName"), "right");
		panel.add(textFieldFirstName, "height 22,width max");

		panel.add(new JLabel("MiddleName:"), "right");
		panel.add(textFieldMiddleName, "width max,height 22");

		panel.add(new JLabel("LastName"), "right");
		panel.add(textFieldLastName, "width max,height 22");

		panel.add(new JLabel("BirthDay:"), "right");
		panel.add(textFieldBirthDay, "width max,height 22");

		panel.add(new JLabel(""), "grow,span");

		panel.add(new JLabel("Street:"), "right");
		panel.add(textFieldAddStreet,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		panel.add(new JLabel("City:"), "right");
		panel.add(textFieldAddCity,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		panel.add(new JLabel("State:"), "right");
		panel.add(textFieldStatus,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		panel.add(new JLabel("Zip:"), "right");
		panel.add(textFieldZip,
				"grow,width max,height 22,gap top 10,gap bottom 10");

		panel.add(new JLabel(""), "grow,span");

		panel.add(new JLabel("Country:"), "right");
		panel.add(textFieldCountry, "grow,width max,height 22");

		panel.add(new JLabel("HomePhone:"), "right");
		panel.add(textFieldHomePhone, "grow,width max,height 22");

		panel.add(new JLabel("WorkPhone:"), "right");
		panel.add(textFieldWorkPhone, "grow,width max,height 22");

		panel.add(new JLabel("Email:"), "right");
		panel.add(textFieldEmail, "grow,width max,height 22");

		return panel;
	}

	private void allReservation() {
		try {
			for (Reservation reservation : ReservationController.ReservationController
					.all()) {
				boxUserName.addItem(reservation.getFirstName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonUpdate) {
			try {
				Integer resrvation = Integer.parseInt(txtReservationID
						.getText());
				Bills bill = new Bills(resrvation, txtCommend.getText());
				bill.setBillID(idBill);
				BillsController.billController.update(bill);
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
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == boxUserName) {
			try {
				int user = boxUserName.getSelectedIndex();
				Reservation c = ReservationController.ReservationController
						.all().get(user);
				txtReservationID.setText(c.getReservationID().toString());

				txtCheckIn.setText(c.getExpectedCheckinDate());
				txtCheckIn.setForeground(new Color(252, 4, 4));
				txtCheckIn.setEditable(false);
				txtCheckOut.setText(c.getExpectedCheckoutDate());
				txtCheckOut.setForeground(new Color(252, 4, 4));
				txtCheckIn.setEditable(false);
				txtArrivalTime.setText(c.getArrivalTime().toString());
				txtArrivalTime.setEditable(false);
				txtNumberGuests.setText(c.getNumberGuests().toString());
				txtNumberGuests.setEditable(false);
				txtRoomRate.setText(c.getRoomRate());
				txtRoomRate.setEditable(false);
				txtRoomType.setText(c.getRoomType());
				txtRoomType.setEditable(false);
				textFieldFirstName.setText(c.getFirstName());
				textFieldFirstName.setEditable(false);
				textFieldWorkPhone.setText(c.getWorkPhone());
				textFieldWorkPhone.setEditable(false);
				textFieldWorkPhone.setForeground(new Color(252, 4, 4));
				textFieldEmail.setText(c.getEmail());
				textFieldEmail.setEditable(false);
				textFieldEmail.setForeground(new Color(252, 4, 4));
				textFieldMiddleName.setText(c.getMiddleName());
				textFieldMiddleName.setEditable(false);
				textFieldLastName.setText(c.getLastName());
				textFieldLastName.setEditable(false);
				textFieldAddCity.setText(c.getAddCity());
				textFieldAddCity.setEditable(false);
				textFieldAddStreet.setText(c.getAddStreet());
				textFieldAddStreet.setEditable(false);
				textFieldBirthDay.setText(c.getBirthDay());
				textFieldBirthDay.setEditable(false);
				textFieldSex.setText(c.getSex());
				textFieldSex.setEditable(false);
				textFieldCountry.setText(c.getAddCountry());
				textFieldCountry.setEditable(false);
				textFieldHomePhone.setText(c.getHomePhone());
				textFieldHomePhone.setEditable(false);
				textFieldStatus.setText(c.getAddState());
				textFieldStatus.setEditable(false);
				textFieldAddStreet.setText(c.getAddStreet());
				textFieldAddStreet.setEditable(false);
				textFieldZip.setText(c.getAddZip());
				textFieldZip.setEditable(false);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void roleadd(EventAll<CheckIn> event) {
		bills.getModel().insertRow(0, event.getSource().toArray());
	}

	public Integer getIdBill() {
		return idBill;
	}

	public void setIdBill(Integer idBill) {
		this.idBill = idBill;
		txtID.setText(idBill.toString());
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
		txtCommend.setText(comment);
	}

	public String getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
		txtReservationID.setText(idReservation);
	}

}
