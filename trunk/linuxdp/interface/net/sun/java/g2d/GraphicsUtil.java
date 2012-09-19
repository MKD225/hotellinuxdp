package net.sun.java.g2d;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GraphicsUtil {
	private static GraphicsConfiguration configuration = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();

	private GraphicsUtil() {
	}

	public static BufferedImage createCompatibleImage(int width, int height) {
		return configuration.createCompatibleImage(width, height);
	}

	public static BufferedImage createTranslucentCompatibleImage(int width,
			int height) {
		return configuration.createCompatibleImage(width, height,
				Transparency.TRANSLUCENT);
	}

	public static BufferedImage loadCompatibleImage(URL resource)
			throws IOException {
		BufferedImage image = ImageIO.read(resource);
		return toCompatibleImage(image);
	}

	public static BufferedImage toCompatibleImage(BufferedImage image) {
		BufferedImage compatibleImage = configuration.createCompatibleImage(
				image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);
		Graphics g = compatibleImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return compatibleImage;
	}

	public static BufferedImage createThumbnail(BufferedImage image,
			int requestedThumbSize) {
		float ratio = (float) image.getWidth() / (float) image.getHeight();
		int width = image.getWidth();
		BufferedImage thumb = image;

		do {
			width /= 2;
			if (width < requestedThumbSize) {
				width = requestedThumbSize;
			}

			BufferedImage temp = new BufferedImage(width,
					(int) (width / ratio), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = temp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
			g2.dispose();

			thumb = temp;
		} while (width != requestedThumbSize);

		return thumb;
	}

	public static BufferedImage createThumbnail(BufferedImage image,
			int newWidth, int newHeight) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage thumb = image;

		do {
			if (width > newWidth) {
				width /= 2;
				if (width < newWidth) {
					width = newWidth;
				}
			}

			if (height > newHeight) {
				height /= 2;
				if (height < newHeight) {
					height = newHeight;
				}
			}

			BufferedImage temp = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = temp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
			g2.dispose();

			thumb = temp;
		} while (width != newWidth || height != newHeight);

		return thumb;
	}

}
