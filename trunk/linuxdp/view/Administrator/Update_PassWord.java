package Administrator;

import hotel.linuxdp.java.controller.AdministratorController;
import hotel.linuxdp.java.model.Administrator;
import hotel.linuxdp.util.IconButton;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

public class Update_PassWord extends JFrame {
	private static final long serialVersionUID = 1L;
	public String userID;
	private String password;
	JLabel lbl = new JLabel();

	public Update_PassWord() {
		setSize(420, 280);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		lbl = new JLabel();
		add(lbl, BorderLayout.CENTER);
		add(new GradientPanel());
	}

	GradientPanel panel = new GradientPanel();

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
		panel.setusename(userID);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public class GradientPanel extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		public static final int VERTICAL = 0;
		public static final int HORIZONTAL = 1;
		public static final int VERTICAL_RAISED = 2;
		public static final int HORIZONTAL_RAISED = 3;
		private int outerRoundRectSize = 10;
		private int innerRoundRectSize = 8;
		private JPasswordField txtoldPass, txtnewPass, txtconfigPass;
		private JTextField txtpassword;
		private JLabel lbltitles;
		private JButton btnUpdate, btnCanel;
		private String usename;

		public GradientPanel() {
			setLayout(new MigLayout());
			lbltitles = new JLabel("");
			lbltitles.setFont(new Font("Tahoma", 0, 20));
			lbltitles.setForeground(new Color(51, 51, 255));
			txtoldPass = new JPasswordField();
			txtnewPass = new JPasswordField();
			txtconfigPass = new JPasswordField();
			btnUpdate = new JButton("Update");
			btnUpdate.setIcon(new IconButton().updateIcon());
			btnCanel = new JButton("Cancel");
			btnCanel.setIcon(new IconButton().deleteIcon());
			txtpassword = new  JTextField();
			
			add(lbltitles, "top,center,span");
			add(txtpassword, "span,width max");
			
			add(new JLabel("Old PassWord"), "right,gap top 20");
			add(txtoldPass, "width max,span");

			add(new JLabel("New Pass PassWord"), "right,gap top 20");
			add(txtnewPass, "width max,span");

			add(new JLabel("Confirm New Password:"), "right,gap top 20");
			add(txtconfigPass, "width max,span");
			add(new JLabel(), "grow,span");

			add(new JLabel(), "right");
			add(btnUpdate, "gap top 20,split 2,right");
			add(btnCanel);
			btnUpdate.addActionListener(this);
			btnCanel.addActionListener(this);
		}

		public String getusename() {
			return usename;
		}

		public void setusename(String usename) {
			this.usename = usename;
			lbltitles.setText(usename);
			System.out.println("tim usename + " + usename);
	
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnUpdate) {
				try {
					Administrator administrator = AdministratorController.administratorController.getbyuser(usename);
						if (txtoldPass.equals(administrator.getPassword())) {
							System.out.println("get pass word  + "+ administrator.getPassword());
						}
						System.out.println("tim firstname+ "+ administrator.getFirstName());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == btnCanel) {
				System.exit(0);
			}
		}

		private Color startColor = new Color(255, 255, 255);
		private Color endColor = new Color(82, 82, 82);
		private int direction = VERTICAL;

		public GradientPanel(Color aStart, Color aEnd) {
			super();
			startColor = aStart;
			endColor = aEnd;
		}

		public GradientPanel(Color aStart, Color aEnd, int aDirection) {
			super();
			startColor = aStart;
			endColor = aEnd;
			direction = aDirection;
		}

		public GradientPanel(Color aStart, Color aEnd, int aDirection,
				int aOuterRoundRectSize, int aInnerRoundRectSize) {
			super();
			startColor = aStart;
			endColor = aEnd;
			direction = aDirection;
			outerRoundRectSize = aOuterRoundRectSize;
			innerRoundRectSize = aInnerRoundRectSize;
		}

		public Color getEndColor() {
			return endColor;
		}

		public void setEndColor(Color aColor) {
			Color oldEndColor = endColor;
			endColor = aColor;
			super.firePropertyChange("endColor", oldEndColor, endColor);
			repaint();
		}

		public Color getStartColor() {
			return startColor;
		}

		public void setStartColor(Color aColor) {
			Color oldStartColor = endColor;
			startColor = aColor;
			super.firePropertyChange("startColor", oldStartColor, startColor);
			repaint();
		}

		public int getDirection() {
			return direction;
		}

		public void setDirection(int aDirection) {
			int oldDirection = direction;
			direction = aDirection;
			super.firePropertyChange("direction", oldDirection, aDirection);
			repaint();
		}

