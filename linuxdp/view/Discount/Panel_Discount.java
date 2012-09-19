package Discount;

import hotel.linuxdp.java.controller.DiscountController;
import hotel.linuxdp.java.model.Discount;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.DiscountListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Discount extends JPanel implements ActionListener,
		MouseListener, DiscountListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	private JTextField txtdisID, txtDisName, txtDisPercent, txtDisAmount;
	private JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private Integer disID;
	private int error = 1;
	private int id;

	public Panel_Discount() {
		initComponents();
		all();
		jTable.addMouseListener(this);
		buttonInsert.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);
		DiscountController.discountController.addDiscountListener(this);
	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jTable(), "width max,span");
		add(new JSeparator(), "width max, height 5,span,grow");
		add(createForm(), "width max,span,grow,height 200");
		add(new JSeparator(), "width max, height 5,span");
		add(createButtons(), "right,dock south");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("DiscountID");
		tableTitle.add("DiscountName");
		tableTitle.add("DiscountPercent(%)");
		tableTitle.add("DiscountAmount($)");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (Discount discount : DiscountController.discountController
					.all()) {
				model.insertRow(0, discount.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Component createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[grow]"));
		panel.setBorder(BorderFactory.createTitledBorder(""));
		txtdisID = new JTextField();
		txtDisAmount = new JTextField();
		txtDisName = new JTextField();
		txtDisPercent = new JTextField();

		panel.add(new JLabel("Discount ID"), "right,gap top 15");
		panel.add(txtdisID, "width max,height 23,span");
		txtdisID.setEnabled(false);
		txtdisID.setDisabledTextColor(new Color(250, 2, 2));

		panel.add(new JLabel("Discount Name"), "right,gap top 15");
		panel.add(txtDisName, "width max,height 23,span");

		panel.add(new JLabel("DisPercent(%)"), "right,gap top 15");
		panel.add(txtDisPercent, "width max, height 23,span");

		panel.add(new JLabel("DisAmount($$)"), "right,gap top 15");
		panel.add(txtDisAmount, "width max,height 23,gap bottom 15");

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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				Integer percent = Integer.parseInt(txtDisPercent.getText()
						.toString());
				Float amount = Float.parseFloat(txtDisAmount.getText()
						.toString());
				Discount discount = new Discount(txtDisName.getText(), percent,
						amount);

				DiscountController.discountController.save(discount);
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				Integer percent = Integer.parseInt(txtDisPercent.getText()
						.toString());
				Float amount = Float.parseFloat(txtDisAmount.getText()
						.toString());
				Discount discount = new Discount(txtDisName.getText(), percent,
						amount);
				discount.setDiscountID(disID);
				DiscountController.discountController.update(discount);
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
					DiscountController.discountController.delete(id);
					int c = model.getRowCount();
					for (int ii = c - 1; ii >= 0; ii--) {
						model.removeRow(ii);
						jTable.revalidate();
					}
					all();
					JOptionPane.showMessageDialog(this, "delete succeed !");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonDelete.setEnabled(false);
			buttonUpdate.setEnabled(false);
			int c = model.getRowCount();
			for (int ii = c - 1; ii >= 0; ii--) {
				model.removeRow(ii);
				jTable.revalidate();
			}
			all();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int iDongDaChon = jTable.getSelectedRow();
		if (iDongDaChon == -1) {
			JOptionPane.showMessageDialog(this, "hay chon 1 row");
		} else {
			buttonDelete.setEnabled(true);
			buttonInsert.setEnabled(false);
			buttonUpdate.setEnabled(true);
			Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
			disID = Integer.parseInt(vDongDaChon.get(0).toString());
			String disName = vDongDaChon.get(1).toString();
			String disPercent = vDongDaChon.get(2).toString();
			String disAmount = vDongDaChon.get(3).toString();

			txtdisID.setText(disID.toString());
			txtDisName.setText(disName);
			txtDisPercent.setText(disPercent);
			txtDisAmount.setText(disAmount);
		}
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

	@Override
	public void roleadd(EventAll<Discount> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

}
