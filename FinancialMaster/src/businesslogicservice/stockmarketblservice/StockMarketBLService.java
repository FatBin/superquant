package businesslogicservice.stockmarketblservice;

import VO.StockMarketVO;

public interface StockMarketBLService {
	// 根据关键字和时间返回当前最新的大盘信息
	public StockMarketVO getStockMarket(String key, String data);
}
