package hotel.linuxdp.java.daoConfig;

import hotel.linuxdp.java.dao.concreate.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JComboBox;

/**
 * 
 * @author linuxdp
 */
public class SqlConnection extends DaoFactory {

	ReadFileConfig read = new ReadFileConfig();
	public String[] data = read.ArrStr();
	private String host = "";
	private String port = "";
	private String userName = "";
	private String passWord = "";
	private String database = "";
	private Connection cn = null;
	public boolean flag = false;

	void readData() {
		for (int i = 0; i < data.length; i++) {
			host = data[0];
			port = data[1];
			userName = data[2];
			passWord = data[3];
			database = data[4];
		}
	}

	protected void testDriver() {
		try {
			readData();
			if (data.length == 0) {
				return;
			}
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (java.lang.ClassNotFoundException e) {
		}
	}

	public Connection openConnection() {
		StringBuilder sb = new StringBuilder();
		if (this.cn == null) {
			testDriver();
			// sb.append("jdbc:mysql://localhost:3306/");
			sb.append("jdbc:sqlserver://");
			sb.append(host);
			sb.append(":");
			sb.append(port);
			sb.append(";databaseName=");
			sb.append(this.database);
			try {
				this.cn = DriverManager.getConnection(sb.toString(),
						this.userName, this.passWord);
				flag = true;
			} catch (java.sql.SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return this.cn;
	}

	public int GetIndexCombobox(JComboBox ComboBox_name) {
		Integer index;
		String str = ComboBox_name.getSelectedItem().toString();
		String[] arr = str.split("\\.");
		index = Integer.parseInt(arr[0]);
		return index;
	}

	@Override
	public RolesDAO getRolesDAO() {
		return new RolesDAO();
	}

	@Override
	public AdministratorDAO getAdministratorDAO() {
		return new AdministratorDAO();
	}

	@Override
	public BillItemDAO getBillItemDAO() {
		return new BillItemDAO();
	}

	@Override
	public BillItemTypeDAO getBillItemTypeDAO() {
		return new BillItemTypeDAO();
	}

	@Override
	public BillsDAO getBillsDAO() {
		return new BillsDAO();
	}

	@Override
	public CheckInDAO getCheckInDAO() {
		return new CheckInDAO();
	}

	@Override
	public CheckOutDAO getCheckOutDAO() {
		return new CheckOutDAO();
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}

	@Override
	public DiscountDAO getDiscountDAO() {
		return new DiscountDAO();
	}

	@Override
	public FoodsDAO getFoodsDAO() {
		return new FoodsDAO();
	}

	@Override
	public PaymentDAO getPaymentDAO() {
		return new PaymentDAO();
	}

	@Override
	public PaymentTypeDAO getPaymentTypeDAO() {
		return new PaymentTypeDAO();
	}

	@Override
	public ReportDAO getReportDAO() {
		return new ReportDAO();
	}

	@Override
	public ReservationDAO getReservationDAO() {
		return new ReservationDAO();
	}

	@Override
	public RoomsDAO getRoomsDAO() {
		return new RoomsDAO();
	}

	@Override
	public RoomStatusDAO getRoomStatusDAO() {
		return new RoomStatusDAO();
	}

	@Override
	public RoomTypeDAO getRoomTypeDAO() {
		return new RoomTypeDAO();
	}
}