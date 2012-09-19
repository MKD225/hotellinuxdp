package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.RoomStatusDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RoomStatus {
	private Integer RoomStatusID;
	private String RoomStatusName;
	private String RoomStatusDesc;
	private Boolean roomStatus;

	public RoomStatus(String roomStatusName, String roomStatusDesc,
			Boolean roomStatus) {
		this.RoomStatusName = roomStatusName;
		this.RoomStatusDesc = roomStatusDesc;
		this.roomStatus = roomStatus;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(RoomStatusID);
		vector.add(RoomStatusName);
		vector.add(RoomStatusDesc);
		vector.add(roomStatus);
		return vector;
	}

	public static RoomStatusDAO roomStatusDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getRoomStatusDAO();
	}

	public static List<RoomStatus> all() throws SQLException {
		return roomStatusDAO().all();
	}

	public RoomStatus save() throws SQLException {
		return roomStatusDAO().insertRoomStatus(this);
	}

	public RoomStatus update() throws SQLException {
		return roomStatusDAO().updateRoomStatus(this);
	}

	public static int delete(int object) throws SQLException {
		return roomStatusDAO().deleteRoomStatus(object);
	}

	public Integer getRoomStatusID() {
		return RoomStatusID;
	}

	public void setRoomStatusID(Integer roomStatusID) {
		RoomStatusID = roomStatusID;
	}

	public String getRoomStatusName() {
		return RoomStatusName;
	}

	public void setRoomStatusName(String roomStatusName) {
		RoomStatusName = roomStatusName;
	}

	public String getRoomStatusDesc() {
		return RoomStatusDesc;
	}

	public void setRoomStatusDesc(String roomStatusDesc) {
		RoomStatusDesc = roomStatusDesc;
	}

	public Boolean getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Boolean roomStatus) {
		this.roomStatus = roomStatus;
	}

}
