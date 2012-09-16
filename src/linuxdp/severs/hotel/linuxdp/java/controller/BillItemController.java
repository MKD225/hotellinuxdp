package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.BillItem;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.BillItemListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillItemController {
	public static BillItemController billItemController = new BillItemController();
	private List<BillItemListener> billItems = new ArrayList<BillItemListener>();

	public List<BillItem> all() throws SQLException {
		return BillItem.all();
	}

	public BillItem save(BillItem billItem) throws SQLException {
		if (billItem != null) {
			billItem.save();
			notifyListeners(billItem);
		}
		return billItem;
	}

	public BillItem updateBillItem(BillItem billItem) throws SQLException {
		if (billItem != null) {
			billItem.update();
		}
		return billItem;
	}

	public int delete(int billItems) throws SQLException {
		return BillItem.delete(billItems);

	}

	public synchronized void addAdminListener(BillItemListener listener) {
		if (!billItems.contains(listener)) {
			billItems.add(listener);
		}
	}

	private void notifyListeners(BillItem billItem) {
		EventAll<BillItem> event = new EventAll<BillItem>(billItem);
		for (BillItemListener listener : billItems) {
			listener.roleadd(event);
		}
	}
}
