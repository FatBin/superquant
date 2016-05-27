package web.blservice.stockInfo;

import ENUM.ManageState;
import VO.StockVO;

public interface StockInfo {

	//根据股票id返回对应的股票的所有信息
	public StockVO getStock(String code);
	
	//根据起止时间，更新对应股票历史数据
	public ManageState updateHistoryData(StockVO sv,String startDate,String endDate);
	
	//根据筛选条件，更新对应历史数据
	public ManageState filterHistoryData(StockVO sv,int item,double lowerLimit,double upperLimit);
}
