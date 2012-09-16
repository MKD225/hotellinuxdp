package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Customer;

import java.sql.SQLException;
import java.util.List;


public interface CustomerInterface {
	List<Customer> all() throws SQLException;

	Customer inserCustomer(Customer customer) throws SQLException;

	Customer updateCustomer(Customer customer) throws SQLException;

	int deleteCustomer(int object) throws SQLException;

}
