package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Discount;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.DiscountListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountController {
	public static DiscountController discountController = new DiscountController();
	private List<DiscountListener> discountListeners = new ArrayList<DiscountListener>();

	public List<Discount> all() throws SQLException {
		return Discount.all();
	}

	public Discount save(Discount discount) throws SQLException {
		if (discount != null) {
			discount.save();
			notifyListeners(discount);
		}
		return discount;
	}

	public Discount update(Discount discount) throws SQLException {
		if (discount != null) {
			discount.update();
		}
		return discount;
	}

	public int delete(int object) throws SQLException {
		return Discount.delete(object);

	}

	public synchronized void addDiscountListener(DiscountListener listener) {
		if (!discountListeners.contains(listener)) {
			discountListeners.add(listener);
		}
	}

	private void notifyListeners(Discount discount) {
		EventAll<Discount> event = new EventAll<Discount>(discount);
		for (DiscountListener listener : discountListeners) {
			listener.roleadd(event);
		}
	}
}
