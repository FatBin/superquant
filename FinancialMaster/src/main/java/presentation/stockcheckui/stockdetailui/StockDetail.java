package presentation.stockcheckui.stockdetailui;

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
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import VO.StockMarketVO;
import VO.StockVO;
import businesslogic.stockcheckbl.StockMessageBL;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockcheckui.StockList;
import presentation.stockmarketui.SearchBar;

@SuppressWarnings("serial")
public class StockDetail extends JPanel {
	JButton closeBtn;
	JButton miniBtn;
	private JTextField searchTextField;
	private boolean click = false;
	StockMessageBLService Message = new StockMessageBL();
	private StockVO datavo;
	private String data[][];
	private int rowpos = -1;
	private StockMarketVO stockMarketVO;
	private SearchBar searchBar;
	private Color brown = new Color(54, 42, 29);
	private Color green = new Color(37, 120, 38);
	private Color red = new Color(179, 43, 56);
	boolean isDragged = false;
	private JButton backBtn;
	private JTextField belowTextField;
	private JTextField aboveTextField;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access" })
	public StockDetail(final JFrame frame, final String id) {

		// JFrame loadframe = new JFrame();
		// loadframe.setAlwaysOnTop(true);
		// loadframe.setUndecorated(true);
		// loadingPanel loadingPanel = new loadingPanel();
		// loadingPanel.setOpaque(false);
		//
		// loadframe.setSize(120, 120);
		// loadframe.setBackground(new Color(0, 0, 0, 0));
		// loadframe.getContentPane().add(loadingPanel);
		// loadframe.setBounds(frame.getX()+533, frame.getY()+215, 120,120);
		// loadframe.repaint();
		// loadframe.setVisible(true);

		datavo = Message.getStockMessage(id);

		// loadframe.dispose();

		stockMarketVO = datavo.getStockMarketVO();
		data = datavo.getHistory_data();

		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);

		setBorder(null);
		setLayout(null);
		final StockDetail detail = this;

		searchBar = new SearchBar(frame, detail);
		detail.add(searchBar);
		searchBar.setVisible(false);

