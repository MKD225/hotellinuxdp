package BillItem_Discount;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Bills.Panel_Bills;

public class Panel_Bill_Dialog extends JFrame {
	private static final long serialVersionUID = 1L;

	public Panel_Bill_Dialog() {
		setLayout(new BorderLayout());
		add(new Panel_Bills());
		setSize(1000, 500);
		setLocation(210, 120);
	}
}
