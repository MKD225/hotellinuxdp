package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.CheckInInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.CheckIn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CheckInDAO implements CheckInInterface {

	private static final String ALL = "SELECT CheckinID,r.ReservationID,FirstName,BirthDay=CONVERT(varchar(20),BirthDay,101), ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101), ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),CheckInDate=CONVERT(varchar,CheckInDate,101),RoomNumber,NumberGuests, RoomType,NumBeds,RoomStatusName ,ro.RoomID FROM CheckIn ci ,Customer cus, Reservation r,RoomType rt, RoomStatus rs , Rooms ro Where ci.ReservationID = r.ReservationID  and ci.RoomID = ro.RoomID and  ro.RoomTypeID = rt.RoomTypeID and  ro.RoomStatusID = rs.RoomStatusID and r.CustomerID = cus.CustomerID";
	private static final String INSERT = "INSERT INTO CheckIn (ReservationID,RoomID,CheckinDate) VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE CheckIn set ReservationID =?,RoomID=?,CheckinDate=? WHERE CheckinID =?";
	private static final String allData = "SELECT CheckinID, r.ReservationID,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),CheckinDate=CONVERT(varchar(20),CheckinDate,101),ArrivalTime,RoomNumber,rs.RoomID,NumberGuests,RoomRate,Comment,FirstName,WorkPhone,Email,MiddleName,LastName,BirthDay=CONVERT(varchar,Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone,RoomType FROM Reservation r,Customer c ,RoomType rt ,CheckIn ci , Rooms rs WHERE r.CustomerID =c.CustomerID and r.RoomTypeID=rt.RoomTypeID and ci.ReservationID = r.ReservationID and ci.RoomID = rs.RoomID";

	@Override
	public List<CheckIn> all() throws SQLException {
		ArrayList<CheckIn> listCheckIns = new ArrayList<CheckIn>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listCheckIns.add(createCheckIn(rSet));
		}
		return listCheckIns;
	}

	@Override
	public List<CheckIn> allTable() throws SQLException {
		ArrayList<CheckIn> listCheckIns = new ArrayList<CheckIn>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(allData);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listCheckIns.add(createAllTable(rSet));
		}
		return listCheckIns;
	}

	@Override
	public CheckIn insertCheckIn(CheckIn checkIn) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setInt(1, checkIn.getReservationID());
		pstmt.setInt(2, checkIn.getRoomID());
		pstmt.setString(3, checkIn.getCheckinDate());
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		rset.next();
		pstmt.close();
		connection.close();
		return checkIn;
	}

	@Override
	public CheckIn updateCheckIn(CheckIn checkIn) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setInt(1, checkIn.getReservationID());
			pstmt.setInt(2, checkIn.getRoomID());
			pstmt.setString(3, checkIn.getCheckinDate());
			pstmt.setInt(4, checkIn.getCheckinID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkIn;
	}

	@Override
	public int deleteCheckIn(int object) throws SQLException {
		String DELETE_CheckIn = "DELETE FROM CheckIn WHERE CheckinID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_CheckIn);
		statement.close();
		return object;
	}

	@Override
	public int deleteCheckInByCheckOut(int object) throws SQLException {
		String DELETE_CheckIn = "DELETE FROM CheckIn WHERE ReservationID ="
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_CheckIn);
		statement.close();
		return object;
	}

	private CheckIn createCheckIn(ResultSet rSet) throws SQLException {
		CheckIn checkIn = new CheckIn(rSet.getInt("CheckinID"),
				rSet.getInt("ReservationID"), rSet.getInt("RoomID"),
				rSet.getString("FirstName"), rSet.getString("BirthDay"),
				rSet.getString("ExpectedCheckinDate"),
				rSet.getString("ExpectedCheckoutDate"),
				rSet.getString("CheckInDate"), rSet.getString("RoomNumber"),
				rSet.getInt("NumberGuests"), rSet.getString("RoomType"),
				rSet.getInt("NumBeds"), rSet.getString("RoomStatusName"));
		return checkIn;
	}

	private CheckIn createAllTable(ResultSet resultSet) throws SQLException {
		CheckIn checkIn = new CheckIn(resultSet.getInt("CheckinID"),
				resultSet.getInt("ReservationID"),
				resultSet.getString("FirstName"),
				resultSet.getString("WorkPhone"), resultSet.getString("Email"),
				resultSet.getString("ExpectedCheckinDate"),
				resultSet.getString("ExpectedCheckoutDate"),
				resultSet.getString("CheckinDate"),
				resultSet.getInt("ArrivalTime"),
				resultSet.getInt("NumberGuests"),
				resultSet.getString("RoomRate"),
				resultSet.getString("RoomNumber"), resultSet.getInt("RoomID"),
				resultSet.getString("RoomType"),
				resultSet.getString("MiddleName"),
				resultSet.getString("LastName"),
				resultSet.getString("BirthDay"), resultSet.getString("Sex"),
				resultSet.getString("AddStreet"),
				resultSet.getString("AddCity"),
				resultSet.getString("AddState"), resultSet.getString("AddZip"),
				resultSet.getString("AddCountry"),
				resultSet.getString("HomePhone"));
		checkIn.setCheckinID(resultSet.getInt("CheckinID"));
		return checkIn;
	}
}
