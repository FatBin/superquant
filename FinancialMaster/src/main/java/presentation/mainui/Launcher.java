package presentation.mainui;

import java.awt.Color;

import javax.swing.JFrame;

import businesslogic.connection.connectionSubject;
import businesslogic.factory.InitFactory;

public class Launcher {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
	    beginningPanel gif = new beginningPanel();
		gif.setOpaque(false);
		
		frame.setSize(600, 600);
		frame.setBackground(new Color(0, 0, 0, 0));
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
		mainframe.repaint();
		mainframe.validate();
		mainframe.setVisible(true);
		
	}
}
