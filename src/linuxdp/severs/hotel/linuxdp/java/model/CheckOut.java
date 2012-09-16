/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.CheckOutDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class CheckOut {

	private Integer CheckOutID;
	private Integer ReservationID;
	private Date CheckoutDate;

	public CheckOut(Integer ReservationID, Date CheckoutDate) {
		this.ReservationID = ReservationID;
		this.CheckoutDate = CheckoutDate;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(CheckOutID);
		objects.add(ReservationID);
		objects.add(CheckoutDate);
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
	public int getCheckOutID() {
		return CheckOutID;
	}

	/**
	 * @param CheckOutID
	 *            the CheckOutID to set
	 */
	public void setCheckOutID(int CheckOutID) {
		this.CheckOutID = CheckOutID;
	}

	/**
	 * @return the ReservationID
	 */
	public int getReservationID() {
		return ReservationID;
	}

	/**
	 * @param ReservationID
	 *            the ReservationID to set
	 */
	public void setReservationID(int ReservationID) {
		this.ReservationID = ReservationID;
	}

	/**
	 * @return the CheckoutDate
	 */
	public Date getCheckoutDate() {
		return CheckoutDate;
	}

	/**
	 * @param CheckoutDate
	 *            the CheckoutDate to set
	 */
	public void setCheckoutDate(Date CheckoutDate) {
		this.CheckoutDate = CheckoutDate;
	}
}
