package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Payment;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface PaymentListener extends EventListener {
	void roleadd(EventAll<Payment> event);
}
