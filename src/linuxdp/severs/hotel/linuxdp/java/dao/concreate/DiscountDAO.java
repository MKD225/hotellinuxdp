package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.DiscountInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO implements DiscountInterface {
	private static final String ALL = "SELECT * FROM Discount";
	private static final String INSERT = "INSERT INTO Discount(DiscountName,DiscountPercent,DiscountAmount)VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE Discount SET DiscountName = ?,DiscountPercent = ?,DiscountAmount = ? WHERE DiscountID = ?";

	@Override
	public List<Discount> all() throws SQLException {
		ArrayList<Discount> listDiscounts = new ArrayList<Discount>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listDiscounts.add(createDiscount(rSet));
		}
		return listDiscounts;
	}

	@Override
	public Discount insretDiscount(Discount discount) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, discount.getDiscountName());
		pstmt.setInt(2, discount.getDiscountPercent());
		pstmt.setFloat(3, discount.getDiscountAmount());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		discount.setDiscountID(integer);
		pstmt.close();
		connection.close();
		return discount;
	}

	@Override
	public Discount updateDiscount(Discount discount) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, discount.getDiscountName());
			pstmt.setInt(2, discount.getDiscountPercent());
			pstmt.setFloat(3, discount.getDiscountAmount());
			pstmt.setInt(4, discount.getDiscountID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discount;
	}

	@Override
	public int deleteDis(int object) throws SQLException {
		String DELETE_Discount = "DELETE FROM Discount WHERE DiscountID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Discount);
		statement.close();
		return object;
	}

	public Discount createDiscount(ResultSet rSet) throws SQLException {
		Discount discount = new Discount(rSet.getString("DiscountName"),
				rSet.getInt("DiscountPercent"), rSet.getFloat("DiscountAmount"));
		discount.setDiscountID(rSet.getInt("DiscountID"));
		return discount;
	}
}
