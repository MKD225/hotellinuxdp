package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.RoomStatus;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface RoomStatusListener extends EventListener{
	void roleadd(EventAll<RoomStatus> event);
}
