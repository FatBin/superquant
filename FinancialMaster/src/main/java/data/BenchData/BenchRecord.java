package data.BenchData;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.BenchDataDaoService;
import DAO.pojo.Benchdata;
import PO.benchCurrentDataPO;
import dataservice.BenchDataService.BenchRecordService;

public class BenchRecord implements BenchRecordService{
	public static final String[] benchRecentDataURL={
			"http://gupiao.baidu.com/stock/",
			".html?from=aladingpc"
			};
	@Override
	public List getBenchData(String benchId, String starttime, String endtime) throws Exception{
		BenchDataDaoService benchDataDaoService=DaoFactory.getBenchdataDaoProxy();
		try {
			return benchDataDaoService.getBenchRecord(benchId, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public benchCurrentDataPO getLastestRecord(String benchId) throws Exception{
		try {
			Document document=Jsoup.connect(benchRecentDataURL[0]+benchId+benchRecentDataURL[1]).get();
			Elements elements=document.select("div[class=stock-bets]");
			Elements elements1=elements.get(0).select("div[class=price s-down ]");
			Elements temp=elements.get(0).select("div[class=bets-col-9]");
			Elements elements2=temp.get(0).select("dd");
			String[] first=elements1.text().replace("%", "").split(" ");
			String[] second=elements2.text().split(" ");
			System.out.println(elements1.text());
			benchCurrentDataPO po=new benchCurrentDataPO(
					Double.parseDouble(first[0]), 
					Double.parseDouble(first[1]), 
					Double.parseDouble(first[2]), 
					Double.parseDouble(second[0]), 
					Double.parseDouble(second[1]), 
					Double.parseDouble(second[2]), 
					Double.parseDouble(second[3]), 
					second[4], 
					second[5], 
					second[6], 
					second[7], 
					second[8]
					);
			return po;
		} catch (IOException e) {
			throw e;
		}
	}

}
