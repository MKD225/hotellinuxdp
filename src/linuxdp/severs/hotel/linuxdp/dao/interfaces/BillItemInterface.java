package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.BillItem;

import java.sql.SQLException;
import java.util.List;


public interface BillItemInterface {
	List<hotel.linuxdp.java.model.BillItem> all() throws SQLException;

	BillItem insertBillItem(BillItem billItem) throws SQLException;

	BillItem updateBillItem(BillItem billItem) throws SQLException;

	int deleteBillItem(int object) throws SQLException;
}
