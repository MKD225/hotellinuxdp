package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.RolesDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class Roles {
	private Integer roleID;
	private String roleName;
	private String roleDescription;

	public Roles(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public Roles() {

	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String[] toArray() {
		return new String[] { this.getRoleID().toString(), this.roleName,
				this.roleDescription };
	}

	private static RolesDAO rolesDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getRolesDAO();
	}

	public static List<Roles> allRole() throws SQLException {
		return rolesDAO().all();
	}

	public void save() throws SQLException {
		rolesDAO().insert(this);
	}

	public void update() throws SQLException {
		rolesDAO().updateRole(this);
	}

	public static int deleteAll(int object) throws SQLException {
		return rolesDAO().deleteRole(object);
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
}
