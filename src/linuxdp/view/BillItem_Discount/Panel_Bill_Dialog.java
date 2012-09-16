package BillItem_Discount;

import java.awt.BorderLayout;

import Bills.Panel_Bills;

public class Panel_Bill_Dialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;

	public Panel_Bill_Dialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setLayout(new BorderLayout());
		add(new Panel_Bills());
		setSize(1000, 500);
		setLocation(210, 120);
	}
}
