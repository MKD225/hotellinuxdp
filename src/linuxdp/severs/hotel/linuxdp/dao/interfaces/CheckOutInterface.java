package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.CheckOut;

import java.sql.SQLException;
import java.util.List;

public interface CheckOutInterface {
	List<CheckOut> all() throws SQLException;

	CheckOut insertCheckOut(CheckOut checkOut) throws SQLException;

	CheckOut updateCheckOut(CheckOut checkOut) throws SQLException;

	int deleteCheckOut(int object) throws SQLException;

}
