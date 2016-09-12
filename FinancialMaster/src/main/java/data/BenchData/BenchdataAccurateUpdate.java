package data.BenchData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import DAO.DAOimpl.BenchdataAccurateDaoImpl;
import DAO.dao.BenchdataAccurateDao;
import DAO.pojo.BenchdataAccurate;
import DAO.pojo.BenchdataAccurateId;

public class BenchdataAccurateUpdate implements Runnable{
	public static final String[] benchRecentDataURL={
			"http://gupiao.baidu.com/stock/",
			".html?from=aladingpc"
			};
	public static final String[] BenchId={"sh000001","sz399001","sh000300"};
	BenchdataAccurateDao benchdataAccurateDao;
	
	public BenchdataAccurateUpdate(){
		Thread thread=new Thread(this);
		thread.start();
		benchdataAccurateDao=new BenchdataAccurateDaoImpl();
	}
	
	public void persist(){
		for (int i=0;i<3;i++) {
			try {
				Document document=Jsoup.connect(benchRecentDataURL[0]+BenchId[i]+benchRecentDataURL[1]).get();
				String[] temp=document.select("div[class=price s-down ]").text().split(" ");
				BenchdataAccurate benchdataAccurate=new BenchdataAccurate();
				BenchdataAccurateId benchdataAccurateId=new BenchdataAccurateId();
				benchdataAccurateId.setBenchId(BenchId[i]);
				benchdataAccurateId.setDate(Calendar.getInstance().getTime());
				benchdataAccurate.setId(benchdataAccurateId);
				benchdataAccurate.setPrice(Double.parseDouble(temp[0]));
				benchdataAccurateDao.persist(benchdataAccurate);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				persist();
				Calendar calendar=Calendar.getInstance();
				int hour=calendar.get(Calendar.HOUR);
				int minute=calendar.get(Calendar.MINUTE);
				if(hour==3&&minute==0){
					benchdataAccurateDao.clean();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
