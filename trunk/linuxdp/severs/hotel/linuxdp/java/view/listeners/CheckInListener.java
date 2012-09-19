package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.CheckIn;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface CheckInListener extends EventListener{
	void roleadd(EventAll<CheckIn> event);
}
