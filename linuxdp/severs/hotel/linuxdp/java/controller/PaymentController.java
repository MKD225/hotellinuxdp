package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Payment;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.PaymentListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
	public static PaymentController paymentController = new PaymentController();
	private List<PaymentListener> paymentListeners = new ArrayList<PaymentListener>();

	public List<Payment> all() throws SQLException {
		return Payment.all();
	}

	public Payment save(Payment payment) throws SQLException {
		if (payment != null) {
			payment.save();
			notifyListeners(payment);
		}
		return payment;
	}

	public Payment update(Payment payment) throws SQLException {
		if (payment != null) {
			payment.update();
		}
		return payment;
	}

	public int delete(int object) throws SQLException {
		return Payment.delete(object);

	}

	public synchronized void addDiscountListener(PaymentListener listener) {
		if (!paymentListeners.contains(listener)) {
			paymentListeners.add(listener);
		}
	}

	private void notifyListeners(Payment payment) {
		EventAll<Payment> event = new EventAll<Payment>(payment);
		for (PaymentListener listener : paymentListeners) {
			listener.roleadd(event);
		}
	}
}
