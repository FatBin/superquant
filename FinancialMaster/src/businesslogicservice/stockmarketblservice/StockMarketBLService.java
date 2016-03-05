package businesslogicservice.stockmarketblservice;

import PO.StockMarketPO;

public interface StockMarketBLService {
	// 根据关键字和时间返回当前最新的大盘信息
	public StockMarketPO getStockMarket(String key, String data);
}
