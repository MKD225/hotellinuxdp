/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.FoodsDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class Foods {

	private Integer foodID;
	private String FoodName;
	private float FoodPrice;
	private Integer BillID;

	public Foods(Integer foodID, String FoodName, float FoodPrice, int BillID) {
		this.foodID = foodID;
		this.FoodName = FoodName;
		this.FoodPrice = FoodPrice;
		this.BillID = BillID;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();

		vector.add(getFoodID());
		vector.add(FoodName);
		vector.add(FoodPrice);
		vector.add(BillID);

		return vector;
	}

	public static FoodsDAO foodsDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getFoodsDAO();
	}

	public static List<Foods> all() throws SQLException {
		return foodsDAO().all();
	}

	public Foods save() throws SQLException {
		return foodsDAO().insertFoods(this);
	}

	public Foods update() throws SQLException {
		return foodsDAO().updateFoods(this);
	}

	public static int delete(int object) throws SQLException {
		return foodsDAO().deletePayment(object);
	}

	/**
	 * @return the FoodName
	 */
	public String getFoodName() {
		return FoodName;
	}

	/**
	 * @param FoodName
	 *            the FoodName to set
	 */
	public void setFoodName(String FoodName) {
		this.FoodName = FoodName;
	}

	/**
	 * @return the FoodPrice
	 */
	public float getFoodPrice() {
		return FoodPrice;
	}

	/**
	 * @param FoodPrice
	 *            the FoodPrice to set
	 */
	public void setFoodPrice(float FoodPrice) {
		this.FoodPrice = FoodPrice;
	}

	/**
	 * @return the BillID
	 */
	public int getBillID() {
		return BillID;
	}

	/**
	 * @param BillID
	 *            the BillID to set
	 */
	public void setBillID(int BillID) {
		this.BillID = BillID;
	}

	public Integer getFoodID() {
		return foodID;
	}

	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}
}
