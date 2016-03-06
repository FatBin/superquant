package presentation.stockcheckui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import presentation.repaintComponent.TextBubbleBorder;

@SuppressWarnings("serial")
public class StockDetail extends JPanel {
	JButton btnX;
	JButton button_2;
	private JTextField textField;
	private boolean click = false;
	DefaultTableModel tableModel;
	private int rowpos = -1;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "static-access" })
	public StockDetail(final JFrame frame) {
		setBorder(null);

		// setBounds(new Rectangle(960, 600));
		setLayout(null);
		final StockDetail detail = this;

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
		btnX.setBounds(711, 15, 16, 16);
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
		button_2.setBounds(684, 14, 16, 16);
		add(button_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 110, 696, 440);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);

		table = new JTable();
		table.setRowHeight(30);
		// 使表格居中
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setSelectionBackground(new Color(88, 93, 103, 200));
		table.setSelectionForeground(new Color(255, 255, 255, 230));
		table.setOpaque(false);
		// 选取行
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Point mousepoint;
				mousepoint = e.getPoint();
				rowpos = table.rowAtPoint(mousepoint);
				table.setRowSelectionInterval(rowpos, rowpos);
			}
		});
		scrollPane.setViewportView(table);
		table.setBorder(null);
		// table.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		table.setEnabled(false);
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
				new String[] { "日期", "开盘价", "最高价", "收盘价", "最低价", "交易量（股）", "交易金额（元）" });
		table.setModel(tableModel);

		setDragable(frame);

		// AliasingButton button = new AliasingButton();
		JButton button = new JButton();
		button.setBounds(634, 15, 18, 18);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		ImageIcon image1 = new ImageIcon("image/search.png");
		Image temp1 = image1.getImage().getScaledInstance(button.getWidth(), button.getHeight(),
				image1.getImage().SCALE_DEFAULT);
		image1 = new ImageIcon(temp1);
		button.setIcon(image1);
		// button.setIcon(new ImageIcon("image/search.png"));
		// button.setUI(new MyBottonUI());
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
		textField.setBounds(466, 11, 196, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u4E2A\u80A1\u8BE6\u60C5");
		lblNewLabel.setBackground(new Color(245, 245, 245));
		lblNewLabel.setBounds(25, 65, 151, 32);
		lblNewLabel.setFont(new Font("Lantinghei TC", Font.PLAIN, 22));
		add(lblNewLabel);
		
		button_1 = new JButton("back");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				detail.setVisible(false);
				PersonalStock.addLable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setForeground(new Color(216, 216, 216));
			}
		});
		button_1.setForeground(new Color(216, 216, 216));
		button_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		button_1.setContentAreaFilled(false);
		button_1.setBorder(null);
		button_1.setBounds(27, 15, 46, 16);
		add(button_1);

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

	}

	// 边框圆滑
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}

	// 设置界面可拖动
	Point loc = null;
	Point tmp = null;
	boolean isDragged = false;
	private JTable table;
	private JButton button_1;

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
