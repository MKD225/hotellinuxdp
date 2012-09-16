package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Foods;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface FoodListener extends EventListener {
	void roleadd(EventAll<Foods> event);
}
