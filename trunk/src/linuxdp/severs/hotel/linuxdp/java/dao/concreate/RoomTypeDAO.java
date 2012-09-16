package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.RoomTypeInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.RoomType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDAO implements RoomTypeInterface {
	private static final String ALL = "SELECT * FROM RoomType";
	private static final String INSERT = "INSERT INTO RoomType(RoomType,RoomDesc,RoomTypeRate,NumBeds)VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE RoomType SET RoomType = ?,RoomDesc = ?,RoomTypeRate = ?,NumBeds = ? WHERE RoomTypeID = ?";

	@Override
	public List<RoomType> all() throws SQLException {
		ArrayList<RoomType> listRoomTypes = new ArrayList<RoomType>();

		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRoomTypes.add(createRoomType(resultSet));
		}
		return listRoomTypes;
	}

	@Override
	public RoomType insertRoomType(RoomType roomType) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, roomType.getRoomType());
		pstmt.setString(2, roomType.getRoomDesc());
		pstmt.setString(3, roomType.getRoomRate());
		pstmt.setInt(4, roomType.getNumBeds());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		roomType.setRoomTypeID(integer);
		pstmt.close();
		connection.close();
		return roomType;
	}

	@Override
	public RoomType updateRoomType(RoomType roomType) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, roomType.getRoomType());
			pstmt.setString(2, roomType.getRoomDesc());
			pstmt.setString(3, roomType.getRoomRate());
			pstmt.setInt(4, roomType.getNumBeds());
			pstmt.setInt(5, roomType.getRoomTypeID());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomType;
	}

	@Override
	public int deleteRoomType(int object) throws SQLException {
		String DELETE_RoomType = "DELETE FROM RoomType WHERE RoomTypeID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_RoomType);
		statement.close();
		return object;
	}

	public RoomType createRoomType(ResultSet resultSet) throws SQLException {
		RoomType roomType = new RoomType(resultSet.getString("RoomType"),
				resultSet.getString("RoomDesc"),
				resultSet.getString("RoomTypeRate"), resultSet.getInt("NumBeds"));
		roomType.setRoomTypeID(resultSet.getInt("RoomTypeID"));
		return roomType;
	}
}
