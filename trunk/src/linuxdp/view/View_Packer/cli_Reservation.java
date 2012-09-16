package View_Packer;

import hotel.linuxdp.java.controller.ReservationController;
import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class cli_Reservation extends JPanel implements KeyListener,
		ActionListener {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	private JTextField txtSearch;
	private JButton btnSearch;
	private Vector tableRecord;
	private Vector tableTitle;
	private JTable jTable;

	public cli_Reservation() {
		setLayout(new MigLayout("insets 0", "[grow]"));
		txtSearch = new JTextField();
		btnSearch = new JButton();
		btnSearch.setIcon(new IconButton().searchIcon());
		add(new JLabel("ExpCheckinDate"), "right");
		add(txtSearch, "width 450, grow");
		add(btnSearch, "span,width 80,height 25");
		add(new JLabel(), "grow,span");
		add(panelRes(), "dock south");
		txtSearch.addKeyListener(this);
		btnSearch.addActionListener(this);
	}

	public JPanel panelRes() {
		tableRecord = new Vector();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		scrollPane = new JScrollPane();
		model = new DefaultTableModel();
		tableTitle = new Vector();
		jTable = new JTable();

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
		model = new DefaultTableModel(tableRecord,tableTitle);
		jTable.setModel(model);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane, BorderLayout.SOUTH);
		return panel;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtSearch) {
			try {
				for (Reservation reservation : ReservationController.ReservationController
						.search(txtSearch.getText().trim())) {
					model.insertRow(0, reservation.toArray());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {

		}

	}
}
