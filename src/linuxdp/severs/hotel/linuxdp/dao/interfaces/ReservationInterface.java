package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ReservationInterface {
	// get all data show table
	List<Reservation> all() throws SQLException;

	// insert datta
	Reservation insertReservation(Reservation reservation) throws SQLException;

	// update
	Reservation updateReservation(Reservation reservation) throws SQLException;

	// delete
	int deleteReservation(int object) throws SQLException;

	List<Reservation> searchReservation(String search) throws SQLException;

}
