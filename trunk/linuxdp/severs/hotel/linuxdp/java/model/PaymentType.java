/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.PaymentTypeDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class PaymentType {

	private Integer paymentTypeID;
	private String PaymentType;
	private String PaymentTypeDesc;

	public PaymentType(String PaymentType, String PaymentTypeDesc) {
		this.PaymentType = PaymentType;
		this.PaymentTypeDesc = PaymentTypeDesc;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(paymentTypeID);
		objects.add(PaymentType);
		objects.add(PaymentTypeDesc);

		return objects;
	}

	public static PaymentTypeDAO paymentTypeDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getPaymentTypeDAO();
	}

	public static List<PaymentType> all() throws SQLException {
		return paymentTypeDAO().all();
	}

	public PaymentType save() throws SQLException {
		return paymentTypeDAO().insertPaymentType(this);
	}
	public PaymentType update() throws SQLException {
		return paymentTypeDAO().updatePaymentType(this);
	}
	public static int delete(int object) throws SQLException {
		return paymentTypeDAO().deletePaymentType(object);
	}

	/**
	 * @return the paymentTypeID
	 */
	public Integer getPaymentTypeID() {
		return paymentTypeID;
	}

	/**
	 * @param paymentTypeID
	 *            the paymentTypeID to set
	 */
	public void setPaymentTypeID(Integer paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}

	/**
	 * @return the PaymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}

	/**
	 * @param PaymentType
	 *            the PaymentType to set
	 */
	public void setPaymentType(String PaymentType) {
		this.PaymentType = PaymentType;
	}

	/**
	 * @return the PaymentTypeDesc
	 */
	public String getPaymentTypeDesc() {
		return PaymentTypeDesc;
	}

	/**
	 * @param PaymentTypeDesc
	 *            the PaymentTypeDesc to set
	 */
	public void setPaymentTypeDesc(String PaymentTypeDesc) {
		this.PaymentTypeDesc = PaymentTypeDesc;
	}
}
