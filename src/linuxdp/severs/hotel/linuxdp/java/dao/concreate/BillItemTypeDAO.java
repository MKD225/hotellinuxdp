package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.BillItemTypeInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.BillItemType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BillItemTypeDAO implements BillItemTypeInterface {
	private static final String ALL = "SELECT * FROM BillItemType";
	private static final String INSERT = "INSERT INTO BillItemType(BillItemType,_Description) VALUES(?,?)";
	private static final String UPDATE = "UPDATE BillItemType SET BillItemType = ?,_Description = ? WHERE BillItemTypeID = ?";

	@Override
	public List<BillItemType> all() throws SQLException {
		ArrayList<BillItemType> listItemTypes = new ArrayList<BillItemType>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listItemTypes.add(createBillItemType(rSet));
		}
		return listItemTypes;
	}

	@Override
	public BillItemType insertItemType(BillItemType billItemType)
			throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT);
		pstmt.setString(1, billItemType.getBillItemType());
		pstmt.setString(2, billItemType.getDescription());
		pstmt.executeUpdate();
		pstmt.close();
		connection.close();
		return billItemType;
	}

	@Override
	public BillItemType updateBillItemType(BillItemType billItemType)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, billItemType.getBillItemType());
			pstmt.setString(2, billItemType.getDescription());
			pstmt.setInt(3, billItemType.getBillItemTypeID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {

		}
		return billItemType;
	}

	@Override
	public int deleteBillItemType(int object) throws SQLException {
		String DELETE_BillItemType = "DELETE FROM BillItemType WHERE BillItemTypeID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_BillItemType);
		statement.close();
		return object;
	}

	private BillItemType createBillItemType(ResultSet rSet) throws SQLException {
		BillItemType billItemType = new BillItemType(
				rSet.getString("BillItemType"), rSet.getString("_Description"),
				rSet.getInt("billItemTypeID"));
		return billItemType;
	}
}
