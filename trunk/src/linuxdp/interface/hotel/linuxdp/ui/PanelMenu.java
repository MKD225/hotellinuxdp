package hotel.linuxdp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PanelMenu extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public PanelMenu() {
		JMenu menuFile = new JMenu("File");
		JMenu menuTools = new JMenu("Tools");
		JMenu menuHelp = new JMenu("Search");
		JMenu menuSearch = new JMenu("Help");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(menuFile);
		menuFile.add(menuItemExit);
		add(menuTools);
		add(menuHelp);
		add(menuSearch);
	}
}
