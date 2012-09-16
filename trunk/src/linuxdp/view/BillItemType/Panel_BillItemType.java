package BillItemType;

import hotel.linuxdp.java.controller.BillItemController;
import hotel.linuxdp.java.controller.BillItemTypeController;
import hotel.linuxdp.java.model.BillItem;
import hotel.linuxdp.java.model.BillItemType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.BillItemTypeListener;
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
 * @author linuxdp Bill_BillItemType
 */
@SuppressWarnings("rawtypes")
public class Panel_BillItemType extends JPanel implements ActionListener,
		BillItemTypeListener, MouseListener {

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
	private static JTextField txtID, txtBillItemType;
	private JTextArea txtDescription;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator;
	private Integer ID;
	private Integer error = 0;
	private int id;

	public Panel_BillItemType() {
		initComponents();
		all();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonUpdate.setEnabled(false);
		buttonDelete.addActionListener(this);
		buttonDelete.setEnabled(false);
		buttonRefresh.addActionListener(this);
		BillItemTypeController.billItemController.addAdminListener(this);
		jTable.addMouseListener(this);
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(getTable(), "grow, span,gap top 1");
		add(createForm(), "grow,span");
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
		tableTitle.add("ID");
		tableTitle.add("BillItemType");
		tableTitle.add("Description");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (BillItemType itemType : BillItemTypeController.billItemController
					.all()) {
				model.insertRow(0, itemType.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("Bill Type ID:"), "right");
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtID, "height 22,width 450,span");

		txtBillItemType = new JTextField();
		titleData.add(new JLabel("BillItem Type:"), "right");
		titleData.add(txtBillItemType, "height 22,width max,span");

		txtDescription = new JTextArea();
		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtDescription, "span, growx,hmin 220");

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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				BillItemType itemType = new BillItemType(
						txtBillItemType.getText(), txtDescription.getText(),
						null);
				BillItemTypeController.billItemController.save(itemType);
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				BillItemType itemType = new BillItemType(
						txtBillItemType.getText(), txtDescription.getText(),
						null);
				itemType.setBillItemTypeID(ID);
				BillItemTypeController.billItemController
						.updateBillItem(itemType);
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			int lsRemote = jTable.getSelectedRow();
			if (lsRemote == -1) {
				JOptionPane.showMessageDialog(this, "hay chon 1 cot di ban");
			} else {
				id = Integer.valueOf(jTable.getValueAt(lsRemote, 0).toString());
				try {
					List<BillItem> temp = BillItemController.billItemController.all();
					for (int i = 0; i < temp.size(); i++) {
						if (id == temp.get(i).getBillItemTypeID()) {
							this.error = 1;
							JOptionPane.showMessageDialog(this,"ban khong the xoa khi admin van con");
							break;
						} else {
							this.error = 0;
							BillItemTypeController.billItemController.delete(id);
							JOptionPane.showMessageDialog(this, "thanh cong");
							int c = model.getRowCount();
							for (int ii = c - 1; ii >= 0; ii--) {
								model.removeRow(ii);
								jTable.revalidate();
							}
							all();
						}
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
			buttonInsert.setEnabled(true);
		}
	}

	@Override
	public void roleadd(EventAll<BillItemType> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int iDongDaChon = jTable.getSelectedRow();
		if (iDongDaChon == -1) {
			JOptionPane.showMessageDialog(this, "hay chon 1 cot");
		} else {
			buttonUpdate.setEnabled(true);
			buttonDelete.setEnabled(true);
			buttonInsert.setEnabled(false);
			Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);

			ID = Integer.parseInt(vDongDaChon.get(0).toString());
			String typeName = vDongDaChon.get(1).toString();
			String description = vDongDaChon.get(2).toString();

			txtID.setText(ID.toString());
			txtBillItemType.setText(typeName);
			txtDescription.setText(description);

		}
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

	public Integer getErrors() {
		return error;
	}

	public void setErrors(Integer errors) {
		this.error = errors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
