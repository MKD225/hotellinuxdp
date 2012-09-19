package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.RoomType;

import java.sql.SQLException;
import java.util.List;


public interface RoomTypeInterface {
	List<RoomType> all() throws SQLException;

	RoomType insertRoomType(RoomType roomType) throws SQLException;

	RoomType updateRoomType(RoomType roomType) throws SQLException;

	int deleteRoomType(int object) throws SQLException;
}
