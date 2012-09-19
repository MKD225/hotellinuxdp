package hotel.linuxdp.java.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import hotel.linuxdp.java.dao.concreate.DiscountDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

public class Discount {
	private Integer DiscountID;
	private String DiscountName;
	private Integer DiscountPercent;
	private float DiscountAmount;

	public Discount(String DiscountName, Integer DiscountPercent,
			float DiscountAmount) {
		this.DiscountName = DiscountName;
		this.DiscountPercent = DiscountPercent;
		this.DiscountAmount = DiscountAmount;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(DiscountID);
		objects.add(DiscountName);
		objects.add(DiscountPercent);
		objects.add(DiscountAmount);

		return objects;
	}

	public static DiscountDAO discountDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getDiscountDAO();
	}

	public static List<Discount> all() throws SQLException {
		return discountDAO().all();
	}

	public Discount save() throws SQLException {
		return discountDAO().insretDiscount(this);
	}

	public Discount update() throws SQLException {
		return discountDAO().updateDiscount(this);
	}

	public static int delete(int object) throws SQLException {
		return discountDAO().deleteDis(object);
	}

	public String getDiscountName() {
		return DiscountName;
	}

	public void setDiscountName(String discountName) {
		DiscountName = discountName;
	}

	public Integer getDiscountPercent() {
		return DiscountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		DiscountPercent = discountPercent;
	}

	public Float getDiscountAmount() {
		return DiscountAmount;
	}

	public void setDiscountAmount(Float discountAmount) {
		DiscountAmount = discountAmount;
	}

	public Integer getDiscountID() {
		return DiscountID;
	}

	public void setDiscountID(Integer discountID) {
		DiscountID = discountID;
	}
}
