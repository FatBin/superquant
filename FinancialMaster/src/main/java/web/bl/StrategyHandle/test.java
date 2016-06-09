package web.bl.StrategyHandle;

import java.util.ArrayList;

import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import PO.StrategyPO;

public class test {
	public static void main(String[] args) {
		ArrayList<Strategy> arrayList=StrategyMap.getStrategy();
		
		StrategyPO po1=new StrategyPO("sh", "2016-05-01", "2016-05-31", 
				2, 3, 1000, 2000, 2, 8, 0.5, 1, 0.3, 0.7, 3, 1000);
		StrategyPO po2=new StrategyPO("sh", "2016-05-15", "2016-06-15", 
				4, 6, 3000, 4000, 2, 8, 0.5, 1, 0.4, 0.8, 3, 1000);
		TradeRecordId tradeRecordId=new TradeRecordId("sh", "2016-04-30");
		TradeRecord tradeRecord=new TradeRecord(tradeRecordId, 0, 3, 0, 0, 0, 1000, 3, 0.5, 0.5);
		StrategyHandle strategyHandle=new StrategyHandle();
		System.out.println(strategyHandle.check(po1, tradeRecord));

	}
}
