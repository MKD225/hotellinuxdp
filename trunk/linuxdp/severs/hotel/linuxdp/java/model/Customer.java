/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.CustomerDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author linuxdp
 */
public class Customer {

	private Integer customerID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String sex;
	private String addStreet;
	private String addCity;
	private String addState;
	private String addZip;
	private String addCountry;
	private String homePhone;
	private String workPhone;
	private String Email;
	private String LastVisited;
	private String birthDay;


	public Customer(String FirstName, String MiddleName, String lastName,String birthDay,
			String sex, String AddStreet, String AddCity, String AddState,
			String AddZip, String AddCountry, String HomePhone,
			String WorkPhone, String Email, String LastVisited) {

		this.firstName = FirstName;
		this.middleName = MiddleName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.sex = sex;
		this.addStreet = AddStreet;
		this.addCity = AddCity;
		this.addState = AddState;
		this.addZip = AddZip;
		this.addCountry = AddCountry;
		this.homePhone = HomePhone;
		this.workPhone = WorkPhone;
		this.Email = Email;
		this.LastVisited = LastVisited;
	}

	public Vector<Object> toArray() {
		Vector<Object> objects = new Vector<Object>();
		objects.add(customerID);
		objects.add(firstName);
		objects.add(middleName);
		objects.add(lastName);
		objects.add(birthDay);
		objects.add(sex);
		objects.add(addStreet);
		objects.add(addCity);
		objects.add(addState);
		objects.add(addZip);
		objects.add(addCountry);
		objects.add(homePhone);
		objects.add(workPhone);
		objects.add(Email);
		objects.add(LastVisited);

		return objects;
	}

	public static CustomerDAO customerDAO() {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getCustomerDAO();
	}

	public static List<Customer> all() throws SQLException {
		return customerDAO().all();
	}

	public Customer save() throws SQLException {
		return customerDAO().inserCustomer(this);
	}

	public Customer update() throws SQLException {
		return customerDAO().updateCustomer(this);
	}

	public static int delete(int object) throws SQLException {
		return customerDAO().deleteCustomer(object);
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the addStreet
	 */
	public String getAddStreet() {
		return addStreet;
	}

	/**
	 * @param addStreet
	 *            the addStreet to set
	 */
	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}

	/**
	 * @return the addCity
	 */
	public String getAddCity() {
		return addCity;
	}

	/**
	 * @param addCity
	 *            the addCity to set
	 */
	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	/**
	 * @return the addState
	 */
	public String getAddState() {
		return addState;
	}

	/**
	 * @param addState
	 *            the addState to set
	 */
	public void setAddState(String addState) {
		this.addState = addState;
	}

	/**
	 * @return the addZip
	 */
	public String getAddZip() {
		return addZip;
	}

	/**
	 * @param addZip
	 *            the addZip to set
	 */
	public void setAddZip(String addZip) {
		this.addZip = addZip;
	}

	/**
	 * @return the addCountry
	 */
	public String getAddCountry() {
		return addCountry;
	}

	/**
	 * @param addCountry
	 *            the addCountry to set
	 */
	public void setAddCountry(String addCountry) {
		this.addCountry = addCountry;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone
	 *            the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @param workPhone
	 *            the workPhone to set
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * @return the Email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @param Email
	 *            the Email to set
	 */
	public void setEmail(String Email) {
		this.Email = Email;
	}

	/**
	 * @return the LastVisited
	 */
	public String getLastVisited() {
		return LastVisited;
	}

	/**
	 * @param LastVisited
	 *            the LastVisited to set
	 */
	public void setLastVisited(String LastVisited) {
		this.LastVisited = LastVisited;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
}
