package hotel.linuxdp.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * 
 * @author linuxdp
 * 
 */
public class Footer extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Color colorPrimario = new Color(0x666f7f);
	private Color colorSecundario = new Color(0x2a3342);
	private Color colorContorno = new Color(0x2a3342);
	private int arcw = 0;
	private int arch = 0;
	/**
	 * 
	 */
	JLabel timeLabel = new JLabel();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	Timer timer;
	LC layC = new LC().fill().wrap();
	AC colC = new AC().align("right", 0).fill(1, 1).grow(0, 1, 1)
			.align("right", 0).gap("15", 1);
	AC rowC = new AC();//.index(6).gap("15!").align("top").grow(100, 8);

	public Footer() {
		super();
		setOpaque(false);
		setLayout(new MigLayout(layC, colC,rowC));
		timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
		timeLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
		timeLabel.setForeground(new Color(255, 255, 255));
		timer = new Timer(500, this);
		timer.setRepeats(true);
		timer.start();

		add(timeLabel,"dock east");
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0,
				getWidth(), getHeight() - 1, getArcw(), getArch());
		g2.clip(r2d);
		g2.setPaint(new GradientPaint(0.0f, 0.0f, getColorPrimario().darker(),
				0.0f, getHeight(), getColorSecundario().darker()));
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setStroke(new BasicStroke(1f));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, getColorContorno(), 0.0f,
				getHeight(), getColorContorno()));
		g2.drawRoundRect(0, 0, getWidth() - 0, getHeight() - 0, 0, 0);

		g2.setPaint(oldPaint);
		super.paintComponent(g);
	}

	public Color getColorPrimario() {
		return colorPrimario;
	}

	public void setColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

	public void setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
	}

	public Color getColorContorno() {
		return colorContorno;
	}

	public void setColorContorno(Color colorContorno) {
		this.colorContorno = colorContorno;
	}

	public int getArcw() {
		return arcw;
	}

	public void setArcw(int arcw) {
		this.arcw = arcw;
	}

	public int getArch() {
		return arch;
	}

	public void setArch(int arch) {
		this.arch = arch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			timeLabel.setText(sdf.format(new Date(System.currentTimeMillis())));
		}

	}
}