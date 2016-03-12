package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import sun.swing.DefaultLookup;

public class MyComboBoxUI extends BasicComboBoxUI {
	private JButton arrow;

	public MyComboBoxUI() {
		super();
	}

	@SuppressWarnings("static-access")
	@Override
	protected JButton createArrowButton() {
		arrow = new JButton();
		arrow.setSize(26, 20);
		ImageIcon image1 = new ImageIcon("src/main/resources/image/arrow-Enter.png");
		Image temp1 = image1.getImage().getScaledInstance(arrow.getWidth(), arrow.getHeight(),
				image1.getImage().SCALE_DEFAULT);
		image1 = new ImageIcon(temp1);
		arrow.setIcon(image1);
		arrow.setBorder(null);
		arrow.setBackground(new Color(0, 0, 0, 0));
		arrow.setOpaque(false);
		arrow.setContentAreaFilled(false);
		arrow.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				arrow.setSize(26, 20);
				ImageIcon image1 = new ImageIcon("src/main/resources/image/arrow-Enter.png");
				Image temp1 = image1.getImage().getScaledInstance(arrow.getWidth(), arrow.getHeight(),
						image1.getImage().SCALE_DEFAULT);
				image1 = new ImageIcon(temp1);
				arrow.setIcon(image1);
				arrow.setOpaque(false);
				arrow.setContentAreaFilled(false);
				arrow.setBorder(null);
				arrow.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				arrow.setSize(26, 20);
				ImageIcon image1 = new ImageIcon("src/main/resources/image/arrow.png");
				Image temp1 = image1.getImage().getScaledInstance(arrow.getWidth(), arrow.getHeight(),
						image1.getImage().SCALE_DEFAULT);
				image1 = new ImageIcon(temp1);
				arrow.setIcon(image1);
				arrow.setOpaque(false);
				arrow.setContentAreaFilled(false);
				arrow.setBorder(null);
				arrow.setBackground(new Color(0, 0, 0, 0));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		return arrow;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		hasFocus = comboBox.hasFocus();
		Graphics2D g2 = (Graphics2D) g;
		if (!comboBox.isEditable()) {
			Rectangle r = rectangleForCurrentValue();
			// 重点:JComboBox的textfield 的绘制 并不是靠Renderer来控制 这点让我很吃惊.
			// 它会通过paintCurrentValueBackground来绘制背景
			// 然后通过paintCurrentValue();去绘制JComboBox里显示的值
			paintCurrentValueBackground(g2, r, hasFocus);
			paintCurrentValue(g2, r, hasFocus);
		}

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = (int) this.getPreferredSize(c).getWidth() + arrow.getWidth() - 2;
		int height = 0;
		int heightOffset = 0;
		if (comboBox.isPopupVisible()) {
			heightOffset = 5;
			height = (int) this.getPreferredSize(c).getHeight();
			// arrow.setIcon(XUtil.defaultComboBoxArrowIcon_Into);
		} else {
			heightOffset = 0;
			height = (int) this.getPreferredSize(c).getHeight() - 1;
			// arrow.setIcon(XUtil.defaultComboBoxArrowIcon);
		}
		if (comboBox.isFocusable()) {
			g2.setColor(new Color(0, 0, 0, 0));
		}
		g2.fillRoundRect(0, 0, width, height + heightOffset, 4, 4);
	}

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
		Color t = g.getColor();
		if (comboBox.isEnabled())
			g.setColor(new Color(0, 0, 0, 0));
		else
			g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(t);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
		ListCellRenderer renderer = comboBox.getRenderer();
		Component c;
		if (hasFocus && !isPopupVisible(comboBox)) {
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
		} else {
			c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
			c.setBackground(new Color(95, 99, 108));
		}
		c.setFont(comboBox.getFont());
		if (hasFocus && !isPopupVisible(comboBox)) {
			c.setForeground(new Color(95, 99, 108));
			c.setBackground(new Color(0, 0, 0, 0));
		} else {
			if (comboBox.isEnabled()) {
				c.setForeground(new Color(95, 99, 108));
				c.setBackground(new Color(0, 0, 0, 0));
				// new Color(248, 179, 29)
			} else {
				c.setForeground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledForeground", null));
				c.setBackground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledBackground", null));
			}
		}

		// Fix for 4238829: should lay out the JPanel.
		boolean shouldValidate = false;
		if (c instanceof JPanel) {
			shouldValidate = true;
		}

		int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
		if (padding != null) {
			x = bounds.x + padding.left;
			y = bounds.y + padding.top;
			w = bounds.width - (padding.left + padding.right);
			h = bounds.height - (padding.top + padding.bottom);
		}

		currentValuePane.paintComponent(g, c, comboBox, x, y, w, h, shouldValidate);
	}

	@SuppressWarnings("serial")
	protected ComboPopup createPopup() {
		ComboPopup popup = new BasicComboPopup(comboBox) {

			// 重载paintBorder方法 来画出我们想要的边框..
			public void paintBorder(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(0, 0, 0, 0));
				g2.drawRoundRect(0, -arrow.getHeight(), getWidth() - 1, getHeight() + arrow.getHeight() - 1, 6, 6);
			}
		};
		return popup;
	}

}
