package presentation.mainui;
import java.awt.Color;
import javax.swing.JFrame;

import presentation.stockmarketui.Marketui;;

@SuppressWarnings("serial")
public class Launcher extends JFrame {

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setUndecorated(true); // È¥µô±ß¿ò
		frame.setLayout(null);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Marketui panel = new Marketui(frame);
		panel.setBorder(null);
		panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
		frame.add(panel);
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 */

}
