package hotel.linuxdp.main;

import hotel.linuxdp.ui.UserFrame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainUser {

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				UserFrame frame = new UserFrame();
				frame.setSize(1200, 700);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
