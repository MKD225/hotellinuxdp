package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Customer;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CustomerListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
	public static CustomerController customerController = new CustomerController();
	private List<CustomerListener> customerListeners = new ArrayList<CustomerListener>();

	public List<Customer> all() throws SQLException {
		return Customer.all();
	}

	public Customer save(Customer customer) throws SQLException {
		if (customer != null) {
			customer.save();
			notifyListeners(customer);
		}
		return customer;
	}

	public Customer update(Customer customer) throws SQLException {
		if (customer != null) {
			customer.update();
		}
		return customer;
	}

	public int delete(int object) throws SQLException {
		return Customer.delete(object);

	}

	public synchronized void addRoleListener(CustomerListener listener) {
		if (!customerListeners.contains(listener)) {
			customerListeners.add(listener);
		}
	}

	private void notifyListeners(Customer role) {
		EventAll<Customer> event = new EventAll<Customer>(role);
		for (CustomerListener listener : customerListeners) {
			listener.roleadd(event);
		}
	}
}