		JScrollPane contentScroll = new JScrollPane();
		contentScroll.setBounds(0, 51, 730, 540);
		contentScroll.setOpaque(false);
		contentScroll.getViewport().setOpaque(false);
		contentScroll.setBorder(BorderFactory.createEmptyBorder());
		contentScroll.getVerticalScrollBar().setUI(new MyScrollBarUI());

		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(710, 1220));
		content.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 14));

		belowTextField = new JTextField();
		aboveTextField = new JTextField();

		IntentPane1 intentPane1 = new IntentPane1(id, searchBar);
		IntentPane2 intentPane2 = new IntentPane2(data, datavo, searchBar);
		IntentPane3 intentPane3 = new IntentPane3(data, belowTextField, aboveTextField, searchBar, frame, id);

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

		miniBtn = new JButton("－");
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
				frame.setExtendedState(frame.ICONIFIED); // 最小化窗口
			}
		});
		miniBtn.setForeground(new Color(216, 216, 216));
		miniBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		miniBtn.setContentAreaFilled(false);
		miniBtn.setBorder(null);
		miniBtn.setBounds(680, 14, 16, 16);
		add(miniBtn);

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
		searchTextField.setText("�����Ʊ��������");
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
				searchBar.setVisible(false);
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

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					searchBar.setVisible(false);
					detail.setVisible(false);
					String anotherID = searchBar.getID();
					StockDetail another = new StockDetail(frame, anotherID);
					frame.getContentPane().add(another);
					another.setBounds(224, 0, 737, getHeight());
					another.setVisible(true);
					frame.repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				String key = searchTextField.getText();
				if (key.equals("")) {
					searchBar.setVisible(false);
				} else if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_UP) {
					searchBar.showTable(key);
					searchBar.setBounds(476, 38, searchBar.getWidth(), searchBar.getHeight());

					if (searchBar.getRowCount() > 0) {
						searchBar.setVisible(true);
					} else {
						searchBar.setVisible(false);
					}

					rowpos = -1;
				}

				if (searchBar.getRowCount() > 0) {
					if (e.getKeyCode() == KeyEvent.VK_DOWN && rowpos < searchBar.getRowCount() - 1) {
						rowpos++;
						searchBar.setSelect(rowpos, true);
					}

					if (e.getKeyCode() == KeyEvent.VK_UP && rowpos > 0) {
						rowpos--;
						searchBar.setSelect(rowpos, false);
					}
				}
			}
		});

		JLabel name = new JLabel("����300");
		name.setBounds(70, 14, 80, 26);
		name.setForeground(brown);
		add(name);

		// �����ǵ���
		JLabel change = new JLabel();
		double changeRange = stockMarketVO.getChangeRange();
		change.setText((changeRange + "").substring(0, 7));
		change.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		change.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			change.setForeground(red);
		} else if (changeRange < 0) {
			change.setForeground(green);
		}
		change.setBounds(231, 14, 60, 24);
		add(change);

		ImageIcon upArrowIcon = new ImageIcon("src/main/resources/image/upArrow.png");
		Image tempArrow = upArrowIcon.getImage().getScaledInstance(14, 9, upArrowIcon.getImage().SCALE_SMOOTH);
		upArrowIcon = new ImageIcon(tempArrow);
		ImageIcon downArrowIcon = new ImageIcon("src/main/resources/image/downArrow.png");
		tempArrow = downArrowIcon.getImage().getScaledInstance(14, 9, downArrowIcon.getImage().SCALE_SMOOTH);
		downArrowIcon = new ImageIcon(tempArrow);

		JLabel upArrow = new JLabel();
		upArrow.setBounds(210, 22, 14, 9);
		upArrow.setOpaque(false);
		if (changeRange > 0) {
			upArrow.setIcon(upArrowIcon);
		} else {
			upArrow.setIcon(downArrowIcon);
		}
		add(upArrow);

		// �����ּ�
		JLabel nowMarket = new JLabel();
		double now = stockMarketVO.getClose();
		nowMarket.setText((now + "").substring(0, 7));
		nowMarket.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		nowMarket.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			nowMarket.setForeground(red);
		} else if (changeRange < 0) {
			nowMarket.setForeground(green);
		}
		nowMarket.setBounds(135, 14, 60, 24);
		add(nowMarket);

		// �����ǵ���
		JLabel marketUpAndDown = new JLabel();
		double marketup = stockMarketVO.getUps_and_downs();
		marketUpAndDown.setText(nf.format(marketup));
		marketUpAndDown.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		marketUpAndDown.setForeground(new Color(62, 56, 49, 240));
		if (changeRange > 0) {
			marketUpAndDown.setForeground(red);
		} else if (changeRange < 0) {
			marketUpAndDown.setForeground(green);
		}
		marketUpAndDown.setBounds(328, 14, 60, 24);
		add(marketUpAndDown);

		JLabel downArrow = new JLabel();
		downArrow.setBounds(305, 22, 14, 9);
		downArrow.setOpaque(false);
		if (changeRange > 0) {
			downArrow.setIcon(upArrowIcon);
		} else {
			downArrow.setIcon(downArrowIcon);
		}
		add(downArrow);

		backBtn = new JButton("back");
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchBar.setVisible(false);
				frame.remove(detail);
				StockList listui = new StockList(frame);
				listui.setBounds(224, 0, 737, getHeight());
				frame.getContentPane().add(listui);
				frame.repaint();
				frame.validate();
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

		// ���scrollPane
		content.add(intentPane1);
		content.add(intentPane2);
		content.add(intentPane3);
		contentScroll.setViewportView(content);
		contentScroll.getVerticalScrollBar().setUnitIncrement(20);
		add(contentScroll);

		// ��������ط�ʹtext field��������
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reSet();
			}

		});
		
		intentPane1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reSet();
			}

		});
		
		intentPane2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reSet();
			}

		});
		
		intentPane3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reSet();
			}

		});
	}

	// �߿�Բ��
	protected void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon("src/main/resources/image/right.png");
		g.drawImage(image.getImage(), 0, 0, getSize().width - 1, getSize().height - 1, this);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	// ���ý�����϶�
	Point loc = null;
	Point tmp = null;

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
	
	public void reSet(){
		searchBar.setVisible(false);
		searchTextField.setFocusable(false);
		searchTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		searchTextField.setText("�����Ʊ��������");

		belowTextField.setFocusable(false);
		belowTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		belowTextField.setText("��������");

		aboveTextField.setFocusable(false);
		aboveTextField.setBorder(new TextBubbleBorder(new Color(197, 197, 197), 1, 30, 0));
		aboveTextField.setText("��������");
	}
}
