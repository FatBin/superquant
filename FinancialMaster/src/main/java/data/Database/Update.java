package data.Database;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import PO.industriesPO;

public class Update implements Runnable{
	
	public Update(){
		Thread thread=new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Calendar calendar=Calendar.getInstance();
			int hour=calendar.get(Calendar.HOUR);
			int minute=calendar.get(Calendar.MINUTE);
			
			if(hour==15&&minute==0){
				//update the benchdata
				BenchDataUpdate benchDataUpdate=new BenchDataUpdate();
				benchDataUpdate.benchUpdate();
				//update the industries
				IndustriesUpdate industriesUpdate=new IndustriesUpdate();
				industriesUpdate.IndustriesUpdate();
				//update the tradeRecord
				TradeRecordUpdate tradeRecordUpdate=new TradeRecordUpdate();
				tradeRecordUpdate.TradeRecordUpdate(tradeRecordUpdate.getTradeRecord());
			}
			
		}
	}
}
