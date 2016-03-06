package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	// 返回所选股票的详细信息，打包成StockPO
	public StockVO getStockMessage(String id);

	// 根据用户输入的时间段返回筛选过后的列表
	public StockVO updateStockList(String id, String startData, String overData);
}
