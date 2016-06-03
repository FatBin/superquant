package data.StockData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import PO.RiseStockPO;
import PO.UpToDateStockPO;
import dataservice.StockDataService.StockDataService;

public class StockData implements StockDataService{
	public static final String[] riseStock={
			"http://data.10jqka.com.cn/rank/lxsz/field/lxts/order/desc/page/",
			"/ajax/1/"
			};
	public static final int[] page={1,2,3,4,5,6,7,8,9,10};
	
	public static final String[] UpToDateStocks={"http://www.shdjt.com/",".htm"};
	@Override
	public ArrayList<RiseStockPO> getRiseStock() throws Exception{
		try {
			ArrayList<RiseStockPO> arrayList=new ArrayList<>();
			for (int i = 0; i < page.length; i++) {
				Document document=Jsoup.connect(riseStock[0]+page[i]+riseStock[1]).get();
				Elements elements=document.select("table[class=m-table J-ajax-table]");
				Elements result=elements.get(0).select("tbody").get(0).select("tr");
				for (int j = 0; j < result.size(); j++) {
					String[] temp=result.get(j).text().split(" ");
					if(temp[1].charAt(0)=='6'){
						temp[1]="sh"+temp[1];
					}else {
						temp[1]="sz"+temp[1];
					}
					RiseStockPO po=new RiseStockPO(
							temp[1],
							temp[2], 
							Double.parseDouble(temp[3]), 
							Double.parseDouble(temp[4]), 
							Double.parseDouble(temp[5]), 
							Integer.parseInt(temp[6]), 
							temp[7], 
							temp[8],
							temp[9]
							);
					arrayList.add(po);
				}
			}
			return arrayList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public ArrayList<UpToDateStockPO> getToDateStockPOs(String exchange) throws Exception {
		try {
			ArrayList<UpToDateStockPO> arrayList=new ArrayList<>();
			Document document=Jsoup.connect(UpToDateStocks[0]+exchange+UpToDateStocks[1]).get();
			Elements elements=document.select("tr[height=25]");
			for (Element element : elements) {
				String[] temp=element.text().replace(",", "").split(" ");
				if(temp[1].charAt(0)=='6'){
					temp[1]="sh"+temp[1];
				}else {
					temp[1]="sz"+temp[1];
				}
				UpToDateStockPO po=new UpToDateStockPO(
						temp[1], 
						temp[2], 
						temp[4], 
						Double.parseDouble(temp[5]), 
						temp[6], 
						Double.parseDouble(temp[8]), 
						Double.parseDouble(temp[9]), 
						Double.parseDouble(temp[10]), 
						Double.parseDouble(temp[11]), 
						Double.parseDouble(temp[12]), 
						Double.parseDouble(temp[21]), 
						Double.parseDouble(temp[22]), 
						Double.parseDouble(temp[23]), 
						Double.parseDouble(temp[24]), 
						Double.parseDouble(temp[25]), 
						Double.parseDouble(temp[26]), 
						Double.parseDouble(temp[27]), 
						Double.parseDouble(temp[28]), 
						Double.parseDouble(temp[29]), 
						Double.parseDouble(temp[30]));
				arrayList.add(po);
			}
			return arrayList;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public List getStockRecord(String stockId, String starttime, String endtime) throws Exception {
		TradeRecordDaoProxyService tradeRecordDaoProxyService=DaoFactory.getTradeRecordDaoProxy();
		try {
			return tradeRecordDaoProxyService.getTradeRecord(stockId, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	@Override
	public List getStockInfos() throws Exception {
		StockDaoProxyService stockDaoProxyService=DaoFactory.getStockDaoProxy();
		try {
			return stockDaoProxyService.findAll();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public UpToDateStockPO getUpToDateStockPO(String stockId) throws Exception {
		ArrayList<UpToDateStockPO> arrayList=getToDateStockPOs(stockId.substring(0,2));
		for (UpToDateStockPO upToDateStockPO : arrayList) {
			if (upToDateStockPO.getStockId().equals(stockId)) {
				return upToDateStockPO;
			}
		}
		return new UpToDateStockPO();
	}

}
