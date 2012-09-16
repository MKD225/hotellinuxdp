package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.BillItemType;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.BillItemTypeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillItemTypeController {
	public static BillItemTypeController billItemController = new BillItemTypeController();
	private List<BillItemTypeListener> billItems = new ArrayList<BillItemTypeListener>();

	public List<BillItemType> all() throws SQLException {
		return BillItemType.all();
	}

	public BillItemType save(BillItemType billItem) throws SQLException {
		if (billItem != null) {
			billItem.save();
			notifyListeners(billItem);
		}
		return billItem;
	}

	public BillItemType updateBillItem(BillItemType billItem)
			throws SQLException {
		if (billItem != null) {
			billItem.update();
		}
		return billItem;
	}

	public int delete(int billItems) throws SQLException {
		return BillItemType.delete(billItems);

	}

	public synchronized void addAdminListener(BillItemTypeListener listener) {
		if (!billItems.contains(listener)) {
			billItems.add(listener);
		}
	}

	private void notifyListeners(BillItemType billItemType) {
		EventAll<BillItemType> event = new EventAll<BillItemType>(billItemType);
		for (BillItemTypeListener listener : billItems) {
			listener.roleadd(event);
		}
	}
}
