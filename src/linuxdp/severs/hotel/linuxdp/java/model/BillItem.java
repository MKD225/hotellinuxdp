/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.BillItemDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class BillItem {

	private Integer billItemID;
	private Integer RoomID;
	private Integer BillItemTypeID;
	private String BillItemDescription;
	private Float BillItemAmount;
	private Integer DiscountID;
	private Integer BillID;
	private String firstName;
	private Integer roomNumber;
	private Integer roomRate;
	private String billItemType;
	private Date expectedCheckinDate;
	private Date expectedCheckOutDate;
	private String discountName;
	private Float discountAmount;
	private String billChargeDatev;
	private String billChargeDate;
	private String CheckinDate;
	private String CheckoutDate;

	public BillItem(Integer BillItemID, Integer RoomID, Integer BillItemTypeID,
			String BillItemDescription, Float BillItemAmount,
			String billChargeDate, Integer DiscountID, Integer BillID) {
		this.billItemID = BillItemID;
		this.RoomID = RoomID;
		this.BillItemTypeID = BillItemTypeID;
		this.BillItemDescription = BillItemDescription;
		this.BillItemAmount = BillItemAmount;
		this.billChargeDate = billChargeDate;
		this.DiscountID = DiscountID;
		this.BillID = BillID;

	}

	public BillItem(Integer billItemID, String firstNane, Integer roomNumber,
			Integer roomRate, Integer BillItemTypeID,
			String billItemType, Float billItemAmount, Integer DiscountID, String discountName,
			Float discountAmount, String expectedCheckinDate,
			String expectedCheckoutDate, String billChargeDate) {

		this.billItemID = billItemID;
		this.firstName = firstNane;
		this.roomNumber = roomNumber;
		this.roomRate = roomRate;
		this.BillItemTypeID = BillItemTypeID;
		this.billItemType = billItemType;
		this.DiscountID = DiscountID;
		this.discountName = discountName;
		this.discountAmount = discountAmount;
		this.BillItemAmount = billItemAmount;
		this.CheckinDate = expectedCheckinDate;
		this.CheckoutDate = expectedCheckoutDate;
		this.billChargeDatev = billChargeDate;
	}

	public static BillItemDAO billItemDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getBillItemDAO();
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(billItemID);
		objects.add(firstName);
		objects.add(roomNumber);
		objects.add(roomRate);
		objects.add(billItemType);
		objects.add(BillItemAmount);
		objects.add(discountName);
		objects.add(discountAmount);
		objects.add(CheckinDate);
		objects.add(CheckoutDate);
		objects.add(billChargeDatev);
		return objects;
	}

	public static List<BillItem> all() throws SQLException {
		return billItemDAO().all();
	}

	public BillItem save() throws SQLException {
		return billItemDAO().insertBillItem(this);
	}

	public BillItem update() throws SQLException {
		return billItemDAO().updateBillItem(this);
	}

	public static int delete(int object) throws SQLException {
		return billItemDAO().deleteBillItem(object);
	}

	/**
	 * @return the billItemID
	 */
	public int getBillItemID() {
		return billItemID;
	}

	/**
	 * @param billItemID
	 *            the billItemID to set
	 */
	public void setBillItemID(int billItemID) {
		this.billItemID = billItemID;
	}

	/**
	 * @return the RoomID
	 */
	public int getRoomID() {
		return RoomID;
	}

	/**
	 * @param RoomID
	 *            the RoomID to set
	 */
	public void setRoomID(int RoomID) {
		this.RoomID = RoomID;
	}

	/**
	 * @return the BillItemTypeID
	 */
	public Integer getBillItemTypeID() {
		return BillItemTypeID;
	}

	/**
	 * @param BillItemTypeID
	 *            the BillItemTypeID to set
	 */
	public void setBillItemTypeID(int BillItemTypeID) {
		this.BillItemTypeID = BillItemTypeID;
	}

	/**
	 * @return the BillItemDescription
	 */
	public String getBillItemDescription() {
		return BillItemDescription;
	}

	/**
	 * @param BillItemDescription
	 *            the BillItemDescription to set
	 */
	public void setBillItemDescription(String BillItemDescription) {
		this.BillItemDescription = BillItemDescription;
	}

	/**
	 * @return the BillItemAmount
	 */
	public float getBillItemAmount() {
		return BillItemAmount;
	}

	/**
	 * @param BillItemAmount
	 *            the BillItemAmount to set
	 */
	public void setBillItemAmount(float BillItemAmount) {
		this.BillItemAmount = BillItemAmount;
	}

	/**
	 * @return the DiscountID
	 */
	public Integer getDiscountID() {
		return DiscountID;
	}

	/**
	 * @param DiscountID
	 *            the DiscountID to set
	 */
	public void setDiscountID(Integer DiscountID) {
		this.DiscountID = DiscountID;
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
	public void setBillID(Integer BillID) {
		this.BillID = BillID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(Integer roomRate) {
		this.roomRate = roomRate;
	}

	public String getBillItemType() {
		return billItemType;
	}

	public void setBillItemType(String billItemType) {
		this.billItemType = billItemType;
	}

	public Date getExpectedCheckinDate() {
		return expectedCheckinDate;
	}

	public void setExpectedCheckinDate(Date expectedCheckinDate) {
		this.expectedCheckinDate = expectedCheckinDate;
	}

	public Date getExpectedCheckOutDate() {
		return expectedCheckOutDate;
	}

	public void setExpectedCheckOutDate(Date expectedCheckOutDate) {
		this.expectedCheckOutDate = expectedCheckOutDate;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getBillChargeDatev() {
		return billChargeDatev;
	}

	public void setBillChargeDatev(String billChargeDatev) {
		this.billChargeDatev = billChargeDatev;
	}

	public String getBillChargeDate() {
		return billChargeDate;
	}

	public void setBillChargeDate(String billChargeDate) {
		this.billChargeDate = billChargeDate;
	}

	public String getCheckinDate() {
		return CheckinDate;
	}

	public void setCheckinDate(String checkinDate) {
		CheckinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return CheckoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		CheckoutDate = checkoutDate;
	}
}
