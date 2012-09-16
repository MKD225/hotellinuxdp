package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomsListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomsController {
	public static RoomsController roomsController = new RoomsController();
	private List<RoomsListener> RoomsListeners = new ArrayList<RoomsListener>();

	public List<Rooms> all() throws SQLException {
		return Rooms.all();
	}

	public List<Rooms> fillRoomStatus() throws SQLException {
		return Rooms.fillRoomStatus();
	}

	public Rooms save(Rooms rooms) throws SQLException {
		if (rooms != null) {
			rooms.save();
			notifyListeners(rooms);
		}
		return rooms;
	}

	public Rooms update(Rooms rooms) throws SQLException {
		if (rooms != null) {
			rooms.update();
		}
		return rooms;
	}

	public Rooms updateStatusFalse(Rooms rooms) throws SQLException {
		if (rooms != null) {
			rooms.fillUpdateStatusFalse();
		}
		return rooms;
	}
	public Rooms updateStatusTrue(Rooms rooms) throws SQLException {
		if (rooms != null) {
			rooms.fillUpdateStatusTrue();
		}
		return rooms;
	}
	public int delete(int object) throws SQLException {
		return Rooms.delete(object);

	}

	public synchronized void addDiscountListener(RoomsListener listener) {
		if (!RoomsListeners.contains(listener)) {
			RoomsListeners.add(listener);
		}
	}

	private void notifyListeners(Rooms rooms) {
		EventAll<Rooms> event = new EventAll<Rooms>(rooms);
		for (RoomsListener listener : RoomsListeners) {
			listener.roleadd(event);
		}
	}
}
