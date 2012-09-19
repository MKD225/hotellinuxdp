package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Report;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.ReportListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportController {
	public static ReportController reportController = new ReportController();
	private List<ReportListener> ReportListeners = new ArrayList<ReportListener>();

	public List<Report> all() throws SQLException {
		return Report.all();
	}

	public Report save(Report report) throws SQLException {
		if (report != null) {
			report.save();
			notifyListeners(report);
		}
		return report;
	}

	public Report update(Report Report) throws SQLException {
		if (Report != null) {
			Report.update();
		}
		return Report;
	}

	public int delete(int object) throws SQLException {
		return Report.delete(object);

	}

	public synchronized void addDiscountListener(ReportListener listener) {
		if (!ReportListeners.contains(listener)) {
			ReportListeners.add(listener);
		}
	}

	private void notifyListeners(Report report) {
		EventAll<Report> event = new EventAll<Report>(report);
		for (ReportListener listener : ReportListeners) {
			listener.roleadd(event);
		}
	}
}
