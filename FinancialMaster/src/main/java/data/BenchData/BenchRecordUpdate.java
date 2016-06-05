package data.BenchData;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import PO.benchCurrentDataPO;

public class BenchRecordUpdate implements Runnable{
	public static final String[] benchRecentDataURL={
			"http://gupiao.baidu.com/stock/",
			".html?from=aladingpc"
			};
	public static final String[] BenchId={"sh000001","sz399001","sh000300"};
	public static benchCurrentDataPO[] benchCurrentDataPO;
	public BenchRecordUpdate(){
		benchCurrentDataPO=new benchCurrentDataPO[BenchId.length];
		Thread thread=new Thread(this);
		thread.start();
	}

	public benchCurrentDataPO getLastestRecord(String benchId){
		try {
			Document document=Jsoup.connect(benchRecentDataURL[0]+benchId+benchRecentDataURL[1]).get();
			Elements elements=document.select("div[class=stock-bets]");
			String temp1=elements.text().replace("%", "");
			String[] temp2=temp1.split(" ");
			benchCurrentDataPO po=new benchCurrentDataPO(
					temp2[2],
					temp2[3]+" "+temp2[4].substring(1),
					Double.parseDouble(temp2[5]), 
					Double.parseDouble(temp2[6]), 
					Double.parseDouble(temp2[7]), 
					Double.parseDouble(temp2[9]), 
					Double.parseDouble(temp2[11]), 
					Double.parseDouble(temp2[13]), 
					Double.parseDouble(temp2[15]), 
					temp2[17], 
					temp2[19], 
					temp2[21], 
					temp2[23], 
					temp2[25]
					);
			return po;
		} catch (IOException e) {
			e.printStackTrace();
			benchCurrentDataPO currentDataPO=new benchCurrentDataPO("", "", 0, 0, 0, 
					0, 0, 0, 0, "", "", "", "", "");
			return currentDataPO;
		} catch( Exception e){
			benchCurrentDataPO Currenpo=new benchCurrentDataPO("今天不开盘", "", 0, 0, 0, 
					0, 0, 0, 0, "", "", "", "", "");
			return Currenpo;
		}
	}
	
	public static benchCurrentDataPO getBenchCurrentDataPO(String benchId){
		benchCurrentDataPO po=null;
		for (int i = 0; i < BenchId.length; i++) {
			if (BenchId[i].equals(benchId)) {
				po=benchCurrentDataPO[i];
			}
		}
		return po;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				for (int i = 0; i < BenchId.length; i++) {
					benchCurrentDataPO[i] = getLastestRecord(BenchId[i]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
}
