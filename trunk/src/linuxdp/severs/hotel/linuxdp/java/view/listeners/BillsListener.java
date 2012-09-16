package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Bills;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface BillsListener extends EventListener{
	void roleadd(EventAll<Bills> event);
}
