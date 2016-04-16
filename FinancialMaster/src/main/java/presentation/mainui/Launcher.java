package presentation.mainui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

import businesslogic.connection.connectionSubject;
import businesslogic.factory.InitFactory;

public class Launcher {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
	    beginningPanel gif = new beginningPanel();
		gif.setOpaque(false);
		
		frame.setSize(600, 600);
		AWTUtilities.setWindowOpaque(frame, false);
		frame.setIconImage(new ImageIcon("src/main/resources/image/Superman.png").getImage());
		frame.getContentPane().add(gif);
		frame.setLocationRelativeTo(null);
		frame.repaint();
		frame.setVisible(true);
		
		
		InitFactory.getFactory(); // ≥ı ºªØ
		frame.dispose();
		connectionSubject connectionSubject=new connectionSubject();
		Thread thread=new Thread(connectionSubject);
		thread.start();
		mainframe mainframe=new mainframe(connectionSubject);
		mainframe.validate();
		mainframe.setVisible(true);
		mainframe.repaint();
		mainframe.getContentPane().repaint();
		
	}
}
