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
public class Cli_Reservation extends JPanel implements KeyListener {
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

	public Cli_Reservation() {
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
		Title.addElement("Id");
		Title.addElement("CusName");
		Title.addElement("CusPhone");
		Title.addElement("CusMail");
		Title.addElement("CheckIn");
		Title.addElement("CheckOut");
		Title.addElement("ArrivalTime");
		Title.addElement("NumberGuests");
		Title.addElement("RoomRate");
		
		DefaultTableModel Default = new DefaultTableModel(Title, 0);
		try {
			while (resultSet.next()) {
				Row = new Vector();
				Row.addElement(resultSet.getString("ReservationID"));
				Row.addElement(resultSet.getString("FirstName"));
				Row.addElement(resultSet.getString("WorkPhone"));
				Row.addElement(resultSet.getString("Email"));
				Row.addElement(resultSet.getString("ExpectedCheckinDate"));
				Row.addElement(resultSet.getString("ExpectedCheckoutDate"));
				Row.addElement(resultSet.getString("ArrivalTime"));
				Row.addElement(resultSet.getString("NumberGuests"));
				Row.addElement(resultSet.getString("RoomRate"));

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
			String sql = "SELECT ReservationID,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),ArrivalTime,NumberGuests,RoomRate,Comment,FirstName,WorkPhone,Email,MiddleName,LastName,BirthDay=CONVERT(varchar,Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone,RoomType FROM Reservation r,Customer c ,RoomType rt WHERE r.CustomerID =c.CustomerID and r.RoomTypeID=rt.RoomTypeID";
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
						.executeQuery("SELECT ReservationID,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),ArrivalTime,NumberGuests,RoomRate,Comment,FirstName,WorkPhone,Email,MiddleName,LastName,BirthDay=CONVERT(varchar,Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone,RoomType FROM Reservation r,Customer c ,RoomType rt WHERE r.CustomerID =c.CustomerID and r.RoomTypeID=rt.RoomTypeID and FirstName like '%"
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
