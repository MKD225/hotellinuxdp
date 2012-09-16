package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.RoomsDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Rooms {
	private Integer roomID;
	private String roomNumber;
	private String description;
	// Foregn key
	private Integer roomTypeID;
	private Integer roomStatusID;
	private String roomType;
	private Integer numBeds;
	private String roomTypeRate;
	private String roomStatus;

	public Rooms() {

	}

	public Rooms(String roomNumber, String description, Integer roomTypeID,
			Integer roomStatusID) {
		this.roomNumber = roomNumber;
		this.description = description;
		this.roomTypeID = roomTypeID;
		this.roomStatusID = roomStatusID;
	}

	public Rooms(Integer roomID, String roomNumber, String roomType,
			Integer numberBed, String roomTypeRate, Integer RoomStatusID,
			String roomStatus, String description) {
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.numBeds = numberBed;
		this.roomTypeRate = roomTypeRate;
		this.roomStatusID = RoomStatusID;
		this.roomStatus = roomStatus;
		this.description = description;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(roomID);
		vector.add(roomNumber);
		vector.add(roomType);
		vector.add(numBeds);
		vector.add(roomTypeRate);
		vector.add(roomStatus);
		vector.add(description);

		return vector;
	}

	public static RoomsDAO roomsDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getRoomsDAO();
	}

	public static List<Rooms> all() throws SQLException {
		return roomsDAO().all();
	}

	public static List<Rooms> fillRoomStatus() throws SQLException {
		return roomsDAO().fillRoom();
	}

	public Rooms save() throws SQLException {
		return roomsDAO().insertRooms(this);
	}

	public Rooms update() throws SQLException {
		return roomsDAO().updateRooms(this);
	}

	public Rooms fillUpdateStatusFalse() throws SQLException {
		return roomsDAO().updateRoomStatusFalse(this);
	}

	public Rooms fillUpdateStatusTrue() throws SQLException {
		return roomsDAO().updateRoomStatusTrue(this);
	}
	
	public static int delete(int object) throws SQLException {
		return roomsDAO().deleteRooms(object);
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String rescription) {
		this.description = rescription;
	}

	public Integer getRoomStatusID() {
		return roomStatusID;
	}

	public void setRoomStatusID(int roomStatusID) {
		this.roomStatusID = roomStatusID;
	}

	public Integer getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(Integer numBeds) {
		this.numBeds = numBeds;
	}

	public String getRoomTypeRate() {
		return roomTypeRate;
	}

	public void setRoomTypeRate(String roomTypeRate) {
		this.roomTypeRate = roomTypeRate;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
}
