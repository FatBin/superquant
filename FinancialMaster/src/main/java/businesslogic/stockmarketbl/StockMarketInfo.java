package businesslogic.stockmarketbl;

import VO.StockMarketVO;

public interface StockMarketInfo {

	
	//得到大盘最新价（最近的收盘价），涨跌额，涨跌幅
	public StockMarketVO getStockMarketVO();
}
