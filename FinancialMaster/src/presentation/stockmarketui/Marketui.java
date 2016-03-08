package presentation.stockmarketui;

import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ENUM.date_enum;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import javafx.scene.control.TabPane;
import presentation.OptionalStock.OptionalStock;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockList;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Marketui extends JPanel {

	JButton btnNewButton;
	JButton button;
	JButton button_1;
	JButton btnX;
	JButton button_2;
	private JTextField textField;
	private boolean click = false;
	private JTable dayTable;
	private JTable weekTable;
	private JTable monthTable;
	private JTable halfYearTable;
	private JTable yearTable;
	private JTable fiveYearTable;
	private JTable tenYearTable;
	private StockMarketBLService stockMarketBL = new businesslogic.stockmarketbl.StockMarketBL();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public Marketui(final JFrame frame) {
		setBorder(null);

		setLayout(null);
		final Marketui mpanel = this;

		btnNewButton = new JButton("\u5927\u76D8\u6570\u636E");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(248, 179, 29));
		btnNewButton.setBounds(68, 68, 117, 44);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		add(btnNewButton);

		button = new JButton("\u4E2A\u80A1\u6570\u636E");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(new Color(248, 179, 29));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(new Color(216, 216, 216));
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
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		button.setForeground(new Color(216, 216, 216));
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

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(mpanel);
				OptionalStock opanel = new OptionalStock(frame);
				frame.getContentPane().add(opanel);
				opanel.setBounds(0, 0, getWidth(), getHeight());
				frame.repaint();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		button_1.setForeground(new Color(216, 216, 216));
		button_1.setContentAreaFilled(false);
		button_1.setBorder(null);
		button_1.setBounds(75, 156, 117, 44);
		add(button_1);

		btnX = new JButton("X");
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setForeground(new Color(252, 98, 93));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnX.setForeground(new Color(216, 216, 216));
			}
		});
		btnX.setForeground(new Color(216, 216, 216));
		btnX.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnX.setContentAreaFilled(false);
		btnX.setBorder(null);
		btnX.setBounds(931, 15, 16, 16);
		add(btnX);

		button_2 = new JButton("—");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_2.setForeground(new Color(253, 188, 64));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setForeground(new Color(216, 216, 216));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(frame.ICONIFIED); // 最小化
			}
		});
		button_2.setForeground(new Color(216, 216, 216));
		button_2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		button_2.setContentAreaFilled(false);
		button_2.setBorder(null);
		button_2.setBounds(904, 14, 16, 16);
		add(button_2);

		dayTable = new JTable();
		dayTable.setBounds(247, 110, 696, 440);
		add(dayTable);
		
		weekTable = new JTable();
		weekTable.setBounds(247, 110, 696, 440);
		add(weekTable);
		
		monthTable = new JTable();
		monthTable.setBounds(247, 110, 696, 440);
		add(monthTable);
		
		halfYearTable = new JTable();
		halfYearTable.setBounds(247, 110, 696, 440);
		add(halfYearTable);
		
		yearTable = new JTable();
		yearTable.setBounds(247, 110, 696, 440);
		add(yearTable);
		
		fiveYearTable = new JTable();
		fiveYearTable.setBounds(247, 110, 696, 440);
		add(fiveYearTable);
		
		tenYearTable = new JTable();
		tenYearTable.setBounds(247, 110, 696, 440);
		add(tenYearTable);
		
		JTabbedPane tabbedPane= new JTabbedPane();
		tabbedPane.setBounds(247, 98, 696, 440);
		tabbedPane.addTab("当天", dayTable);
		tabbedPane.addTab("一周", weekTable);
		tabbedPane.addTab("一个月", monthTable);
		tabbedPane.addTab("半年", halfYearTable);
		tabbedPane.addTab("一年", yearTable);
		tabbedPane.addTab("五年", fiveYearTable);
		tabbedPane.addTab("十年", tenYearTable);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane tab = (JTabbedPane)e.getSource();
				int selectedIndex = tab.getSelectedIndex();
				switch (selectedIndex) {
				case 0:
					StockMarketVO stockMarketVO = stockMarketBL.getStockMarket("sh", date_enum.Day);
//					String[][] data = stockMarketVO.
					break;

				default:
					break;
				}
				
			}
		});
		add(tabbedPane);
		
		

		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		comboBox.setBounds(245, 65, 151, 32);
		comboBox.addItem("上证指数");
		comboBox.addItem("深证指数");
		comboBox.setSelectedIndex(0);
		comboBox.setOpaque(false);
		comboBox.setBorder(null);
		add(comboBox);

		// AliasingButton button = new AliasingButton();
		JButton button = new JButton();
		button.setBounds(854, 15, 18, 18);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		ImageIcon image1 = new ImageIcon("image/search.png");
		Image temp1 = image1.getImage().getScaledInstance(button.getWidth(), button.getHeight(),
				image1.getImage().SCALE_DEFAULT);
		image1 = new ImageIcon(temp1);
		button.setIcon(image1);
		button.setMargin(new Insets(0, 0, 0, 0));
		add(button);

		textField = new JTextField();
		textField.setFocusable(false);
		textField.setOpaque(false);
		textField.setForeground(new Color(150, 150, 150));
		textField.setCaretColor(new Color(150, 150, 150));
		textField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!click) {
					textField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				click = true;
				textField.setBorder(new TextBubbleBorder(new Color(150, 150, 150), 1, 30, 0));
				textField.setFocusable(true);
				textField.requestFocus();
			}
		});
		textField.setBounds(686, 11, 196, 27);
		add(textField);
		textField.setColumns(10);

		// 点击其他地方使textfield不能输入
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setFocusable(false);
				textField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
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
