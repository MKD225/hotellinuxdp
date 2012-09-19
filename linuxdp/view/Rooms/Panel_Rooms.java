package Rooms;

import hotel.linuxdp.java.controller.CheckInController;
import hotel.linuxdp.java.controller.RoomStatusController;
import hotel.linuxdp.java.controller.RoomTypeController;
import hotel.linuxdp.java.controller.RoomsController;
import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.model.RoomStatus;
import hotel.linuxdp.java.model.RoomType;
import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomsListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Rooms extends JPanel implements ActionListener,
		RoomsListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jRoom;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private JComboBox boxRoomTypeID, boxRoomStatusID;
	private JTextField txtRoomNumber;
	private JTextArea txtDescription;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private JSeparator separator = new JSeparator();
	private JSeparator separator2 = new JSeparator();
	private Hashtable hashRoomType = new Hashtable();
	private Hashtable hashRoomStatus = new Hashtable();
	private Integer ID, IDSTATUS, IDROOM;
	private int error = 1;

	public Panel_Rooms() {
		initComponents();
		all();
		allRoomType();
		addRoomStatus();
		RoomsController.roomsController.addDiscountListener(this);
		jRoom.addMouseListener(this);
		buttonInsert.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonRefresh.addActionListener(this);
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);
		
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(jRoom(), "grow, span,gap top 1,width max");
		add(separator, "span,width max,height 5");
		add(createForm(), "grow,span,width max");
		add(separator2, "width max, height 5,span");
		add(createButtons(), "right,dock south");
	}

	@SuppressWarnings("unchecked")
	private JComponent jRoom() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		scrollPane = new JScrollPane();
		jRoom = new JTable();
		tableTitle.add("RoomID");
		tableTitle.add("RoomNumber");
		tableTitle.add("RoomType");
		tableTitle.add("NumBeds");
		tableTitle.add("RoomTypeRate");
		tableTitle.add("RoomStatus");
		tableTitle.add("Description");

		model = new DefaultTableModel(tableRecords, tableTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		jRoom.setModel(model);
		jRoom.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jRoom);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (Rooms rooms : RoomsController.roomsController.all()) {
				model.insertRow(0, rooms.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		titleData = new JPanel();
		titleData.setLayout(new MigLayout("insets 0", "[grow]"));
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		txtRoomNumber = new JTextField();
		txtDescription = new JTextArea();
		boxRoomStatusID = new JComboBox();
		boxRoomTypeID = new JComboBox();

		titleData.add(new JLabel("Room Number"), "right");
		titleData.add(txtRoomNumber,
				"grow,width max,height 22,span,gap top 4,gap bottom 4");

		titleData.add(new JLabel("RoomType"), "right");
		titleData.add(boxRoomTypeID,
				"grow,width max,height 22,span,gap top 4,gap bottom 4");

		titleData.add(new JLabel("RoomStatus"), "right");
		titleData.add(boxRoomStatusID,
				"grow,width max,height 22,span,gap top 4,gap bottom 4");

		titleData.add(new JLabel("Description"), "right");
		titleData.add(txtDescription,
				"grow,width max,height 150,span,gap top 4,gap bottom 4");

		panel.add(titleData, "span,width max,height 355");
		return panel;
	}

	@SuppressWarnings("unchecked")
	private void allRoomType() {
		try {
			for (RoomType roomType : RoomTypeController.roomTypeController
					.all()) {
				boxRoomTypeID.addItem(roomType.getRoomType());
				hashRoomType.put(roomType.getRoomType(),
						roomType.getRoomTypeID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void addRoomStatus() {
		try {
			for (RoomStatus roomStatus : RoomStatusController.roomStatusController
					.all()) {
				boxRoomStatusID.addItem(roomStatus.getRoomStatusName());
				hashRoomStatus.put(roomStatus.getRoomStatusName(),
						roomStatus.getRoomStatusID());
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

	@Override
	public void roleadd(EventAll<Rooms> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ID = (Integer) hashRoomType.get(boxRoomTypeID.getSelectedItem()
				.toString());
		IDSTATUS = (Integer) hashRoomStatus.get(boxRoomStatusID
				.getSelectedItem().toString());
		if (e.getSource() == buttonInsert) {
			try {
				Rooms rooms = new Rooms(txtRoomNumber.getText(),
						txtDescription.getText(), ID, IDSTATUS);
				RoomsController.roomsController.save(rooms);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jRoom.revalidate();
				}
				all();
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				Rooms rooms = new Rooms(txtRoomNumber.getText(),
						txtDescription.getText(), ID, IDSTATUS);
				rooms.setRoomID(IDROOM);
				RoomsController.roomsController.update(rooms);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jRoom.revalidate();
				}
				all();
				JOptionPane.showMessageDialog(this, "Update to succeed !");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			try {
				List<CheckIn> temp = CheckInController.checkInController.all();
				for (int i = 0; i < temp.size(); i++) {
					if (IDROOM == temp.get(i).getRoomID()) {
						this.error = 0;
					} else {
						this.error = 0;
						RoomsController.roomsController.delete(IDROOM);
						int c = model.getRowCount();
						for (int ii = c - 1; ii >= 0; ii--) {
							model.removeRow(ii);
							jRoom.revalidate();
						}
						all();
					}
				}
				JOptionPane.showMessageDialog(this, "Delete to succeed !");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this,
						"can't delete row bcause check still !");
			}
		}
		if (e.getSource() == buttonRefresh) {

		}
	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		int iDongDaChon = jRoom.getSelectedRow();
		if (iDongDaChon == -1) {
			JOptionPane.showMessageDialog(this, "you select 1 row");
		} else {
			buttonDelete.setVisible(true);
			buttonUpdate.setEnabled(true);
			Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
			IDROOM = Integer.parseInt(vDongDaChon.get(0).toString());
			String roomNumber = vDongDaChon.get(1).toString();
			String roomType = vDongDaChon.get(2).toString();
			String numBeds = vDongDaChon.get(3).toString();
			String roomTypeRate = vDongDaChon.get(4).toString();
			String roomStatus = vDongDaChon.get(5).toString();
			String des = vDongDaChon.get(6).toString();

			txtRoomNumber.setText(roomNumber);
			boxRoomTypeID.setSelectedItem(roomType.toString());
			boxRoomStatusID.setSelectedItem(roomStatus.toString());
			txtDescription.setText(des);
			try {
				List<CheckIn> temp = CheckInController.checkInController.all();
				for (int i = 0; i < temp.size(); i++) {
					if (IDROOM == temp.get(i).getRoomID()) {
						this.error = 0;
						buttonUpdate.setEnabled(false);
						buttonDelete.setEnabled(false);
						break;
					} else {
						this.error = 0;
						buttonUpdate.setEnabled(true);
						buttonDelete.setEnabled(true);
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
