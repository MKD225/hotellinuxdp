package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.AdminListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorController {

	public static AdministratorController administratorController = new AdministratorController();
	private List<AdminListener> adminListeners = new ArrayList<AdminListener>();

	public List<Administrator> all() throws SQLException {
		return Administrator.all();
	}

	public Administrator save(Administrator administrator) throws SQLException {
		if (administrator != null) {
			administrator.save();
			notifyListeners(administrator);
		}
		return administrator;
	}

	public Administrator updateAdministrator(Administrator administrator)
			throws SQLException {
		if (administrator != null) {
			administrator.update();
		}
		return administrator;
	}

	public Administrator updateAdministratorbyUser(Administrator administrator)
			throws SQLException {
		if (administrator != null) {
			administrator.updateByUser();
		}
		return administrator;
	}

	public Administrator getbyuser(String username) throws SQLException {
		return new Administrator().getByUser(username);
	
	}

	public Administrator update_password(Administrator administrator)
			throws SQLException {
		if (administrator != null) {
			administrator.update_passwors();
		}
		return administrator;
	}

	public String deleteAdmin(String userName) throws SQLException {
		return Administrator.delete(userName);

	}

	public synchronized void addAdminListener(AdminListener listener) {
		if (!adminListeners.contains(listener)) {
			adminListeners.add(listener);
		}
	}

	private void notifyListeners(Administrator administrator) {
		EventAll<Administrator> event = new EventAll<Administrator>(
				administrator);
		for (AdminListener listener : adminListeners) {
			listener.adminadd(event);
		}
	}
}
