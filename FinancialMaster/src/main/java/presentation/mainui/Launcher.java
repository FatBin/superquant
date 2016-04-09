package presentation.mainui;

import businesslogic.connectionSubject.connectionSubject;

public class Launcher {
	public static void main(String[] args){
		connectionSubject connectionSubject=new connectionSubject();
		Thread thread=new Thread(connectionSubject);
		thread.start();
		mainframe mainframe=new mainframe(connectionSubject);
	}
}
