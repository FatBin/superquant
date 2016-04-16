package presentation.mainui;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

import businesslogic.connection.connectionSubject;
import oracle.jrockit.jfr.JFR;
import presentation.stockmarketui.Marketui;
import sun.launcher.resources.launcher;;

@SuppressWarnings("serial")
public class mainframe extends JFrame implements Observer{
	private Observable observable;
	private connectionReminder connectionReminder;
	private JFrame connection;
	/**
	 * Create the frame.
	 */
	public mainframe(Observable observable) {
		this.setUndecorated(true); // È¥µô±ß¿ò
		this.setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(960, 600);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Marketui panel = new Marketui(this);
		panel.setBorder(null);
		panel.setBounds(0,0,this.getWidth(),this.getHeight());
		this.add(panel);
		this.setVisible(true);
		
		this.observable=observable;
		//Ìí¼Ó
		observable.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof connectionSubject) {
			connectionSubject connectionSubject=(connectionSubject) o;
			if(connectionSubject.getState()==true){
				connection = new JFrame();
				connection.setUndecorated(true);
				connection.setAlwaysOnTop(true);
				connection.setSize(960, 600);
				connection.setOpacity(0);
				connection.setLocationRelativeTo(null);
				
				
				connectionReminder= new connectionReminder();
				connection.getContentPane().add(connectionReminder);
				connection.repaint();
				connection.setVisible(true);
//				connectionReminder.updateUI();
//				connectionReminder.setEnabled(false);
//				this.setComponentZOrder(connectionReminder, 0);
//				invalidate();
//				repaint();
			}else {
				connection.dispose();
			}
		}
	}
}
