package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.RoomTypeDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RoomType {
	private Integer RoomTypeID;
	private String RoomType;
	private String RoomDesc;
	private String RoomRate;
	private Integer NumBeds;

	public RoomType(String roomType, String roomDesc, String roomRate,
			Integer numBeds) {
		this.RoomType = roomType;
		this.RoomDesc = roomDesc;
		this.RoomRate = roomRate;
		this.NumBeds = numBeds;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(RoomTypeID);
		vector.add(RoomType);
		vector.add(RoomDesc);
		vector.add(RoomRate);
		vector.add(NumBeds);

		return vector;
	}

	public static RoomTypeDAO roomTypeDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getRoomTypeDAO();
	}

	public static List<RoomType> all() throws SQLException {
		return roomTypeDAO().all();
	}

	public RoomType save() throws SQLException {
		return roomTypeDAO().insertRoomType(this);
	}

	public RoomType update() throws SQLException {
		return roomTypeDAO().updateRoomType(this);
	}

	public static int delete(int object) throws SQLException {
		return roomTypeDAO().deleteRoomType(object);
	}

	public Integer getRoomTypeID() {
		return RoomTypeID;
	}

	public void setRoomTypeID(Integer roomTypeID) {
		RoomTypeID = roomTypeID;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public String getRoomRate() {
		return RoomRate;
	}

	public void setRoomRate(String roomRate) {
		RoomRate = roomRate;
	}

	public String getRoomDesc() {
		return RoomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		RoomDesc = roomDesc;
	}

	public Integer getNumBeds() {
		return NumBeds;
	}

	public void setNumBeds(Integer numBeds) {
		NumBeds = numBeds;
	}
}
