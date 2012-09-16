package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.CheckInListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckInController {
	public static CheckInController checkInController = new CheckInController();
	private List<CheckInListener> checkInListeners = new ArrayList<CheckInListener>();

	public List<CheckIn> all() throws SQLException {
		return CheckIn.all();
	}

	public CheckIn save(CheckIn checkIn) throws SQLException {
		if (checkIn != null) {
			checkIn.save();
			notifyListeners(checkIn);
		}
		return checkIn;
	}

	public CheckIn updateCheckIn(CheckIn checkIn) throws SQLException {
		if (checkIn != null) {
			checkIn.update();
		}
		return checkIn;
	}

	public int deleteCheckIn(int checkInID) throws SQLException {
		return CheckIn.delete(checkInID);

	}

	public synchronized void addCheckInListener(CheckInListener listener) {
		if (!checkInListeners.contains(listener)) {
			checkInListeners.add(listener);
		}
	}

	private void notifyListeners(CheckIn checkIn) {
		EventAll<CheckIn> event = new EventAll<CheckIn>(checkIn);
		for (CheckInListener listener : checkInListeners) {
			listener.roleadd(event);
		}
	}
}
