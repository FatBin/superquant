package presentation.stockcheckui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import VO.StockVO;
import businesslogic.stockcheckbl.StockMessageBL;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import presentation.repaintComponent.DateChooser;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.TextBubbleBorder;

@SuppressWarnings("serial")
public class StockDetail extends JPanel {
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;
	private boolean click1 = false;
	private boolean click2 = false;
	DefaultTableModel tableModel;
	private int rowpos = -1;
	private JLabel timeGotolbl;
	StockMessageBLService Message = new StockMessageBL();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public StockDetail(final JFrame frame, final String id, final JPanel listui) {
		setBorder(null);

		setLayout(null);
		final StockDetail detail = this;

		JScrollPane contentScroll = new JScrollPane();
		contentScroll.setBounds(0, 51, 730, 540);
		contentScroll.setOpaque(false);
		contentScroll.getViewport().setOpaque(false);
		contentScroll.setBorder(BorderFactory.createEmptyBorder());
		contentScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());

		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(710, 900));
		content.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 14));

		IntentPane intentPane1 = new IntentPane();
		intentPane1.setPreferredSize(new Dimension(700, 300));
		intentPane1.setLayout(null);
		content.add(intentPane1);

		IntentPane intentPane2 = new IntentPane();
		intentPane2.setPreferredSize(new Dimension(700, 450));
		intentPane2.setLayout(null);
		content.add(intentPane2);
		
		JLabel openLabel = new JLabel("开盘价");
		openLabel.setBounds(44, 76, 70, 30);
		openLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		intentPane1.add(openLabel);
		
		closeBtn = new JButton("X");
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				closeBtn.setForeground(new Color(252, 98, 93));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				closeBtn.setForeground(new Color(216, 216, 216));
			}
		});
		closeBtn.setForeground(new Color(216, 216, 216));
		closeBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		closeBtn.setContentAreaFilled(false);
		closeBtn.setBorder(null);
		closeBtn.setBounds(707, 15, 16, 16);
		add(closeBtn);

		miniBtn = new JButton("—");
		miniBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				miniBtn.setForeground(new Color(253, 188, 64));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				miniBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(frame.ICONIFIED); // 最小化
			}
		});
		miniBtn.setForeground(new Color(216, 216, 216));
		miniBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		miniBtn.setContentAreaFilled(false);
		miniBtn.setBorder(null);
		miniBtn.setBounds(680, 14, 16, 16);
		add(miniBtn);

		Date today = new Date();
		Date dbefore = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 前一个月
		dbefore = calendar.getTime();

		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		final String start = dt.format(dbefore);
		final JLabel startTimelbl = new JLabel(start);
		startTimelbl.setLocation(546, 75);
		startTimelbl.setPreferredSize(new Dimension(90, 22));
		dateChooser1.register(startTimelbl);
//		intentPane2.add(startTimelbl);

		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		final String end = dt.format(today);
		final JLabel endTimelbl = new JLabel(end);
		endTimelbl.setLocation(586, 75);
		endTimelbl.setPreferredSize(new Dimension(90, 22));
		dateChooser2.register(endTimelbl);
//		intentPane2.add(endTimelbl);

		JScrollPane stockDetailPane = new JScrollPane();
		stockDetailPane.setBounds(10, 110, 715, 440);
		stockDetailPane.setOpaque(false);
		stockDetailPane.setBorder(BorderFactory.createEmptyBorder());
		stockDetailPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		stockDetailPane.getViewport().setOpaque(false);
//		add(stockDetailPane);

		StockVO datavo = Message.getStockMessage(id);
		String[][] data = datavo.getHistory_data();

		table = new JTable();
		table.setRowHeight(26);
		// 使表格居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setSelectionBackground(new Color(88, 93, 103, 200));
		table.setSelectionForeground(new Color(255, 255, 255, 230));
		table.setOpaque(false);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		// 选取行
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Point mousepoint;
				mousepoint = e.getPoint();
				rowpos = table.rowAtPoint(mousepoint);
				table.setRowSelectionInterval(rowpos, rowpos);
			}
		});
		stockDetailPane.setViewportView(table);
		table.setBorder(null);
		table.setEnabled(false);
		tableModel = new DefaultTableModel(data,
				new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
		table.setModel(tableModel);

		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		for (int i = 1; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(70);
		}

		setDragable(frame);

		JButton searchBtn = new JButton();
		searchBtn.setBounds(630, 15, 18, 18);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		ImageIcon image1 = new ImageIcon("src/main/resources/image/search.png");
		Image temp1 = image1.getImage().getScaledInstance(searchBtn.getWidth(), searchBtn.getHeight(),
				image1.getImage().SCALE_DEFAULT);
		image1 = new ImageIcon(temp1);
		searchBtn.setIcon(image1);
		searchBtn.setMargin(new Insets(0, 0, 0, 0));
		add(searchBtn);

		searchTextField = new JTextField();
		searchTextField.setFocusable(false);
		searchTextField.setOpaque(false);
		searchTextField.setForeground(new Color(150, 150, 150));
		searchTextField.setCaretColor(new Color(150, 150, 150));
		searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		searchTextField.setText("输入股票代码或名称搜索");
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click) {
					searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setText("");
				click = true;
				searchTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				searchTextField.setFocusable(true);
				searchTextField.requestFocus();
			}
		});
		searchTextField.setBounds(462, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		JLabel namelbl = new JLabel();
		namelbl.setText("("+id+")");
		namelbl.setBackground(new Color(245, 245, 245));
		namelbl.setForeground(new Color(95, 99, 108));
		namelbl.setBounds(10, 5, 200, 32);
		namelbl.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		intentPane1.add(namelbl);

		backBtn = new JButton("back");
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(detail);
				listui.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setForeground(new Color(216, 216, 216));
			}
		});
		backBtn.setForeground(new Color(216, 216, 216));
		backBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		backBtn.setContentAreaFilled(false);
		backBtn.setBorder(null);
		backBtn.setBounds(10, 15, 46, 16);
		add(backBtn);

		JLabel label = new JLabel("至");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		label.setBounds(554, 75, 25, 22);
