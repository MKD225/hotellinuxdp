package hotel.linuxdp.ui;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.ui.TabbedPaneUI.BarTabbedPaneUI;
import hotel.linuxdp.util.IconButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import Administrator.Admin_detail;
import Administrator.Update_PassWord;
import Wecome.IndexWebcome;

public class MainFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private static JPanel panelRight;
	private String username;
	IndexWebcome webcome = new IndexWebcome();
	private JLabel lbluserName, lblchangepass;
	Administrator administrator;

	public MainFrame() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		add(panelToolBar(), BorderLayout.NORTH);
		add(new PanelLeft(), BorderLayout.WEST);
		add(panelRight());
		add(new Footer(), BorderLayout.SOUTH);
		if (panelRight.getComponents().length == 0) {
			callJPanel(webcome);
		}
		lbluserName.addMouseListener(this);
		lblchangepass.addMouseListener(this);
	}

	public JComponent panelRight() {
		panelRight = new JPanel();
		panelRight.setBackground(new Color(0x203451));
		panelRight.setLayout(new BorderLayout());
		return panelRight;

	}

	public static void callJPanel(JPanel jpanel) {
		panelRight.removeAll();
		jpanel.setVisible(false);
		panelRight.add(jpanel);
		jpanel.setVisible(true);
	}

	private JComponent panelToolBar() {
		JTabbedPane pane = new JTabbedPane();
		pane.setUI(new BarTabbedPaneUI());
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		lbluserName = new JLabel("");
		lbluserName
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lbluserName.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		lbluserName.setIcon(new IconButton().user_adminIcon());
		lblchangepass = new JLabel("ChangePassWord");
		lblchangepass
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lblchangepass
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		lblchangepass.setIcon(new IconButton().change_ekyIcon());
		panel.add(lbluserName);
		panel.add(lblchangepass);

		pane.add("     Home     ", panel);
		pane.add("     Help     ", new JPanel());

		return pane;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		lbluserName.setText(username);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == lbluserName) {
			String userID = lbluserName.getText().toString().trim();
			Admin_detail admin_detail = new Admin_detail();
			try {
				Administrator administrator = AdministratorController.administratorController
						.getbyuser(userID);
				admin_detail.setUsername(administrator.getUserID());
				admin_detail.setFirstname(administrator.getFirstName());
				admin_detail.setLastname(administrator.getUserLastName());
				admin_detail.setMiddename(administrator.getUserMiddleName());
				admin_detail.setSex(administrator.getSex());
				admin_detail.setContactInfo(administrator.getUserContactInfo());
				admin_detail.setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == lblchangepass) {
			String userID = lbluserName.getText().toString().trim();
			Update_PassWord passWord = new Update_PassWord();
			try {
				Administrator administrator = AdministratorController.administratorController.getbyuser(userID);
				passWord.setUserID(administrator.getUserID());
				passWord.setVisible(true);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

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
