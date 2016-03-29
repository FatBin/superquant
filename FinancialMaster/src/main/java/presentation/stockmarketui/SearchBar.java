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
	public SearchBar() {
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
	}
	
	public void showTable(String key){
		data = searchBL.getList(key);
		tableModel = new DefaultTableModel(data, new String[] { "" });
		table.setModel(tableModel);
		this.setVisible(true);
	}

	////////////////Î´Íê³É
	public void Jump(JFrame frame){
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				frame.getContentPane().removeAll();
				
				int pos = table.getSelectedRow();				
				String id = table.getValueAt(pos, 0).toString();
				
				StockDetail detail = new StockDetail(frame, id, searchPanel);
				detail.setBounds(224, 0, 737, getHeight());
				frame.getContentPane().add(detail);
				
				PersonalStock ppanel = new PersonalStock(frame);
				ppanel.setBounds(0, 0, 225, getHeight());
				frame.getContentPane().add(ppanel);
				
				frame.repaint();
			}
		});
	}
	
}
