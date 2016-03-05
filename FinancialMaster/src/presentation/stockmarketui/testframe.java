package presentation.stockmarketui;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;

import presentation.stockmarketui.Marketui;;

@SuppressWarnings("serial")
public class testframe extends JFrame {

//	JFrame frame;
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		testframe test = new testframe();
		JFrame frame = new JFrame();
//		frame = new JFrame();
		frame.setUndecorated(true); // È¥µô±ß¿ò
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Marketui panel = new Marketui(frame);
		panel.setBorder(null);
		frame.add(panel);
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
//	public testframe() {
//		frame = new JFrame();
//		setUndecorated(true); // È¥µô±ß¿ò
//		setBackground(new Color(0, 0, 0, 0));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(960, 600);
//		setLocationRelativeTo(null);
//		setResizable(false);
//		Marketui panel = new Marketui(frame);
//		panel.setBorder(null);
//		add(panel);
//		setVisible(true);
//
//	}

}
