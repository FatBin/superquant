package presentation.stockmarketui;

import java.awt.Color;
//import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import businesslogic.stockcheckbl.StockSearchBL;
import businesslogicservice.stockcheckblservice.StockSearchBLService;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockDetail;
import presentation.stockcheckui.StockList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class SearchBar extends JPanel {

	private JTable table;
	DefaultTableModel tableModel;
	private StockSearchBLService searchBL = new StockSearchBL();
	private String[][] data;
	private SearchBar searchPanel;

	/**
	 * Create the panel.
	 */
	public SearchBar(JFrame frame) {
		searchPanel = this;

		this.setSize(175, 200);
		this.setBackground(new Color(255, 255, 255));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(1, 1, 168, 198);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);

		table = new JTable();
		table.setRowHeight(26);
		table.setPreferredSize(new Dimension(165, 195));
		scrollPane.setViewportView(table);

		// Êó±ê¼àÌý
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				int pos = table.getSelectedRow();
				String id = table.getValueAt(pos, 0).toString();

				Jump(frame, id);
			}
		});

	}

	public void showTable(String key) {
		data = searchBL.getList(key);
		tableModel = new DefaultTableModel(data, new String[] { "" });
		table.setModel(tableModel);
		this.setVisible(true);
	}

	public void jump(JFrame frame) {
		int pos = table.getSelectedRow();
		if (pos != -1) {
			String id = table.getValueAt(pos, 0).toString();
			Jump(frame, id);
		}
	}

	public void Jump(JFrame frame, String id) {
		frame.getContentPane().removeAll();

		StockDetail detail = new StockDetail(frame, id, searchPanel);
		detail.setBounds(224, 0, 737, frame.getHeight());
		frame.getContentPane().add(detail);

		PersonalStock ppanel = new PersonalStock(frame);
		ppanel.setBounds(0, 0, 225, frame.getHeight());
		frame.getContentPane().add(ppanel);

		frame.repaint();
	}

	public void setSelect(int row) {
		table.setRowSelectionInterval(row, row);
		table.setSelectionBackground(new Color(88, 93, 103, 200));
		table.setSelectionForeground(new Color(255, 255, 255, 230));
	}

}
