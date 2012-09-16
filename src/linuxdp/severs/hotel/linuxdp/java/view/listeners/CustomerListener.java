package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Customer;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface CustomerListener extends EventListener{
	void roleadd(EventAll<Customer> event);
}
