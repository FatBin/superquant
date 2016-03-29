package presentation.repaintComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class barPanel extends JPanel{
	
	private double value;
	
	public barPanel(Double value) {
		this.value = value;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		
	}
}
