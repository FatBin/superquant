package presentation.repaintComponent;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MyScrollBarUI extends BasicScrollBarUI{

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		// TODO Auto-generated method stub
		super.paintTrack(g, c, trackBounds);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		// TODO Auto-generated method stub
		super.paintThumb(g, c, thumbBounds);
	}

}
