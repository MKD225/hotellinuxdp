package BillItem_Discount;

import hotel.linuxdp.java.controller.BillItemController;
import hotel.linuxdp.java.controller.BillItemTypeController;
import hotel.linuxdp.java.controller.BillsController;
import hotel.linuxdp.java.controller.DiscountController;
import hotel.linuxdp.java.controller.RoomsController;
import hotel.linuxdp.java.model.BillItem;
import hotel.linuxdp.java.model.BillItemType;
import hotel.linuxdp.java.model.Bills;
import hotel.linuxdp.java.model.Discount;
import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.DiscountListener;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Bills.Panel_Bills;
import Discount.DiscountInsert;
import Discount.DiscountUpdate;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "unused", "serial", "rawtypes", "unchecked" })
public class Panel_BillItem extends JPanel implements ActionListener,
		DiscountListener, ItemListener {

	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh,
			btnInsert, btnUpdate, btnDelete, btnRefresh, btnBil, btnCalculator;
	private JPanel panelButton, panelData, panelEven;
	private JTextField txtBillIemID, txtRoomID, txtBillItemDescription,
			txtBillItemAmount, txtArrivalTime, txtRoomRate, txtDiscountAmount,
			txtDiscountPercent;
	private JComboBox boxRoomID, boxBillItemTypeID, boxDiscountID, boxBillID;
	private JDateChooser txtBillChargeDate;
	private DefaultTableModel model, modelDiscount;
	private JScrollPane scrollPane, scrollPaneDiscount;
	private JTable tableBillItem, tableDiscount;
	private Integer disCountID;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	Vector tableRecordD = new Vector();
	Vector tableTitleD = new Vector();
	Hashtable hashRoomID = new Hashtable();
	Hashtable hashBillItemTypeID = new Hashtable();
	Hashtable hashDiscountID = new Hashtable();
	Hashtable hashBillID = new Hashtable();
	private Integer disPercent;
	private Integer arrivalTime;
	private Integer roomRate;
	private Float disMount;
	private Float billamount;

	public Panel_BillItem() {
		initComponents();
		allBliiItem();
		allDis();
		allBillItemType();
		allRoom();
		allBills();
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRefresh.addActionListener(this);
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		btnBil.addActionListener(this);
		boxBillID.addItemListener(this);
		boxDiscountID.addItemListener(this);
		btnCalculator.addActionListener(this);
		DiscountController.discountController.addDiscountListener(this);
	}

	protected void initComponents() {
		setLayout(new MigLayout("wrap 3", "[] [grow] []",
				"[][grow] [grow] [grow] []"));
		add(paneltest(), "grow,span ");
		add(getTable(), "span");
		add(getDiscount(), "grow,span 3");
	}

	private JPanel getTable() {
		JPanel panel = getPanel("BillItem Table");
		panel.setLayout(new MigLayout());
		scrollPane = new JScrollPane();
		tableBillItem = new JTable();

		tableTitle.add("BillItemID");
		tableTitle.add("FirstName");
		tableTitle.add("WorkPhone");
		tableTitle.add("Email");
		tableTitle.add("RoomNumber");
		tableTitle.add("DisName");
		tableTitle.add("DisPercent");
		tableTitle.add("DisAmount");
		tableTitle.add("RTypeRate");
		tableTitle.add("BillItemType");
		tableTitle.add("Amount");
		tableTitle.add("BillChargeDate");

		model = new DefaultTableModel(tableRecords, tableTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tableBillItem.setModel(model);
		tableBillItem.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableBillItem);
		panel.add(scrollPane, "width max,height 200");

		return panel;
	}

	private void allBliiItem() {
		try {
			for (BillItem billItem : BillItemController.billItemController
					.all()) {
				model.insertRow(0, billItem.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JPanel paneltest() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		panel.add(getContracts(), "dock west");
		panel.add(createButton(), "dock south");
		return panel;
	}

	private JPanel getContracts() {
		JPanel panel = getPanel("Information");
		panel.setLayout(new MigLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new MigLayout("insets 0", "[grow]"));
		//
		boxRoomID = new JComboBox();
		boxBillItemTypeID = new JComboBox();
		boxDiscountID = new JComboBox();
		txtBillItemDescription = new JTextField();
		txtBillItemAmount = new JTextField();
		txtBillChargeDate = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtBillChargeDate.setMaxSelectableDate(null);
		txtBillChargeDate.setMinSelectableDate(new Date());
		boxBillID = new JComboBox();
		btnBil = new JButton("..");
		txtArrivalTime = new JTextField();
		txtRoomRate = new JTextField();
		txtDiscountAmount = new JTextField();
		txtDiscountPercent = new JTextField();
		btnCalculator = new JButton("calculator");
		btnCalculator.setIcon(new IconButton().calculatorIcon());
		//
		panel.add(new JLabel("RoomNumber"), "right");
		panel.add(boxRoomID, "width max,height 22,grow");

		panel.add(new JLabel("ChargeDate"), "right");
		panel.add(txtBillChargeDate, "grow,width max,height 22");
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("BillItemType:"), "right");
		panel.add(boxBillItemTypeID, "grow,height 22,width max");

		panel.add(new JLabel("Description:"), "right");
		panel.add(txtBillItemDescription, "grow,width max,height 22");
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		boxDiscountID.addItem("- choose -");
		panel.add(new JLabel("DiscountName:"), "right");
		panel.add(boxDiscountID, "grow,width max,height 22");

		panel.add(new JLabel("DisPercent(%):"), "right");
		panel.add(txtDiscountPercent, "height 22,grow,split 3");
		txtDiscountPercent.setEditable(false);
		txtDiscountAmount.setForeground(new Color(252, 0, 0));
		panel.add(new JLabel("DisAmount:"), "right");
		panel.add(txtDiscountAmount, "grow,height 22");
		txtDiscountAmount.setEditable(false);
		txtDiscountAmount.setForeground(new Color(252, 0, 0));
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("BillID"), "right");
		panel.add(boxBillID, "grow,width max,height 22,split 2");
		panel.add(btnBil, "width 3,height 3");

		panel.add(new JLabel("Amount"), "right");
		panel.add(txtBillItemAmount, "width max,height 22,grow");
		txtBillItemAmount.setForeground(new Color(252, 0, 0));
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("ArrivalTime"), "right");
		panel.add(txtArrivalTime, "grow,width max,height 22");
		txtArrivalTime.setEditable(false);
		txtArrivalTime.setForeground(new Color(252, 0, 0));

		panel.add(new JLabel("Calculator"), "right");
		panel.add(btnCalculator, "");
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel("RoomRate"), "right");
		panel.add(txtRoomRate, "grow,width max,height 22");
		txtRoomRate.setForeground(new Color(252, 0, 0));
		txtRoomRate.setEditable(false);
		/**
		 * Create Clear 2 column
		 */
		panel.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		panel.add(new JLabel(), "right");
		return panel;
	}

	public JPanel createButton() {
		panelEven = new JPanel(new MigLayout("right", "[]"));
		buttonInsert = new JButton("Insert");
		buttonInsert.setIcon(new IconButton().insertIcon());
		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new IconButton().updateIcon());
		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new IconButton().refreshIcon());

		panelEven.add(buttonInsert, "split 3, flowy, sg 1");
		panelEven.add(buttonUpdate, "sg 1");
		panelEven.add(buttonDelete, "sg 1,wrap");
		panelEven.add(buttonRefresh, "sg 1,wrap ");

		return panelEven;
	}

	private JPanel getDiscount() {
		JPanel panel = getPanel("Discount Table");
		panel.setLayout(new MigLayout("wrap 4", "[grow] 16 [] 32 [] []",
				"[grow,:200:] [] [] []"));
		scrollPaneDiscount = new JScrollPane();
		setTableDiscount(new JTable());
		setModelDiscount(new DefaultTableModel());

		setTableDiscount(new JTable());
		tableTitleD.add("DiscountID");
		tableTitleD.add("DiscountName");
		tableTitleD.add("DiscountPercent(%)");
		tableTitleD.add("DiscountAmount($ USA)");

		setModelDiscount(new DefaultTableModel(tableRecordD, tableTitleD));
		getTableDiscount().setModel(getModelDiscount());
		scrollPaneDiscount.setViewportView(getTableDiscount());
		panel.add(scrollPaneDiscount, "width max,grow, span 3");

		// table panel add
		btnInsert = new JButton("Insert");
		btnInsert.setIcon(new IconButton().insertIcon());
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new IconButton().updateIcon());
		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new IconButton().deleteIcon());
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new IconButton().refreshIcon());

		panel.add(btnInsert, "split 4, flowy, sg 1, top");
		panel.add(btnUpdate, "sg 1");
		panel.add(btnDelete, "sg 1");
		panel.add(btnRefresh, "sg 1, wrap");

		return panel;
	}

	public void allDis() {
		try {
			for (Discount discount : DiscountController.discountController
					.all()) {
				getModelDiscount().insertRow(0, discount.toArray());
				boxDiscountID.addItem(discount.getDiscountName());
				hashDiscountID.put(discount.getDiscountName(),
						discount.getDiscountID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean rootPaneCheckingEnabled = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInsert) {
			DiscountInsert panel = new DiscountInsert(null,
					rootPaneCheckingEnabled);
			panel.setVisible(true);
		}
		if (e.getSource() == btnUpdate) {

			int iDongDaChon = tableDiscount.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(null, "no row role select");
			} else {
				disCountID = Integer.parseInt(tableDiscount.getValueAt(
						iDongDaChon, 0).toString());
				String name = (tableDiscount.getValueAt(iDongDaChon, 1)
						.toString());
				String Percent = (tableDiscount.getValueAt(iDongDaChon, 2)
						.toString());
				String mount = (tableDiscount.getValueAt(iDongDaChon, 3)
						.toString());

				DiscountUpdate update = new DiscountUpdate();
				update.setVisible(true);

				update.setID(disCountID);
				update.setDiscountName(name);
				update.setDiscountPercent(Percent);
				update.setDiscountAmount(mount);
			}
		}
		if (e.getSource() == btnDelete) {
			int lsRemote = tableDiscount.getSelectedRow();
			if (lsRemote == -1) {
			} else {
				int id = Integer.valueOf(tableDiscount.getValueAt(lsRemote, 0)
						.toString());
				try {
					DiscountController.discountController.delete(id);
					JOptionPane.showMessageDialog(null, id + " is deleted");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnRefresh) {
			int c = model.getRowCount();
			for (int i = c - 1; i >= 0; i--) {
				model.removeRow(i);
				tableDiscount.revalidate();
			}
			allBliiItem();
		}

		if (e.getSource() == btnBil) {
			Panel_Bill_Dialog bills = new Panel_Bill_Dialog();
			bills.setVisible(true);

		}
		if (e.getSource() == btnCalculator) {
			arrivalTime = Integer.parseInt(txtArrivalTime.getText());
			disMount = Float.parseFloat(txtDiscountAmount.getText());
			roomRate = Integer.parseInt(txtRoomRate.getText());
			billamount = (float) (roomRate * arrivalTime) - disMount;
			txtBillItemAmount.setText(String.valueOf(billamount));
		}
	}

	private JPanel getPanel(String title) {
		JPanel panel = new JPanel();
		return panel;
	}

	private void allRoom() {
		try {
			for (Rooms rooms : RoomsController.roomsController.fillRCheckIn()) {
				boxRoomID.addItem(rooms.getRoomNumber());
				hashRoomID.put(rooms.getRoomNumber(), rooms.getRoomID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void allBillItemType() {
		try {
			for (BillItemType billItemType : BillItemTypeController.billItemController
					.all()) {
				boxBillItemTypeID.addItem(billItemType.getBillItemType());
				hashBillItemTypeID.put(billItemType.getBillItemType(),
						billItemType.getBillItemTypeID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void allBills() {
		try {
			for (Bills bills : BillsController.billController.select()) {
				boxBillID.addItem(bills.getBillID());
				hashBillID.put(bills.getBillID(), bills.getBillID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void roleadd(EventAll<Discount> event) {
		getModelDiscount().insertRow(0, event.getSource().toArray());

	}

	public DefaultTableModel getModelDiscount() {
		return modelDiscount;
	}

	public void setModelDiscount(DefaultTableModel modelDiscount) {
		this.modelDiscount = modelDiscount;
	}

	public JTable getTableDiscount() {
		return tableDiscount;
	}

	public void setTableDiscount(JTable tableDiscount) {
		this.tableDiscount = tableDiscount;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == boxBillID) {
			int billid = boxBillID.getSelectedIndex();
			try {
				Bills bills = BillsController.billController.all().get(billid);
				txtArrivalTime.setText(bills.getArrivalTime().toString());
				txtRoomRate.setText(bills.getRoomRate());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == boxDiscountID) {
			try {
				int id = boxDiscountID.getSelectedIndex() - 1;
				Discount s = DiscountController.discountController.all()
						.get(id);
				txtDiscountAmount.setText(s.getDiscountAmount().toString());
				txtDiscountPercent.setText(s.getDiscountPercent().toString());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Integer getDisPercent() {
		return disPercent;
	}

	public void setDisPercent(Integer disPercent) {
		this.disPercent = disPercent;
	}

	public Integer getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Integer arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(Integer roomRate) {
		this.roomRate = roomRate;
	}

	public Float getDisMount() {
		return disMount;
	}

	public void setDisMount(Float disMount) {
		this.disMount = disMount;
	}

	public Float getBillamount() {
		return billamount;
	}

	public void setBillamount(Float billamount) {
		this.billamount = billamount;
	}
}
