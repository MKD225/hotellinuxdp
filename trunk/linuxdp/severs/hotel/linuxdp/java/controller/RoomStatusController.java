package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.RoomStatus;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomStatusListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomStatusController {
	public static RoomStatusController roomStatusController = new RoomStatusController();
	private List<RoomStatusListener> roomStatusListeners = new ArrayList<RoomStatusListener>();

	public List<RoomStatus> all() throws SQLException {
		return RoomStatus.all();
	}

	public RoomStatus save(RoomStatus roomStatus) throws SQLException {
		if (roomStatus != null) {
			roomStatus.save();
			notifyListeners(roomStatus);
		}
		return roomStatus;
	}

	public RoomStatus update(RoomStatus roomStatus) throws SQLException {
		if (roomStatus != null) {
			roomStatus.update();
		}
		return roomStatus;
	}

	public int delete(int object) throws SQLException {
		return RoomStatus.delete(object);

	}

	public synchronized void addDiscountListener(RoomStatusListener listener) {
		if (!roomStatusListeners.contains(listener)) {
			roomStatusListeners.add(listener);
		}
	}

	private void notifyListeners(RoomStatus roomStatus) {
		EventAll<RoomStatus> event = new EventAll<RoomStatus>(roomStatus);
		for (RoomStatusListener listener : roomStatusListeners) {
			listener.roleadd(event);
		}
	}
}
