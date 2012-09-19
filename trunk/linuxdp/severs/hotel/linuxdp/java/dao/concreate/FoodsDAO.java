package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.FoodsInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Foods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FoodsDAO implements FoodsInterface {
	private static final String ALL = "SELECT * FROM Foods";
	private static final String INSERT = "INSERT INTO Foods(FoodID,FoodName,FoodPrice,BillID) VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE Foods SET FoodName = ?,FoodPrice = ?,BillID = ? WHERE FoodID = ?";

	@Override
	public List<Foods> all() throws SQLException {
		ArrayList<Foods> listFoods = new ArrayList<Foods>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet rSet = statement.executeQuery();
		while (rSet.next()) {
			listFoods.add(createFoods(rSet));
		}
		return listFoods;
	}

	@Override
	public Foods insertFoods(Foods foods) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, foods.getFoodName());
		pstmt.setFloat(2, foods.getFoodPrice());
		pstmt.setInt(3, foods.getBillID());
		pstmt.setInt(4, foods.getFoodID());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		foods.setFoodID(integer);
		pstmt.close();
		connection.close();
		return foods;
	}

	@Override
	public Foods updateFoods(Foods foods) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, foods.getFoodName());
			pstmt.setFloat(2, foods.getFoodPrice());
			pstmt.setInt(3, foods.getBillID());
			pstmt.setInt(4, foods.getFoodID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foods;
	}

	@Override
	public int deletePayment(int object) throws SQLException {
		String DELETE_Foods = "DELETE FROM Foods WHERE FoodID = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Foods);
		statement.close();
		return object;
	}

	public Foods createFoods(ResultSet rSet) throws SQLException {
		Foods foods = new Foods(rSet.getInt("FoodID"),
				rSet.getString("FoodName"), rSet.getFloat("FoodPrice"),
				rSet.getInt("BillID"));
		return foods;
	}
}
