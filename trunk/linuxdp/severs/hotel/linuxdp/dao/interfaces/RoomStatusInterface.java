package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.RoomStatus;

import java.sql.SQLException;
import java.util.List;


public interface RoomStatusInterface {
	List<RoomStatus> all() throws SQLException;

	RoomStatus insertRoomStatus(RoomStatus roomStatus) throws SQLException;

	RoomStatus updateRoomStatus(RoomStatus roomStatus) throws SQLException;

	int deleteRoomStatus(int object) throws SQLException;

}
