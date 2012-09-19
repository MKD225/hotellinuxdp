package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface ReservationListener extends EventListener{
	void roleadd(EventAll<Reservation> event);
}
