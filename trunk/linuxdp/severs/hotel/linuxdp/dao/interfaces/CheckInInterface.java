package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.CheckIn;

import java.sql.SQLException;
import java.util.List;


public interface CheckInInterface {
	List<CheckIn> all() throws SQLException;

	CheckIn insertCheckIn(CheckIn checkIn) throws SQLException;

	CheckIn updateCheckIn(CheckIn checkIn) throws SQLException;

	int deleteCheckIn(int object) throws SQLException;

	List<CheckIn> allTable() throws SQLException;

	int deleteCheckInByCheckOut(int object) throws SQLException;

}
