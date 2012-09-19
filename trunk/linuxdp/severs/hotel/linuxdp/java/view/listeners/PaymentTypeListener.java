package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.PaymentType;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface PaymentTypeListener extends EventListener{
	void roleadd(EventAll<PaymentType> event);
}
