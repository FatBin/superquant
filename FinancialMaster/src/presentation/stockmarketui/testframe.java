package presentation.stockmarketui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import presentation.repaintComponent.TextBubbleBorder;
import presentation.stockmarketui.Marketui;;

@SuppressWarnings("serial")
public class testframe extends JFrame {

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testframe frame = new testframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testframe() {
		setUndecorated(true); // È¥µô±ß¿ò
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		Marketui panel = new Marketui(this);
		panel.setBorder(null);
		setContentPane(panel);
		

	}

}
