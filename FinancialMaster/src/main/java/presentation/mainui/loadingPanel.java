package presentation.mainui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class loadingPanel extends JPanel{
	ImageIcon image = new ImageIcon("src/main/resources/image/loading.gif");
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image.getImage(), this.getX(),this.getY(),this.getWidth(),this.getHeight(),this);
	}

	public static void main(String[] args){
		JFrame loadframe = new JFrame();
		loadframe.setAlwaysOnTop(true);
		loadframe.setUndecorated(true);
		loadingPanel loadingPanel = new loadingPanel();
		loadingPanel.setOpaque(false);
		
		loadframe.setSize(120, 120);
		loadframe.setBackground(new Color(0, 0, 0, 0));
		loadframe.getContentPane().add(loadingPanel);
//		loadframe.setBounds(frame.getX()+533, frame.getY()+215, 120,120);
		loadframe.repaint();
		loadframe.validate();
		loadframe.setVisible(true);
	}
}
