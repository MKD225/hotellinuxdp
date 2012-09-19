package View_Packer;

import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

/**
 * 
 * @author linuxdp
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class Cli_CheckIn extends JPanel implements KeyListener {
	private JLabel jLabel;
	private JPanel jPanel_Reser;
	private JScrollPane jScrollPane;
	private JTable jTable;
	private JTextField txtSearch;

	Connection connection;
	Statement statement;
	ResultSet resultSet;
	Vector Title;
	Vector Row;

	public Cli_CheckIn() {
		setLayout(new MigLayout("insets 0", "[grow]"));
		jLabel = new JLabel("FirstName");
		txtSearch = new JTextField();
		add(jLabel, "right");
		add(txtSearch, "width 400,span");
		add(initComponents(), "dock south");
		all();
		txtSearch.addKeyListener(this);
	}

	private Component initComponents() {
		jPanel_Reser = new JPanel();
		jPanel_Reser.setLayout(new BorderLayout());
		jScrollPane = new JScrollPane();
		jTable = new JTable();
		jTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane.setViewportView(jTable);
		jPanel_Reser.add(jScrollPane);
		return jPanel_Reser;
	}

	public void All() {
		Title = new Vector();
		Title.addElement("CheckinID");
		Title.addElement("ReservationID");
		Title.addElement("FirstName");
		Title.addElement("ExpectedCheckinDate");
		Title.addElement("ExpectedCheckoutDate");
		Title.addElement("CheckInDate");
		Title.addElement("RoomNumber");
		Title.addElement("NumberGuests");
		Title.addElement("RoomType");
		Title.addElement("NumBeds");
		
		DefaultTableModel Default = new DefaultTableModel(Title, 0);
		try {
			while (resultSet.next()) {
				Row = new Vector();
				Row.addElement(resultSet.getString("CheckinID"));
				Row.addElement(resultSet.getString("ReservationID"));
				Row.addElement(resultSet.getString("FirstName"));
				Row.addElement(resultSet.getString("ExpectedCheckinDate"));
				Row.addElement(resultSet.getString("ExpectedCheckoutDate"));
				Row.addElement(resultSet.getString("CheckInDate"));
				Row.addElement(resultSet.getString("RoomNumber"));
				Row.addElement(resultSet.getString("NumberGuests"));
				Row.addElement(resultSet.getString("RoomType"));
				Row.addElement(resultSet.getString("NumBeds"));
				Default.addRow(Row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jTable.setModel(Default);
	}

	private void all() {
		try {
			connection = DaoFactory.getMysql().openConnection();
			statement = connection.createStatement();
			String sql = "SELECT CheckinID,r.ReservationID,FirstName, ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101), ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),CheckInDate=CONVERT(varchar,CheckInDate,101),RoomNumber,NumberGuests, RoomType,NumBeds,RoomStatusName ,ro.RoomID FROM CheckIn ci ,Customer cus, Reservation r,RoomType rt, RoomStatus rs , Rooms ro Where ci.ReservationID = r.ReservationID  and ci.RoomID = ro.RoomID and  ro.RoomTypeID = rt.RoomTypeID and  ro.RoomStatusID = rs.RoomStatusID and r.CustomerID = cus.CustomerID";
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		All();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtSearch) {
			try {
				resultSet = statement
						.executeQuery("SELECT CheckinID,r.ReservationID,FirstName, ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101), ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),CheckInDate=CONVERT(varchar,CheckInDate,101),RoomNumber,NumberGuests, RoomType,NumBeds,RoomStatusName ,ro.RoomID FROM CheckIn ci ,Customer cus, Reservation r,RoomType rt, RoomStatus rs , Rooms ro Where ci.ReservationID = r.ReservationID  and ci.RoomID = ro.RoomID and  ro.RoomTypeID = rt.RoomTypeID and  ro.RoomStatusID = rs.RoomStatusID and r.CustomerID = cus.CustomerID and FirstName like '%"
								+ txtSearch.getText().trim() + "%'");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			All();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
