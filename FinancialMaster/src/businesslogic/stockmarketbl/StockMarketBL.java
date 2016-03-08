package businesslogic.stockmarketbl;

import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import data.stockmarketdata.BenchData;
import dataservice.stockmarketdataservice.BenchDataService;

public class StockMarketBL implements StockMarketBLService {

	@Override
	public StockMarketVO getStockMarket(String key, String data) {
		BenchDataService benchDataservice=new BenchData();
		
		return null;
	} 

}
