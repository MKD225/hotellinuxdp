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


	List<Administrator> allbyUsrName(String username) throws SQLException;

	Administrator updateAdministratorByUser(Administrator administrator)
			throws SQLException;

	Administrator updatePass_word(Administrator administrator)
			throws SQLException;

	Administrator getByUser(String username) throws SQLException;
}
