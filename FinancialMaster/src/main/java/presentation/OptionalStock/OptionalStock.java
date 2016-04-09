package presentation.OptionalStock;

import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import VO.StockItemVO;
import businesslogic.stockContrastbl.StockContrastBL;
import businesslogic.stockcheckbl.StockItemBL;
import businesslogicservice.stockContrastblservice.StockContrastBLService;
import businesslogicservice.stockcheckblservice.StockItemRankBLService;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.MyTableCellRenderer;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockList;
import presentation.stockmarketui.Marketui;
import presentation.stockmarketui.SearchBar;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class OptionalStock extends JPanel {

	JButton marketBtn;
	JButton stockListBtn;
	JButton optionalStockBtn;
	JButton closeBtn;
	JButton miniBtn;
	JCheckBox checkBox[];
	private JTextField searchTextField;
	private boolean click = false;
	private SearchBar searchBar;
	private SpiderChart spiderChart;
	private int rowpos = -1;
	private ArrayList<String> nameList;
	private JScrollPane rankPane;
	private JTable table;
	private ArrayList<StockItemVO> rankDataList;
	private JPanel chartPanel;

	DefaultTableModel tableModel;
	StockContrastBLService stockContrastBL = new StockContrastBL();
	StockItemRankBLService stockItemBL = new StockItemBL();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public OptionalStock(final JFrame frame) {
		setBorder(null);

		setLayout(null);
		final OptionalStock opanel = this;

		searchBar = new SearchBar(frame, opanel);

		marketBtn = new JButton("   大盘数据");
		marketBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				marketBtn.setForeground(new Color(248, 179, 29));
				marketBtn.setIcon(new ImageIcon("src/main/resources/image/line-enter.png"));
				marketBtn.setContentAreaFilled(false);
				marketBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				marketBtn.setForeground(new Color(216, 216, 216));
				marketBtn.setIcon(new ImageIcon("src/main/resources/image/line.png"));
				marketBtn.setContentAreaFilled(false);
				marketBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				frame.getContentPane().removeAll();
				Marketui mpanel = new Marketui(frame);
				frame.getContentPane().add(mpanel);
				mpanel.setBounds(0, 0, 960, 600);
				frame.repaint();
				frame.setVisible(true);
			}
		});
		marketBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		marketBtn.setForeground(new Color(216, 216, 216));
		marketBtn.setBounds(0, 68, 224, 44);
		marketBtn.setIcon(new ImageIcon("src/main/resources/image/line.png"));
		marketBtn.setContentAreaFilled(false);
		marketBtn.setBorder(null);
		add(marketBtn);

		stockListBtn = new JButton("   个股数据");
		stockListBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				stockListBtn.setForeground(new Color(248, 179, 29));
				stockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie-enter.png"));
				stockListBtn.setContentAreaFilled(false);
				stockListBtn.setBorder(null);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				stockListBtn.setForeground(new Color(216, 216, 216));
				stockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie.png"));
				stockListBtn.setContentAreaFilled(false);
				stockListBtn.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				frame.remove(opanel);
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
		stockListBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		stockListBtn.setForeground(new Color(216, 216, 216));
		stockListBtn.setIcon(new ImageIcon("src/main/resources/image/pie.png"));
		stockListBtn.setContentAreaFilled(false);
		stockListBtn.setBorder(null);
		stockListBtn.setBounds(0, 112, 224, 44);
		add(stockListBtn);

		optionalStockBtn = new JButton("   行业排行");
		optionalStockBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		optionalStockBtn.setForeground(new Color(248, 179, 29));
		optionalStockBtn.setIcon(new ImageIcon("src/main/resources/image/rank-enter.png"));
		optionalStockBtn.setContentAreaFilled(false);
		optionalStockBtn.setBorder(null);
		optionalStockBtn.setBounds(0, 156, 224, 44);
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

		JButton searchBtn = new JButton();
		searchBtn.setBounds(854, 15, 18, 18);
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

		opanel.add(searchBar);
		searchBar.setVisible(false);
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

				rowpos = -1;
				searchBar.setVisible(false);
			}
		});

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchBar.jump(frame, opanel);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String key = searchTextField.getText();
				if (key.equals("")) {
					searchBar.setVisible(false);
				} else {
					searchBar.showTable(key);
					searchBar.setBounds(697, 38, searchBar.getWidth(), searchBar.getHeight());
					searchBar.setVisible(true);
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					rowpos++;
					searchBar.setSelect(rowpos);
				}

				if (e.getKeyCode() == KeyEvent.VK_UP && rowpos > -1) {
					rowpos--;
					searchBar.setSelect(rowpos);
				}
			}
		});

		searchTextField.setBounds(686, 11, 196, 27);
		add(searchTextField);
		searchTextField.setColumns(10);

		// 雷达图
		chartPanel = new JPanel();
		chartPanel.setBounds(240, 70, 505, 380);
		chartPanel.setOpaque(false);
		add(chartPanel);

		String name[] = stockContrastBL.getList();
		int count = name.length;
		checkBox = new JCheckBox[count];

		for (int i = 0; i < count; i++) {
			checkBox[i] = new JCheckBox(name[i]);
			checkBox[i].setBounds(252 + i % 5 * 95, 465 + i / 5 * 35, 85, 25);
			checkBox[i].setOpaque(true);
			checkBox[i].setContentAreaFilled(false);
			add(checkBox[i]);
		}

		for (int i = 0; i < count; i++) {
			checkBox[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showChart(count);
				}
			});
		}
		
		showChart(count);

		// 排行
		final MyComboBox conditionBox = new MyComboBox();
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 15));
		conditionBox.setBounds(770, 80, 80, 25);
		conditionBox.setMaximumRowCount(20);
		String conditionstr[] = { "开盘价", "收盘价", "最高价", "最低价", "后复权价", "成交量", "换手率", "市盈率", "市净率", "涨跌幅" };

		for (int i = 0; i < conditionstr.length; i++) {
			conditionBox.addItem(conditionstr[i]);
		}

		conditionBox.setSelectedIndex(0);
		conditionBox.setOpaque(false);
		conditionBox.setBorder(null);
		add(conditionBox);

		// 排行表格
		rankPane = new JScrollPane();
		rankPane.setBounds(770, 115, 160, 400);
		rankPane.setOpaque(false);
		rankPane.setBorder(BorderFactory.createEmptyBorder());
		rankPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		rankPane.getViewport().setOpaque(false);
		add(rankPane);

		table = new JTable();
		table.setRowHeight(26);
		// 使表格居中
		MyTableCellRenderer r = new MyTableCellRenderer();
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
		table.setBorder(null);
		table.setEnabled(false);
		rankPane.setViewportView(table);
		rankPane.setOpaque(false);

		showRank(conditionBox.getItemAt(0).toString());
		
		rankDataList = new ArrayList<>();
		conditionBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String condition = e.getItem().toString();
				showRank(condition);
			}
		});

		// 点击其他地方使textfield不能输入
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTextField.setFocusable(false);
				searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				searchTextField.setText("输入股票代码或名称搜索");
				searchBar.setVisible(false);
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

	// 显示排行
	public void showRank(String condition) {

		rankDataList = stockItemBL.getRank(condition);
		int size = rankDataList.size();
		StockItemVO vo;
		String data[][] = new String[size][3];

		for (int i = 0; i < size; i++) {
			data[i][0] = i + 1 + "";
			vo = rankDataList.get(i);
			data[i][1] = vo.getStockname();
			data[i][2] = vo.getItem();
		}

		tableModel = new DefaultTableModel(data, new String[] { "排名", "股票名称", condition });
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(35);

		rankPane.repaint();
		rankPane.validate();
	}
	
	public void showChart(int count){
		// 每勾一次遍历一次
		nameList = new ArrayList<>();
		for (int j = 0; j < count; j++) {
			if (checkBox[j].isSelected()) {
				String name = checkBox[j].getText();
				nameList.add(name);
			}
		}
		// 获取新的图
		chartPanel.removeAll();
		// 如果为空，默认显示第一个
		if (nameList.isEmpty()) {
			nameList.add(checkBox[0].getText());
			checkBox[0].setSelected(true);
		}
		spiderChart = new SpiderChart(nameList, stockContrastBL);
		ChartPanel cpanel = spiderChart.getChart();
		cpanel.setPreferredSize(new Dimension(500, 370));
		chartPanel.add(cpanel);
		
		chartPanel.repaint();
		chartPanel.validate();
	}
}
