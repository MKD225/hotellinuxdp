package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Payment;
import java.sql.SQLException;
import java.util.List;

public interface PaymentInteface {
	List<Payment> all() throws SQLException;

	Payment insertPayment(Payment payment) throws SQLException;

	Payment updatePayment(Payment payment) throws SQLException;

	int deleteReport(int object) throws SQLException;
}
