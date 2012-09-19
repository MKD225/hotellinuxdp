package hotel.linuxdp.java.daoConfig;

import hotel.linuxdp.java.dao.concreate.AdministratorDAO;
import hotel.linuxdp.java.dao.concreate.BillItemDAO;
import hotel.linuxdp.java.dao.concreate.BillItemTypeDAO;
import hotel.linuxdp.java.dao.concreate.BillsDAO;
import hotel.linuxdp.java.dao.concreate.CheckInDAO;
import hotel.linuxdp.java.dao.concreate.CheckOutDAO;
import hotel.linuxdp.java.dao.concreate.CustomerDAO;
import hotel.linuxdp.java.dao.concreate.DiscountDAO;
import hotel.linuxdp.java.dao.concreate.FoodsDAO;
import hotel.linuxdp.java.dao.concreate.PaymentDAO;
import hotel.linuxdp.java.dao.concreate.PaymentTypeDAO;
import hotel.linuxdp.java.dao.concreate.ReportDAO;
import hotel.linuxdp.java.dao.concreate.ReservationDAO;
import hotel.linuxdp.java.dao.concreate.RolesDAO;
import hotel.linuxdp.java.dao.concreate.RoomStatusDAO;
import hotel.linuxdp.java.dao.concreate.RoomTypeDAO;
import hotel.linuxdp.java.dao.concreate.RoomsDAO;

import java.sql.Connection;


/**
 * 
 * @author linuxdp
 * 
 */
public abstract class DaoFactory {
	public static DaoFactory getMysql() {
		return new SqlConnection();
	}

	public Connection openConnection() {
		return new SqlConnection().openConnection();
	}

	public abstract RolesDAO getRolesDAO();

	public abstract AdministratorDAO getAdministratorDAO();

	public abstract BillItemDAO getBillItemDAO();

	public abstract BillItemTypeDAO getBillItemTypeDAO();

	public abstract BillsDAO getBillsDAO();

	public abstract CheckInDAO getCheckInDAO();

	public abstract CheckOutDAO getCheckOutDAO();

	public abstract CustomerDAO getCustomerDAO();

	public abstract DiscountDAO getDiscountDAO();

	public abstract FoodsDAO getFoodsDAO();

	public abstract PaymentDAO getPaymentDAO();

	public abstract PaymentTypeDAO getPaymentTypeDAO();

	public abstract ReportDAO getReportDAO();

	public abstract ReservationDAO getReservationDAO();

	public abstract RoomsDAO getRoomsDAO();

	public abstract RoomStatusDAO getRoomStatusDAO();

	public abstract RoomTypeDAO getRoomTypeDAO();
}
