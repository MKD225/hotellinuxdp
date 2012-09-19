package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.CheckOutInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.CheckOut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CheckOutDAO implements CheckOutInterface {
	private static final String ALL = "SELECT CheckoutId,FirstName,WorkPhone,Email,r.ArrivalTime,NumberGuests,RoomRate,CheckoutDate=CONVERT(VARCHAR(10),CheckoutDate,101),r.ReservationID FROM CheckOut co, Reservation r ,Customer c WHERE co.ReservationID = r.ReservationID and r.CustomerID = c.CustomerID";
	private static final String INSERT = "INSERT INTO CheckOut (ReservationID,CheckoutDate) VALUES(?,?)";
	private static final String UPDATE = "UPDATE CheckOut SET ReservationID = ?,CheckoutDate = ? WHERE CheckoutId = ?";

	@Override
	public List<CheckOut> all() throws SQLException {
		ArrayList<CheckOut> listCheckOuts = new ArrayList<CheckOut>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rsSet = pstmt.executeQuery();
		while (rsSet.next()) {
			listCheckOuts.add(createCheckOut(rsSet));
		}
		return listCheckOuts;
	}

	@Override
	public CheckOut insertCheckOut(CheckOut checkOut) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT);
		pstmt.setInt(1, checkOut.getReservationID());
		pstmt.setString(2, checkOut.getCheckoutDate());
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		return checkOut;
	}

	@Override
	public CheckOut updateCheckOut(CheckOut checkOut) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setInt(1, checkOut.getReservationID());
			pstmt.setString(2, checkOut.getCheckoutDate());
			pstmt.setInt(3, checkOut.getCheckOutID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkOut;
	}

	@Override
	public int deleteCheckOut(int object) throws SQLException {
		String DELETE_Checkout = "DELETE FROM CheckOut WHERE CheckoutId = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Checkout);
		statement.close();
		return object;
	}

	private CheckOut createCheckOut(ResultSet rSet) throws SQLException {
		CheckOut checkOut = new CheckOut(rSet.getInt("CheckoutId"),
				rSet.getString("CheckoutDate"), rSet.getString("FirstName"),
				rSet.getString("WorkPhone"), rSet.getString("Email"),
				rSet.getString("ArrivalTime"), rSet.getString("NumberGuests"),
				rSet.getString("RoomRate"), rSet.getInt("ReservationID"));
		checkOut.setCheckOutID(rSet.getInt("CheckoutId"));
		return checkOut;
	}
}
