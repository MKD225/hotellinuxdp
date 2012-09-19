package Customer;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.controller.CustomerController;
import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.controller.RolesController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.java.model.Customer;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CustomerListener;
import hotel.linuxdp.util.IconButton;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "unused", "serial", "unchecked", "rawtypes" })
public class Panel_Customer extends JPanel implements ActionListener,
		CustomerListener, MouseListener {

	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	JPanel panelButton, panelData, panelEven;
	JDateChooser textFieldBirthDay;
	JTable jTable;
	JScrollPane scrollPane;
	DefaultTableModel model;
	JTextField textFieldFirstName, textFieldMiddleName, textFieldLastName,
			textFieldSex, textFieldAddStreet, textFieldAddCity,
			textFieldStatus, textFieldZip, textFieldCountry,
			textFieldHomePhone, textFieldWorkPhone, textFieldEmail,
			textFieldLastVisited, textFieldCCNumber, txtCusID;

	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	ButtonGroup buttonGroupGender;
	JRadioButton radioGenderMale, radioGenderFemale;
	String gender = "";
	private Integer cusID;
	private int error = 1;

	public Panel_Customer() {
		super();
		setSize(1110, 525);
		initComponents();
		loadCustomer();
		CustomerController.customerController.addRoleListener(this);
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonDelete.setEnabled(false);
		buttonRefresh.addActionListener(this);
		jTable.addMouseListener(this);
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(getContracts(), "grow,wrap 3");
		add(panelCustomer(), "grow,span,height max");
		add(createButton(), "dock south,height 60");
	}

	private JPanel panelCustomer() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();

		jTable = new JTable();
		tableTitle.add("ID");
		tableTitle.add("FirstName");
		tableTitle.add("MiddleName");
		tableTitle.add("LastName");
		tableTitle.add("BirthDay");
		tableTitle.add("Sex");
		tableTitle.add("AddStreet");
		tableTitle.add("AddCity");
		tableTitle.add("AddState");
		tableTitle.add("AddZip");
		tableTitle.add("AddCountry");
		tableTitle.add("HomePhone");
		tableTitle.add("WorkPhone");
		tableTitle.add("Email");
		tableTitle.add("LastVisited");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void loadCustomer() {
		try {
			for (Customer customer : CustomerController.customerController
					.all()) {
				model.insertRow(0, customer.toArray());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private JPanel getContracts() {

		JPanel panel = new JPanel();
		Date date = new Date();

		panel.setLayout(new MigLayout());
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		txtCusID = new JTextField();
		txtCusID.setVisible(false);
		textFieldFirstName = new JTextField();
		textFieldMiddleName = new JTextField();
		textFieldLastName = new JTextField();
		textFieldBirthDay = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		textFieldBirthDay.setMaxSelectableDate(date);
		textFieldBirthDay.setMinSelectableDate(null);

		textFieldSex = new JTextField();
		textFieldAddStreet = new JTextField();
		textFieldAddCity = new JTextField();
		textFieldStatus = new JTextField();
		textFieldZip = new JTextField();
		textFieldCountry = new JTextField();
		textFieldHomePhone = new JTextField();
		textFieldWorkPhone = new JTextField();
		textFieldEmail = new JTextField();
		textFieldLastVisited = new JTextField();
		textFieldCCNumber = new JTextField();
		buttonGroupGender = new ButtonGroup();
		radioGenderFemale = new JRadioButton("Female");
		radioGenderMale = new JRadioButton("Male");

		panel.setLayout(new MigLayout("insets 0", "[grow]"));

		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		panel.add(new JLabel("FirstName"), "split 1,right");
		panel.add(textFieldFirstName, "grow,height 22");

		panel.add(new JLabel("MiddleName:"), "right");
		panel.add(textFieldMiddleName, "grow,width 250,height 22");
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("LastName"), "right");
		panel.add(textFieldLastName, "grow,width 250,height 22");

		panel.add(new JLabel("BirthDay:"), "right");
		panel.add(textFieldBirthDay, "grow,width 250,height 22");

		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("Gender:"), "right");
		buttonGroupGender.add(radioGenderFemale);
		buttonGroupGender.add(radioGenderMale);
		panel.add(radioGenderMale, "split 2");
		panel.add(radioGenderFemale);

		panel.add(new JLabel("Street:"), "right");
		panel.add(textFieldAddStreet, "grow,width 250,height 22");
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("City:"), "right");
		panel.add(textFieldAddCity, "grow,width 250,height 22");

		panel.add(new JLabel("State:"), "right");
		panel.add(textFieldStatus, "grow,width 250,height 22");
		//
		panel.add(new JLabel(""), "grow,span");
		//
		panel.add(new JLabel("Zip:"), "right");
		panel.add(textFieldZip, "grow,width 250,height 22");

		panel.add(new JLabel("Country:"), "right");
		panel.add(textFieldCountry, "grow,width 250,height 22");
		/**
		 * 
		 */
		panel.add(new JLabel(""), "grow,span");
		/*
		 * 
		 */
		panel.add(new JLabel("HomePhone:"), "right");
		panel.add(textFieldHomePhone, "grow,width 250,height 22");

		panel.add(new JLabel("WorkPhone:"), "right");
		panel.add(textFieldWorkPhone, "grow,width 250,height 22");
		//
		/**
		 * 
		 */
		panel.add(new JLabel(""), "grow,span");
		/*
		 * 
		 */
		panel.add(new JLabel("Email:"), "right");
		panel.add(textFieldEmail, "grow,width 250,height 22");

		panel.add(new JLabel("LastVisited:"), "right");
		panel.add(textFieldLastVisited, "grow,width 250,height 22");
		//
		return panel;
	}

	public JPanel createButton() {
		panelEven = new JPanel(new MigLayout("right", "[]"));
		panelEven.setBorder(BorderFactory.createTitledBorder(""));
		buttonInsert = new JButton("Insert");
		buttonInsert.setIcon(new IconButton().insertIcon());
		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new IconButton().updateIcon());
		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new IconButton().refreshIcon());

		panelEven.add(separator, "height 5,width max");
		panelEven.add(buttonInsert);
		panelEven.add(buttonUpdate);
		panelEven.add(buttonDelete);
		panelEven.add(buttonRefresh);

		return panelEven;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon != -1) {
				try {
					buttonInsert.setEnabled(false);
					buttonDelete.setEnabled(true);
					buttonUpdate.setEnabled(true);

					Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
					setCusID(Integer.parseInt(vDongDaChon.get(0).toString()));
					String firstname = vDongDaChon.get(1).toString();
					String middleName = vDongDaChon.get(2).toString();
					String lastname = vDongDaChon.get(3).toString();
					String birthday = vDongDaChon.get(4).toString();
					String sex = vDongDaChon.get(5).toString();
					String street = vDongDaChon.get(6).toString();
					String city = vDongDaChon.get(7).toString();
					String status = vDongDaChon.get(8).toString();
					String zip = vDongDaChon.get(9).toString();
					String country = vDongDaChon.get(10).toString();
					String hphone = vDongDaChon.get(11).toString();
					String wphone = vDongDaChon.get(12).toString();
					String email = vDongDaChon.get(13).toString();
					String lastVisited = vDongDaChon.get(14).toString();

					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					Date date = (Date) formatter.parse(birthday);
					// String sex;
					textFieldFirstName.setText(firstname);
					textFieldMiddleName.setText(middleName);
					textFieldLastName.setText(lastname);
					textFieldBirthDay.setDate(date);
					if (sex.equals("Male")) {
						radioGenderMale.isSelected();
					}
					if (sex.equals("Female")) {
						radioGenderFemale.isSelected();
					}
					textFieldAddStreet.setText(street);
					textFieldAddCity.setText(city);
					textFieldStatus.setText(status);
					textFieldZip.setText(zip);
					textFieldCountry.setText(country);
					textFieldHomePhone.setText(hphone);
					textFieldWorkPhone.setText(wphone);
					textFieldEmail.setText(email);
					textFieldLastVisited.setText(lastVisited);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String datePost = sdf.format(textFieldBirthDay.getDate());
		setRadioBotton();
		if (e.getSource() == buttonInsert) {
			try {
				Customer customer = new Customer(textFieldFirstName.getText(),
						textFieldMiddleName.getText(),
						textFieldLastName.getText(), datePost,
						gender.toString(), textFieldAddStreet.getText(),
						textFieldAddCity.getText(), textFieldStatus.getText(),
						textFieldZip.getText(), textFieldCountry.getText(),
						textFieldHomePhone.getText(),
						textFieldHomePhone.getText(), textFieldEmail.getText(),
						textFieldLastVisited.getText());
				CustomerController.customerController.save(customer);
				JOptionPane.showMessageDialog(this, "me no thanh cong roi");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {

				Customer customer = new Customer(textFieldFirstName.getText()
						.trim(), textFieldMiddleName.getText().trim(),
						textFieldLastName.getText().trim(), datePost,
						gender.toString(), textFieldAddStreet.getText().trim(),
						textFieldAddCity.getText(), textFieldStatus.getText()
								.trim(), textFieldZip.getText(),
						textFieldCountry.getText().trim(),
						textFieldHomePhone.getText(),
						textFieldHomePhone.getText(), textFieldEmail.getText()
								.trim(), textFieldLastVisited.getText().trim());
				customer.setCustomerID(getCusID());
				CustomerController.customerController.update(customer);
				int c = model.getRowCount();
				for (int ii = c - 1; ii >= 0; ii--) {
					model.removeRow(ii);
					jTable.revalidate();
				}
				loadCustomer();
				JOptionPane.showMessageDialog(this, "me no thanh cong roi");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		// chuc nang delete duoc khi ben bang cha khong co khoa ID cua bang con
		if (e.getSource() == buttonDelete) {
			try {
				List<Reservation> temp = ReservationController.ReservationController
						.all();
				for (int i = 0; i < temp.size(); i++) {
					if (getCusID() == temp.get(i).getCustomerID()) {
						this.error = 1;
					} else {
						this.error = 0;
						CustomerController.customerController.delete(getCusID());
						int c = model.getRowCount();
						for (int ii = c - 1; ii >= 0; ii--) {
							model.removeRow(ii);
							jTable.revalidate();
						}
						loadCustomer();
					}
				}
				JOptionPane.showMessageDialog(this, "delete to succeed");
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this,"you can't delete then Reservation still");
				//exception.printStackTrace();
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
			int c = model.getRowCount();
			for (int ii = c - 1; ii >= 0; ii--) {
				model.removeRow(ii);
				jTable.revalidate();
			}
			loadCustomer();
		}
	}

	private void setRadioBotton() {
		if (radioGenderMale.isSelected() == true) {
			gender = "Male";
		} else {
			gender = "Female";
		}
	}

	@Override
	public void roleadd(EventAll<Customer> event) {
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

	public Integer getCusID() {
		return cusID;
	}

	public void setCusID(Integer cusID) {
		this.cusID = cusID;
	}
}
