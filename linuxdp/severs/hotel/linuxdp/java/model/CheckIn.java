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
	private String birthDay;
	private Integer CustomerID;
	private String ExpectedCheckinDate;
	private String ExpectedCheckoutDate;
	private Integer ArrivalTime;
	private Integer NumberGuests;
	private Integer RoomTypeID;
	private String RoomRate;
	private String Comments;
	private String workPhone;
	private String email;
	private Integer numbeds;
	private String MiddleName;
	private String LastName;
	private String Sex;
	private String AddStreet;
	private String AddCity;
	private String AddState;
	private String AddZip;
	private String AddCountry;
	private String HomePhone;

	public CheckIn(Integer reservationID, Integer roomID, String checkinDate) {
		this.ReservationID = reservationID;
		this.RoomID = roomID;
		this.CheckinDate = checkinDate;
	}

	public CheckIn(Integer checkinID, Integer ReservationID, Integer roomID,
			String firstname, String birthDay, String checkIn, String checkOut,
			String checkInDate, String roomNumber, Integer numGuests,
			String RoomType, Integer numBeds, String roomStatus) {

		this.CheckinID = checkinID;
		this.ReservationID = ReservationID;
		this.RoomID = roomID;
		this.firstName = firstname;
		this.birthDay = birthDay;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.checkInDate = checkInDate;
		this.roomNumber = roomNumber;
		this.numGuests = numGuests;
		this.roomType = RoomType;
		this.numBeds = numBeds;
		this.roomStatus = roomStatus;
	}

	public CheckIn(Integer CheckinID, Integer reservationID, String firstName,
			String workPhone, String email, String ExpectedCheckinDate,
			String ExpectedCheckoutDate, String checkinDate,
			Integer ArrivalTime, Integer NumberGuests, String RoomRate,
			String roomNumber, Integer roomID, String roomType,
			String MiddleName, String LastName, String BirthDay, String Sex,
			String AddStreet, String AddCity, String AddState, String AddZip,
			String AddCountry, String HomePhone) {
		this.CheckinID = CheckinID;
		this.ReservationID = reservationID;
		this.firstName = firstName;
		this.workPhone = workPhone;
		this.email = email;
		this.ExpectedCheckinDate = ExpectedCheckinDate;
		this.ExpectedCheckoutDate = ExpectedCheckoutDate;
		this.CheckinDate = checkinDate;
		this.ArrivalTime = ArrivalTime;
		this.NumberGuests = NumberGuests;
		this.RoomRate = RoomRate;
		this.roomNumber = roomNumber;
		this.RoomID = roomID;
		this.roomType = roomType;
		this.MiddleName = MiddleName;
		this.LastName = LastName;
		this.birthDay = BirthDay;
		this.Sex = Sex;
		this.AddState = AddState;
		this.AddStreet = AddStreet;
		this.AddCity = AddCity;
		this.AddZip = AddZip;
		this.AddCountry = AddCountry;
		this.HomePhone = HomePhone;
	}

	// get data show table
	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(CheckinID);
		vector.add(ReservationID);
		vector.add(firstName);
		vector.add(birthDay);
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

	// all list data
	public static List<CheckIn> allTable() throws SQLException {
		return checkInDAO().allTable();
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

	// delet
	public static int deleteCheckInbyCheckOut(int object) throws SQLException {
		return checkInDAO().deleteCheckInByCheckOut(object);
	}

	/**
	 * @return the CheckinID
	 */
	public Integer getCheckinID() {
		return CheckinID;
	}

	/**
	 * @param CheckinID
	 *            the CheckinID to set
	 */
	public void setCheckinID(Integer CheckinID) {
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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}

	public String getExpectedCheckinDate() {
		return ExpectedCheckinDate;
	}

	public void setExpectedCheckinDate(String expectedCheckinDate) {
		ExpectedCheckinDate = expectedCheckinDate;
	}

	public String getExpectedCheckoutDate() {
		return ExpectedCheckoutDate;
	}

	public void setExpectedCheckoutDate(String expectedCheckoutDate) {
		ExpectedCheckoutDate = expectedCheckoutDate;
	}

	public Integer getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(Integer arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public Integer getNumberGuests() {
		return NumberGuests;
	}

	public void setNumberGuests(Integer numberGuests) {
		NumberGuests = numberGuests;
	}

	public String getRoomRate() {
		return RoomRate;
	}

	public void setRoomRate(String roomRate) {
		RoomRate = roomRate;
	}

	public Integer getRoomTypeID() {
		return RoomTypeID;
	}

	public void setRoomTypeID(Integer roomTypeID) {
		RoomTypeID = roomTypeID;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNumbeds() {
		return numbeds;
	}

	public void setNumbeds(Integer numbeds) {
		this.numbeds = numbeds;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddStreet() {
		return AddStreet;
	}

	public void setAddStreet(String addStreet) {
		AddStreet = addStreet;
	}

	public String getAddCity() {
		return AddCity;
	}

	public void setAddCity(String addCity) {
		AddCity = addCity;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getAddState() {
		return AddState;
	}

	public void setAddState(String addState) {
		AddState = addState;
	}

	public String getAddZip() {
		return AddZip;
	}

	public void setAddZip(String addZip) {
		AddZip = addZip;
	}

	public String getAddCountry() {
		return AddCountry;
	}

	public void setAddCountry(String addCountry) {
		AddCountry = addCountry;
	}

	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}
}
