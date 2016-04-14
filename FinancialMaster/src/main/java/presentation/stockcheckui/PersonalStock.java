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

import presentation.OptionalStock.OptionalStock;
import presentation.stockmarketui.Marketui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PersonalStock extends JPanel {

	JButton marketBtn;
	JButton stockListBtn;
	JButton optionStockBtn;
	DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	public PersonalStock(final JFrame frame) {
		setBorder(null);

		setLayout(null);

		marketBtn = new JButton("   大盘数据");
		marketBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				marketBtn.setForeground(new Color(248, 179, 29));
				marketBtn.setIcon(new ImageIcon("src/main/resources/image/line-enter.png"));
				marketBtn.setContentAreaFilled(false);
				marketBtn.setOpaque(false);
				marketBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				marketBtn.setForeground(new Color(252, 241, 224));
				marketBtn.setIcon(new ImageIcon("src/main/resources/image/line.png"));
				marketBtn.setContentAreaFilled(false);
				marketBtn.setOpaque(false);
				marketBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				Marketui mpanel = new Marketui(frame);
				mpanel.setBounds(0, 0, 960, getHeight());
				frame.getContentPane().add(mpanel);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		marketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		marketBtn.setForeground(new Color(252, 241, 224));
		marketBtn.setBounds(0, 68, 224, 44);
		marketBtn.setIcon(new ImageIcon("src/main/resources/image/line.png"));
		marketBtn.setContentAreaFilled(false);
		marketBtn.setOpaque(false);
		marketBtn.setBorder(null);
		add(marketBtn);

		stockListBtn = new JButton("   个股数据");
		stockListBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		stockListBtn.setForeground(new Color(248, 179, 29));
		stockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie-enter.png"));
		stockListBtn.setContentAreaFilled(false);
		stockListBtn.setBorder(null);
		stockListBtn.setBounds(0, 112, 224, 44);
		add(stockListBtn);

		optionStockBtn = new JButton("   行情对比");
		optionStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank.png"));
		optionStockBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionStockBtn.setForeground(new Color(248, 179, 29));
//				optionStockBtn.setBackground(new Color(169, 107, 16));
				optionStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank-enter.png"));
				optionStockBtn.setContentAreaFilled(false);
				optionStockBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionStockBtn.setForeground(new Color(252, 241, 224));
				optionStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank.png"));
				optionStockBtn.setContentAreaFilled(false);
				optionStockBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				OptionalStock opanel = new OptionalStock(frame);
				frame.getContentPane().add(opanel);
				opanel.setBounds(0, 0, 960, 600);
				frame.repaint();
				frame.validate();
			}
		});
		optionStockBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		optionStockBtn.setForeground(new Color(252, 241, 224));
		optionStockBtn.setContentAreaFilled(false);
		optionStockBtn.setBorder(null);
		optionStockBtn.setBounds(0, 156, 224, 44);
		add(optionStockBtn);
		
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
		ImageIcon image = new ImageIcon("src/main/resources/image/left.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
