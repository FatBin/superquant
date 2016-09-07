package web.blservice.StrategyHandleService;

import java.util.ArrayList;

import PO.profitPO;

public interface perfectStrategyService {
	//need the id of the stock , starting time , end time , the money you spend in 
	public ArrayList<profitPO> getProfit(String stockId,String startTime,String endTime,double cost);
}
