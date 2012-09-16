package RoomStatus;

import hotel.linuxdp.java.controller.RoomStatusController;
import hotel.linuxdp.java.controller.RoomsController;
import hotel.linuxdp.java.model.RoomStatus;
import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomStatusListener;
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
public class Panel_RoomStatus extends JPanel implements ActionListener,
		MouseListener, RoomStatusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable jTable;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtRoomName, txtID;
	private JTextArea txtRoomDesc;
	ButtonGroup buttonGroupGender;
	JRadioButton radioGenderCP, radioGenderHP;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	JSeparator separator1 = new JSeparator();
	private Integer ID;
	private int error = 1;
	// private int id;
	private static boolean boolKiemTra;

	public Panel_RoomStatus() {
		initComponents();
		all();
		setRadioBotton();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		RoomStatusController.roomStatusController.addDiscountListener(this);
		jTable.addMouseListener(this);
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(jTable(), "grow, span,gap top 1,width max");
		add(separator, "span,width max, height 5 ");
		add(createForm(), "grow,span");
		add(separator1, "width max, hmin 5,span");
		add(createButtons(), "dock south,right");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("RoomStatusID");
		tableTitle.add("RoomStatusName");
		tableTitle.add("RoomStatusDesc");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (RoomStatus roomStatus : RoomStatusController.roomStatusController
					.all()) {
				model.insertRow(0, roomStatus.toArray());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("ID:"), "right");
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtID, "height 23,width 450,span");

		buttonGroupGender = new ButtonGroup();
		radioGenderCP = new JRadioButton("Con Phong");
		radioGenderHP = new JRadioButton("Het Phong(Dang Nang Cap)");

		buttonGroupGender.add(radioGenderCP);
		buttonGroupGender.add(radioGenderHP);
		titleData.add(radioGenderCP, "right");
		titleData.add(radioGenderHP, "span");

		titleData.add(new JLabel(), "span,grow");

		txtRoomName = new JTextField();
		titleData.add(new JLabel("Role Name:"), "right");
		titleData.add(txtRoomName, "height 23,width max,span");

		txtRoomDesc = new JTextArea();

		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtRoomDesc, "span, growx,hmin 100");

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

	private void setRadioBotton() {
		if (radioGenderCP.isSelected()) {
			boolKiemTra = true;
		} else {
			boolKiemTra = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				RoomStatus roomStatus = new RoomStatus(txtRoomName.getText(),
						txtRoomDesc.getText(), boolKiemTra);
				RoomStatusController.roomStatusController.save(roomStatus);
				JOptionPane.showMessageDialog(this, "duoc roi");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				RoomStatus roomStatus = new RoomStatus(txtRoomName.getText(),
						txtRoomDesc.getText(), boolKiemTra);
				roomStatus.setRoomStatusID(ID);
				RoomStatusController.roomStatusController.update(roomStatus);
				int c = model.getRowCount();
				for (int ii = c - 1; ii >= 0; ii--) {
					model.removeRow(ii);
					jTable.revalidate();
				}
				all();
				JOptionPane.showMessageDialog(this, "Update to succeed !");
			} catch (Exception exception) {
				exception.printStackTrace();
				// JOptionPane.showMessageDialog(this,"you can't Update then Room stall "
				// + ID);
			}
			if (e.getSource() == buttonDelete) {
				try {
					List<Rooms> temp = RoomsController.roomsController.all();
					for (int i = 0; i < temp.size(); i++) {
						if (ID == temp.get(i).getRoomStatusID()) {
							this.error = 1;
						} else {
							this.error = 0;
							RoomStatusController.roomStatusController
									.delete(ID);
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
							"you can't delete then Room stall " + ID);
				}
			}
			if (e.getSource() == buttonRefresh) {
				int c = model.getRowCount();
				for (int ii = c - 1; ii >= 0; ii--) {
					model.removeRow(ii);
					jTable.revalidate();
				}
				all();
				buttonDelete.setEnabled(false);
				buttonUpdate.setEnabled(false);
				buttonInsert.setEnabled(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon != -1) {
				buttonInsert.setEnabled(false);
				buttonDelete.setEnabled(true);
				// buttonUpdate.setEnabled(true);
				radioGenderHP.setEnabled(true);

				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
				ID = Integer.parseInt(vDongDaChon.get(0).toString());
				String rName = vDongDaChon.get(1).toString();
				String rDesc = vDongDaChon.get(2).toString();

				txtID.setText(ID.toString());
				txtRoomName.setText(rName);
				txtRoomDesc.setText(rDesc);
			}
			try {
				List<Rooms> temp = RoomsController.roomsController.all();
				for (int i = 0; i < temp.size(); i++) {
					if (ID == temp.get(i).getRoomStatusID()) {
						this.error = 1;
						buttonUpdate.setEnabled(false);
						buttonDelete.setEnabled(false);
						break;
					} else {
						this.error = 0;
						buttonUpdate.setEnabled(true);
						buttonDelete.setEnabled(true);
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "when  Room still then can't update or delete");
			}

		}
	}

	@Override
	public void roleadd(EventAll<RoomStatus> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public Integer getId() {
		return ID;
	}

	public void setId(Integer id) {
		this.ID = id;
	}

	public static boolean isBoolKiemTra() {
		return boolKiemTra;
	}

	public static void setBoolKiemTra(boolean boolKiemTra) {
		Panel_RoomStatus.boolKiemTra = boolKiemTra;
	}
}
