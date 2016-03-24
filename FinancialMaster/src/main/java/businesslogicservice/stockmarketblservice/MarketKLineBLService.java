package businesslogicservice.stockmarketblservice;

import ENUM.ManageState;
import ENUM.marketKline_enum;
import VO.StockMarketVO;

public interface MarketKLineBLService {
    //读取网上最新的数据，更新数据文件中的数据
	public ManageState update();
	//返回k线图所需要的数据
	public StockMarketVO getData(marketKline_enum k);
}
