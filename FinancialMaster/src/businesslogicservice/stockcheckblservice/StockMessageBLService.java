package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	// 返回所选股票的详细信息，打包成StockPO
	public StockVO getStockMessage(String id,String startData, String overData);
}
