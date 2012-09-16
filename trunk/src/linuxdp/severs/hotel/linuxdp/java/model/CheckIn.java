/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.CheckInDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class CheckIn {

	private Integer CheckinID;
	private Integer ReservationID;
	private Integer RoomID;
	private String CheckinDate;
	private String checkIn;
	private String checkOut;
	private Integer numGuests;
	private String checkInDate;
	private String roomType;
	private Integer numBeds;
	private String roomNumber;
	private String roomStatus;
	private String firstName;

	public CheckIn(Integer reservationID, Integer roomID, String checkinDate) {
		this.ReservationID = reservationID;
		this.RoomID = roomID;
		this.CheckinDate = checkinDate;
	}

	public CheckIn(Integer checkinID, Integer ReservationID, Integer roomID,
			String firstname, String checkIn, String checkOut,
			String checkInDate, String roomNumber, Integer numGuests,
			String RoomType, Integer numBeds, String roomStatus) {

		this.CheckinID = checkinID;
		this.ReservationID = ReservationID;
		this.RoomID = roomID;
		this.firstName = firstname;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.checkInDate = checkInDate;
		this.roomNumber = roomNumber;
		this.numGuests = numGuests;
		this.roomType = RoomType;
		this.numBeds = numBeds;
		this.roomStatus = roomStatus;
	}

	// get data show table
	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(CheckinID);
		vector.add(ReservationID);
		vector.add(firstName);
		vector.add(checkIn);
		vector.add(checkOut);
		vector.add(checkInDate);
		vector.add(roomNumber);
		vector.add(numGuests);
		vector.add(roomType);
		vector.add(numBeds);
		vector.add(roomStatus);

		return vector;
	}

	public static CheckInDAO checkInDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getCheckInDAO();
	}

	// all list data
	public static List<CheckIn> all() throws SQLException {
		return checkInDAO().all();
	}

	// insert
	public CheckIn save() throws SQLException {
		return checkInDAO().insertCheckIn(this);
	}

	// update
	public CheckIn update() throws SQLException {
		return checkInDAO().updateCheckIn(this);
	}

	// delet
	public static int delete(int object) throws SQLException {
		return checkInDAO().deleteCheckIn(object);
	}

	/**
	 * @return the CheckinID
	 */
	public int getCheckinID() {
		return CheckinID;
	}

	/**
	 * @param CheckinID
	 *            the CheckinID to set
	 */
	public void setCheckinID(int CheckinID) {
		this.CheckinID = CheckinID;
	}

	/**
	 * @return the ReservationID
	 */
	public Integer getReservationID() {
		return ReservationID;
	}

	/**
	 * @param ReservationID
	 *            the ReservationID to set
	 */
	public void setReservationID(Integer ReservationID) {
		this.ReservationID = ReservationID;
	}

	/**
	 * @return the RoomID
	 */
	public Integer getRoomID() {
		return RoomID;
	}

	/**
	 * @param RoomID
	 *            the RoomID to set
	 */
	public void setRoomID(Integer RoomID) {
		this.RoomID = RoomID;
	}

	/**
	 * @return the CheckinDate
	 */
	public String getCheckinDate() {
		return CheckinDate;
	}

	/**
	 * @param CheckinDate
	 *            the CheckinDate to set
	 */
	public void setCheckinDate(String CheckinDate) {
		this.CheckinDate = CheckinDate;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public Integer getNumGuests() {
		return numGuests;
	}

	public void setNumGuests(Integer numGuests) {
		this.numGuests = numGuests;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
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

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
