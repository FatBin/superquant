package web.blservice.StrategyHandleService;

import java.util.ArrayList;

import PO.StrategyPO;
import PO.profitPO;

public interface StrategyHandleService {
	public ArrayList<profitPO> handle(ArrayList<StrategyPO> arrayList1,ArrayList<StrategyPO>arrayList2);

	public profitPO getProfit(String userId,String stockId,String strategyName,String starttime,String endtime);
}
