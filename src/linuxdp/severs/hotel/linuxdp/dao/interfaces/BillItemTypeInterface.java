package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.BillItemType;

import java.sql.SQLException;
import java.util.List;


public interface BillItemTypeInterface {
	List<BillItemType> all() throws SQLException;

	BillItemType insertItemType(BillItemType billItemType) throws SQLException;

	BillItemType updateBillItemType(BillItemType billItemType)
			throws SQLException;

	int deleteBillItemType(int object) throws SQLException;
}
