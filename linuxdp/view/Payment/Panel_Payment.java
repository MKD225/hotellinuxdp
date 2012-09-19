package Payment;

import hotel.linuxdp.java.controller.PaymentController;
import hotel.linuxdp.java.controller.PaymentTypeController;
import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.model.Payment;
import hotel.linuxdp.java.model.PaymentType;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.PaymentListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Payment extends JPanel implements ActionListener,
		MouseListener, PaymentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable jTable;
	private DefaultTableModel model, model2;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtCCNumber, txtCCOwner, txtPaymentAmount;
	private JDateChooser txtCCExpirationMonth, txtCCExpirationYear,
			txtPaymentDate;
	private JComboBox boxPaymentTypeID, boxReservationID;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	private JSeparator separator1 = new JSeparator();
	private JSeparator separator2 = new JSeparator();
	private Integer idPaymentType, idReservation;
	private Hashtable hspaymentType = new Hashtable();
	private Integer id;
	private Integer paymentID;

	public Panel_Payment() {
		initComponents();
		all();
		paymentType();
		reservation();
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		jTable.addMouseListener(this);
		PaymentController.paymentController.addDiscountListener(this);
	}

	protected void initComponents() {
		setLayout(new MigLayout());
		add(jTable(), "span,width max,gap top 5");
		add(separator1, "span,width max,height 5,gap top 10");
		add(createForm(), "span,grow,gap top 10");
		add(separator2, "span,width max,height 5,gap top 10");
		add(createButtons(), "span,right");
	}

	@SuppressWarnings("unchecked")
	private JComponent jTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("ID");
		tableTitle.add("ResID");
		tableTitle.add("FirstName");
		tableTitle.add("CCOwner");
		tableTitle.add("PaymentType");
		tableTitle.add("CCNumber");
		tableTitle.add("CCEMonth");
		tableTitle.add("CCEYear");
		tableTitle.add("CheckIn");
		tableTitle.add("CheckOut");
		tableTitle.add("PaymentAmount");
		tableTitle.add("PaymentDate");

		model = new DefaultTableModel(tableRecords, tableTitle){
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
			for (Payment payment : PaymentController.paymentController.all()) {
				model.insertRow(0, payment.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void paymentType() {
		try {
			for (PaymentType payment : PaymentTypeController.paymentTypeController
					.all()) {
				boxPaymentTypeID.addItem(payment.getPaymentType());
				hspaymentType.put(payment.getPaymentType(),
						payment.getPaymentTypeID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void reservation() {
		try {
			for (Reservation reservation : ReservationController.ReservationController
					.all()) {
				boxReservationID.addItem(reservation.getReservationID());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JComponent createForm() {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		titleData = new JPanel();
		titleData.setLayout(new MigLayout("insets 0", "[grow]"));
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		boxPaymentTypeID = new JComboBox();
		boxReservationID = new JComboBox();
		txtCCNumber = new JTextField();
		txtCCExpirationMonth = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtCCExpirationYear = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		txtCCOwner = new JTextField();
		txtPaymentAmount = new JTextField();
		txtPaymentDate = new JDateChooser("MM/dd/yyyy", "##/##/####", '_');
		Calendar calendar = Calendar.getInstance();
		txtPaymentDate.setCalendar(calendar);
		txtPaymentDate.setEnabled(false);
		titleData.add(new JLabel("PaymentType"), "right");
		titleData.add(boxPaymentTypeID, "grow,width 250,height 22");

		titleData.add(new JLabel("Reservation:"), "right");
		titleData.add(boxReservationID, "grow,width 250,height 22");
		/**
		 * Create Clear 2 column
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("CCExpirationYear:"), "right");
		titleData.add(txtCCExpirationYear, "grow,width 250,height 22");

		titleData.add(new JLabel("CCOwner"), "right");
		titleData.add(txtCCOwner, "grow,width 250,height 22");
		/**
		 * 
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */
		titleData.add(new JLabel("CCExpirationMonth"), "right");
		titleData.add(txtCCExpirationMonth, "grow,width 250,height 22");

		titleData.add(new JLabel("CCNumber :"), "right");
		titleData.add(txtCCNumber, "grow,width 250,height 22");
		/**
		 * 
		 */
		titleData.add(new JLabel(""), "grow,span");
		/**
		 * 
		 */

		titleData.add(new JLabel("PaymentDate"), "right");
		titleData.add(txtPaymentDate, "grow,width 250,height 22");

		titleData.add(new JLabel("PaymentAmount:"), "right");
		titleData.add(txtPaymentAmount, "grow,width 250,height 22");

		panel.add(titleData);

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

	@SuppressWarnings("unchecked")
	public JPanel panelReservation() {
		Vector tableRecord = new Vector();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		model2 = new DefaultTableModel();

		Vector tableTitle = new Vector();
		JTable tableCustomer1 = new JTable();

		tableTitle.add("ID");
		tableTitle.add("CusName");
		tableTitle.add("CusPhone");
		tableTitle.add("CusMail");
		tableTitle.add("CheckIn");
		tableTitle.add("CheckOut");
		tableTitle.add("ArrivalTime");
		tableTitle.add("NumberGuests");
		tableTitle.add("RoomRate");
		tableTitle.add("RoomType");

		model2 = new DefaultTableModel(tableRecord, tableTitle);
		tableCustomer1.setModel(model2);
		tableCustomer1.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableCustomer1);
		panel.add(scrollPane);
		return panel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String ccExpirationMonth = sdf.format(txtCCExpirationMonth.getDate());
		String ccCCExpirationYear = sdf.format(txtCCExpirationYear.getDate());
		String paymentDate = sdf.format(txtPaymentDate.getDate());
		idPaymentType = (Integer) hspaymentType.get(boxPaymentTypeID
				.getSelectedItem().toString());
		idReservation = Integer.parseInt(boxReservationID.getSelectedItem()
				.toString());
		Integer cNumber = Integer.parseInt(txtCCNumber.getText().toString());
		Float paymentAmount = Float.parseFloat(txtPaymentAmount.getText()
				.toString());
		if (e.getSource() == buttonInsert) {
			try {
				Payment payment = new Payment(idPaymentType, cNumber,
						ccExpirationMonth, ccCCExpirationYear,
						txtCCOwner.getText(), paymentAmount, paymentDate,
						idReservation);
				PaymentController.paymentController.save(payment);
				JOptionPane.showMessageDialog(this, "ok");
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
		if (e.getSource() == buttonUpdate) {
			try {
				Payment payment = new Payment(idPaymentType, cNumber,
						ccExpirationMonth, ccCCExpirationYear,
						txtCCOwner.getText(), paymentAmount, paymentDate,
						idReservation);
				payment.setPaymentID(paymentID);
				PaymentController.paymentController.update(payment);
				JOptionPane.showMessageDialog(this, "xửa đổi thành công");
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
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			int c = model.getRowCount();
			for (int i = c - 1; i >= 0; i--) {
				model.removeRow(i);
				jTable.revalidate();
			}
			all();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jTable) {
			int iDongDaChon = jTable.getSelectedRow();
			if (iDongDaChon != -1) {
				try {
					buttonUpdate.setEnabled(true);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
					setPaymentID(Integer.parseInt(vDongDaChon.get(0).toString()));
					Integer resrv = Integer.parseInt(vDongDaChon.get(1)
							.toString());
					String firstName = vDongDaChon.get(2).toString();
					String ccOwner = vDongDaChon.get(3).toString();
					String paymentType = vDongDaChon.get(4).toString();
					String ccNumber = vDongDaChon.get(5).toString();
					String ccEMonth = vDongDaChon.get(6).toString();
					String ccEYear = vDongDaChon.get(7).toString();
					String checkIn = vDongDaChon.get(8).toString();
					String checkOut = vDongDaChon.get(9).toString();
					String paymentAmount = vDongDaChon.get(10).toString();
					String paymentDate = vDongDaChon.get(11).toString();

					Date ccYear = dateFormat.parse(ccEYear);
					Date ccMonth = dateFormat.parse(ccEMonth);
					boxPaymentTypeID.setSelectedItem(paymentType.toString());
					txtCCExpirationYear.setDate(ccYear);
					txtCCExpirationMonth.setDate(ccMonth);
					boxReservationID.setSelectedItem(resrv.toString());
					txtCCOwner.setText(ccOwner);
					txtCCNumber.setText(ccNumber);
					txtPaymentAmount.setText(paymentAmount);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	public void roleadd(EventAll<Payment> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(Integer paymentID) {
		this.paymentID = paymentID;
	}
}