//		add(label);

		final MyComboBox conditionBox = new MyComboBox();
		conditionBox.setOpaque(false);
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 16));
		String[] item = { "筛选关键字", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "成交量", "换手率", "市盈率", "市净率" };
		for (int i = 0; i < 10; i++) {
			conditionBox.addItem(item[i]);
		}
		conditionBox.setBorder(null);
		conditionBox.setBounds(10, 562, 110, 25);
		conditionBox.setSelectedIndex(0);
//		add(conditionBox);

		belowTextField = new JTextField();
		belowTextField.setOpaque(false);
		belowTextField.setForeground(new Color(150, 150, 150));
		belowTextField.setFocusable(false);
		belowTextField.setColumns(10);
		belowTextField.setCaretColor(new Color(150, 150, 150));
		belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		belowTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				belowTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click1) {
					belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				belowTextField.setText("");
				click1 = true;
				belowTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				belowTextField.setFocusable(true);
				belowTextField.requestFocus();
			}
		});
		belowTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}
		});
		belowTextField.setBounds(146, 562, 97, 27);
		belowTextField.setText("输入下限");
//		add(belowTextField);

		aboveTextField = new JTextField();
		aboveTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				aboveTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click2) {
					aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				aboveTextField.setText("");
				click2 = true;
				aboveTextField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				aboveTextField.setFocusable(true);
				aboveTextField.requestFocus();
			}
		});
		aboveTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.')) {
					e.consume();
				}
			}
		});
		aboveTextField.setOpaque(false);
		aboveTextField.setForeground(new Color(150, 150, 150));
		aboveTextField.setFocusable(false);
		aboveTextField.setColumns(10);
		aboveTextField.setCaretColor(new Color(150, 150, 150));
		aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		aboveTextField.setBounds(269, 562, 97, 27);
		aboveTextField.setText("输入上限");
//		add(aboveTextField);

		timeGotolbl = new JLabel("\u203A");
		timeGotolbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				timeGotolbl.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				timeGotolbl.setForeground(new Color(150, 150, 150));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String newStart = startTimelbl.getText();
				String newEnd = endTimelbl.getText();
				StockVO datavo = Message.updateStockMessage(newStart, newEnd);
				String[][] data = datavo.getHistory_data();
				tableModel = new DefaultTableModel(data,
						new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
				table.setModel(tableModel);

				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				for (int i = 1; i < 6; i++) {
					table.getColumnModel().getColumn(i).setPreferredWidth(70);
				}

				conditionBox.setSelectedIndex(0);
				belowTextField.setText("输入下限");
				aboveTextField.setText("输入上限");
				repaint();

			}
		});
		timeGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		timeGotolbl.setForeground(new Color(150, 150, 150));
		timeGotolbl.setBounds(682, 73, 25, 22);
//		add(timeGotolbl);

		final JLabel conditionGotolbl = new JLabel("\u203A");
		conditionGotolbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				conditionGotolbl.setForeground(new Color(72, 77, 78));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				conditionGotolbl.setForeground(new Color(150, 150, 150));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				String low = belowTextField.getText();
				String high = aboveTextField.getText();

				int index = conditionBox.getSelectedIndex();
				if (index != 0) {
					StockVO datavo = Message.filterStockMessage(index, low, high);
					String data2[][] = datavo.getHistory_data();
					tableModel = new DefaultTableModel(data2,
							new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "交易量(股)", "换手率", "市盈率", "市净率" });
					table.setModel(tableModel);

					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					for (int i = 1; i < 6; i++) {
						table.getColumnModel().getColumn(i).setPreferredWidth(70);
					}
					repaint();
				}
			}
		});
		conditionGotolbl.setForeground(new Color(150, 150, 150));
		conditionGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		conditionGotolbl.setBounds(382, 562, 25, 22);
//		add(conditionGotolbl);

		// 添加scrollPane
		content.add(intentPane1);
		content.add(intentPane2);
		contentScroll.setViewportView(content);
		contentScroll.getVerticalScrollBar().setUnitIncrement(20);
		add(contentScroll);

		// 点击其他地方使text field不能输入
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("输入股票代码或名称搜索");

				belowTextField.setFocusable(false);
				belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				belowTextField.setText("输入下限");

				aboveTextField.setFocusable(false);
				aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				aboveTextField.setText("输入上限");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	// 边框圆滑
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/right.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	// 设置界面可拖动
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private JTable table;
	private JButton backBtn;
	private JTextField belowTextField;
	private JTextField aboveTextField;

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
