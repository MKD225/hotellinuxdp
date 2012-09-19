package Wecome;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sun.java.g2d.Reflection;

import org.jdesktop.fuse.ResourceInjector;

/**
 * 
 * @author linuxdp
 * 
 */
public class IndexWebcome extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float shadowOffsetX;
	private float shadowOffsetY;
	private float shadowOpacity;
	private Color shadowColor = new Color(0x000000);
	private Font categoryFont = new Font("Arial Black-Narrow-19",
			Font.HANGING_BASELINE, 18);
	private Font categorySmallFont = new Font("Arial-Narrow-14",
			Font.LAYOUT_LEFT_TO_RIGHT, 14);
	private float categorySmallOpacity = (float) 0.9;
	private Color categoryColor = new Color(255, 255, 255);

	LC layC = new LC().fill().wrap();
	AC colC = new AC().align("center", 8).fill(4, 4).grow(100, 1, 3)
			.gap("0", 16).align("center", 4);
	AC rowC = new AC().align("center", 0).fill(4, 4);
	JButton button = new JButton();

	public IndexWebcome() {
		ResourceInjector.get().inject(this);
		setBorder(javax.swing.BorderFactory.createLineBorder(
				new java.awt.Color(209, 209, 209), 1));
		setOpaque(false);
		setLayout(new MigLayout(layC, colC, rowC));
		setTasks();
	}

	private JButton createButton(String name, String description, String image) {
		return new TaskButton(name, description, image);
	}

	@SuppressWarnings("unused")
	private void setTasks() {
		int counter = 0;

		add(button = createButton("The Libarary",
				"Book Store in project hotel management System",
				"/resources/library.png"), "span,top,center");
		counter++;
		add(new JSeparator(), "width 700,span,top,center");

		add(button = createButton("Role Management",
				"Role of authorization Administrator",
				"/resources/role_cen.png"), "width max,gap left 30");
		counter++;
		button.addActionListener(new clo_library());

		add(button = createButton("Administrator", "" + "Administer and User",
				"/resources/Account_cen.png"), "width max");
		counter++;
		button.addActionListener(new clo_library());

		add(button = createButton("BillItem", "s", "/resources/payment_cen.png"),
				"width max");
		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("BillItemType", "t",
				"/resources/billItem.png"), "span ,width max");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Bills", "Bills ", "/resources/bill.png"),
				"width max,gap left 30");
		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("CheckIn", "CheckIn",
				"/resources/check-icon.png"), "width max");
		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("CheckOut", "CheckOut",
				"/resources/checkout.png"), "width max");
		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Customer", "Customer",
				"/resources/Users-icon.png"), "span,width max");

		counter++;
		add(button = createButton("PaymentType", "PaymentType",
				"/resources/payment_cen.png"), "width max,gap left 30");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Payment", "PaymentType",
				"/resources/payment.png"), "width max");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Report", "Report", "/resources/report.png"));

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Reservation", "Reservation",
				"/resources/reservation.png"), "span,width max");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("Rooms", "Rooms", "/resources/room.png"),
				"width max,gap left 30");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("RoomStatus", "RoomStatus",
				"/resources/room.png"), "width max");

		counter++;
		button.addActionListener(new clo_library());
		add(button = createButton("RoomType", "RoomType", "/resources/room.png"),
				"width max");

		counter++;
		add(button = createButton("Discount", "Discount",
				"/resources/discount.png"), "width max");
		counter++;
		button.addActionListener(new clo_library());
	}

	private class clo_library implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane
					.showMessageDialog(null, "The library is being upgraded");
		}
	}

	@SuppressWarnings("unused")
	private class TaskButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Dimension componentDimension = new Dimension(0, 0);
		private BufferedImage image;
		private Rectangle clickable;

		private float ghostValue = 0.0f;
		private int distance_r;
		private int distance_g;
		private int distance_b;

		private int color_r;
		private int color_g;
		private int color_b;

		private final String name;
		private final String description;
		private final String imageName;

		private TaskButton(String name, String description, String imageName) {
			this.name = name;
			this.description = description;
			this.imageName = imageName;
			new ImageFetcher().execute();
			color_r = categoryColor.getRed();
			color_g = categoryColor.getGreen();
			color_b = categoryColor.getBlue();
		}

		@SuppressWarnings("rawtypes")
		private class ImageFetcher extends SwingWorker {
			protected Object doInBackground() throws Exception {
				getImage();
				return image;
			}

			protected void done() {
				componentDimension = computeDimension();
				revalidate();
			}
		}

		private void getImage() {
			try {
				this.image = ImageIO.read(getClass().getResource(imageName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedImage mask = Reflection.createGradientMask(
					image.getWidth(), image.getHeight());
			this.image = Reflection.createReflectedPicture(image, mask);
		}

		private Dimension computeDimension() {
			Insets insets = getInsets();

			FontMetrics metrics = getFontMetrics(categoryFont);
			Rectangle2D bounds = metrics.getMaxCharBounds(getGraphics());
			int height = (int) bounds.getHeight() + metrics.getLeading();
			int nameWidth = SwingUtilities.computeStringWidth(metrics, name);

			metrics = getFontMetrics(categorySmallFont);
			bounds = metrics.getMaxCharBounds(getGraphics());
			height += bounds.getHeight();
			int descWidth = SwingUtilities.computeStringWidth(metrics,
					description == null ? "" : description);

			int width = Math.max(nameWidth, descWidth);
			width += image.getWidth();
			height = Math.max(height, image.getHeight());
			height += 4;

			return new Dimension(width + insets.left + insets.right, height
					+ insets.top + insets.bottom);
		}

		@Override
		public Dimension getMinimumSize() {
			return getPreferredSize();
		}

		@Override
		public Dimension getPreferredSize() {
			return componentDimension;
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			setupGraphics(g2);

			float y = paintText(g2);
			paintImage(g2, y);
		}

		// dieu chinh vi tri cua cac image
		private void paintImage(Graphics2D g2, float y) {
			Insets insets = getInsets();
			g2.drawImage(image, null, insets.left,
					0 + (int) (y - image.getHeight() / (2)));
		}

		private float paintText(Graphics2D g2) {

			g2.setFont(categoryFont);
			Insets insets = getInsets();

			FontRenderContext context = g2.getFontRenderContext();
			TextLayout layout = new TextLayout(name, categoryFont, context);

			float x = image.getWidth() + 10.0f;
			x += insets.left;
			float y = 4.0f + layout.getAscent() - layout.getDescent();
			y += insets.top;

			g2.setColor(shadowColor);
			Composite composite = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					shadowOpacity));
			layout.draw(g2, shadowOffsetX + x, shadowOffsetY + y);
			g2.setComposite(composite);

			g2.setColor(new Color(color_r, color_g, color_b));
			layout.draw(g2, x, y);
			y += layout.getDescent();

			layout = new TextLayout(description == null ? " " : description,
					categorySmallFont, context);
			y += layout.getAscent();
			composite = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					categorySmallOpacity));
			layout.draw(g2, x, y);
			g2.setComposite(composite);
			return y;
		}

		private void setupGraphics(Graphics2D g2) {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}

	}
}