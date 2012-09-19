package Administrator;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.controller.RolesController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.java.model.Roles;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.AdminListener;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("rawtypes")
public class Panel_Administrator extends JPanel implements ActionListener,
		AdminListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tableAdmin;
	private JTextField txtUserName, txtFirstName, txtMiddleName, txtLastName,
			txtContactInfo;
	private JRadioButton radioGenderMale, radioGenderFemale;
	private JPasswordField txtpassword, txtconfpass;
	private ButtonGroup buttonGroupGender;
	private JComboBox cboRoleName;
	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	public DefaultTableModel model;
	private JSeparator separator1 = new JSeparator();
	private JSeparator separator2 = new JSeparator();
	private JLabel labelconfigPassword;
	Hashtable hashtable = new Hashtable();
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private Integer roleID;
	private String usetID;
	String gender = "";

	public Panel_Administrator() {
		setLayout(new MigLayout());
		add(tableAdmin(), "span,width max,gap top 5");
		add(separator1, "span,width max,height 5,gap top 10");
		add(componentDate(), "span,grow,gap top 10");
		add(separator2, "span,width max,height 5,gap top 10");
		add(createButtons(), "dock south");
		all();
		loadRole();
		tableAdmin.addMouseListener(this);
		buttonInsert.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		buttonUpdate.addActionListener(this);
		AdministratorController.administratorController.addAdminListener(this);
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);

	}

	@SuppressWarnings("unchecked")
	private JComponent tableAdmin() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane = new JScrollPane();
		tableAdmin = new JTable();

		tableTitle.add("UserID");
		tableTitle.add("FirstName");
		tableTitle.add("MiddleName");
		tableTitle.add("LastName");
		tableTitle.add("Gender");
		tableTitle.add("ContactInfo");
		tableTitle.add("RoleName");

		model = new DefaultTableModel(tableRecords, tableTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tableAdmin.setModel(model);
		tableAdmin.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableAdmin);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (Administrator administrator : AdministratorController.administratorController
					.all()) {
				model.insertRow(0, administrator.toArray());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadRole() {
		try {
			for (Roles role : RolesController.roleController.allRoles()) {
				cboRoleName.addItem(role.getRoleName());
				hashtable.put(role.getRoleName(), role.getRoleID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JComponent componentDate() {
		JPanel panel = new JPanel();
		JPanel panelData = new JPanel();
		panel.setLayout(new BorderLayout());
		panelData.setLayout(new MigLayout("insets 0", "[grow]"));
		panelData.setBorder(BorderFactory.createTitledBorder(""));

		txtUserName = new JTextField();
		txtpassword = new JPasswordField();
		txtconfpass = new JPasswordField();
		labelconfigPassword = new JLabel();
		labelconfigPassword.setForeground(new Color(255, 0, 0));
		txtFirstName = new JTextField();
		txtMiddleName = new JTextField();
		txtLastName = new JTextField();
		txtContactInfo = new JTextField();

		buttonGroupGender = new ButtonGroup();
		new ButtonGroup();
		radioGenderFemale = new JRadioButton("Female");
		radioGenderMale = new JRadioButton("Male");
		cboRoleName = new JComboBox();
		new JRadioButton("True");
		new JRadioButton("False");
		new DefaultComboBoxModel();

		panelData.add(new JLabel("UserName"), "right");
		panelData.add(txtUserName, "grow,width 250,height 22");

		panelData.add(new JLabel("PassWord:"), "right");
		panelData.add(txtpassword, "grow,width 250,height 22");
		/**
		 * Create Clear 2 column
		 */
		panelData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panelData.add(new JLabel("FirstName:"), "right");
		panelData.add(txtFirstName, "grow,width 250,height 22");

		panelData.add(new JLabel("Config.PassWord:"), "right");
		panelData.add(txtconfpass, "grow,width 250,height 22");
		panelData.add(labelconfigPassword, "right");
		/**
		 * Clear
		 */
		panelData.add(new JLabel(), "grow,span");
		/**
		 * 
		 */

		panelData.add(new JLabel("MiddleName:"), "right");
		panelData.add(txtMiddleName, "grow,width 250,height 22");

		panelData.add(new JLabel("LastName:"), "right");
		panelData.add(txtLastName, "grow,width 250,height 22");
		/**
		 * Clear
		 */
		panelData.add(new JLabel(), "grow,span");
		/**
		 * 
		 */
		panelData.add(new JLabel("RoleName:"), "right");
		panelData.add(cboRoleName, "grow,height 22");

		panelData.add(new JLabel("ContactInfo:"), "right");
		panelData.add(txtContactInfo, "grow,height 22");
		/**
		  * 
		  */
		panelData.add(new JLabel(), "grow,span");
		/**
		 * 
		 */
		panelData.add(new JLabel("Sex:"), "right");
		buttonGroupGender.add(radioGenderFemale);
		buttonGroupGender.add(radioGenderMale);
		panelData.add(radioGenderMale, "split 2");
		panelData.add(radioGenderFemale);

		panel.add(panelData);
		setRadioBotton();
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
		panelButton.add(buttonUpdate);

		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
		panelButton.add(buttonDelete);

		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new IconButton().refreshIcon());
		panelButton.add(buttonRefresh);

		return panelButton;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			setRadioBotton();
			roleID = (Integer) hashtable.get(cboRoleName.getSelectedItem()
					.toString().trim());
			if (!txtpassword.getText().equals(txtconfpass.getText())) {
				labelconfigPassword.setText("Password not the same !");
				txtconfpass.requestFocus();
				return;
			} else
				try {
					Administrator administrator = new Administrator(
							txtUserName.getText(), txtconfpass.getText(),
							txtFirstName.getText(), txtMiddleName.getText(),
							txtLastName.getText(), gender,
							txtContactInfo.getText(), roleID);
					AdministratorController.administratorController
							.save(administrator);
					JOptionPane.showMessageDialog(this, "insert thanh cong",
							"", JOptionPane.INFORMATION_MESSAGE);
					int c = model.getRowCount();
					for (int i = c - 1; i >= 0; i--) {
						model.removeRow(i);
						tableAdmin.revalidate();
					}
					all();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				roleID = (Integer) hashtable.get(cboRoleName.getSelectedItem()
						.toString().trim());
				Administrator administrator = new Administrator(
						txtFirstName.getText(), txtMiddleName.getText(),
						txtLastName.getText(), gender,
						txtContactInfo.getText(), roleID);

				administrator.setUserID(usetID);
				AdministratorController.administratorController
						.updateAdministrator(administrator);
				JOptionPane.showMessageDialog(this, "update thanh cong", "",
						JOptionPane.INFORMATION_MESSAGE);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					tableAdmin.revalidate();
				}
				all();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			int lsRemote = tableAdmin.getSelectedRow();

			if (lsRemote == -1) {
				JOptionPane.showMessageDialog(this, "hay chon 1 row");
			} else {
				try {
					AdministratorController.administratorController
							.deleteAdmin(usetID);
					int c = model.getRowCount();
					for (int i = c - 1; i >= 0; i--) {
						model.removeRow(i);
						tableAdmin.revalidate();
					}
					all();
					JOptionPane.showMessageDialog(this, "ok");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonDelete.setEnabled(false);
			buttonUpdate.setEnabled(false);
			buttonInsert.setEnabled(true);
			txtUserName.setEnabled(true);
			txtpassword.setEnabled(true);
			txtconfpass.setEnabled(true);
			int c = model.getRowCount();
			for (int i = c - 1; i >= 0; i--) {
				model.removeRow(i);
				tableAdmin.revalidate();
			}
			all();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tableAdmin) {
			int iDongDaChon = tableAdmin.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "ban ne chon 1 row");
			}
			if (iDongDaChon == 0) {
				buttonDelete.setEnabled(false);
			} else {
				buttonDelete.setEnabled(true);
				buttonUpdate.setEnabled(true);
				buttonInsert.setEnabled(false);
				txtUserName.setEnabled(false);
				txtUserName.setDisabledTextColor(new Color(250, 2, 2));
				txtpassword.setEnabled(false);
				txtconfpass.setEnabled(false);
				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);

				usetID = vDongDaChon.get(0).toString();
				String firstname = vDongDaChon.get(1).toString();
				String middleName = vDongDaChon.get(2).toString();
				String lastname = vDongDaChon.get(3).toString();
				String sex = vDongDaChon.get(4).toString();
				String contractInfo = vDongDaChon.get(5).toString();
				String roleName = vDongDaChon.get(6).toString();

				txtUserName.setText(usetID);
				txtMiddleName.setText(middleName);
				txtFirstName.setText(firstname);
				txtLastName.setText(lastname);
				txtContactInfo.setText(contractInfo);
				cboRoleName.setSelectedItem(roleName.toString());

			}

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
	public void adminadd(EventAll<Administrator> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseEntered(MouseEvent e) {

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
