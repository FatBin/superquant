package presentation.mainui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class loadingPanel extends JPanel{
	ImageIcon image = new ImageIcon("");
	
	
	public loadingPanel(){
		setSize(500,500);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image.getImage(), 50, 50,150,150,this);
	}

}
