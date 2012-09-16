package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.BillItemInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.BillItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class BillItemDAO implements BillItemInterface {
	// private static final String ALL = "SELECT * FROM BillItem";
	private static final String ALL = "SELECT BillItemID,FirstName, RoomNumber,RoomRate, bt.BillItemTypeID,BillItemType,BillItemAmount,d.DiscountID,DiscountName,DiscountAmount ,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate =CONVERT(VARCHAR,ExpectedCheckoutDate,101),BillChargeDate=CONVERT(varchar,BillChargeDate,101) FROM BillItem bi, Rooms r,Discount d ,Bills b, BillItemType bt ,Reservation rs ,Customer cus  WHERE bi.RoomID=r.RoomID and  bi.BillItemTypeID = bt.BillItemTypeID and bi.DiscountID = d.DiscountID and bi.BillID = b.BillID and  b.ReservationID = rs.ReservationID and rs.CustomerID = cus.CustomerID";
	private static final String INSERT = "INSERT INTO BillItem (RoomID ,BillItemTypeID,BillItemDescription,BillItemAmount,BillChargeDate,DiscountID,BillID)VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE BillItem SET RoomID = ?,BillItemTypeID = ? ,BillItemDescription = ? ,BillItemAmount = ? ,BillChargeDate = ? ,DiscountID = ? ,BillID = ? WHERE BillItemID = ?";
	@Override
	public List<BillItem> all() throws SQLException {
		ArrayList<BillItem> listBillItems = new ArrayList<BillItem>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listBillItems.add(createBillItem(rSet));
		}
		return listBillItems;
	}

	@Override
	public BillItem insertBillItem(BillItem billItem) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, billItem.getBillItemID());
		pstmt.setInt(2, billItem.getRoomID());
		pstmt.setInt(3, billItem.getBillItemTypeID());
		pstmt.setString(4, billItem.getBillItemDescription());
		pstmt.setFloat(5, billItem.getBillItemAmount());
		pstmt.setString(6, billItem.getBillChargeDate());
		pstmt.setInt(7, billItem.getDiscountID());
		pstmt.setInt(8, billItem.getBillID());
		pstmt.executeUpdate();
		ResultSet rseSet = pstmt.getGeneratedKeys();
		rseSet.next();
		Integer integer = rseSet.getInt(1);
		billItem.setBillItemID(integer);
		pstmt.close();
		connection.close();
		return billItem;
	}

	@Override
	public BillItem updateBillItem(BillItem billItem) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setInt(1, billItem.getRoomID());
			pstmt.setInt(2, billItem.getBillItemTypeID());
			pstmt.setString(3, billItem.getBillItemDescription());
			pstmt.setFloat(4, billItem.getBillItemAmount());
			pstmt.setString(5, billItem.getBillChargeDate());
			pstmt.setInt(6, billItem.getDiscountID());
			pstmt.setInt(7, billItem.getBillID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succees ! ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return billItem;
	}

	@Override
	public int deleteBillItem(int object) throws SQLException {
		String DELETE_BillItem = "DELETE FROM BillItem WHERE BillItemID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_BillItem);
		statement.close();
		return object;
	}

	private BillItem createBillItem(ResultSet rSet) throws SQLException {
		BillItem billItem = new BillItem(rSet.getInt("BillItemID"),
				rSet.getString("FirstName"), rSet.getInt("RoomNumber"),
				rSet.getInt("RoomRate"), rSet.getInt("BillItemTypeID"),
				rSet.getString("BillItemType"),
				rSet.getFloat("BillItemAmount"),rSet.getInt("DiscountID"),
				rSet.getString("DiscountName"),
				rSet.getFloat("DiscountAmount"),
				rSet.getString("ExpectedCheckinDate"),
				rSet.getString("ExpectedCheckoutDate"),
				rSet.getString("BillChargeDate"));
		billItem.setBillItemID(rSet.getInt("BillItemID"));
		return billItem;
	}

}
