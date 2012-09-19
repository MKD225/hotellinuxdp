package hotel.linuxdp.java.view.listeners;

import hotel.linuxdp.java.model.Report;
import hotel.linuxdp.java.view.events.EventAll;

import java.util.EventListener;

public interface ReportListener extends EventListener{
	void roleadd(EventAll<Report> event);
}
