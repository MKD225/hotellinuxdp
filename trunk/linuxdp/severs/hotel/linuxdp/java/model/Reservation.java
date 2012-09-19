/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.ReservationDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class Reservation {

	private Integer ReservationID;
	private Integer CustomerID;
	private String ExpectedCheckinDate;
	private String ExpectedCheckoutDate;
	private Integer ArrivalTime;
	private Integer NumberGuests;
	private Integer RoomTypeID;
	private String RoomRate;
	private String Comments;
	private String firstName;
	private String workPhone;
	private String email;
	private String roomType;
	private Integer numbeds;
	private String MiddleName;
	private String LastName;
	private String BirthDay;
	private String Sex;
	private String AddStreet;
	private String AddCity;
	private String AddState;
	private String AddZip;
	private String AddCountry;
	private String HomePhone;

	public Reservation(String ExpectedCheckinDate, String ExpectedCheckoutDate,
			Integer ArrivalTime, Integer NumberGuests, String RoomRate,
			String Comments, Integer CustomerID, Integer RoomTypeID) {

		this.ExpectedCheckinDate = ExpectedCheckinDate;
		this.ExpectedCheckoutDate = ExpectedCheckoutDate;
		this.ArrivalTime = ArrivalTime;
		this.NumberGuests = NumberGuests;
		this.RoomRate = RoomRate;
		this.Comments = Comments;
		this.CustomerID = CustomerID;
		this.RoomTypeID = RoomTypeID;
	}

	public Reservation(Integer reservationID, String firstName,
			String workPhone, String email, String ExpectedCheckinDate,
			String ExpectedCheckoutDate, Integer ArrivalTime,
			Integer NumberGuests, String RoomRate, String roomType,
			String comment, String MiddleName, String LastName,
			String BirthDay, String Sex, String AddStreet, String AddCity,
			String AddState, String AddZip, String AddCountry, String HomePhone) {
		this.ReservationID = reservationID;
		this.firstName = firstName;
		this.workPhone = workPhone;
		this.email = email;
		this.ExpectedCheckinDate = ExpectedCheckinDate;
		this.ExpectedCheckoutDate = ExpectedCheckoutDate;
		this.ArrivalTime = ArrivalTime;
		this.NumberGuests = NumberGuests;
		this.RoomRate = RoomRate;
		this.roomType = roomType;
		this.Comments = comment;
		this.MiddleName = MiddleName;
		this.LastName = LastName;
		this.BirthDay = BirthDay;
		this.Sex = Sex;
		this.AddState = AddState;
		this.AddStreet = AddStreet;
		this.AddCity = AddCity;
		this.AddZip = AddZip;
		this.AddCountry = AddCountry;
		this.HomePhone = HomePhone;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(ReservationID);
		vector.add(firstName);
		vector.add(workPhone);
		vector.add(email);
		vector.add(ExpectedCheckinDate);
		vector.add(ExpectedCheckoutDate);
		vector.add(ArrivalTime);
		vector.add(NumberGuests);
		vector.add(RoomRate);
		vector.add(Comments);
		vector.add(roomType);
		return vector;
	}

	public static ReservationDAO reservationDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getReservationDAO();
	}

	public static List<Reservation> all() throws SQLException {
		return reservationDAO().all();
	}

	public Reservation save() throws SQLException {
		return reservationDAO().insertReservation(this);
	}

	public Reservation update() throws SQLException {
		return reservationDAO().updateReservation(this);
	}

	public static int delete(int object) throws SQLException {
		return reservationDAO().deleteReservation(object);
	}

	public static List<Reservation> search(String search) throws SQLException {
		return reservationDAO().searchReservation(search);
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
	 * @return the CustomerID
	 */
	public Integer getCustomerID() {
		return CustomerID;
	}

	/**
	 * @param CustomerID
	 *            the CustomerID to set
	 */
	public void setCustomerID(Integer CustomerID) {
		this.CustomerID = CustomerID;
	}

	/**
	 * @return the ExpectedCheckinDate
	 */
	public String getExpectedCheckinDate() {
		return ExpectedCheckinDate;
	}

	/**
	 * @param ExpectedCheckinDate
	 *            the ExpectedCheckinDate to set
	 */
	public void setExpectedCheckinDate(String ExpectedCheckinDate) {
		this.ExpectedCheckinDate = ExpectedCheckinDate;
	}

	/**
	 * @return the ExpectedCheckoutDate
	 */
	public String getExpectedCheckoutDate() {
		return ExpectedCheckoutDate;
	}

	/**
	 * @param ExpectedCheckoutDate
	 *            the ExpectedCheckoutDate to set
	 */
	public void setExpectedCheckoutDate(String ExpectedCheckoutDate) {
		this.ExpectedCheckoutDate = ExpectedCheckoutDate;
	}

	/**
	 * @return the ArrivalTime
	 */
	public Integer getArrivalTime() {
		return ArrivalTime;
	}

	/**
	 * @param ArrivalTime
	 *            the ArrivalTime to set
	 */
	public void setArrivalTime(Integer ArrivalTime) {
		this.ArrivalTime = ArrivalTime;
	}

	/**
	 * @return the NumberGuests
	 */
	public Integer getNumberGuests() {
		return NumberGuests;
	}

	/**
	 * @param NumberGuests
	 *            the NumberGuests to set
	 */
	public void setNumberGuests(Integer NumberGuests) {
		this.NumberGuests = NumberGuests;
	}

	/**
	 * @return the RoomTypeID
	 */
	public Integer getRoomTypeID() {
		return RoomTypeID;
	}

	/**
	 * @param RoomTypeID
	 *            the RoomTypeID to set
	 */
	public void setRoomTypeID(Integer RoomTypeID) {
		this.RoomTypeID = RoomTypeID;
	}

	/**
	 * @return the RoomRate
	 */
	public String getRoomRate() {
		return RoomRate;
	}

	/**
	 * @param RoomRate
	 *            the RoomRate to set
	 */
	public void setRoomRate(String RoomRate) {
		this.RoomRate = RoomRate;
	}

	/**
	 * @return the Comments
	 */
	public String getComments() {
		return Comments;
	}

	/**
	 * @param Comments
	 *            the Comments to set
	 */
	public void setComments(String Comments) {
		this.Comments = Comments;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
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

	public String getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(String birthDay) {
		BirthDay = birthDay;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
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

	public String getAddState() {
		return AddState;
	}

	public void setAddState(String addState) {
		AddState = addState;
	}

	public String getAddCountry() {
		return AddCountry;
	}

	public void setAddCountry(String addCountry) {
		AddCountry = addCountry;
	}

	public String getAddZip() {
		return AddZip;
	}

	public void setAddZip(String addZip) {
		AddZip = addZip;
	}

	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}
}
