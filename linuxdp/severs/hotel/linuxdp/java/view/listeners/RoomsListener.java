package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Rooms;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface RoomsListener extends EventListener {
	void roleadd(EventAll<Rooms> event);
}
