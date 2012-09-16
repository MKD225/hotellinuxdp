package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.RoomStatusInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.RoomStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RoomStatusDAO implements RoomStatusInterface {
	private static final String ALL = "SELECT * from RoomStatus";
	private static final String INSERT = "INSERT INTO RoomStatus(RoomStatusName,RoomStatusDesc,RoomStatus)VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE RoomStatus SET RoomStatusName = ? ,RoomStatusDesc = ?,RoomStatus=? WHERE RoomStatusID = ?";

	@Override
	public List<RoomStatus> all() throws SQLException {
		ArrayList<RoomStatus> listRoomStatus = new ArrayList<RoomStatus>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRoomStatus.add(createRoomStatus(resultSet));
		}
		return listRoomStatus;
	}

	@Override
	public RoomStatus insertRoomStatus(RoomStatus roomStatus)
			throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, roomStatus.getRoomStatusName());
		pstmt.setString(2, roomStatus.getRoomStatusDesc());
		pstmt.setBoolean(3, roomStatus.getRoomStatus());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		roomStatus.setRoomStatusID(integer);
		pstmt.close();
		connection.close();
		return roomStatus;
	}

	@Override
	public RoomStatus updateRoomStatus(RoomStatus roomStatus)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, roomStatus.getRoomStatusName());
			pstmt.setString(2, roomStatus.getRoomStatusDesc());
			pstmt.setBoolean(3, roomStatus.getRoomStatus());
			pstmt.setInt(4, roomStatus.getRoomStatusID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomStatus;
	}

	@Override
	public int deleteRoomStatus(int object) throws SQLException {
		String DELETE_RoomStatus = "DELETE FROM RoomStatus WHERE RoomStatusID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_RoomStatus);
		statement.close();
		return object;
	}

	public RoomStatus createRoomStatus(ResultSet resultSet) throws SQLException {
		RoomStatus roomStatus = new RoomStatus(
				resultSet.getString("RoomStatusName"),
				resultSet.getString("RoomStatusDesc"),
				resultSet.getBoolean("RoomStatus"));
		roomStatus.setRoomStatusID(resultSet.getInt("RoomStatusID"));
		return roomStatus;
	}
}
