package presentation.mainui;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businesslogic.connectionSubject.connectionSubject;

public class testframe extends JFrame implements Observer{

	private JPanel contentPane;
	private connectionSubject subject;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 25, 202, 131);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		subject=new connectionSubject();
		subject.addObserver(this);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		connectionSubject connectionSubject=(connectionSubject) o;
		System.out.println(connectionSubject.getState());
		this.setEnabled(connectionSubject.getState());
	}
}
