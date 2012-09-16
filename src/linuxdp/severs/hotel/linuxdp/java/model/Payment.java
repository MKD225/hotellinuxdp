/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.PaymentDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class Payment {

	private Integer PaymentID;
	private Integer PaymentTypeID;
	private Integer ReservationID;
	private String paymentTypeName;
	private Integer CCNumber;
	private float PaymentAmount;
	private String CCExpirationMonth;
	private String CCExpirationYear;
	private String PaymentDate;
	private String expectedCheckinDate;
	private String expectedCheckOutDate;
	private String CCOwner;
	private String firstName;
	private String paymentType;

	public Payment(Integer PaymentTypeID, Integer CCNumber,
			String CCExpirationMonth, String CCExpirationYear, String CCOwner,
			float PaymentAmount, String PaymentDate, Integer ReservationID) {

		this.PaymentTypeID = PaymentTypeID;
		this.CCNumber = CCNumber;
		this.CCExpirationMonth = CCExpirationMonth;
		this.CCExpirationYear = CCExpirationYear;
		this.CCOwner = CCOwner;
		this.PaymentAmount = PaymentAmount;
		this.PaymentDate = PaymentDate;
		this.ReservationID = ReservationID;
	}

	public Payment(Integer paymentID,Integer ReservationID ,String firstName, String ccOwner,
			Integer PaymentTypeID, String paymentType, Integer ccNumber,
			String ccExpirationMonth, String ccExpirationYear,
			String expectedCheckinDate, String expectedCheckOutDate,
			float paymentAmount, String paymentDate) {

		this.PaymentID = paymentID;
		this.ReservationID = ReservationID;
		this.firstName = firstName;
		this.CCOwner = ccOwner;
		this.PaymentTypeID = PaymentTypeID;
		this.paymentType = paymentType;
		this.CCNumber = ccNumber;
		this.CCExpirationMonth = ccExpirationMonth;
		this.CCExpirationYear = ccExpirationYear;
		this.expectedCheckinDate = expectedCheckinDate;
		this.expectedCheckOutDate = expectedCheckOutDate;
		this.PaymentAmount = paymentAmount;
		this.PaymentDate = paymentDate;

	}

	// vecter show data
	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(PaymentID);
		objects.add(ReservationID);
		objects.add(firstName);
		objects.add(CCOwner);
		objects.add(paymentType);
		objects.add(CCNumber);
		objects.add(CCExpirationMonth);
		objects.add(CCExpirationYear);
		objects.add(expectedCheckinDate);
		objects.add(expectedCheckOutDate);
		objects.add(PaymentAmount);
		objects.add(PaymentDate);

		return objects;

	}

	public static PaymentDAO paymentDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getPaymentDAO();
	}

	public static List<Payment> all() throws SQLException {
		return paymentDAO().all();
	}

	public Payment save() throws SQLException {
		return paymentDAO().insertPayment(this);
	}

	public Payment update() throws SQLException {
		return paymentDAO().updatePayment(this);
	}

	public static int delete(int object) throws SQLException {
		return paymentDAO().deleteReport(object);
	}

	/**
	 * @return the PaymentID
	 */
	public Integer getPaymentID() {
		return PaymentID;
	}

	/**
	 * @param PaymentID
	 *            the PaymentID to set
	 */
	public void setPaymentID(Integer PaymentID) {
		this.PaymentID = PaymentID;
	}

	/**
	 * @return the PaymentTypeID
	 */
	public int getPaymentTypeID() {
		return PaymentTypeID;
	}

	/**
	 * @param PaymentTypeID
	 *            the PaymentTypeID to set
	 */
	public void setPaymentTypeID(Integer PaymentTypeID) {
		this.PaymentTypeID = PaymentTypeID;
	}

	/**
	 * @return the CCNumber
	 */
	public Integer getCCNumber() {
		return CCNumber;
	}

	/**
	 * @param CCNumber
	 *            the CCNumber to set
	 */
	public void setCCNumber(Integer CCNumber) {
		this.CCNumber = CCNumber;
	}

	/**
	 * @return the CCExpirationMonth
	 */
	public String getCCExpirationMonth() {
		return CCExpirationMonth;
	}

	/**
	 * @param CCExpirationMonth
	 *            the CCExpirationMonth to set
	 */
	public void setCCExpirationMonth(String CCExpirationMonth) {
		this.CCExpirationMonth = CCExpirationMonth;
	}

	/**
	 * @return the CCExpirationYear
	 */
	public String getCCExpirationYear() {
		return CCExpirationYear;
	}

	/**
	 * @param CCExpirationYear
	 *            the CCExpirationYear to set
	 */
	public void setCCExpirationYear(String CCExpirationYear) {
		this.CCExpirationYear = CCExpirationYear;
	}

	/**
	 * @return the CCOwner
	 */
	public String getCCOwner() {
		return CCOwner;
	}

	/**
	 * @param CCOwner
	 *            the CCOwner to set
	 */
	public void setCCOwner(String CCOwner) {
		this.CCOwner = CCOwner;
	}

	/**
	 * @return the PaymentAmount
	 */
	public Float getPaymentAmount() {
		return PaymentAmount;
	}

	/**
	 * @param PaymentAmount
	 *            the PaymentAmount to set
	 */
	public void setPaymentAmount(Float PaymentAmount) {
		this.PaymentAmount = PaymentAmount;
	}

	/**
	 * @return the PaymentDate
	 */
	public String getPaymentDate() {
		return PaymentDate;
	}

	/**
	 * @param PaymentDate
	 *            the PaymentDate to set
	 */
	public void setPaymentDate(String PaymentDate) {
		this.PaymentDate = PaymentDate;
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

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExpectedCheckinDate() {
		return expectedCheckinDate;
	}

	public void setExpectedCheckinDate(String expectedCheckinDate) {
		this.expectedCheckinDate = expectedCheckinDate;
	}

	public String getExpectedCheckOutDate() {
		return expectedCheckOutDate;
	}

	public void setExpectedCheckOutDate(String expectedCheckOutDate) {
		this.expectedCheckOutDate = expectedCheckOutDate;
	}
}
