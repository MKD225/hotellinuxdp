package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.CheckOut;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CheckOutListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckOutController {
	public static CheckOutController checkOutController = new CheckOutController();
	private List<CheckOutListener> checkOutListeners = new ArrayList<CheckOutListener>();

	public List<CheckOut> all() throws SQLException {
		return CheckOut.all();
	}

	public CheckOut save(CheckOut checkOut) throws SQLException {
		if (checkOut != null) {
			checkOut.save();
			notifyListeners(checkOut);
		}
		return checkOut;
	}

	public CheckOut updateCheckIn(CheckOut checkOut) throws SQLException {
		if (checkOut != null) {
			checkOut.update();
		}
		return checkOut;
	}

	public int deleteCheckIn(int checkInID) throws SQLException {
		return CheckOut.delete(checkInID);

	}

	public synchronized void addCheckOutListener(CheckOutListener listener) {
		if (!checkOutListeners.contains(listener)) {
			checkOutListeners.add(listener);
		}
	}

	private void notifyListeners(CheckOut CheckOut) {
		EventAll<CheckOut> event = new EventAll<CheckOut>(CheckOut);
		for (CheckOutListener listener : checkOutListeners) {
			listener.roleadd(event);
		}
	}
}
