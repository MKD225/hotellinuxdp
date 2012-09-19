package CheckOut;

import hotel.linuxdp.java.controller.CheckInController;
import hotel.linuxdp.java.controller.CheckOutController;
import hotel.linuxdp.java.controller.RoomsController;
import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.model.CheckOut;
import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CheckInListener;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import View_Packer.Cli_CheckIn;

import com.toedter.calendar.JDateChooser;

public class Insert_CheckOut extends JDialog implements ActionListener,
		CheckInListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private JDateChooser txtCheckOutDate;
	private JComboBox boxCheckIn;
	private JButton buttonInsert;
	private Integer room;

	JTextField txtReservationID, textFieldFirstName, textFieldMiddleName,
			textFieldLastName, textFieldSex, textFieldAddStreet,
			textFieldBirthDay, textFieldAddCity, textFieldStatus, textFieldZip,
			textFieldCountry, textFieldHomePhone, textFieldWorkPhone,
			textFieldEmail, textFieldCCNumber, txtCusID, txtArrivalTime,
			txtCheckIn, txtCheckOut, txtNumberGuests, txtRoomRate, txtRoomType,
			txtRoomNumber, txtCheckInDate, txtRoomID;
	private static JPanel titleData;

	public Insert_CheckOut(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setLayout(new MigLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight() - 40);
		setSize(xSize, ySize);
		add(new Cli_CheckIn(), "span, width max");
		add(initComponent(), "span,width max");
		add(createForm(), "span,width max");
		add(getCustomer(), "span,grow,width max");
		allCheckIn();
		buttonInsert.addActionListener(this);
		boxCheckIn.addItemListener(this);
		CheckInController.checkInController.addCheckInListener(this);
	}

	public boolean rootPaneCheckingEnabled = true;

	private JPanel initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[grow]"));
		panel.setBorder(BorderFactory.createTitledBorder(""));
		Calendar calendar = Calendar.getInstance();

		txtCheckOutDate = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtCheckOutDate.setCalendar(calendar);
		txtCheckOutDate.setForeground(new Color(252, 4, 4));
		txtCheckOutDate.setEnabled(false);
		txtCheckInDate = new JTextField();
		buttonInsert = new JButton("Insert");
		buttonInsert.setIcon(new IconButton().insertIcon());
		boxCheckIn = new JComboBox();
		txtRoomNumber = new JTextField();
		txtReservationID = new JTextField();
		txtReservationID.setForeground(new Color(252, 4, 4));

		panel.add(new JLabel("CheckIn ID"), "right");
		panel.add(boxCheckIn, "width max");

		panel.add(new JLabel("Check Out Date"), "right");
		panel.add(txtCheckOutDate, "width max");

		panel.add(new JLabel("CheckIn Date"), "right");
		panel.add(txtCheckInDate, "width max");
		panel.add(new JLabel(), "span,grow");

		panel.add(new JLabel("Reservation"), "right");
		panel.add(txtReservationID, "width max");

		panel.add(new JLabel("Room Number"), "right");
		panel.add(txtRoomNumber, "width max");
		txtRoomID = new JTextField();
		panel.add(new JLabel("Room ID Test"), "right");
		panel.add(txtRoomID, "width max");

		panel.add(buttonInsert, "span");

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

		titleData.add(new JLabel("Check Out:"), "right");
		titleData.add(txtCheckOut, "grow,width max,height 22");

		titleData.add(new JLabel("Check In"), "right");
		titleData.add(txtCheckIn, "grow,width max,height 22");

		titleData.add(new JLabel("Room Rate"), "right");
		titleData.add(txtRoomRate, "grow,width max,height 22");
		/**
	   * 
	   */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("Arrival Time:"), "right");
		titleData.add(txtArrivalTime, "grow,width max,height 22");

		titleData.add(new JLabel("Number Guests"), "right");
		titleData.add(txtNumberGuests, "grow,width max,height 22");

		titleData.add(new JLabel("RoomType"), "right");
		titleData.add(txtRoomType, "grow,width max,height 22");

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
		panel.add(textFieldAddStreet, "grow,width max,height 22");

		panel.add(new JLabel("City:"), "right");
		panel.add(textFieldAddCity, "grow,width max,height 22");

		panel.add(new JLabel("State:"), "right");
		panel.add(textFieldStatus, "grow,width max,height 22");

		panel.add(new JLabel("Zip:"), "right");
		panel.add(textFieldZip, "grow,width max,height 22");

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

	private void allCheckIn() {
		try {
			for (CheckIn checkIn : CheckInController.checkInController
					.allTable()) {
				boxCheckIn.addItem(checkIn.getCheckinID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dateIn = sdf.format(txtCheckOutDate.getDate());
				Integer idRese = Integer.parseInt(txtReservationID.getText().toString());
				String roomnumber = txtRoomNumber.getText();
				// insert Check Out
				CheckOut checkOut = new CheckOut(idRese, dateIn);
				CheckOutController.checkOutController.save(checkOut);
				JOptionPane.showConfirmDialog(this, "Insert thanh cong");
				//delete check in then checout instal
				CheckInController.checkInController.deleteCheckInbyCheckOut(idRese);
				Rooms rooms = new Rooms();
				rooms.setRoomNumber(roomnumber);
				RoomsController.roomsController.updateStatusTrue(rooms);
				this.dispose();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == boxCheckIn) {
			try {
				int user = boxCheckIn.getSelectedIndex();
				CheckIn c = CheckInController.checkInController.allTable().get(
						user);

				txtReservationID.setText(c.getReservationID().toString());
				txtCheckIn.setText(c.getExpectedCheckinDate());
				txtCheckIn.setForeground(new Color(252, 4, 4));
				txtCheckIn.setEditable(false);
				txtRoomNumber.setText(c.getRoomNumber());
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
				txtCheckInDate.setText(c.getCheckinDate());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void roleadd(EventAll<CheckIn> event) {

	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

}
