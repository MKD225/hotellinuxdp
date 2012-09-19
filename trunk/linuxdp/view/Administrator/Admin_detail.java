package Administrator;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.util.IconButton;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Admin_detail extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername, txtfirstName, txtmiddeName, txtlastName,
			txtrole, txtContactInfo;
	private JCheckBox boxEnable;
	private String username, firstname, middename, lastname, sex, role,
			ContactInfo;
	private JComboBox txtsex;
	private JButton btnupdate;
	private JRadioButton radioGenderMale, radioGenderFemale;
	private ButtonGroup buttonGroupGender;
	private String gender = "";

	public Admin_detail() {
		add(Admin());
		setSize(400, 400);
		btnupdate.addActionListener(this);
		boxEnable.addActionListener(this);
		btnupdate.addActionListener(this);
		setRadioBotton();
	}

	private JComponent Admin() {
		JPanel panel = new JPanel();
		setLayout(new MigLayout());
		JLabel label = new JLabel("User Detail");
		label.setFont(new Font("Tahoma", 0, 20));
		label.setForeground(new Color(51, 51, 255));
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtfirstName = new JTextField();
		txtfirstName.setEnabled(false);
		txtmiddeName = new JTextField();
		txtmiddeName.setEnabled(false);
		txtlastName = new JTextField();
		txtlastName.setEnabled(false);
		txtsex = new JComboBox();
		txtsex.addItem("-choose-");
		txtsex.addItem("Male");
		txtsex.addItem("Female");
		txtsex.setEnabled(false);
		txtrole = new JTextField();
		txtrole.setEnabled(false);
		boxEnable = new JCheckBox();
		txtContactInfo = new JTextField();
		btnupdate = new JButton("update");
		btnupdate.setIcon(new IconButton().updateIcon());
		buttonGroupGender = new ButtonGroup();
		radioGenderFemale = new JRadioButton("Female");
		radioGenderMale = new JRadioButton("Male");

		add(label, "top,center,span");

		add(new JLabel("UserName"), "right");
		add(txtUsername, "height 22,span,width 300");

		add(new JLabel("FirstName"), "right");
		add(txtfirstName, "height 22,span,width 300");

		add(new JLabel("MiddleName"), "right");
		add(txtmiddeName, "height 22,span,width 300");

		add(new JLabel("LastName"), "right");
		add(txtlastName, "height 22,span,width 300");

		buttonGroupGender.add(radioGenderFemale);
		buttonGroupGender.add(radioGenderMale);
		add(new JLabel("Gender"), "right");
		add(radioGenderMale, "split 2,span");
		add(radioGenderFemale);

		add(new JLabel("ContactInfo"), "right");
		add(txtContactInfo, "height 22,span,width 300");

		add(new JLabel("Role"), "right");
		add(txtrole, "height 22,span,width 300");

		add(new JLabel("Edit"), "right");
		add(boxEnable);
		add(btnupdate);
		boxEnable.setSelected(true);

		setRadioBotton();
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boxEnable) {
			boolean state;
			state = boxEnable.isSelected();
			if (state == false) {
				txtfirstName.setEnabled(true);
				txtlastName.setEnabled(true);
				txtmiddeName.setEnabled(true);
				txtsex.setEnabled(true);
				txtContactInfo.setEnabled(true);
			} else {
				txtfirstName.setEnabled(false);
				txtfirstName.setEnabled(false);
				txtlastName.setEnabled(false);
				txtmiddeName.setEnabled(false);
				txtsex.setEnabled(false);
				txtContactInfo.setEnabled(false);
			}
		}
		if (e.getSource() == btnupdate) {
			setRadioBotton();
			try {
				String userID = txtUsername.getText().toString().trim();
				Administrator administrator = new Administrator(
						txtfirstName.getText(), txtmiddeName.getText(),
						txtlastName.getText(), gender, txtContactInfo.getText());

				administrator.setUserID(userID);
				AdministratorController.administratorController
						.updateAdministratorbyUser(administrator);
				JOptionPane.showMessageDialog(null, "Update Thanh Cong");
				this.dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	private void setRadioBotton() {
		if (radioGenderMale.isSelected() == true) {
			setGender("Male");
		} else {
			setGender("Female");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		txtUsername.setText(username);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
		txtfirstName.setText(firstname.toString().trim());
	}

	public String getMiddename() {
		return middename;
	}

	public void setMiddename(String middename) {
		this.middename = middename;
		txtmiddeName.setText(middename);
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
		txtlastName.setText(lastname);
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
		txtsex.setSelectedItem(sex.toString().trim());
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
		txtrole.setText(role);
	}

	public String getContactInfo() {
		return ContactInfo;
	}

	public void setContactInfo(String contactInfo) {
		ContactInfo = contactInfo;
		txtContactInfo.setText(contactInfo);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}