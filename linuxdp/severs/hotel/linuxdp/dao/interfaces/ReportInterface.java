package hotel.linuxdp.dao.interfaces;

import hotel.linuxdp.java.model.Report;

import java.sql.SQLException;
import java.util.List;


public interface ReportInterface {

	List<Report> all() throws SQLException;

	Report insertReport(Report report) throws SQLException;

	Report updateReport(Report report) throws SQLException;

	int deleteReport(int object) throws SQLException;

}
