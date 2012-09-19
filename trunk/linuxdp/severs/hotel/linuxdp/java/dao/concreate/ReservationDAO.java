package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.ReservationInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO implements ReservationInterface {

	private static final String ALL = "SELECT ReservationID,ExpectedCheckinDate=CONVERT(varchar,ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar,ExpectedCheckoutDate,101),ArrivalTime,NumberGuests,RoomRate,Comment,FirstName,WorkPhone,Email,MiddleName,LastName,BirthDay=CONVERT(varchar,Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone,RoomType FROM Reservation r,Customer c ,RoomType rt WHERE r.CustomerID =c.CustomerID and r.RoomTypeID=rt.RoomTypeID";
	private static final String INSERT = "INSERT INTO Reservation(ExpectedCheckinDate,ExpectedCheckoutDate,ArrivalTime,NumberGuests,RoomRate,Comment,CustomerID,RoomTypeID)VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Reservation SET ExpectedCheckinDate = ?,ExpectedCheckoutDate = ?,ArrivalTime = ?,NumberGuests = ?,RoomRate = ?,Comment = ?,CustomerID = ? ,RoomTypeID = ? WHERE ReservationID = ?";

	@Override
	public List<Reservation> all() throws SQLException {
		ArrayList<Reservation> listReservations = new ArrayList<Reservation>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listReservations.add(createReservation(resultSet));
		}
		return listReservations;
	}

	@Override
	public Reservation insertReservation(Reservation reservation)
			throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, reservation.getExpectedCheckinDate());
		pstmt.setString(2, reservation.getExpectedCheckoutDate());
		pstmt.setInt(3, reservation.getArrivalTime());
		pstmt.setInt(4, reservation.getNumberGuests());
		pstmt.setString(5, reservation.getRoomRate());
		pstmt.setString(6, reservation.getComments());
		pstmt.setInt(7, reservation.getCustomerID());
		pstmt.setInt(8, reservation.getRoomTypeID());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		reservation.setReservationID(integer);
		pstmt.close();
		connection.close();
		return reservation;
	}

	@Override
	public Reservation updateReservation(Reservation reservation)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);

			pstmt.setString(1, reservation.getExpectedCheckinDate());
			pstmt.setString(2, reservation.getExpectedCheckoutDate());
			pstmt.setInt(3, reservation.getArrivalTime());
			pstmt.setFloat(4, reservation.getNumberGuests());
			pstmt.setString(5, reservation.getRoomRate());
			pstmt.setString(6, reservation.getComments());
			pstmt.setInt(7, reservation.getCustomerID());
			pstmt.setInt(8, reservation.getRoomTypeID());
			pstmt.setInt(9, reservation.getReservationID());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservation;
	}

	@Override
	public int deleteReservation(int object) throws SQLException {
		String DELETE_Reservation = "DELETE FROM Reservation WHERE ReservationID = "
				+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Reservation);
		statement.close();
		return object;
	}

	public Reservation createReservation(ResultSet resultSet)
			throws SQLException {
		Reservation reservation = new Reservation(
				resultSet.getInt("ReservationID"),
				resultSet.getString("FirstName"),
				resultSet.getString("WorkPhone"), resultSet.getString("Email"),
				resultSet.getString("ExpectedCheckinDate"),
				resultSet.getString("ExpectedCheckoutDate"),
				resultSet.getInt("ArrivalTime"),
				resultSet.getInt("NumberGuests"),
				resultSet.getString("RoomRate"),
				resultSet.getString("Comment"),
				resultSet.getString("RoomType"),
				resultSet.getString("MiddleName"),
				resultSet.getString("LastName"),
				resultSet.getString("BirthDay"), resultSet.getString("Sex"),
				resultSet.getString("AddStreet"),
				resultSet.getString("AddCity"),
				resultSet.getString("AddState"), resultSet.getString("AddZip"),
				resultSet.getString("AddCountry"),
				resultSet.getString("HomePhone"));
		return reservation;
	}

	@Override
	public List<Reservation> searchReservation(String search)
			throws SQLException {
		String SEARCH = "SELECT ReservationID,ExpectedCheckinDate=CONVERT(varchar(50),ExpectedCheckinDate,101),ExpectedCheckoutDate=CONVERT(varchar(50),ExpectedCheckoutDate,101),ArrivalTime,NumberGuests,RoomRate,Comment,FirstName,WorkPhone,Email,MiddleName,LastName,BirthDay=CONVERT(varchar(50),Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone,RoomType FROM Reservation r,Customer c ,RoomType rt WHERE r.CustomerID =c.CustomerID and r.RoomTypeID=rt.RoomTypeID and FirstName LIKE '%"
				+ search + "%'";
		List<Reservation> listReservations = new ArrayList<Reservation>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(SEARCH);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listReservations.add(createReservation(resultSet));
		}
		return listReservations;
	}
}