		public void paintComponent(Graphics g) {
			Dimension dim = getSize();
			Graphics2D g2 = (Graphics2D) g;
			Insets inset = getInsets();
			int vWidth = dim.width - (inset.left + inset.right);
			int vHeight = dim.height - (inset.top + inset.bottom);

			if (direction == HORIZONTAL) {
				paintHorizontalGradient(g2, inset.left, inset.top, vWidth,
						vHeight, dim.width);
			} else if (direction == VERTICAL) {
				paintVerticalGradient(g2, inset.left, inset.top, vWidth,
						vHeight, dim.height);
			} else if (direction == HORIZONTAL_RAISED) {
				paintHorizontalRaisedGradient(g2, inset.left, inset.top,
						vWidth, vHeight, dim.width);
			} else if (direction == VERTICAL_RAISED) {
				paintVerticalRaisedGradient(g2, inset.left, inset.top, vWidth,
						vHeight, dim.height);

			}

		}

		/**
		 * Paints a vertical gradient background from the start color to the end
		 * color.
		 */
		private void paintVerticalGradient(Graphics2D g2d, int x, int y, int w,
				int h, int height) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h,
					endColor, true);
			g2d.setPaint(GP);

			new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
					100, 100, 100));
			new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
					new Color(255, 255, 255, 100));
			g2d.setPaint(Color.BLACK);
			g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(GP);
			g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
					outerRoundRectSize);

		}

		/**
		 * Paints a horizontal gradient background from the start color to the
		 * end color.
		 */
		private void paintHorizontalGradient(Graphics2D g2d, int x, int y,
				int w, int h, int width) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			GradientPaint GP = new GradientPaint(0, 0, startColor, w, 0,
					endColor, true);
			g2d.setPaint(GP);

			new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(
					100, 100, 100));
			new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
					new Color(255, 255, 255, 100));
			g2d.setPaint(Color.BLACK);
			g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(GP);
			g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
					outerRoundRectSize);

		}

		/**
		 * Paints a vertical-raised gradient background from the start color to
		 * the end color.
		 */
		private void paintVerticalRaisedGradient(Graphics2D g2d, int x, int y,
				int w, int h, int height) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			GradientPaint p1;
			GradientPaint p2;
			GradientPaint GP = new GradientPaint(0, 0, startColor, 0, h,
					endColor, true);
			g2d.setPaint(GP);

			p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
					new Color(100, 100, 100));
			p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
					new Color(255, 255, 255, 100));
			g2d.setPaint(Color.BLACK);
			g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(GP);
			g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
					outerRoundRectSize);

			g2d.setPaint(p1);
			g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(p2);
			g2d.drawRoundRect(1, 1, w - 3, h - 3, innerRoundRectSize,
					innerRoundRectSize);

		}

		/**
		 * Paints a horizontal-raised gradient background from the start color
		 * to the end color.
		 */
		private void paintHorizontalRaisedGradient(Graphics2D g2d, int x,
				int y, int w, int h, int width) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			GradientPaint p1;
			GradientPaint p2;
			GradientPaint GP = new GradientPaint(0, 0, startColor, w, 0,
					endColor, true);
			g2d.setPaint(GP);
			p1 = new GradientPaint(0, 0, new Color(0, 0, 0), w - 3, 0,
					new Color(100, 100, 100));
			p2 = new GradientPaint(1, 0, new Color(0, 0, 0, 50), w - 3, 0,
					new Color(255, 255, 255, 100));

			RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0,
					w - 1, h - 1, outerRoundRectSize, outerRoundRectSize);
			Shape clip = g2d.getClip();
			g2d.clip(r2d);
			g2d.fillRect(0, 0, w, h);
			g2d.setClip(clip);
			g2d.setPaint(p1);

			g2d.setPaint(Color.BLACK);
			g2d.drawRoundRect(0, 0, w - 1, h - 1, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(p2);
			g2d.fillRoundRect(1, 1, w - 2, h - 2, outerRoundRectSize,
					outerRoundRectSize);

			g2d.setPaint(Color.WHITE);
			g2d.drawRoundRect(1, 1, w - 3, h - 3, outerRoundRectSize,
					outerRoundRectSize);
			g2d.setPaint(p2);
			g2d.drawRoundRect(1, 1, w - 2, h - 2, innerRoundRectSize,
					innerRoundRectSize);

		}

		/**
		 * Get the OuterRoundedSize of the RoundedRectangle
		 */
		public int getOuterRoundRectSize() {
			return outerRoundRectSize;
		}

		/**
		 * Set the OuterRoundSize of Rectangle
		 * 
		 * @param outerRoundRectSize
		 */
		public void setOuterRoundRectSize(int outerRoundRectSize) {
			this.outerRoundRectSize = outerRoundRectSize;
		}

		public int getInnerRoundRectSize() {
			return innerRoundRectSize;
		}

		public void setInnerRoundRectSize(int innerRoundRectSize) {
			this.innerRoundRectSize = innerRoundRectSize;
		}

	}

}
