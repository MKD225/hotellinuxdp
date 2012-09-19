package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Bills;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.BillsListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillsController {
	public static BillsController billController = new BillsController();
	private List<BillsListener> billsListeners = new ArrayList<BillsListener>();

	public List<Bills> all() throws SQLException {
		return Bills.all();
	}

	public List<Bills> select() throws SQLException {
		return Bills.selectIndex();
	}

	public Bills save(Bills bills) throws SQLException {
		if (bills != null) {
			bills.save();
			notifyListeners(bills);
		}
		return bills;
	}

	public Bills update(Bills bills) throws SQLException {
		if (bills != null) {
			bills.update();
		}
		return bills;
	}

	public int deleteAdmin(int billsID) throws SQLException {
		return Bills.delete(billsID);

	}

	public synchronized void addAdminListener(BillsListener listener) {
		if (!billsListeners.contains(listener)) {
			billsListeners.add(listener);
		}
	}

	private void notifyListeners(Bills bills) {
		EventAll<Bills> event = new EventAll<Bills>(bills);
		for (BillsListener listener : billsListeners) {
			listener.roleadd(event);
		}
	}
}
