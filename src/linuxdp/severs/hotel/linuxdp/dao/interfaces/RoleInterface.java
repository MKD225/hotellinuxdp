package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Roles;

import java.sql.SQLException;
import java.util.List;

public interface RoleInterface {

	List<Roles> all() throws SQLException;

	Roles insert(Roles object) throws SQLException;

	Roles updateRole(Roles obiect) throws SQLException;

	int deleteRole(int object) throws SQLException;
}
