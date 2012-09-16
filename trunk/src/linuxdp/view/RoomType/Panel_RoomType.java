package RoomType;

import hotel.linuxdp.java.controller.RoomTypeController;
import hotel.linuxdp.java.controller.RoomsController;
import hotel.linuxdp.java.model.RoomType;
import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomTypeListener;
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
public class Panel_RoomType extends JPanel implements RoomTypeListener,
		ActionListener, MouseListener {

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
	private static JTextField txtRoomTypeID, txtRoomType, txtRoomDesc,
			txtRoomRate, txtNumbeds;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private Integer ID;
	private Integer iDongDaChon;
	private JSeparator separator = new JSeparator();
	private JSeparator separator2 = new JSeparator();
	private int error = 1;

	public Panel_RoomType() {
		initComponents();
		RoomTypeController.roomTypeController.addDiscountListener(this);
		all();
		buttonInsert.addActionListener(this);
		jTable.addMouseListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(getTable(), "grow, span,gap top 1");
		add(separator, "span,width max,height 5");
		add(createForm(), "span");
		add(separator2, "width max,height 5");
		add(createButtons(), "right ,dock south");
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
		tableTitle.add("RoomTypeID");
		tableTitle.add("RoomType");
		tableTitle.add("RoomDesc");
		tableTitle.add("RoomRate");
		tableTitle.add("NumBeds");

		model = new DefaultTableModel(tableRecords, tableTitle) {
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
			for (RoomType roomType : RoomTypeController.roomTypeController
					.all()) {
				model.insertRow(0, roomType.toArray());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			Integer number = Integer.parseInt(txtNumbeds.getText());
			try {
				RoomType roomType = new RoomType(txtRoomType.getText(),
						txtRoomDesc.getText(), txtRoomRate.getText(), number);
				RoomTypeController.roomTypeController.save(roomType);
				System.out.println("Thanh cong ");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			Integer numBeds = Integer.parseInt(txtNumbeds.getText().trim());
			try {
				RoomType roomType = new RoomType(txtRoomType.getText().trim(),
						txtRoomDesc.getText().trim(), txtRoomRate.getText()
								.trim(), numBeds);
				roomType.setRoomTypeID(ID);
				RoomTypeController.roomTypeController.update(roomType);
				JOptionPane.showMessageDialog(this, "thanh cong");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, "that bai");
			}
		}
		if (e.getSource() == buttonDelete) {
			try {
				List<Rooms> temp = RoomsController.roomsController.all();
				for (int i = 0; i < temp.size(); i++) {
					if (ID == temp.get(i).getRoomTypeID()) {
						this.setError(1);
						break;
					} else {
						this.setError(0);
						RoomTypeController.roomTypeController.deleteRole(ID);
						int c = model.getRowCount();
						for (int ii = c - 1; ii >= 0; ii--) {
							model.removeRow(ii);
							jTable.revalidate();
						}
						all();
					}
				}
				JOptionPane.showMessageDialog(this, "delete to succeed !");
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, "can't delete");
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
			txtRoomTypeID.setText("");
			txtRoomType.setText("");
			txtRoomDesc.setText("");
			txtRoomRate.setText("");
			txtNumbeds.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon != -1) {
				buttonInsert.setEnabled(false);
				buttonDelete.setEnabled(true);
				buttonUpdate.setEnabled(true);
				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
				ID = Integer.parseInt(jTable.getValueAt(iDongDaChon, 0)
						.toString());

				String roomType = vDongDaChon.get(1).toString();
				String roomDesc = vDongDaChon.get(2).toString();
				String roomRate = vDongDaChon.get(3).toString();
				String numBeds = vDongDaChon.get(4).toString();

				txtRoomTypeID.setText(ID.toString());
				txtRoomType.setText(roomType);
				txtRoomDesc.setText(roomDesc);
				txtRoomRate.setText(roomRate);
				txtNumbeds.setText(numBeds);

			}
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("Role ID:"), "right");
		txtRoomTypeID = new JTextField();
		txtRoomTypeID.setEnabled(false);
		txtRoomTypeID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtRoomTypeID, "height 23,width 450,span");

		txtRoomType = new JTextField();
		titleData.add(new JLabel("Ronm Type:"), "right");
		titleData.add(txtRoomType, "height 23,width max,span");

		txtRoomDesc = new JTextField();
		titleData.add(new JLabel("Room Desc:"), "right");
		titleData.add(txtRoomDesc, "height 23,width max,span");

		txtRoomRate = new JTextField();
		titleData.add(new JLabel("Room Rate:"), "right");
		titleData.add(txtRoomRate, "height 23,width max,span");

		txtNumbeds = new JTextField();
		titleData.add(new JLabel("Number Bed:"), "right");
		titleData.add(txtNumbeds, "height 23,width max,span");

		panel.add(titleData, "span");
		separator = new JSeparator();
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
	public void roleadd(EventAll<RoomType> event) {
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

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
