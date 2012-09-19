package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.AdministratorInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AdministratorDAO implements AdministratorInterface {
	private static final String ALL = "SELECT UserID,FirstName,UserMiddleName,UserLastName,Sex,UserContactInfo,Roles.RoleID,Roles.RoleName FROM Administrator,Roles WHERE Administrator.RoleID = Roles.RoleID";
	private static final String INSERT = "INSERT INTO Administrator(UserID ,_Password,FirstName,UserMiddleName,UserLastName,Sex,UserContactInfo,RoleID) VALUES(?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Administrator SET FirstName = ?,UserMiddleName = ?,UserLastName = ?,Sex = ?,UserContactInfo = ? ,RoleID = ? WHERE UserID =?";
	private static final String UPDATEbyUSER = "UPDATE Administrator SET FirstName = ?,UserMiddleName = ?,UserLastName = ?,Sex = ?,UserContactInfo = ?  WHERE UserID =?";
	private static final String updatePass = "UPDATE Administrator SET _Password = ? WHERE UserID = ?";

	@Override
	public List<Administrator> all() throws SQLException {
		ArrayList<Administrator> listAdministrators = new ArrayList<Administrator>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listAdministrators.add(createAdmin(rSet));
		}
		return listAdministrators;
	}

	@Override
	public Administrator getByUser(String username) throws SQLException {
		String AllByUserName = "SELECT UserID,_Password,FirstName,UserMiddleName,UserLastName,Sex,UserContactInfo,Roles.RoleID,Roles.RoleName FROM Administrator,Roles WHERE Administrator.RoleID = Roles.RoleID and UserID='"
				+ username + "'";
		Administrator listAdministrators = new Administrator();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(AllByUserName);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listAdministrators = new Administrator(rSet.getString("UserID"),
					rSet.getString("_Password"), rSet.getString("FirstName"),
					rSet.getString("UserMiddleName"),
					rSet.getString("UserLastName"), rSet.getString("Sex"),
					rSet.getString("UserContactInfo"), rSet.getInt("RoleID"));
		}
		return listAdministrators;
	}

	@Override
	public Administrator insert(Administrator administrator)
			throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, administrator.getUserID());
		pstmt.setString(2, administrator.getPassword());
		pstmt.setString(3, administrator.getFirstName());
		pstmt.setString(4, administrator.getUserMiddleName());
		pstmt.setString(5, administrator.getUserLastName());
		pstmt.setString(6, administrator.getSex());
		pstmt.setString(7, administrator.getUserContactInfo());
		pstmt.setInt(8, administrator.getRoleID());
		pstmt.executeUpdate();
		ResultSet rset = pstmt.getGeneratedKeys();
		rset.next();
		pstmt.close();
		return administrator;
	}

	@Override
	public Administrator updateAdministrator(Administrator administrator)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, administrator.getFirstName());
			pstmt.setString(2, administrator.getUserMiddleName());
			pstmt.setString(3, administrator.getUserLastName());
			pstmt.setString(4, administrator.getSex());
			pstmt.setString(5, administrator.getUserContactInfo());
			pstmt.setInt(6, administrator.getRoleID());
			pstmt.setString(7, administrator.getUserID());

			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return administrator;
	}

	@Override
	public Administrator updateAdministratorByUser(Administrator administrator)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATEbyUSER);
			pstmt.setString(1, administrator.getFirstName());
			pstmt.setString(2, administrator.getUserMiddleName());
			pstmt.setString(3, administrator.getUserLastName());
			pstmt.setString(4, administrator.getSex());
			pstmt.setString(5, administrator.getUserContactInfo());
			pstmt.setString(6, administrator.getUserID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return administrator;
	}

	@Override
	public Administrator updatePass_word(Administrator administrator)
			throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(updatePass);
			pstmt.setString(1, administrator.getPassword());
			pstmt.setString(2, administrator.getUserID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return administrator;
	}

	@Override
	public String deleteAdmin(String userName) throws SQLException {
		String DELETE_ADMIN = "DELETE FROM Administrator WHERE UserID ='"
				+ userName + "'";
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_ADMIN);
		statement.close();
		return userName;
	}

	private Administrator createAdmin(ResultSet rSet) throws SQLException {
		Administrator administrator = new Administrator(
				rSet.getString("UserID"), rSet.getString("FirstName"),
				rSet.getString("UserMiddleName"),
				rSet.getString("UserLastName"), rSet.getString("Sex"),
				rSet.getString("UserContactInfo"), rSet.getInt("RoleID"),
				rSet.getString("RoleName"));
		administrator.setUserID(rSet.getString("UserID"));
		return administrator;
	}

	@Override
	public List<Administrator> allbyUsrName(String username)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
