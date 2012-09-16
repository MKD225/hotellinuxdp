package PaymentType;

import hotel.linuxdp.java.controller.PaymentController;
import hotel.linuxdp.java.controller.PaymentTypeController;
import hotel.linuxdp.java.model.Payment;
import hotel.linuxdp.java.model.PaymentType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.PaymentTypeListener;
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
public class Panel_PaymentType extends JPanel implements ActionListener,
		MouseListener, PaymentTypeListener {

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
	private static JTextField txtPaymentTypeID, txtPaymentType,
			txtPaymentTypeDesc;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator;
	private Integer ID;
	private int error = 1;
	private int id;

	public Panel_PaymentType() {
		initComponents();
		all();
		PaymentTypeController.paymentTypeController.addDiscountListener(this);
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		jTable.addMouseListener(this);
		buttonRefresh.addActionListener(this);
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);

	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jTable(), "grow, span,gap top 1,width max");
		add(createForm(), "grow,span,width max");
		add(new JSeparator(), "width max, height 5,span");
		add(createButtons(), "right,dock south");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("PaymentTypeID");
		tableTitle.add("PaymentType");
		tableTitle.add("PaymentTypeDesc");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (PaymentType paymentType : PaymentTypeController.paymentTypeController
					.all()) {
				model.insertRow(0, paymentType.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("Payment Type ID:"), "right");
		txtPaymentTypeID = new JTextField();
		txtPaymentTypeID.setEnabled(false);
		txtPaymentTypeID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtPaymentTypeID, "height 23,width 450,span");

		txtPaymentType = new JTextField();
		titleData.add(new JLabel("PaymentType:"), "right");
		titleData.add(txtPaymentType, "height 23,width max,span");

		txtPaymentTypeDesc = new JTextField();
		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtPaymentTypeDesc, "span, growx , hmin 200");

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
				PaymentType paymentType = new PaymentType(
						txtPaymentType.getText(), txtPaymentTypeDesc.getText());
				PaymentTypeController.paymentTypeController.save(paymentType);
				JOptionPane.showMessageDialog(this, "thanh cong");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "that bai");
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				PaymentType paymentType = new PaymentType(txtPaymentType
						.getText().trim(), txtPaymentTypeDesc.getText().trim());
				paymentType.setPaymentTypeID(ID);
				PaymentTypeController.paymentTypeController.update(paymentType);
				JOptionPane.showMessageDialog(this, "thanh cong");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			int lsRemote = jTable.getSelectedRow();
			if (lsRemote == -1) {
				JOptionPane.showMessageDialog(this, "you need choose one row");
			} else {
				id = Integer.valueOf(jTable.getValueAt(lsRemote, 0).toString());
				try {
					List<Payment> temp = PaymentController.paymentController.all();
					for (int i = 0; i < temp.size(); i++) {
						if (id == temp.get(i).getPaymentTypeID()) {
							this.error = 1;
						} else {
							this.setError(0);
							PaymentTypeController.paymentTypeController
									.delete(id);
							int c = model.getRowCount();
							for (int ii = c - 1; ii >= 0; ii--) {
								model.removeRow(ii);
								jTable.revalidate();
							}
							all();
							JOptionPane.showMessageDialog(this, "Delete to succeed !");
						}
					}
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(this,"you can't delete then Payment ...");
				}
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonDelete.setEnabled(false);
			buttonUpdate.setEnabled(false);
			buttonInsert.setEnabled(true);
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
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon == -1) {
				JOptionPane.showMessageDialog(this, "hay lua chon di ban");
			} else if (iDongDaChon != -1) {

				buttonInsert.setEnabled(false);
				buttonUpdate.setEnabled(true);
				buttonDelete.setEnabled(true);

				Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);

				ID = Integer.parseInt(vDongDaChon.get(0).toString());
				String typeName = vDongDaChon.get(1).toString();
				String desc = vDongDaChon.get(2).toString();

				txtPaymentTypeID.setText(ID.toString());
				txtPaymentType.setText(typeName);
				txtPaymentTypeDesc.setText(desc);

			}
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
	public void roleadd(EventAll<PaymentType> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}
}
