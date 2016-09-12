package data.StockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOimpl.TradeRecordAccurateDaoImpl;
import DAO.dao.TradeRecordAccurateDao;
import DAO.pojo.TradeRecordAccurate;
import DAO.pojo.TradeRecordAccurateId;

public class TradeRecordAccurateUpdate implements Runnable{
	public static final String[] UpToDateStocks={"http://www.shdjt.com/",".htm"};
	
	public static final String[] exchanges={"sh","sz","cy"};
	
	public TradeRecordAccurateDao tradeRecordAccurateDao;
	
	public TradeRecordAccurateUpdate(){
		Thread thread=new Thread(this);
		thread.start();
		tradeRecordAccurateDao=new TradeRecordAccurateDaoImpl();
	}
	
	public ArrayList<TradeRecordAccurate> getDatas(){
		ArrayList<TradeRecordAccurate> arrayList=new ArrayList<>();
		for (String string : exchanges) {
			try {
				Document document=Jsoup.connect(UpToDateStocks[0]+string+UpToDateStocks[1]).get();
				Elements elements=document.select("tr[height=25]");
				for (Element element : elements) {
					String[] temp=element.text().split(" ");
					TradeRecordAccurate tradeRecordAccurate=new TradeRecordAccurate();
					TradeRecordAccurateId tradeRecordAccurateId=new TradeRecordAccurateId();
					if(temp[1].charAt(0)=='6'){
						temp[1]="sh"+temp[1];
					}else {
						temp[1]="sz"+temp[1];
					}
					tradeRecordAccurateId.setStockId(temp[1]);
					tradeRecordAccurateId.setDate(Calendar.getInstance().getTime());
					tradeRecordAccurate.setId(tradeRecordAccurateId);
					tradeRecordAccurate.setPrice(Double.parseDouble(temp[5]));
					arrayList.add(tradeRecordAccurate);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return arrayList;
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				System.out.println(getDatas().size());
				tradeRecordAccurateDao.persist(getDatas());
				Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR);
				int minute=calendar.get(Calendar.MINUTE);
				if(hour==3&&minute==0){
					tradeRecordAccurateDao.clean();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
