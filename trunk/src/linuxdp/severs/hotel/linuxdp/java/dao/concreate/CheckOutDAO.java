package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.CheckOutInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.CheckOut;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CheckOutDAO implements CheckOutInterface {
	private static final String ALL = "SELECT * FROM ";
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
		pstmt.setDate(2, (Date) checkOut.getCheckoutDate());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		checkOut.setCheckOutID(integer);
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
			pstmt.setDate(2, (Date) checkOut.getCheckoutDate());
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
		String DELETE_Checkout = "DELETE FROM CheckOut WHERE CheckoutId = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Checkout);
		statement.close();
		return object;
	}

	private CheckOut createCheckOut(ResultSet rSet) throws SQLException {
		CheckOut checkOut = new CheckOut(rSet.getInt("ReservationID"),
				rSet.getDate("CheckoutDate"));
		checkOut.setCheckOutID(rSet.getInt("CheckoutId"));
		return checkOut;
	}

}
