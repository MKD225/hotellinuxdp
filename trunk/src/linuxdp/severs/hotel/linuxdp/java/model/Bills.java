/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.BillsDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class Bills {

	private Integer BillID;
	private Integer reservationID;
	private String comments;
	private String firstName;
	private String roomType;
	private String CheckIn;
	private String CheckOut;
	private Integer arrivalTime;
	private Integer numberGuests;
	private String roomRate;

	public Bills(Integer reservationID, String comments) {
		this.reservationID = reservationID;
		this.comments = comments;
	}

	public Bills(Integer billID, String firstName, String roomType,
			String checkIn, String checkOut, Integer arrivalTime,
			Integer numberGuests, String roomRate, String comments) {
		this.BillID = billID;
		this.setFirstName(firstName);
		this.setRoomType(roomType);
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setArrivalTime(arrivalTime);
		this.setNumberGuests(numberGuests);
		this.setRoomRate(roomRate);
		this.setComments(comments);
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(BillID);
		objects.add(firstName);
		objects.add(roomType);
		objects.add(CheckIn);
		objects.add(CheckOut);
		objects.add(arrivalTime);
		objects.add(numberGuests);
		objects.add(roomRate);
		objects.add(comments);

		return objects;
	}

	public static BillsDAO billsDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getBillsDAO();
	}

	public static List<Bills> all() throws SQLException {
		return billsDAO().all();
	}

	public static List<Bills> selectIndex() throws SQLException {
		return billsDAO().selectIndex();
	}

	public Bills save() throws SQLException {
		return billsDAO().insertBills(this);
	}

	public Bills update() throws SQLException {
		return billsDAO().update(this);
	}

	public static int delete(int object) throws SQLException {
		return billsDAO().deleteBills(object);
	}

	/**
	 * @return the BillID
	 */
	public Integer getBillID() {
		return BillID;
	}

	/**
	 * @param BillID
	 *            the BillID to set
	 */
	public void setBillID(int BillID) {
		this.BillID = BillID;
	}

	/**
	 * @return the ReservationID
	 */
	public Integer getReservationID() {
		return reservationID;
	}

	/**
	 * @param ReservationID
	 *            the ReservationID to set
	 */
	public void setReservationID(Integer ReservationID) {
		this.reservationID = ReservationID;
	}

	/**
	 * @return the Comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param Comments
	 *            the Comments to set
	 */
	public void setComments(String Comments) {
		this.comments = Comments;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCheckIn() {
		return CheckIn;
	}

	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}

	public String getCheckOut() {
		return CheckOut;
	}

	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}

	public Integer getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Integer arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getNumberGuests() {
		return numberGuests;
	}

	public void setNumberGuests(Integer numberGuests) {
		this.numberGuests = numberGuests;
	}

	public String getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(String roomRate) {
		this.roomRate = roomRate;
	}
}
