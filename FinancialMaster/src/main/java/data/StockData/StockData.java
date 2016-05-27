package data.StockData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import PO.RiseStockPO;
import PO.UpToDateStockPO;
import dataservice.StockDataService.StockDataService;

public class StockData implements StockDataService{
	public static final String[] riseStock={
			"http://data.10jqka.com.cn/rank/lxsz/field/lxts/order/desc/page/",
			"/ajax/1/"
			};
	public static final int[] page={1,2,3,4,5,6,7,8,9,10};
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
	@Override
	public ArrayList<UpToDateStockPO> geToDateStockPOs(String exchange) throws Exception {
		
		return null;
	}

}
