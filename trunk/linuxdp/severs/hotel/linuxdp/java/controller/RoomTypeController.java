package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.RoomType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoomTypeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeController {
	public static RoomTypeController roomTypeController = new RoomTypeController();
	private List<RoomTypeListener> roomTypeListeners = new ArrayList<RoomTypeListener>();

	public List<RoomType> all() throws SQLException {
		return RoomType.all();
	}

	public RoomType save(RoomType roomType) throws SQLException {
		if (roomType != null) {
			roomType.save();
			notifyListeners(roomType);
		}
		return roomType;
	}

	public RoomType update(RoomType roomType) throws SQLException {
		if (roomType != null) {
			roomType.update();
		}
		return roomType;
	}

	public int deleteRole(int object) throws SQLException {
		return RoomType.delete(object);

	}

	public synchronized void addDiscountListener(RoomTypeListener listener) {
		if (!roomTypeListeners.contains(listener)) {
			roomTypeListeners.add(listener);
		}
	}

	private void notifyListeners(RoomType roomType) {
		EventAll<RoomType> event = new EventAll<RoomType>(roomType);
		for (RoomTypeListener listener : roomTypeListeners) {
			listener.roleadd(event);
		}
	}
}
