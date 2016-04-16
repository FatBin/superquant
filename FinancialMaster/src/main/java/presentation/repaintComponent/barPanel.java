package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class barPanel extends JPanel{
	
	private double value;
	private double highest;
	
	public barPanel(double value,double highest) {
		this.value = value;
		this.highest = highest;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(182, 174, 164, 100));
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
		if(highest == 0){
			
		}else{
		double proportion = value/highest;
		for (int i = 0; i < 5; i++) {
			proportion*=proportion;
		}
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
		}
	}
}
