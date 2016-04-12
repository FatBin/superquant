package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.sun.media.jfxmedia.track.Track;

public class MyScrollBarUI extends BasicScrollBarUI {

	private final Dimension d = new Dimension();

	private Rectangle Track;
	
	@Override
	protected Rectangle getTrackBounds() {
//		trackRect.setSize(12.0,trackRect.getHeight());
		return super.getTrackBounds();
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
//		g.setColor(new Color(240, 240, 240));
//		int x = trackBounds.x;
//		int y = trackBounds.y;
//		int height = trackBounds.height;
//		int width = trackBounds.width;
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.fillRoundRect(x+width/2, y, width / 2, height, 2, 2);
		
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		int width = thumbBounds.width;
		int height = thumbBounds.height;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = null;
        JScrollBar sb = (JScrollBar)c;
        if(!sb.isEnabled() || thumbBounds.width>thumbBounds.height) {
          return;
        }else if(isDragging) {
          color = new Color(95,99,108,160);
        }else if(isThumbRollover()) {
          color = new Color(95,99,108,160);
        }
        else {
          color = new Color(95,99,108,90);
        }
        g2d.setPaint(color);
		g2d.fillRoundRect(thumbBounds.x+6,thumbBounds.y+1,width-7, height - 1, 10, 10);
	}

	@SuppressWarnings("serial")
	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new JButton() {
			@Override
			public Dimension getPreferredSize() {
				return d;
			}
		};
	}

	@SuppressWarnings("serial")
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new JButton() {
			@Override
			public Dimension getPreferredSize() {
				return d;
			}
		};
	}

}
