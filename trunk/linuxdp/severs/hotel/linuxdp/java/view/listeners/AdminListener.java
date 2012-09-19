package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

/**
 * 
 * @author linuxdp
 * 
 */
public interface AdminListener extends EventListener {

	void adminadd(EventAll<Administrator> event);

}
