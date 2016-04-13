package presentation.mainui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class beginningPanel extends JPanel {
	ImageIcon image = new ImageIcon("src/main/resources/image/beginning.gif");

	public beginningPanel() {
		setSize(600, 600);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);
	}

}
