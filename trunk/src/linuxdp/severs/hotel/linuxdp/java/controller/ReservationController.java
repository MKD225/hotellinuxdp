package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Reservation;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.ReservationListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
	public static ReservationController ReservationController = new ReservationController();
	private List<ReservationListener> ReservationListeners = new ArrayList<ReservationListener>();

	public List<Reservation> all() throws SQLException {
		return Reservation.all();
	}

	public Reservation save(Reservation reservation) throws SQLException {
		if (reservation != null) {
			reservation.save();
			notifyListeners(reservation);
		}
		return reservation;
	}

	public Reservation update(Reservation Reservation) throws SQLException {
		if (Reservation != null) {
			Reservation.update();
		}
		return Reservation;
	}

	public int delete(int object) throws SQLException {
		return Reservation.delete(object);

	}

	public List<Reservation> search(String search) throws SQLException {
		return Reservation.search(search);
	}

	public synchronized void addDiscountListener(ReservationListener listener) {
		if (!ReservationListeners.contains(listener)) {
			ReservationListeners.add(listener);
		}
	}

	private void notifyListeners(Reservation reservation) {
		EventAll<Reservation> event = new EventAll<Reservation>(reservation);
		for (ReservationListener listener : ReservationListeners) {
			listener.roleadd(event);
		}
	}
}
