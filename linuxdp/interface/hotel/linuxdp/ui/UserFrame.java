package hotel.linuxdp.ui;

import hotel.linuxdp.ui.TabbedPaneUI.BarTabbedPaneUI;
import hotel.linuxdp.ui.user.PanelLeftUser;
import hotel.linuxdp.util.IconButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;
import Wecome.IndexWebcome;

public class UserFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel panelRight;
	private String username;
	IndexWebcome webcome = new IndexWebcome();
	private JLabel lbluserName, lblchangepass;
	
	public UserFrame() {
		setLayout(new BorderLayout());
		add(panelToolBar(), BorderLayout.NORTH);
		add(new PanelLeftUser(), BorderLayout.WEST);
		add(panelRight());
		add(new Footer(), BorderLayout.SOUTH);
		if (panelRight.getComponents().length == 0) {
			callJPanel(webcome);
		}
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

}
