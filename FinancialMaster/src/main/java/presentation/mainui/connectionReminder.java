package presentation.mainui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import businesslogic.connection.connectionSubject;

import javax.swing.JLabel;

public class connectionReminder extends JPanel implements Runnable{

	private JLabel label;
	private ImageIcon image = new ImageIcon("");

	public connectionReminder() {
		setLayout(null);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(new Color(0, 0, 0,50));
		g2d.fillRoundRect(0, 0, 960, 600, 12, 12);
		g2d.setColor(new Color(248, 247, 243));
		g2d.fillRoundRect(280, 132, 400, 270, 6, 6);
	}
	
	
	@Override
	public void run() {
		while (true) {//‘À––∂Øª≠
		}
	}

}
