package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.PaymentType;

import java.sql.SQLException;
import java.util.List;


public interface PaymentTypeInteface {
	List<PaymentType> all() throws SQLException;

	PaymentType insertPaymentType(PaymentType paymentType) throws SQLException;

	PaymentType updatePaymentType(PaymentType paymentType) throws SQLException;

	int deletePaymentType(int object) throws SQLException;
}
