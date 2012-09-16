package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.BillItemTypeDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class BillItemType {

	private Integer billItemTypeID;
	private String billItemType;
	private String description;

	public BillItemType(String BillItemType, String _Description,
			Integer billItemTypeID) {
		this.billItemTypeID = billItemTypeID;
		this.billItemType = BillItemType;
		this.description = _Description;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(billItemTypeID);
		objects.add(billItemType);
		objects.add(description);

		return objects;
	}

	public static BillItemTypeDAO billItemTypeDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getBillItemTypeDAO();

	}

	public static List<BillItemType> all() throws SQLException {
		return billItemTypeDAO().all();
	}

	public BillItemType save() throws SQLException {
		return billItemTypeDAO().insertItemType(this);
	}

	public void update() throws SQLException {
		billItemTypeDAO().updateBillItemType(this);
	}

	public static int delete(int object) throws SQLException {
		return billItemTypeDAO().deleteBillItemType(object);
	}

	public int getBillItemTypeID() {
		return billItemTypeID;
	}

	public void setBillItemTypeID(int billItemTypeID) {
		this.billItemTypeID = billItemTypeID;
	}

	public String getBillItemType() {
		return billItemType;
	}

	public void setBillItemType(String billItemType) {
		this.billItemType = billItemType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
