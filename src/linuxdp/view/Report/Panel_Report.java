package Report;

import hotel.linuxdp.java.controller.ReportController;
import hotel.linuxdp.java.model.Report;
import hotel.linuxdp.java.view.events.EventAll;
import hotel.linuxdp.java.view.listeners.ReportListener;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;

/**
 * @author linuxdp
 */
@SuppressWarnings("rawtypes")
public class Panel_Report extends JPanel implements ActionListener,
		ReportListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable jTable;
	private DefaultTableModel model;
	JScrollPane scrollPane;
	protected boolean autoscrolls = false;
	JButton buttonInsert, buttonUpdate, buttonDelete, buttonRefresh;
	private static JPanel titleData;
	private static JTextField txtReportType, txtReportName, txtID;
	private JTextArea txtReportDesc;
	Vector tableRecords = new Vector();
	Vector tableTitle = new Vector();
	JSeparator separator = new JSeparator();
	private Integer ID;

	public Panel_Report() {
		initComponents();
		all();
		buttonInsert.addActionListener(this);
		buttonUpdate.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonRefresh.addActionListener(this);
		ReportController.reportController.addDiscountListener(this);
		jTable.addMouseListener(this);
		buttonDelete.setEnabled(false);
		buttonUpdate.setEnabled(false);
	}

	protected void initComponents() {
		MigLayout layout = new MigLayout();
		setLayout(layout);
		add(getTable(), "grow, span,gap top 1,width max");
		add(createForm(), "grow,span,width max");
		add(separator, "width max, hmin 5,span");
		add(createButtons(), "dock south,right");
	}

	public JPanel getTable() {
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		panelTable.add(table());
		return panelTable;
	}

	@SuppressWarnings("unchecked")
	private JComponent table() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		scrollPane = new JScrollPane();
		jTable = new JTable();
		tableTitle.add("ReportID");
		tableTitle.add("ReportType");
		tableTitle.add("ReportName");
		tableTitle.add("ReportDesc");

		model = new DefaultTableModel(tableRecords, tableTitle);
		jTable.setModel(model);
		jTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(jTable);
		panel.add(scrollPane);

		return panel;
	}

	private void all() {
		try {
			for (Report report : ReportController.reportController.all()) {
				model.insertRow(0, report.toArray());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private JComponent createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		titleData = new JPanel();
		titleData.setLayout(new MigLayout());
		titleData.setBorder(BorderFactory.createTitledBorder(""));

		titleData.add(new JLabel("ID:"), "right");
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setDisabledTextColor(new Color(250, 2, 2));
		titleData.add(txtID, "height 23,width 450,span");

		txtReportType = new JTextField();
		titleData.add(new JLabel("Report Type:"), "right");
		titleData.add(txtReportType, "height 23,width max,span");

		txtReportName = new JTextField();
		titleData.add(new JLabel("Report Name:"), "right");
		titleData.add(txtReportName, "height 23,width max,span");

		txtReportDesc = new JTextArea();
		titleData.add(new JLabel("Description:"), "right");
		titleData.add(txtReportDesc, "span, growx,hmin 180");

		panel.add(titleData, "span");
		return panel;
	}

	private JPanel createButtons() {
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new MigLayout("right", "[]"));
		buttonInsert = new JButton("New");
		buttonInsert.setIcon(new IconButton().insertIcon());
		panelButton.add(buttonInsert);

		buttonUpdate = new JButton("Update");
		buttonUpdate.setIcon(new IconButton().updateIcon());
		panelButton.add(buttonUpdate);

		buttonDelete = new JButton("Delete");
		buttonDelete.setIcon(new IconButton().deleteIcon());
		panelButton.add(buttonDelete);

		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setIcon(new IconButton().refreshIcon());
		panelButton.add(buttonRefresh);

		return panelButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonInsert) {
			try {
				Report report = new Report(txtReportType.getText(),
						txtReportName.getText(), txtReportDesc.getText());
				ReportController.reportController.save(report);
				JOptionPane.showMessageDialog(this, "ok");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonUpdate) {
			try {
				Report report = new Report(txtReportType.getText(),
						txtReportName.getText(), txtReportDesc.getText());
				report.setReportID(ID);
				ReportController.reportController.update(report);
				JOptionPane.showMessageDialog(this, "ok");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonDelete) {
			try {
				ReportController.reportController.delete(ID);
				JOptionPane.showMessageDialog(this, "ok");
				int c = model.getRowCount();
				for (int i = c - 1; i >= 0; i--) {
					model.removeRow(i);
					jTable.revalidate();
				}
				all();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (e.getSource() == buttonRefresh) {
			buttonInsert.setEnabled(true);
			buttonUpdate.setEnabled(false);
			buttonDelete.setEnabled(false);
			int c = model.getRowCount();
			for (int i = c - 1; i >= 0; i--) {
				model.removeRow(i);
				jTable.revalidate();
			}
			all();
		}
	}

	@Override
	public void roleadd(EventAll<Report> event) {
		model.insertRow(0, event.getSource().toArray());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int iDongDaChon = jTable.getSelectedRow();
		if (iDongDaChon == -1) {
			JOptionPane.showMessageDialog(this, "hay chon 1 row");
		} else {
			buttonUpdate.setEnabled(true);
			buttonDelete.setEnabled(true);
			buttonInsert.setEnabled(false);
			Vector vDongDaChon = (Vector) tableRecords.get(iDongDaChon);
			ID = Integer.parseInt(vDongDaChon.get(0).toString());
			String reportType = vDongDaChon.get(1).toString();
			String reportName = vDongDaChon.get(2).toString();
			String reportDesc = vDongDaChon.get(3).toString();

			txtReportType.setText(reportType);
			txtReportName.setText(reportName);
			txtReportDesc.setText(reportDesc);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
