package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.AdministratorDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Administrator {
	private String userID;
	private String password;
	private Integer roleID;
	private String firstName;
	private String userMiddleName;
	private String userLastName;
	private String sex;
	private String userContactInfo;
	private String roleName;

	public Administrator(String userID, String _password, String firstName,
			String userMiddleName, String userLastName, String sex,
			String userContactInfo, Integer roleID) {

		this.userID = userID;
		this.password = _password;
		this.firstName = firstName;
		this.userMiddleName = userMiddleName;
		this.userLastName = userLastName;
		this.sex = sex;
		this.userContactInfo = userContactInfo;
		this.roleID = roleID;
	}

	public Administrator(String firstName, String userMiddleName,
			String userLastName, String sex, String userContactInfo) {

		this.firstName = firstName;
		this.userMiddleName = userMiddleName;
		this.userLastName = userLastName;
		this.sex = sex;
		this.userContactInfo = userContactInfo;
	}

	public Administrator(String firstName, String userMiddleName,
			String userLastName, String sex, String userContactInfo,
			Integer roleID) {
		this.firstName = firstName;
		this.userMiddleName = userMiddleName;
		this.userLastName = userLastName;
		this.sex = sex;
		this.userContactInfo = userContactInfo;
		this.roleID = roleID;
	}

	public Administrator(String userID, String firstName,
			String userMiddleName, String userLastName, String sex,
			String userContactInfo, Integer roleID, String roleName) {
		this.userID = userID;
		this.firstName = firstName;
		this.userMiddleName = userMiddleName;
		this.userLastName = userLastName;
		this.sex = sex;
		this.userContactInfo = userContactInfo;
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public Administrator() {
		// TODO Auto-generated constructor stub
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserMiddleName() {
		return userMiddleName;
	}

	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserContactInfo() {
		return userContactInfo;
	}

	public void setUserContactInfo(String userContactInfo) {
		this.userContactInfo = userContactInfo;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(userID);
		// objects.add(password);
		objects.add(firstName);
		objects.add(userMiddleName);
		objects.add(userLastName);
		objects.add(sex);
		objects.add(userContactInfo);
		objects.add(roleName);
		return objects;
	}

	public static AdministratorDAO administratorDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getAdministratorDAO();
	}

	public static List<Administrator> all() throws SQLException {
		return administratorDAO().all();
	}

	public Administrator save() throws SQLException {
		return administratorDAO().insert(this);
	}

	public void update() throws SQLException {
		administratorDAO().updateAdministrator(this);
	}

	public void update_passwors() throws SQLException {
		administratorDAO().updatePass_word(this);
	}

	public Administrator getByUser(String username) throws SQLException {
		return administratorDAO().getByUser(username);
	}

	public void updateByUser() throws SQLException {
		administratorDAO().updateAdministratorByUser(this);
	}

	public static String delete(String userName) throws SQLException {
		return administratorDAO().deleteAdmin(userName);

	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
