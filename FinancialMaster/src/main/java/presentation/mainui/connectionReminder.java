package presentation.mainui;

import javax.swing.JPanel;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JToolBar;

import businesslogic.connection.connectionSubject;

import javax.swing.JLabel;

public class connectionReminder extends JPanel implements Runnable{

	private JLabel label;

	public connectionReminder() {
		setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(168, 46, 97, 17);
		add(toolBar);
		
		label = new JLabel("重连中");
		label.setBounds(400, 500, 100, 100);
		toolBar.add(label);
		
	}

	@Override
	public void run() {
		while (true) {//运行动画
		}
	}

}
