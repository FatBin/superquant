package presentation.mainui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import businesslogic.connection.connectionSubject;

import javax.swing.JLabel;

public class connectionReminder extends JPanel implements Runnable{

	ImageIcon image = new ImageIcon("src/main/resources/image/loading.gif");
	boolean connect = false;

	public connectionReminder(boolean connect) {
		setLayout(null);
		this.connect = connect;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setColor(new Color(0, 0, 0, 50));
//		g2d.fillRoundRect(0, 0, 960, 600, 12, 12);
		g2d.setColor(new Color(248, 247, 243));
		g2d.fillRoundRect(0, 0, 400, 270, 6, 6);
		g2d.drawImage(image.getImage(), 140, 43, 120, 120, this);
		g2d.setColor(new Color(84, 80, 76,200));
		g2d.setFont(new Font("微软雅黑",Font.PLAIN, 20));
		if (connect) {
			g2d.drawString("正在重连......", 120, 228);
		}else {
			g2d.setColor(new Color(84, 80, 76,200));
			g2d.setFont(new Font("微软雅黑",Font.PLAIN, 20));
			g2d.drawString("请检查网络是否正常连接", 95, 228);
		}
	}
	
	public boolean getConnect(){
		return connect;
	}
	public void setConnect(boolean connect){
		this.connect = connect;
	}

	@Override
	public void run() {
		while (true) {
		}
	}


}
