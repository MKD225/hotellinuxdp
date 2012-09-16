package Roles;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.controller.RolesController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.java.model.Roles;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoleListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Roles extends JPanel implements MouseListener,
		ActionListener, RoleListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtRoleName, txtRoleID;
	private JTextArea txtRoleDes;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private JSeparator separator = new JSeparator();
	private Integer roleID;
	private int error = 1;
	private int id;

	public Panel_Roles() {
		initComponents();
		all();
		jTable.addMouseListener(this);
		buttonInsert.addActionListener(this);
		RolesController.roleController.addRoleListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);

	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(getTable(), "span,width max");
		add(createForm(), "grow,span,width max");
		add(separator, "width max, height 5,span");
		add(createButtons(), "right,dock south");
	}

	public JPanel getTable() {
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		panelTable.add(jTable());
		return panelTable;
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("RoleID");
		tableTitle.add("RoleName");
		tableTitle.add("Description");

		model = new DefaultTableModel(tableRecords, tableTitle){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (Roles roles : RolesController.roleController.allRoles()) {
				model.insertRow(0, roles.toArray());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro", e.getMessage(),
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("Role ID:"), "right");
		txtRoleID = new JTextField();
		txtRoleID.setEnabled(false);
		txtRoleID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtRoleID, "height 23,width 450,span");

		txtRoleName = new JTextField();
		titleData.add(new JLabel("Role Name:"), "right");
		titleData.add(txtRoleName, "height 23,width max,span");

		txtRoleDes = new JTextArea();
		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtRoleDes, "span, growx,height 120");

		panel.add(titleData, "span");
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon != -1) {
				buttonInsert.setEnabled(false);
				buttonDelete.setEnabled(true);
				buttonUpdate.setEnabled(true);
				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
				roleID = Integer.parseInt(jTable.getValueAt(iDongDaChon, 0)
						.toString());

				String name = vDongDaChon.get(1).toString();
				String des = vDongDaChon.get(2).toString();

				txtRoleID.setText(this.roleID.toString());
				txtRoleName.setText(name);
				txtRoleDes.setText(des);
			}
			try {
				List<Administrator> temp = AdministratorController.administratorController
						.all();
				for (int i = 0; i < temp.size(); i++) {
					if (roleID == temp.get(i).getRoleID()) {
						this.error = 1;
						buttonDelete.setEnabled(false);
						buttonUpdate.setEnabled(false);
						break;
					} else {
						this.error = 0;
						buttonDelete.setEnabled(true);
						buttonUpdate.setEnabled(true);
					}
				}
			} catch (Exception ex) {

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				Roles roles = new Roles(txtRoleName.getText(),
						txtRoleDes.getText());
				RolesController.roleController.save(roles);
				JOptionPane.showMessageDialog(this, "insert to succeed", "",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage(),
						"Insert not succeed !", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				Roles roleModel = new Roles(txtRoleName.getText().trim(),
						txtRoleDes.getText().trim());
				roleModel.setRoleID(roleID);
				RolesController.roleController.updateRole(roleModel);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			int lsRemote = jTable.getSelectedRow();
			if (lsRemote == -1) {
				JOptionPane.showMessageDialog(this, "hay chon 1 cot di ban");
			} else {
				id = Integer.valueOf(jTable.getValueAt(lsRemote, 0).toString());
				try {
					List<Administrator> temp = AdministratorController.administratorController
							.all();
					for (int i = 0; i < temp.size(); i++) {
						if (id == temp.get(i).getRoleID()) {
							this.error = 1;
						} else {
							this.error = 0;
							RolesController.roleController.deleteRole(id);
							int c = model.getRowCount();
							for (int ii = c - 1; ii >= 0; ii--) {
								model.removeRow(ii);
								jTable.revalidate();
							}
							all();
						}
					}
					JOptionPane.showMessageDialog(this, "Delete to succeed !");
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(this,
							"can't delete then admin still");
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
			txtRoleID.setText("");
			txtRoleName.setText("");
			txtRoleDes.setText("");
			int c = model.getRowCount();
			for (int ii = c - 1; ii >= 0; ii--) {
				model.removeRow(ii);
				jTable.revalidate();
			}
			all();
		}
	}

	@Override
	public void roleadd(EventAll<Roles> event) {
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

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
