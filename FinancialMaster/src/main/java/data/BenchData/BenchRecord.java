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
			throw e;
		}
	}

}
