package businesslogicservice.stockmarketblservice;

import java.util.ArrayList;

import ENUM.date_enum;
import VO.StockMarketVO;

public interface StockMarketBLService {
	//得到所有可用的大盘指数，目前只有沪深300指数(hs300)
	public ArrayList<String> getBenchmark();
	// 根据大盘指数和时间（枚举类）返回当前的大盘信息（顺序是时间、开盘价、最高价、最低价、收盘价）
	public StockMarketVO getStockMarket(String key, date_enum date);
}
