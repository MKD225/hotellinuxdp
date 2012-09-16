package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.BillItem;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface BillItemListener extends EventListener {
	void roleadd(EventAll<BillItem> event);
}
