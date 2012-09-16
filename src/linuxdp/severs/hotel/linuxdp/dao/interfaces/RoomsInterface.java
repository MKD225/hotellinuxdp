package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Rooms;

import java.sql.SQLException;
import java.util.List;

public interface RoomsInterface {
	List<Rooms> all() throws SQLException;

	Rooms insertRooms(Rooms rooms) throws SQLException;

	Rooms updateRooms(Rooms rooms) throws SQLException;

	int deleteRooms(int object) throws SQLException;

	Rooms updateRoomStatusTrue(Rooms rooms) throws SQLException;

	Rooms updateRoomStatusFalse(Rooms rooms) throws SQLException;
}
