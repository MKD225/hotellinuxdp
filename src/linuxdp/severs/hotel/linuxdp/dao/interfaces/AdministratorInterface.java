package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Administrator;

import java.sql.SQLException;
import java.util.List;

public interface AdministratorInterface {
	List<Administrator> all() throws SQLException;

	Administrator insert(Administrator administrator) throws SQLException;

	Administrator updateAdministrator(Administrator administrator)
			throws SQLException;

	String deleteAdmin(String userName) throws SQLException;
}
