package presentation.stockmarketui;

import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import ENUM.date_enum;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import presentation.OptionalStock.OptionalStock;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockList;

@SuppressWarnings("serial")
public class Marketui extends JPanel {

	JButton marketBtn;
	JButton shockListBtn;
	JButton optionalStockBtn;
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;

	String[][] data;
	
	private JScrollPane[] scrollPane;
	private JTable[] table;

	private StockMarketBLService stockMarketBL = new businesslogic.stockmarketbl.StockMarketBL();
	DefaultTableModel TableModel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public Marketui(final JFrame frame) {
		setBorder(null);

		setLayout(null);
		final Marketui mpanel = this;

		marketBtn = new JButton("\u5927\u76D8\u6570\u636E");
		marketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		marketBtn.setForeground(new Color(248, 179, 29));
		marketBtn.setBounds(68, 68, 117, 44);
		marketBtn.setContentAreaFilled(false);
		marketBtn.setBorder(null);
		add(marketBtn);

		shockListBtn = new JButton("\u4E2A\u80A1\u6570\u636E");
		shockListBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				shockListBtn.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shockListBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(mpanel);

				StockList listui = new StockList(frame);
				listui.setBounds(224, 0, getWidth() - 223, getHeight());
				frame.getContentPane().add(listui);

				PersonalStock ppanel = new PersonalStock(frame);
				ppanel.setBounds(0, 0, 225, getHeight());
				frame.getContentPane().add(ppanel);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		shockListBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		shockListBtn.setForeground(new Color(216, 216, 216));
		shockListBtn.setContentAreaFilled(false);
		shockListBtn.setBorder(null);
		shockListBtn.setBounds(68, 112, 117, 44);
		add(shockListBtn);

		optionalStockBtn = new JButton("\u81EA\u9009\u80A1\u5206\u6790");
		optionalStockBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				optionalStockBtn.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				optionalStockBtn.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(mpanel);
				OptionalStock opanel = new OptionalStock(frame);
				frame.getContentPane().add(opanel);
				opanel.setBounds(0, 0, getWidth(), getHeight());
				frame.repaint();
			}
		});
		optionalStockBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		optionalStockBtn.setForeground(new Color(216, 216, 216));
		optionalStockBtn.setContentAreaFilled(false);
		optionalStockBtn.setBorder(null);
		optionalStockBtn.setBounds(75, 156, 117, 44);
		add(optionalStockBtn);

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
		closeBtn.setBounds(931, 15, 16, 16);
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
		miniBtn.setBounds(904, 14, 16, 16);
		add(miniBtn);

		scrollPane = new JScrollPane[7];

		table = new JTable[7];
		for (int i = 0; i < 7; i++) {
			table[i] = new JTable();
			scrollPane[i] = new JScrollPane();
			scrollPane[i].setBounds(247, 110, 696, 440);
			scrollPane[i].add(table[i]);
			scrollPane[i].setViewportView(table[i]);
			scrollPane[i].getVerticalScrollBar().setUI(new MyScrollBarUI());
			scrollPane[i].setBorder(BorderFactory.createEmptyBorder());
		}

		JTabbedPane marketPane = new JTabbedPane();
		marketPane.setBounds(247, 98, 696, 440);

		String title[] = { "当天", "一周", "一个月", "半年", "一年", "五年", "十年" };

		for (int i = 0; i < 7; i++) {
			marketPane.add(title[i], scrollPane[i]);
		}

		marketPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				StockMarketVO stockMarketVO;
				JTabbedPane tab = (JTabbedPane) e.getSource();
				int selectedIndex = tab.getSelectedIndex();
				date_enum[] date = date_enum.values();
				stockMarketVO = stockMarketBL.getStockMarket("hs300", date[selectedIndex]);
				data = stockMarketVO.getData();
				TableModel = new DefaultTableModel(data, new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价" });
				table[selectedIndex].setModel(TableModel);
			}
		});
		add(marketPane);
		
		StockMarketVO stockMarketVO;
		stockMarketVO = stockMarketBL.getStockMarket("hs300", date_enum.Day);
		data = stockMarketVO.getData();
		TableModel = new DefaultTableModel(data, new String[] { "日期", "开盘价", "最高价", "最低价", "收盘价" });
		table[0].setModel(TableModel);

		final MyComboBox nameBox = new MyComboBox();
		nameBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		nameBox.setBounds(255, 65, 125, 32);
		nameBox.addItem("上证指数");
		nameBox.addItem("深证指数");
		nameBox.setSelectedIndex(0);
		nameBox.setOpaque(false);
		nameBox.setBorder(null);
		add(nameBox);

		JButton searchBtn = new JButton();
		searchBtn.setBounds(854, 15, 18, 18);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		searchBtn.setIcon(new ImageIcon("src/main/resources/image/search.png"));
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
		searchTextField.setBounds(686, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		// 点击其他地方使text field不能输入
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("输入股票代码或名称搜索");
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

		setDragable(frame);
	}

	// 边框圆滑
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/background.png");
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
