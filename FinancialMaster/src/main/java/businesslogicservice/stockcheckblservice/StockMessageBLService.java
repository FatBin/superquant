package businesslogicservice.stockcheckblservice;

import VO.StockVO;

public interface StockMessageBLService {
	
	
	// 返回所选股票的详细信息，打包成StockVO
    public StockVO getStockMessage(String id);

    //根据选择日期段更新历史数据列表列表
    
    public StockVO updateStockMessage( String startData, String overData);
    
	// 根据关键字范围筛选股票
	public StockVO filterStockMessage(int i, String low, String high);
}
