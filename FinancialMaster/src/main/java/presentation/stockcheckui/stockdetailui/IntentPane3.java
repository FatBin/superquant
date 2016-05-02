package presentation.stockcheckui.stockdetailui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import VO.StockVO;
import businesslogic.stockcheckbl.StockMessageBL;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import presentation.repaintComponent.DateChooser;
import presentation.repaintComponent.HeaderCellRenderer;
import presentation.repaintComponent.IntentPane;
import presentation.repaintComponent.MyComboBox;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.repaintComponent.MyTableCellRenderer;
import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockmarketui.SearchBar;

@SuppressWarnings("serial")
public class IntentPane3 extends IntentPane {
	private Color brown = new Color(54, 42, 29);
	private Color green = new Color(37, 120, 38);
	private Color red = new Color(179, 43, 56);
	private JTable table;
	DefaultTableModel tableModel;
	private int rowpos = -1;
	private JLabel percentLbl;
	private JLabel timeGotolbl;
	private boolean click1 = false;
	private boolean click2 = false;
	StockMessageBLService Message = new StockMessageBL();

	@SuppressWarnings("unchecked")
	public IntentPane3(String data[][],final JTextField belowTextField, final JTextField aboveTextField, SearchBar searchBar,
			JFrame frame, String id) {
		IntentPane intentPane3 = this;

		intentPane3.setPreferredSize(new Dimension(700, 404));
		intentPane3.setLayout(null);

		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);

		JLabel historyLbl = new JLabel("历史数据");
		historyLbl.setBackground(new Color(245, 245, 245));
		historyLbl.setForeground(brown);
		historyLbl.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		historyLbl.setBounds(10, 5, 200, 32);
		intentPane3.add(historyLbl);

		Date today = new Date();
		Date dbefore = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1); // 前一个月
		dbefore = calendar.getTime();

		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		final String start = dt.format(dbefore);
		final JLabel startTimelbl = new JLabel(start);
		startTimelbl.setBounds(456, 15, 90, 22);
		dateChooser1.register(startTimelbl);
		intentPane3.add(startTimelbl);

		DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
		final String end = dt.format(today);
		final JLabel endTimelbl = new JLabel(end);
		endTimelbl.setBounds(586, 15, 90, 22);
		dateChooser2.register(endTimelbl);
		intentPane3.add(endTimelbl);

		JScrollPane stockDetailPane = new JScrollPane();
		stockDetailPane.setBounds(0, 50, 700, 300);
		stockDetailPane.setOpaque(false);
		stockDetailPane.setBorder(BorderFactory.createEmptyBorder());
		stockDetailPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		stockDetailPane.getViewport().setOpaque(false);
		intentPane3.add(stockDetailPane);

		// init the table
		table = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				int modelRow = convertRowIndexToModel(row);
				int modelColumn = convertColumnIndexToModel(column);
				Component comp = super.prepareRenderer(renderer, row, column);
				// if (!isRowSelected(modelRow)) {
				int temp = modelRow;
				double close = Double.parseDouble(this.getModel().getValueAt(temp, 1).toString());
				if (modelColumn == 2 || modelColumn == 3 || modelColumn == 4 || modelColumn == 5) {
					if (Double.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) > close) {
						comp.setForeground(red);
					} else if (Double
							.parseDouble(this.getModel().getValueAt(modelRow, modelColumn).toString()) == close) {
						comp.setForeground(new Color(62, 56, 49, 240));
					} else {
						comp.setForeground(green);
					}
				} else // 不符合条件的保持原表格样式
					comp.setForeground(new Color(62, 56, 49, 240));
				// }
				return comp;
			}
		};

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
				searchBar.setVisible(false);
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

		table.getColumnModel().getColumn(0).setPreferredWidth(118);
		for (int i = 1; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(63);
		}
		table.getColumnModel().getColumn(6).setPreferredWidth(118);

		JTableHeader header = table.getTableHeader();
		header.setOpaque(false);
		header.getTable().setOpaque(false);
		header.getTable().setIntercellSpacing(new Dimension(0, getHeight()));
		header.setDefaultRenderer(new HeaderCellRenderer());

		JLabel labelz = new JLabel("至");
		labelz.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		labelz.setBounds(554, 13, 25, 22);
		intentPane3.add(labelz);

		final MyComboBox conditionBox = new MyComboBox();
		conditionBox.setOpaque(false);
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 16));
		String[] item = { "筛选关键字", "开盘价", "最高价", "最低价", "收盘价", "后复权价", "成交量", "换手率", "市盈率", "市净率" };
		for (int i = 0; i < 10; i++) {
			conditionBox.addItem(item[i]);
		}
		conditionBox.setBorder(null);
		conditionBox.setBounds(10, 362, 120, 25);
		conditionBox.setSelectedIndex(0);
		intentPane3.add(conditionBox);

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
		belowTextField.setBounds(146, 362, 97, 27);
		belowTextField.setText("输入下限");
		intentPane3.add(belowTextField);

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
				searchBar.setVisible(false);
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
		aboveTextField.setBounds(269, 362, 97, 27);
		aboveTextField.setText("输入上限");
		intentPane3.add(aboveTextField);

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
				searchBar.setVisible(false);

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

				frame.repaint();
				frame.validate();
			}
		});
		timeGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		timeGotolbl.setForeground(new Color(150, 150, 150));
		timeGotolbl.setBounds(682, 13, 25, 22);
		intentPane3.add(timeGotolbl);

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
				searchBar.setVisible(false);

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

					percentLbl.setText("筛选结果占比：" + nf.format(datavo.getRatio()));

					frame.repaint();
					frame.validate();
				}
			}
		});
		conditionGotolbl.setForeground(new Color(150, 150, 150));
		conditionGotolbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		conditionGotolbl.setBounds(382, 362, 25, 22);
		intentPane3.add(conditionGotolbl);

		// 筛选比例
		StockVO datavo = Message.getStockMessage(id);
		percentLbl = new JLabel("筛选结果占比: " + nf.format(datavo.getRatio()));
		conditionBox.setFont(new Font("Lantinghei TC", Font.PLAIN, 18));
		percentLbl.setBounds(530, 367, 250, 25);
		intentPane3.add(percentLbl);
	}
}
