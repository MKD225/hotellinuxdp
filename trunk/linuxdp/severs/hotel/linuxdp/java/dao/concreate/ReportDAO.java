package hotel.linuxdp.java.dao.concreate;

import hotel.linuxdp.dao.interfaces.ReportInterface;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ReportDAO implements ReportInterface {
	private static final String ALL = "SELECT * FROM Report";
	private static final String INSERT = "INSERT INTO Report(ReportType,ReportName,ReportDesc)VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE Report SET ReportType = ?,ReportName = ?,ReportDesc = ? WHERE ReportID =?";

	@Override
	public List<Report> all() throws SQLException {
		ArrayList<Report> listReports = new ArrayList<Report>();
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement statement = connection.prepareStatement(ALL);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			listReports.add(createReport(resultSet));
		}
		return listReports;
	}

	@Override
	public Report insertReport(Report report) throws SQLException {
		Connection connection = DaoFactory.getMysql().openConnection();
		PreparedStatement pstmt = connection.prepareStatement(INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, report.getReportType());
		pstmt.setString(2, report.getReportName());
		pstmt.setString(3, report.getReportDesc());
		pstmt.executeUpdate();
		ResultSet rSet = pstmt.getGeneratedKeys();
		rSet.next();
		Integer integer = rSet.getInt(1);
		report.setReportID(integer);
		pstmt.close();
		connection.close();
		return report;
	}

	@Override
	public Report updateReport(Report report) throws SQLException {
		try {
			Connection connection = DaoFactory.getMysql().openConnection();
			PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			pstmt.setString(1, report.getReportType());
			pstmt.setString(2, report.getReportName());
			pstmt.setString(3, report.getReportDesc());
			pstmt.setInt(4, report.getReportID());
			pstmt.executeUpdate();
			JOptionPane.showConfirmDialog(null, "Update to succeed !");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return report;
	}

	@Override
	public int deleteReport(int object) throws SQLException {
		String DELETE_Report = "DELETE FROM Report WHERE ReportID = " + object;
		Connection connection = DaoFactory.getMysql().openConnection();
		Statement statement = connection.createStatement();
		statement.executeUpdate(DELETE_Report);
		statement.close();
		return object;
	}

	public Report createReport(ResultSet resultSet) throws SQLException {
		Report report = new Report(resultSet.getString("ReportType"),
				resultSet.getString("ReportName"),
				resultSet.getString("ReportDesc"));
		report.setReportID(resultSet.getInt("ReportID"));
		return report;
	}
}
