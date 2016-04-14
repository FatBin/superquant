package presentation.mainui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businesslogic.connection.connectionSubject;
import javafx.scene.control.Labeled;

public class testframe extends JFrame implements Observer{

	private JPanel contentPane;
	connectionReminder connectionReminder;
	private Observable subject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					connectionSubject connectionSubject=new connectionSubject();
					Thread thread=new Thread(connectionSubject);
					thread.start();
					testframe frame = new testframe(connectionSubject);
					frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public testframe(Observable o) {
		JLabel test=new JLabel("hello");
		this.add(test);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		connectionReminder = new connectionReminder();
		connectionReminder.setBounds(0, 0, 960, 600);
		subject=o;
		subject.addObserver(this);
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,500,400);
		panel.setForeground(Color.green);
		
		JLabel label = new JLabel("ssss");
		label.setBounds(100, 100, 40, 30);
		panel.add(label);
		add(panel);
	}



	@Override
	public void update(Observable o, Object arg) {
		connectionSubject connectionSubject=(connectionSubject) o;
		Reminder(connectionSubject.getState());
	}
	
	public void Reminder(boolean state) {
		if (state) {
			this.add(connectionReminder);
			System.out.println("yes");
			repaint();
		}else {
			this.remove(connectionReminder);
			System.out.println("false");
			repaint();
		}
	}
}
