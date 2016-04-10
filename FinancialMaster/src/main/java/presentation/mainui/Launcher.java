package presentation.mainui;

import businesslogic.connection.connectionSubject;
import businesslogic.factory.InitFactory;

public class Launcher {
	public static void main(String[] args){
		InitFactory.getFactory(); // ≥ı ºªØ
		connectionSubject connectionSubject=new connectionSubject();
		Thread thread=new Thread(connectionSubject);
		thread.start();
		mainframe mainframe=new mainframe(connectionSubject);
	}
}
