package hotel.linuxdp.java.view.listeners;

import java.util.EventListener;

import hotel.linuxdp.java.model.CheckOut;
import hotel.linuxdp.java.view.events.EventAll;

public interface CheckOutListener extends EventListener{
	void roleadd(EventAll<CheckOut> event);
}
