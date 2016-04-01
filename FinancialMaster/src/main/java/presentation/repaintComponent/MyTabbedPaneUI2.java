package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class MyTabbedPaneUI2 extends BasicTabbedPaneUI{
	private Color selectColor;
    private Color deSelectColor;
    private int anchoCarpetas = 18;
	
	@Override
	protected void installDefaults() {
		// TODO Auto-generated method stub
		super.installDefaults();
//        selectColor = new Color(0,0,0);
//        deSelectColor = new Color(197, 193, 168); //设置选中的框点
        tabAreaInsets.right = anchoCarpetas;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
	}

	@Override
	protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
		int tabCount = tabPane.getTabCount();

        Rectangle iconRect = new Rectangle(),
                  textRect = new Rectangle();
        Rectangle clipRect = g.getClipBounds();

        // Paint tabRuns of tabs from back to front
        for (int i = runCount - 1; i >= 0; i--) {
            int start = tabRuns[i];
            int next = tabRuns[(i == runCount - 1)? 0 : i + 1];
            int end = (next != 0? next - 1: tabCount - 1);
            for (int j = start; j <= end; j++) {
                if (j != selectedIndex && rects[j].intersects(clipRect)) {
                	g.setColor(new Color(0, 0, 0,0));
                	paintTab(g, tabPlacement, rects, j, iconRect, textRect);
                }
            }
        }

        // Paint selected tab if its in the front run
        // since it may overlap other tabs
        if (selectedIndex >= 0 && rects[selectedIndex].intersects(clipRect)) {
        	g.setColor(new Color(62, 56, 49, 30));
            paintTab(g, tabPlacement, rects, selectedIndex, iconRect, textRect);
        }
	}

	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		// TODO Auto-generated method stub
		super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
	}

	@Override
	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
			Rectangle iconRect, Rectangle textRect, boolean isSelected) {
		// TODO Auto-generated method stub
		super.paintFocusIndicator(g, tabPlacement, rects, tabIndex, iconRect, textRect, isSelected);
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
			boolean isSelected) {
		// TODO Auto-generated method stub
		super.paintTabBorder(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
			boolean isSelected) {
		// TODO Auto-generated method stub
		super.paintTabBackground(g, tabPlacement, tabIndex, x, y, w, h, isSelected);
	}
	
	
	
}
