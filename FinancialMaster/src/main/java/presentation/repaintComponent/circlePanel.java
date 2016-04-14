package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.javafx.geom.RoundRectangle2D;

public class circlePanel extends JPanel{
	
	private ImageIcon image;
	
	public circlePanel(ImageIcon image) {
		this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image.getImage(), this.getX(),this.getY(),this.getWidth(),this.getHeight(),this);
	}

}
