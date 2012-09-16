package hotel.linuxdp.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Wecome.IndexWebcome;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel panelRight;
	IndexWebcome webcome = new IndexWebcome();

	public MainFrame() {
		setLayout(new BorderLayout());
		add(new PanelMenu(), BorderLayout.NORTH);
		add(new PanelLeft(), BorderLayout.WEST);
		add(panelRight());
		add(new Footer(),BorderLayout.SOUTH);
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
}
