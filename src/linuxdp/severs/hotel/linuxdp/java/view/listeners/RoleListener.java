package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Roles;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

/**
 * 
 * @author linuxdp
 * 
 */
public interface RoleListener extends EventListener {

	void roleadd(EventAll<Roles> event);

}
