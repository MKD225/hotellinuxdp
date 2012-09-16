package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.RoleInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolesDAO implements RoleInterface {

	private static final String ALL = "SELECT * FROM Roles ORDER BY RoleID DESC";
	private static final String INSERT = "INSERT INTO Roles (RoleName, RoleDescription) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE Roles SET RoleName = ?, RoleDescription= ? WHERE RoleID=?";

	@Override
	public List<Roles> all() throws SQLException {
		ArrayList<Roles> listRoles = new ArrayList<Roles>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listRoles.add(createRole(resultSet));
		}
		return listRoles;
	}

	@Override
	public Roles insert(Roles object) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, object.getRoleName());
		pstmt.setString(2, object.getRoleDescription());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		object.setRoleID(integer);
		pstmt.close();
		connection.close();
		return object;
	}

	@Override
	public Roles updateRole(Roles obiect) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, obiect.getRoleName());
			pstmt.setString(2, obiect.getRoleDescription());
			pstmt.setInt(3, obiect.getRoleID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obiect;
	}

	@Override
	public int deleteRole(int object) throws SQLException {
		String DELETE_ROLE = "DELETE FROM Roles WHERE RoleID = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_ROLE);
		statement.close();
		return object;
	}

	private Roles createRole(ResultSet resultSet) throws SQLException {
		Roles user = new Roles(resultSet.getString("RoleName"),
				resultSet.getString("RoleDescription"));
		user.setRoleID(resultSet.getInt("RoleID"));

		return user;
	}

}
