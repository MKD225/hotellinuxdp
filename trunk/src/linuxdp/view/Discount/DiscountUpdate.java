package Discount;

import hotel.linuxdp.java.controller.DiscountController;
import hotel.linuxdp.java.model.Discount;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.DiscountListener;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import BillItem_Discount.Panel_BillItem;

import net.miginfocom.swing.MigLayout;

public class DiscountUpdate extends JFrame implements ActionListener,
		DiscountListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtDiscountName, txtDiscountPercent, txtDiscountAmount,
			txtID;
	private JButton btnUpdate;
	private String discountName, discountPercent, discountAmount;
	private Integer ID;

	public DiscountUpdate() {
		setLayout(new BorderLayout());
		add(initComponent());
		btnUpdate.addActionListener(this);
		setLocation(470, 250);
		setSize(350, 190);
		DiscountController.discountController
				.addDiscountListener((DiscountListener) this);
	}

	private JComponent initComponent() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("insets 0", "[][grow]"));

		txtID = new JTextField();
		txtDiscountName = new JTextField();
		txtDiscountPercent = new JTextField();
		txtDiscountAmount = new JTextField();
		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new IconButton().updateIcon());
		//
		panel.add(new JLabel("DiscountName"), "right");
		panel.add(txtDiscountName, "span,grow,width max,height 22");
		panel.add(new JLabel("DiscountPercent"), "right");
		panel.add(txtDiscountPercent, "grow,width max,height 22,span");
		panel.add(new JLabel("DiscountAmount"), "right,");
		panel.add(txtDiscountAmount, "grow,width max,height 22,span");

		panel.add(new JSeparator(), "width max,height 5,span");
		panel.add(new JLabel(""), "grow,span");

		panel.add(new JLabel(), "right");
		panel.add(btnUpdate, "right,grow");
		return panel;
	}

	Panel_BillItem billItem = new Panel_BillItem();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdate) {
			Float amount = Float.parseFloat(txtDiscountAmount.getText());
			Integer disPer = Integer.parseInt(txtDiscountPercent.getText());
			try {
				Discount discount = new Discount(txtDiscountName.getText(),
						disPer, amount);
				discount.setDiscountID(ID);
				DiscountController.discountController.update(discount);

				JOptionPane.showMessageDialog(this, "update to succeed", "",
						JOptionPane.INFORMATION_MESSAGE);

				int c = billItem.getModelDiscount().getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					billItem.getModelDiscount().removeRow(i);
					billItem.getTableDiscount().revalidate();
				}
				billItem.allDis();
				this.dispose();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	@Override
	public void roleadd(EventAll<Discount> event) {
		billItem.getModelDiscount().insertRow(0, event.getSource().toArray());
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
		txtDiscountName.setText(discountName);
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
		txtDiscountPercent.setText(discountPercent);
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
		txtDiscountAmount.setText(discountAmount);
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
		txtID.setText(ID.toString());
	}
}
