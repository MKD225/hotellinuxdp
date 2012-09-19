package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.BillInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Bills;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BillsDAO implements BillInterface {
	private static final String select = "SELECT BillID,ReservationID,Comments FROM Bills";
	private static final String ALL = "SELECT BillID,FirstName,RoomType,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),ArrivalTime ,NumberGuests,RoomRate,Comments,r.ReservationID FROM Bills b,Reservation r,Customer c ,RoomType rt WHERE b.ReservationID = r.ReservationID and r.CustomerID = c.CustomerID and r.RoomTypeID=rt.RoomTypeID";
	private static final String INSERT = "INSERT INTO Bills(ReservationID,Comments)VALUES(?,?)";
	private static final String UPDATE = "UPDATE Bills SET ReservationID = ?,Comments = ? WHERE BillID =?";

	@Override
	public List<Bills> all() throws SQLException {
		ArrayList<Bills> listBills = new ArrayList<Bills>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listBills.add(createBillAll(rSet));
		}
		return listBills;
	}

	@Override
	public List<Bills> selectIndex() throws SQLException {
		ArrayList<Bills> listBills = new ArrayList<Bills>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(select);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listBills.add(createBills(rSet));
		}
		return listBills;
	}

	@Override
	public Bills insertBills(Bills bills) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT);
		pstmt.setInt(1, bills.getReservationID());
		pstmt.setString(2, bills.getComments());
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		return bills;
	}

	@Override
	public Bills update(Bills bills) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setInt(1, bills.getReservationID());
			pstmt.setString(2, bills.getComments());
			pstmt.setInt(3, bills.getBillID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bills;
	}

	@Override
	public int deleteBills(int object) throws SQLException {
		String DELETE_Bills = "DELETE FROM Bills WHERE BillID = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Bills);
		statement.close();
		return object;
	}

	private Bills createBills(ResultSet rSet) throws SQLException {
		Bills bills = new Bills(rSet.getInt("ReservationID"),
				rSet.getString("Comments"));
		bills.setBillID(rSet.getInt("BillID"));
		return bills;
	}

	private Bills createBillAll(ResultSet resultSet) throws SQLException {
		Bills bills = new Bills(resultSet.getInt("BillID"),
				resultSet.getString("FirstName"),
				resultSet.getString("RoomType"),
				resultSet.getString("ExpectedCheckinDate"),
				resultSet.getString("ExpectedCheckoutDate"),
				resultSet.getInt("ArrivalTime"),
				resultSet.getInt("NumberGuests"),
				resultSet.getString("RoomRate"),
				resultSet.getString("Comments"),
				resultSet.getInt("ReservationID"));
		return bills;
	}
}
