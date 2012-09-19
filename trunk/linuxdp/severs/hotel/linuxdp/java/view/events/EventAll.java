package hotel.linuxdp.java.view.events;

import java.util.EventObject;

/**
 * 
 * @author linuxdp
 * 
 * @param <T>
 */
public class EventAll<T> extends EventObject {

	private static final long serialVersionUID = 1L;

	public EventAll(Object source) {
		super(source);
	}

	@SuppressWarnings("unchecked")
	public T getSource() {
		return ((T) super.getSource());
	}
}