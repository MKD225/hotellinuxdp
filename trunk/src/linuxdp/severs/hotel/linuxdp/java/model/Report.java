package hotel.linuxdp.java.model;

import hotel.linuxdp.java.dao.concreate.ReportDAO;
import hotel.linuxdp.java.daoConfig.DaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Report {
	private Integer ReportID;
	private String ReportType;
	private String ReportName;
	private String ReportDesc;

	public Report(String ReportType, String ReportName, String ReportDesc) {
		this.ReportType = ReportType;
		this.ReportName = ReportName;
		this.ReportDesc = ReportDesc;
	}

	public Vector<Object> toArray() {
		Vector<Object> vector = new Vector<Object>();

		vector.add(ReportID);
		vector.add(ReportType);
		vector.add(ReportName);
		vector.add(ReportDesc);

		return vector;
	}

	public static ReportDAO reportDAO() throws SQLException {
		DaoFactory daoFactory = DaoFactory.getMysql();
		return daoFactory.getReportDAO();
	}

	public static List<Report> all() throws SQLException {
		return reportDAO().all();
	}

	public Report save() throws SQLException {
		return reportDAO().insertReport(this);
	}

	public Report update() throws SQLException {
		return reportDAO().updateReport(this);
	}

	public static int delete(int object) throws SQLException {
		return reportDAO().deleteReport(object);
	}

	public Integer getReportID() {
		return ReportID;
	}

	public void setReportID(Integer reportID) {
		ReportID = reportID;
	}

	public String getReportType() {
		return ReportType;
	}

	public void setReportType(String reportType) {
		ReportType = reportType;
	}

	public String getReportName() {
		return ReportName;
	}

	public void setReportName(String reportName) {
		ReportName = reportName;
	}

	public String getReportDesc() {
		return ReportDesc;
	}

	public void setReportDesc(String reportDesc) {
		ReportDesc = reportDesc;
	}
}
