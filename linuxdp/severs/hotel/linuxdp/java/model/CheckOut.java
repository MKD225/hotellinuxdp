package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.CheckOutDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class CheckOut {

	private Integer CheckOutID;
	private Integer ReservationID;
	private String CheckoutDate;
	private String FirstName;
	private String WorkPhone;
	private String Email;
	private String ArrivalTime;
	private String NumberGuests;
	private String RoomRate;

	public CheckOut(Integer ReservationID, String CheckoutDate) {
		this.ReservationID = ReservationID;
		this.CheckoutDate = CheckoutDate;
	}

	public CheckOut(Integer checkOutID, String CheckoutDate, String FirstName,
			String WorkPhone, String Email, String ArrivalTime,
			String NumberGuests, String RoomRate, Integer ReservationID) {
		this.CheckOutID = checkOutID;
		this.ReservationID = ReservationID;
		this.CheckoutDate = CheckoutDate;
		this.FirstName = FirstName;
		this.WorkPhone = WorkPhone;
		this.Email = Email;
		this.ArrivalTime = ArrivalTime;
		this.NumberGuests = NumberGuests;
		this.RoomRate = RoomRate;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(CheckOutID);
		objects.add(FirstName);
		objects.add(WorkPhone);
		objects.add(Email);
		objects.add(ArrivalTime);
		objects.add(NumberGuests);
		objects.add(RoomRate);
		objects.add(CheckoutDate);
		objects.add(ReservationID);

		return objects;
	}

	public static CheckOutDAO checkOutDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getCheckOutDAO();
	}

	public static List<CheckOut> all() throws SQLException {
		return checkOutDAO().all();
	}

	public CheckOut save() throws SQLException {
		return checkOutDAO().insertCheckOut(this);
	}

	public CheckOut update() throws SQLException {
		return checkOutDAO().updateCheckOut(this);
	}

	public static int delete(int object) throws SQLException {
		return checkOutDAO().deleteCheckOut(object);
	}

	/**
	 * @return the CheckOutID
	 */
	public Integer getCheckOutID() {
		return CheckOutID;
	}

	/**
	 * @param CheckOutID
	 *            the CheckOutID to set
	 */
	public void setCheckOutID(Integer CheckOutID) {
		this.CheckOutID = CheckOutID;
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
	 * @return the CheckoutDate
	 */
	public String getCheckoutDate() {
		return CheckoutDate;
	}

	/**
	 * @param CheckoutDate
	 *            the CheckoutDate to set
	 */
	public void setCheckoutDate(String CheckoutDate) {
		this.CheckoutDate = CheckoutDate;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getWorkPhone() {
		return WorkPhone;
	}

	public void setWorkPhone(String workPhone) {
		WorkPhone = workPhone;
	}

	public String getNumberGuests() {
		return NumberGuests;
	}

	public void setNumberGuests(String numberGuests) {
		NumberGuests = numberGuests;
	}

	public String getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getRoomRate() {
		return RoomRate;
	}

	public void setRoomRate(String roomRate) {
		RoomRate = roomRate;
	}
}
