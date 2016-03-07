package presentation.stockcheckui;

import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import presentation.stockmarketui.Marketui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PersonalStock extends JPanel {

	JButton btnNewButton;
	JButton button;
	JButton button_1;
	DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public PersonalStock(final JFrame frame, final StockDetail detail, final StockList listui) {
		setBorder(null);

		// setBounds(new Rectangle(960, 600));
		setLayout(null);
		final PersonalStock ppanel = this;

		btnNewButton = new JButton("\u5927\u76D8\u6570\u636E");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(ppanel);
				frame.remove(detail);
				frame.remove(listui);
				Marketui mpanel = new Marketui(frame);
				mpanel.setBounds(0, 0, getWidth(), getHeight());
				frame.getContentPane().add(mpanel);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(216, 216, 216));
		btnNewButton.setBounds(68, 68, 117, 44);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		add(btnNewButton);

		button = new JButton("\u4E2A\u80A1\u6570\u636E");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		button.setForeground(new Color(248, 179, 29));
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setBounds(68, 112, 117, 44);
		add(button);

		button_1 = new JButton("\u81EA\u9009\u80A1\u5206\u6790");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setForeground(new Color(216, 216, 216));
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		button_1.setForeground(new Color(216, 216, 216));
		button_1.setContentAreaFilled(false);
		button_1.setBorder(null);
		button_1.setBounds(75, 156, 117, 44);
		add(button_1);
		// 使表格居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		tableModel = new DefaultTableModel(
				new Object[][] { { "", "", "", "", "", "", "" }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, },
				new String[] { "股票代码", "开盘价", "最高价", "收盘价", "最低价", "交易量（股）", "交易金额（元）" });

		setDragable(frame);

	}

	
	// 边框圆滑
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("image/background.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

	}

	// 设置界面可拖动
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;

	private void setDragable(final JFrame jFrame) {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
				jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
				jFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isDragged) {
					loc = new Point(jFrame.getLocation().x + e.getX() - tmp.x,
							jFrame.getLocation().y + e.getY() - tmp.y);
					jFrame.setLocation(loc);
				}
			}
		});
	}
}
