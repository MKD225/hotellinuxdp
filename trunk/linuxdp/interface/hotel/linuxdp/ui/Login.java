package hotel.linuxdp.ui;

import hotel.linuxdp.java.controller.RolesController;
import hotel.linuxdp.java.daoConfig.DaoFactory;
import hotel.linuxdp.java.model.Roles;
import hotel.linuxdp.util.IconButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

	public Login() {
		setUndecorated(true);
		setLayout(new BorderLayout());
		add(new UserInputsPanel());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("rawtypes")
	public class UserInputsPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private Color startColor = new Color(238, 238, 238);
		private Color endColor = new Color(255, 255, 255);

		GeneralPath path;
		Color accentColor = new Color(0x80ffffff);
		Color textColor = new Color(0x0f0f0f);
		String title = "Login User";
		private JLabel lblUserName, lblPassWord;
		private JTextField txtUserName;
		private JPasswordField txtPassWord;
		private JButton btnLogin, btnExit;
		private JComboBox boxRole;
		private Hashtable hashtRole = new Hashtable();

		public UserInputsPanel() {
			lblUserName = new JLabel("UserName");
			lblPassWord = new JLabel("PassWord");
			txtPassWord = new JPasswordField();
			txtUserName = new JTextField();
			boxRole = new JComboBox();
			boxRole.addItem("-choose-");
			btnLogin = new JButton("Login");
			btnLogin.setIcon(new IconButton().logIcon());
			btnExit = new JButton("Exit");
			btnExit.setIcon(new IconButton().ExitIcon());
			setLayout(new MigLayout("insets 0", "[grow]"));

			btnExit.addActionListener(this);
			btnLogin.addActionListener(this);
			add(lblUserName, "right,gap top 70");
			add(txtUserName, "height 22,width 300,span");

			add(lblPassWord, "right,gap top 20");
			add(txtPassWord, "height 22,width 300,span");

			add(new JLabel("Role"), "right");
			add(boxRole, "height 22,width 300,span");

			add(new JLabel(), "right");
			add(btnLogin, "gap top 10,gap bottom 10,split 2,right");
			add(btnExit, "width 80");
			addRole();
		}

		@SuppressWarnings({ "deprecation" })
		private void Login() {
			if (getTxtUserName().getText().equals("")) {
				JOptionPane.showMessageDialog(this, "username !");
				getTxtUserName().requestFocus();
				return;
			}
			if (getTxtPassWord().getText().equals("")) {
				JOptionPane.showMessageDialog(this, "password !");
				getTxtUserName().requestFocus();
				return;
			}
			if (boxRole.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "choose is null !");
			} else {
				try {
					Connection connection = DaoFactory.getMysql()
							.openConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet;
					Integer role = (Integer) hashtRole.get(boxRole
							.getSelectedItem().toString().trim());
					resultSet = statement
							.executeQuery("SELECT * FROM Administrator where UserID='"
									+ getTxtUserName().getText()
									+ "' and _Password='"
									+ getTxtPassWord().getText()
									+ "' and  RoleID='" + role + "'");
					if (resultSet.next()) {
						if (role == 1) {
							JOptionPane.showMessageDialog(null,"Login success !");
							Login.this.dispose();
							MainFrame frame = new MainFrame();
							frame.setUsername(txtUserName.getText().trim());
							frame.setSize(1200, 700);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
						}
						if (role != 1) {
							JOptionPane.showMessageDialog(null, "log success !");
							Login.this.dispose();
							UserFrame frame = new UserFrame();
							frame.setUsername(txtUserName.getText().trim());
							frame.setSize(1200, 700);
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(this,
								" User or pass invalidate !");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "login fail");
				}
			}
		}

		@SuppressWarnings("unchecked")
		private void addRole() {
			try {
				for (Roles roles : RolesController.roleController.allRoles()) {
					boxRole.addItem(roles.getRoleName());
					hashtRole.put(roles.getRoleName(), roles.getRoleID());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public UserInputsPanel(Color color1, Color color2) {
			super();
			startColor = color1;
			endColor = color2;
		}

		@SuppressWarnings("unused")
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
			int h = getHeight();
			int w = getWidth();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			/**
			 * Top Polygon
			 */
			GeneralPath path = new GeneralPath();
			path.moveTo(70, 0);
			path.lineTo(8, 0);
			path.quadTo(0, 0, 0, 7);
			path.lineTo(0, 55);
			path.lineTo(getWidth() - 1, 55);
			path.lineTo(getWidth() - 1, 7);
			path.quadTo(getWidth() - 1, 0, getWidth() - 8, 0);
			path.lineTo(30, 0);

			Rectangle bounds1 = path.getBounds();
			GradientPaint painter = new GradientPaint(0, path.getBounds().y,
					true ? endColor : startColor, 0, bounds1.y + bounds1.height
							- 1, true ? startColor : endColor);
			g2d.setPaint(painter);
			g2d.fill(path);
			Rectangle rectangle = new Rectangle(0, 40, getWidth(), 20);
			g2d.fill(rectangle);
			g2d.setColor(new Color(128, 128, 128));
			g2d.draw(path);

			/**
			 * Middle Rectangle
			 */
			g2d.setPaint(new Color(240, 240, 240));
			g2d.fillRect(0, 31, getWidth() - 1, h - 50);
			g2d.setColor(new Color(128, 128, 128));
			g2d.drawLine(12, 0, getWidth() - 10, 0);
			g2d.drawRect(0, 30, getWidth() - 1, h - 50);

			/**
			 * Bottom Polygon
			 */
			h = h - 30;
			path = new GeneralPath();
			path.moveTo(0, h);
			path.lineTo(0, h + 22);
			path.quadTo(0, h + 29, 8, h + 29);
			path.lineTo(getWidth() - 8, h + 29);
			path.quadTo(getWidth() - 1, h + 29, getWidth() - 1, h + 22);
			path.lineTo(getWidth() - 1, h);
			g2d.setColor(Color.GRAY);
			startColor = new Color(192, 192, 192);
			endColor = new Color(238, 238, 238);
			bounds1 = path.getBounds();
			painter = new GradientPaint(0, path.getBounds().y, endColor, 0,
					bounds1.y + bounds1.height - 1, startColor);
			g2d.setPaint(painter);
			g2d.fill(path);
			g2d.setColor(new Color(128, 128, 128));
			g2d.draw(path);
			g2d.setColor(new Color(128, 128, 128));
			g2d.drawLine(0, h - 1, getWidth() - 1, h - 1);

			/**
			 * Title
			 */
			g2d.setFont(g2d.getFont().deriveFont(Font.BOLD)
					.deriveFont((float) g2d.getFont().getSize() + 1));

			g2d.setColor(accentColor);
			g2d.drawString(title, 40, 22);
			g2d.setColor(textColor);
			g2d.drawString(title, 40, 21);

			/**
			 * image
			 */
			g2d.drawImage(new ImageIcon("images/images/log.png").getImage(),
					10, 0, null, null);
		}

		/**
		 * This method sets the Actual Background Color of the Button
		 */
		public void setStartColor(Color color) {
			startColor = color;
		}

		/**
		 * This method sets the Pressed Color of the Button
		 */
		public void setEndColor(Color pressedColor) {
			endColor = pressedColor;
		}

		/**
		 * @return Starting Color of the Button
		 */
		public Color getStartColor() {
			return startColor;
		}

		/**
		 * @return Ending Color of the Button
		 */
		public Color getEndColor() {
			return endColor;
		}

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnExit) {
				System.exit(0);
			}
			if (e.getSource() == getBtnLogin()) {
				Login();
			}
		}

		public JTextField getTxtUserName() {
			return txtUserName;
		}

		public void setTxtUserName(JTextField txtUserName) {
			this.txtUserName = txtUserName;
		}

		public JPasswordField getTxtPassWord() {
			return txtPassWord;
		}

		public void setTxtPassWord(JPasswordField txtPassWord) {
			this.txtPassWord = txtPassWord;
		}

		public JButton getBtnLogin() {
			return btnLogin;
		}

		public void setBtnLogin(JButton btnLogin) {
			this.btnLogin = btnLogin;
		}
	}

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
				Login frame = new Login();
				frame.setSize(400, 250);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
