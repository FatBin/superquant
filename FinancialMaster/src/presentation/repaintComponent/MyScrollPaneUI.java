package presentation.repaintComponent;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollPaneUI;

public class MyScrollPaneUI extends BasicScrollPaneUI{

	@Override
	public void paint(Graphics g, JComponent c) {
		// TODO Auto-generated method stub
		super.paint(g, c);
	}

	@Override
	public void installUI(JComponent x) {
		// TODO Auto-generated method stub
		super.installUI(x);
	}

	@Override
	public int getBaseline(JComponent c, int width, int height) {
		// TODO Auto-generated method stub
		return super.getBaseline(c, width, height);
	}
	
}
