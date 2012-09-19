package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.BillItemType;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface BillItemTypeListener extends EventListener {
	void roleadd(EventAll<BillItemType> event);
}
