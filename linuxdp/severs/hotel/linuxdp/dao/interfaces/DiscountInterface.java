package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Discount;

import java.sql.SQLException;
import java.util.List;


public interface DiscountInterface {
	List<Discount> all() throws SQLException;

	Discount insretDiscount(Discount discount) throws SQLException;

	Discount updateDiscount(Discount discount) throws SQLException;

	int deleteDis(int object) throws SQLException;
}
