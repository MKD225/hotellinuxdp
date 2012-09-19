package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.CustomerInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerInterface {

	private static final String ALL = "SELECT CustomerID,FirstName,MiddleName,LastName,BirthDay=CONVERT(varchar,Birthday,101),Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone,WorkPhone ,Email,LastVisited FROM Customer";
	private static final String INSERT = "INSERT INTO Customer(FirstName,MiddleName,LastName,BirthDay,Sex,AddStreet,AddCity,AddState,AddZip,AddCountry,HomePhone ,WorkPhone,Email,LastVisited)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Customer SET FirstName = ?,MiddleName = ?,LastName = ?,BirthDay = ?,Sex = ?,AddStreet = ?,AddCity = ?,AddState = ?,AddZip = ?,AddCountry = ?,HomePhone = ?,WorkPhone = ?,Email = ?,LastVisited = ? WHERE CustomerID =?";

	@Override
	public List<Customer> all() throws SQLException {
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(ALL);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			listCustomers.add(createCustomer(rSet));
		}
		return listCustomers;
	}

	@Override
	public Customer inserCustomer(Customer customer) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, customer.getFirstName());
		pstmt.setString(2, customer.getMiddleName());
		pstmt.setString(3, customer.getLastName());
		pstmt.setString(4, customer.getBirthDay());
		pstmt.setString(5, customer.getSex());
		pstmt.setString(6, customer.getAddStreet());
		pstmt.setString(7, customer.getAddCity());
		pstmt.setString(8, customer.getAddState());
		pstmt.setString(9, customer.getAddZip());
		pstmt.setString(10, customer.getAddCountry());
		pstmt.setString(11, customer.getHomePhone());
		pstmt.setString(12, customer.getWorkPhone());
		pstmt.setString(13, customer.getEmail());
		pstmt.setString(14, customer.getLastName());

		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		customer.setCustomerID(integer);
		pstmt.close();
		connection.close();
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getMiddleName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getBirthDay());
			pstmt.setString(5, customer.getSex());
			pstmt.setString(6, customer.getAddStreet());
			pstmt.setString(7, customer.getAddCity());
			pstmt.setString(8, customer.getAddState());
			pstmt.setString(9, customer.getAddZip());
			pstmt.setString(10, customer.getAddCountry());
			pstmt.setString(11, customer.getHomePhone());
			pstmt.setString(12, customer.getWorkPhone());
			pstmt.setString(13, customer.getEmail());
			pstmt.setString(14, customer.getLastVisited());
			pstmt.setInt(15, customer.getCustomerID());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public int deleteCustomer(int object) throws SQLException {
		String DELETE_Cus = "DELETE FROM Customer WHERE CustomerID = "+ object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Cus);
		statement.close();
		return object;
	}

	private Customer createCustomer(ResultSet rSet) throws SQLException {
		Customer customer = new Customer(rSet.getString("FirstName"),
				rSet.getString("MiddleName"), rSet.getString("LastName"),
				rSet.getString("BirthDay"), rSet.getString("Sex"),
				rSet.getString("AddStreet"), rSet.getString("AddCity"),
				rSet.getString("AddState"), rSet.getString("AddZip"),
				rSet.getString("AddCountry"), rSet.getString("HomePhone"),
				rSet.getString("WorkPhone"), rSet.getString("Email"),
				rSet.getString("LastVisited"));
		customer.setCustomerID(rSet.getInt("CustomerID"));
		return customer;
	}
}
