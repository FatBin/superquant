package presentation.stockmarketui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businesslogic.factory.InitFactory;
import businesslogicservice.stockcheckblservice.StockSearchBLService;
import presentation.repaintComponent.MyScrollBarUI;
import presentation.stockcheckui.PersonalStock;
import presentation.stockcheckui.StockDetail;

@SuppressWarnings("serial")
public class SearchBar extends JPanel {

	private JTable table;
	DefaultTableModel tableModel;
	
	InitFactory factory = InitFactory.getFactory();
	private StockSearchBLService searchBL = factory.getStockSearchBL();
	private String[][] data;

	/**
	 * Create the panel.
	 */
	public SearchBar(JFrame frame, JPanel fromPanel) {

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
		scrollPane.getViewport().setSize(168, 198);
		scrollPane.getViewport().setPreferredSize(new Dimension(165, 195));
		add(scrollPane);

		table = new JTable();
		table.setRowHeight(26);
		table.setSelectionBackground(new Color(88, 93, 103, 200));
		table.setSelectionForeground(new Color(255, 255, 255, 230));
		scrollPane.setViewportView(table);
		

		// Êó±ê¼àÌý
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {

				int pos = table.getSelectedRow();
				table.setRowSelectionInterval(pos, pos);
				
				String id = table.getValueAt(pos, 0).toString();
				Jump(frame, id, fromPanel);
			}
		});

	}

	public void showTable(String key) {
		data = searchBL.getList(key);
		tableModel = new DefaultTableModel(data, new String[] { "" });
		table.setModel(tableModel);
		this.setVisible(true);
	}

	public void jump(JFrame frame, JPanel fromPanel) {
		int pos = table.getSelectedRow();
		if (pos != -1) {
			String id = table.getValueAt(pos, 0).toString();
			Jump(frame, id, fromPanel);
		}
	}

	public void Jump(JFrame frame, String id, JPanel fromPanel) {
		frame.getContentPane().removeAll();

		StockDetail detail = new StockDetail(frame, id, fromPanel, false);
		detail.setBounds(224, 0, 737, frame.getHeight());

		PersonalStock ppanel = new PersonalStock(frame);
		ppanel.setBounds(0, 0, 225, frame.getHeight());	
		
		frame.getContentPane().add(ppanel);
		frame.getContentPane().add(detail);
		frame.repaint();
		frame.validate();
	}

	public void setSelect(int row) {
		table.setRowSelectionInterval(row, row);
	}

	public JTable getTalbe(){
		return table;
	}
}
