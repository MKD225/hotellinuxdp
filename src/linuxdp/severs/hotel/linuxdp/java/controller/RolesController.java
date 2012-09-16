package hotel.linuxdp.java.controller;

import hotel.linuxdp.java.model.Roles;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.RoleListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesController {
	public static RolesController roleController = new RolesController();
	private List<RoleListener> userListeners = new ArrayList<RoleListener>();

	public List<Roles> allRoles() throws SQLException {
		return Roles.allRole();
	}

	public Roles save(Roles roles) throws SQLException {
		if (roles != null) {
			roles.save();
			notifyListeners(roles);
		}
		return roles;
	}

	public Roles updateRole(Roles role) throws SQLException {
		if (role != null) {
			role.update();
		}
		return role;
	}

	public int deleteRole(int object) throws SQLException {
		return Roles.deleteAll(object);

	}

	public synchronized void addRoleListener(RoleListener listener) {
		if (!userListeners.contains(listener)) {
			userListeners.add(listener);
		}
	}

	private void notifyListeners(Roles role) {
		EventAll<Roles> event = new EventAll<Roles>(role);
		for (RoleListener listener : userListeners) {
			listener.roleadd(event);
		}
	}
}
