package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Bills;

import java.sql.SQLException;
import java.util.List;


public interface BillInterface {
	List<Bills> all() throws SQLException;

	Bills insertBills(Bills bills) throws SQLException;

	Bills update(Bills bills) throws SQLException;

	int deleteBills(int object) throws SQLException;

	List<Bills> selectIndex() throws SQLException;

}
