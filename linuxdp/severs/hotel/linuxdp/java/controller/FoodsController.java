package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Foods;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.FoodListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodsController {
	public static FoodsController foodsController = new FoodsController();
	private List<FoodListener> foodListeners = new ArrayList<FoodListener>();

	public List<Foods> all() throws SQLException {
		return Foods.all();
	}

	public Foods save(Foods foods) throws SQLException {
		if (foods != null) {
			foods.save();
			notifyListeners(foods);
		}
		return foods;
	}

	public Foods update(Foods foods) throws SQLException {
		if (foods != null) {
			foods.update();
		}
		return foods;
	}

	public int delete(int object) throws SQLException {
		return Foods.delete(object);

	}

	public synchronized void addDiscountListener(FoodListener listener) {
		if (!foodListeners.contains(listener)) {
			foodListeners.add(listener);
		}
	}

	private void notifyListeners(Foods foods) {
		EventAll<Foods> event = new EventAll<Foods>(foods);
		for (FoodListener listener : foodListeners) {
			listener.roleadd(event);
		}
	}
}
