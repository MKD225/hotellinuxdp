package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Foods;
import java.sql.SQLException;
import java.util.List;


public interface FoodsInterface {
	List<Foods> all() throws SQLException;

	Foods insertFoods(Foods foods) throws SQLException;

	Foods updateFoods(Foods foods) throws SQLException;

	int deletePayment(int object) throws SQLException;
}
