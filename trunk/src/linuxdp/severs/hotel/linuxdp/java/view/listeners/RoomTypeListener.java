package hotel.linuxdp.java.view.listeners;

import java.util.EventListener;

import hotel.linuxdp.java.model.RoomType;
import hotel.linuxdp.java.view.events.EventAll;

public interface RoomTypeListener extends EventListener{
	void roleadd(EventAll<RoomType> event);
}
