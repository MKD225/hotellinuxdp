package hotel.linuxdp.ui;

import hotel.linuxdp.ui.TabbedPaneUI.BarTabbedPaneUI;
import javax.swing.*;
public class PanelToolBar extends JTabbedPane {
	private static final long serialVersionUID = 1L;
	private JLabel lblUser;
	public static PanelToolBar bar = new PanelToolBar();

	public PanelToolBar() {
		setUI(new BarTabbedPaneUI());
		add("sss",new JLabel("dd"));
	}

	private String est;

	public static boolean rootPaneCheckingEnabled = true;

	public String getEst() {
		return est;
	}

	public void setEst(String est) {
		this.est = est;
		System.out.println(" ss" + est);
		lblUser.setText(this.est);
	}
}
