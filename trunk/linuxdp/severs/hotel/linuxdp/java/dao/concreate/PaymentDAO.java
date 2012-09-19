package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.PaymentInteface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements PaymentInteface {

	private static final String ALL = "SELECT p.PaymentID,r.ReservationID,c.FirstName,p.CCOwner,pt.PaymentTypeID,pt.PaymentType, p.CCNumber,CCExpirationMonth=CONVERT(varchar,CCExpirationMonth,101),CCExpirationYear=CONVERT(varchar,CCExpirationYear,101),ExpectedCheckinDate =CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate =CONVERT(varchar,ExpectedCheckoutDate,101),p.PaymentAmount,PaymentDate=CONVERT(varchar,PaymentDate,101) FROM Payment p,PaymentType pt,Reservation r,Customer c WHERE  p.PaymentTypeID = pt.PaymentTypeID and p.ReservationID = r.ReservationID and r.CustomerID = c.CustomerID ";
	private static final String INSERT = "INSERT INTO Payment(PaymentTypeID,CCNumber,CCExpirationMonth,CCExpirationYear,CCOwner,PaymentAmount,PaymentDate,ReservationID)VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE payment SET PaymentTypeID = ?,CCNumber = ?,CCExpirationMonth = ?,CCExpirationYear = ?,CCOwner = ?,PaymentAmount = ?,PaymentDate = ?,ReservationID = ? WHERE PaymentID =?";

	@Override
	public List<Payment> all() throws SQLException {
		ArrayList<Payment> listPayments = new ArrayList<Payment>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listPayments.add(createPayment(rSet));
		}
		return listPayments;
	}

	@Override
	public Payment insertPayment(Payment payment) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setInt(1, payment.getPaymentTypeID());
		pstmt.setInt(2, payment.getCCNumber());
		pstmt.setString(3, payment.getCCExpirationMonth());
		pstmt.setString(4, payment.getCCExpirationYear());
		pstmt.setString(5, payment.getCCOwner());
		pstmt.setFloat(6, payment.getPaymentAmount());
		pstmt.setString(7, payment.getPaymentDate());
		pstmt.setInt(8, payment.getReservationID());

		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		payment.setPaymentID(integer);
		pstmt.close();
		connection.close();
		return payment;
	}

	@Override
	public Payment updatePayment(Payment payment) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);

			pstmt.setInt(1, payment.getPaymentTypeID());
			pstmt.setInt(2, payment.getCCNumber());
			pstmt.setString(3, payment.getCCExpirationMonth());
			pstmt.setString(4, payment.getCCExpirationYear());
			pstmt.setString(5, payment.getCCOwner());
			pstmt.setFloat(6, payment.getPaymentAmount());
			pstmt.setString(7, payment.getPaymentDate());
			pstmt.setInt(8, payment.getReservationID());
			pstmt.setInt(9, payment.getPaymentTypeID());
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}

	@Override
	public int deleteReport(int object) throws SQLException {
		String DELETE_Payment = "DELETE FROM Payment WHERE PaymentID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Payment);
		statement.close();
		return object;
	}

	public Payment createPayment(ResultSet rSet) throws SQLException {
		Payment payment = new Payment(rSet.getInt("PaymentID"),
				rSet.getInt("ReservationID"), rSet.getString("FirstName"),
				rSet.getString("CCOwner"), rSet.getInt("PaymentTypeID"),
				rSet.getString("PaymentType"), rSet.getInt("CCNumber"),
				rSet.getString("CCExpirationMonth"),
				rSet.getString("CCExpirationYear"),
				rSet.getString("ExpectedCheckinDate"),
				rSet.getString("ExpectedCheckoutDate"),
				rSet.getFloat("PaymentAmount"), rSet.getString("PaymentDate"));
		payment.setPaymentID(rSet.getInt("PaymentID"));
		return payment;
	}
}
