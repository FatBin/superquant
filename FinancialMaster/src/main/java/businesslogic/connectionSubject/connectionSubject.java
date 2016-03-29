package businesslogic.connectionSubject;

import java.util.Observable;

import data.IO.ConnectionChecker;
import dataservice.connection.connection;

public class connectionSubject extends Observable implements Runnable{
	private	boolean state;
	
	public connectionSubject(){}
	
	public void measurementsChanged(){
		setChanged();
		notifyObservers();
	}
	
	public void setMeasurements(boolean state){
		this.state=state;
		measurementsChanged();
	}
	
	public boolean getState() {
		return state;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		connection connection=new ConnectionChecker();
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setMeasurements(connection.checkconnection());
		}
	}
}
