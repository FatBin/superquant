package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MyComboBoxRenderer implements ListCellRenderer {

	private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

	public MyComboBoxRenderer() {
		super();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JLabel renderer = (JLabel) defaultCellRenderer.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (isSelected) {
			// renderer.setBackground(new Color(248,179,28));
			renderer.setBackground(new Color(95,99,108,200));
			renderer.setForeground(new Color(248, 179, 28));
		} else {
			renderer.setBackground(new Color(245, 245, 245, 150));
			renderer.setForeground(new Color(95,99,108));

		}
		list.setSelectionBackground(new Color(0, 0, 0, 0));
		list.setForeground(Color.black);
		list.setBorder(null);
		// renderer.setFont(new Font("Î¢ÈíÑÅºÚ", style, size));
		renderer.setHorizontalAlignment(JLabel.CENTER);
		renderer.getInsets().set(4, 6, 4, 6);
		return renderer;

	}

}
