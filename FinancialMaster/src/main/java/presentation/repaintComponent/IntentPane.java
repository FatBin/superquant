package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class IntentPane extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.white);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		g2d.setColor(new Color(248, 247, 243));
		g2d.fillRoundRect(0, 0, getWidth(),50, 10, 10);
		g2d.setColor(Color.white);
		g2d.fillRect(0, 44, getWidth(), 10);
	}
}
