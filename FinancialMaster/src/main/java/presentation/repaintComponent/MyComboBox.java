package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class MyComboBox extends JComboBox {

	public MyComboBox() {
		super();
		init();
	}

	public MyComboBox(ComboBoxModel model) {
		super(model);
		init();
	}

	public MyComboBox(Object[] items) {
		super(items);
		init();
	}

	public MyComboBox(Vector<?> items) {
		super(items);
		init();
	}

	private void init() {
		setOpaque(false);
		setUI(new MyComboBoxUI());
		setRenderer(new MyComboBoxRenderer());
		setBackground(new Color(0, 0, 0, 0));
	}

	public Dimension getPreferredSize() {
		return super.getPreferredSize();
	}
}
