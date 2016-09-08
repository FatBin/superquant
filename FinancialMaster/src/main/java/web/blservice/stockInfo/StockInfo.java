package web.blservice.stockInfo;

import ENUM.ManageState;
import VO.StockDetailVO;

public interface StockInfo {

	//根据股票id返回对应的股票的所有信息
	public StockDetailVO getStock(String code);
	
	//根据股票id返回对应股票当日时分图数据
	public String[][] getTimeSharingData(String code);
	
	//根据起止时间，更新对应股票历史数据
	public ManageState updateHistoryData(StockDetailVO sv,String startDate,String endDate);
	
	//根据筛选条件，更新对应历史数据
	public ManageState filterHistoryData(StockDetailVO sv,int item,double lowerLimit,double upperLimit);
}
