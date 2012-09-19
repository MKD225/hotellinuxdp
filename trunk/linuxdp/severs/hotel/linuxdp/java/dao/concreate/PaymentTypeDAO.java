package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.PaymentTypeInteface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.PaymentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PaymentTypeDAO implements PaymentTypeInteface {
	private static final String ALL = "SELECT * FROM PaymentType";
	private static final String INSERT = "INSERT INTO PaymentType(PaymentType,PaymentTypeDesc)VALUES(?,?)";
	private static final String UPDATE = "UPDATE PaymentType SET PaymentType = ? ,PaymentTypeDesc = ? WHERE PaymentTypeID =?";

	@Override
	public List<PaymentType> all() throws SQLException {
		ArrayList<PaymentType> listPaymentTypes = new ArrayList<PaymentType>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listPaymentTypes.add(createPaymentTypes(resultSet));
		}
		return listPaymentTypes;
	}

	@Override
	public PaymentType insertPaymentType(PaymentType paymentType)
			throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, paymentType.getPaymentType());
		pstmt.setString(2, paymentType.getPaymentTypeDesc());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		paymentType.setPaymentTypeID(integer);
		pstmt.close();
		connection.close();
		return paymentType;
	}

	@Override
	public PaymentType updatePaymentType(PaymentType paymentType)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, paymentType.getPaymentType());
			pstmt.setString(2, paymentType.getPaymentTypeDesc());
			pstmt.setInt(3, paymentType.getPaymentTypeID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentType;
	}

	@Override
	public int deletePaymentType(int object) throws SQLException {
		String DELETE_PaymentType = "DELETE FROM PaymentType WHERE PaymentTypeID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_PaymentType);
		statement.close();
		return object;
	}

	public PaymentType createPaymentTypes(ResultSet resultSet)
			throws SQLException {
		PaymentType paymentType = new PaymentType(
				resultSet.getString("PaymentType"),
				resultSet.getString("PaymentTypeDesc"));
		paymentType.setPaymentTypeID(resultSet.getInt("PaymentTypeID"));

		return paymentType;
	}
}
