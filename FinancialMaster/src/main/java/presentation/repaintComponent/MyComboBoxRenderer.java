package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("rawtypes")
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
			renderer.setBackground(new Color(62,56,49,230));
			renderer.setForeground(new Color(248, 179, 28));
		} else {
			renderer.setBackground(new Color(248, 247, 243));
			renderer.setForeground(new Color(62,56,49,230));

		}
		list.setSelectionBackground(new Color(0, 0, 0, 0));
		list.setForeground(Color.black);
		list.setBorder(null);
		renderer.setHorizontalAlignment(JLabel.CENTER);
		renderer.getInsets().set(4, 6, 4, 6);
		return renderer;

	}

}
