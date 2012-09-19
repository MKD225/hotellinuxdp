package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.PaymentType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.PaymentTypeListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentTypeController {
	public static PaymentTypeController paymentTypeController = new PaymentTypeController();
	private List<PaymentTypeListener> paymentTypeListeners = new ArrayList<PaymentTypeListener>();

	public List<PaymentType> all() throws SQLException {
		return PaymentType.all();
	}

	public PaymentType save(PaymentType paymentType) throws SQLException {
		if (paymentType != null) {
			paymentType.save();
			notifyListeners(paymentType);
		}
		return paymentType;
	}

	public PaymentType update(PaymentType paymentType) throws SQLException {
		if (paymentType != null) {
			paymentType.update();
		}
		return paymentType;
	}

	public int delete(int object) throws SQLException {
		return PaymentType.delete(object);

	}

	public synchronized void addDiscountListener(PaymentTypeListener listener) {
		if (!paymentTypeListeners.contains(listener)) {
			paymentTypeListeners.add(listener);
		}
	}

	private void notifyListeners(PaymentType paymentType) {
		EventAll<PaymentType> event = new EventAll<PaymentType>(paymentType);
		for (PaymentTypeListener listener : paymentTypeListeners) {
			listener.roleadd(event);
		}
	}
}
