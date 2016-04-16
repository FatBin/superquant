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
	
	@Override
	protected void setThumbBounds(int x, int y, int width, int height) {
		/* If the thumbs bounds haven't changed, we're done.
         */
        if ((thumbRect.x == x) &&
            (thumbRect.y == y) &&
            (thumbRect.width == width) &&
            (thumbRect.height == height)) {
            return;
        }

//        /* Update thumbRect, and repaint the union of x,y,w,h and
//         * the old thumbRect.
//         */
//        int minX = Math.min(x, thumbRect.x);
//        int minY = Math.min(y, thumbRect.y);
//        int maxX = Math.max(x + width, thumbRect.x + thumbRect.width);
//        int maxY;
//        if (thumbRect.height<100) {
//        	maxY = Math.max(y + (int)this.getTrackBounds().getHeight(), thumbRect.y + 100);
//		}else {
//			maxY = Math.max(y + (int)this.getTrackBounds().getHeight(), thumbRect.y + thumbRect.height*2);
//		}
        if (height<50) {
        	thumbRect.setBounds(x, y, width, height);
        	scrollbar.repaint(x, y, width, 50);
		}else {
			thumbRect.setBounds(x, y, width, height);
        	scrollbar.repaint(x, y, width, height);
		}

        // Once there is API to determine the mouse location this will need
        // to be changed.
        setThumbRollover(false);
	}

}
