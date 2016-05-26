package data.Database;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Update implements Runnable{
	public static final String[] benchId={"sh000001","sh000300","sz399001"};
	public static final String[] benchdataURL=
		{"http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/",
		 "/type/S.phtml?year=",
		 "&jidu="};
	
	public Update(){
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public static Document getDocument(String url){
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
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
			
			/*
			 * update the benchdata
			 */
			if(hour==21&&minute==0){
				int year=calendar.get(Calendar.YEAR);
				int quarter=calendar.get(Calendar.MONTH)%4+1;
				for(int i=0;i<benchId.length;i++){
					String benchId_part=benchId[i].substring(2);
					String url=benchdataURL[0]+benchId_part+benchdataURL[1]+year+benchdataURL[2]+quarter;
					Document document=getDocument(url);
					BenchDataUpdate benchDataUpdate=new BenchDataUpdate();
					benchDataUpdate.updateBench(document, benchId[i]);
				}
			}
		}
	}
}
