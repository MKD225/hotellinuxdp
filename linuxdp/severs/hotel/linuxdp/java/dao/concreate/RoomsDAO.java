package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.RoomsInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Rooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RoomsDAO implements RoomsInterface {
	
	private static final String fillByRoom = "SELECT r.RoomID,r.RoomNumber,rt.RoomType,rt.RoomTypeRate,rt.NumBeds,rs.RoomStatusID,rs.RoomStatusName,r._Description FROM Rooms r ,RoomType rt,RoomStatus rs WHERE r.RoomTypeID = rt.RoomTypeID and r.RoomStatusID = rs.RoomStatusID and rs.RoomStatus=1";
	private static final String ALL = "SELECT r.RoomID,r.RoomNumber,rt.RoomType,rt.RoomTypeRate,rt.NumBeds,rs.RoomStatusID,rs.RoomStatusName,r._Description FROM Rooms r ,RoomType rt,RoomStatus rs WHERE r.RoomTypeID = rt.RoomTypeID and r.RoomStatusID = rs.RoomStatusID";
	private static final String INSERT = "INSERT INTO Rooms(RoomNumber,_Description,RoomTypeID,RoomStatusID)VALUES(?,?,?,?)";
	private static final String UPDATE = "UPDATE Rooms SET RoomNumber = ?,_Description = ?,RoomTypeID = ?,RoomStatusID = ? WHERE RoomID =?";
	private static final String fillByStatusFalse = "UPDATE Rooms SET RoomStatusID = 2 WHERE RoomID =?";
	private static final String fillByStatusTrue = "UPDATE Rooms SET RoomStatusID = 1 WHERE RoomNumber= ?";
	private static final String fillRoomCheckIn = "SELECT r.RoomID,r.RoomNumber,rt.RoomType,rt.RoomTypeRate,rt.NumBeds,rs.RoomStatusID,rs.RoomStatusName,r._Description FROM Rooms r ,RoomType rt,RoomStatus rs ,CheckIn ci WHERE r.RoomTypeID = rt.RoomTypeID and r.RoomStatusID = rs.RoomStatusID and ci.RoomID = r.RoomID";


	@Override
	public List<Rooms> all() throws SQLException {
		ArrayList<Rooms> listRooms = new ArrayList<Rooms>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRooms.add(createRooms(resultSet));
		}
		return listRooms;
	}
	@Override
	public List<Rooms> fillRCheck() throws SQLException {
		ArrayList<Rooms> listRooms = new ArrayList<Rooms>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(fillRoomCheckIn);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRooms.add(createRooms(resultSet));
		}
		return listRooms;
	}
	public List<Rooms> fillRoom() throws SQLException {
		ArrayList<Rooms> listRooms = new ArrayList<Rooms>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(fillByRoom);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRooms.add(createRooms(resultSet));
		}
		return listRooms;
	}

	@Override
	public Rooms insertRooms(Rooms rooms) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, rooms.getRoomNumber());
		pstmt.setString(2, rooms.getDescription());
		pstmt.setInt(3, rooms.getRoomTypeID());
		pstmt.setInt(4, rooms.getRoomStatusID());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		rooms.setRoomID(integer);
		pstmt.close();
		connection.close();
		return rooms;
	}

	@Override
	public Rooms updateRoomStatusFalse(Rooms rooms) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection
					.prepareStatement(fillByStatusFalse);
			pstmt.setInt(1, rooms.getRoomID());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rooms;
	}

	@Override
	public Rooms updateRoomStatusTrue(Rooms rooms) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection
					.prepareStatement(fillByStatusTrue);
			pstmt.setString(1, rooms.getRoomNumber());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rooms;
	}

	@Override
	public Rooms updateRooms(Rooms rooms) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, rooms.getRoomNumber());
			pstmt.setString(2, rooms.getDescription());
			pstmt.setInt(3, rooms.getRoomTypeID());
			pstmt.setInt(4, rooms.getRoomStatusID());
			pstmt.setInt(5, rooms.getRoomID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public int deleteRooms(int object) throws SQLException {
		String DELETE_Rooms = "DELETE FROM Rooms WHERE RoomID = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Rooms);
		statement.close();
		return object;
	}

	public Rooms createRooms(ResultSet resultSet) throws SQLException {
		Rooms rooms = new Rooms(resultSet.getInt("RoomID"),
				resultSet.getString("RoomNumber"),
				resultSet.getString("RoomType"), resultSet.getInt("NumBeds"),
				resultSet.getString("RoomTypeRate"),
				resultSet.getInt("RoomStatusID"),
				resultSet.getString("RoomStatusName"),
				resultSet.getString("_Description"));
		rooms.setRoomID(resultSet.getInt("RoomID"));
		return rooms;
	}

}
