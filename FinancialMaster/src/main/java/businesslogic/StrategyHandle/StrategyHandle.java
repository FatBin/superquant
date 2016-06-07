package businesslogic.StrategyHandle;

import java.util.ArrayList;
import java.util.List;

import DAO.DaoProxy.StockDaoProxy;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.pojo.TradeRecord;
import PO.StrategyPO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;

public class StrategyHandle {
	public ArrayList<String[]> handle(ArrayList<StrategyPO> arrayList){
		ArrayList<ArrayList<String[]>> eachResult=new ArrayList<>();
		for (StrategyPO strategyPO : arrayList) {
			eachResult.add(countEachDay(strategyPO));
		}
		return null;
	}

	private ArrayList<String[]> countEachDay(StrategyPO strategyPO) {
		StockDataService stockDataService=new StockData();
		ArrayList<String[]> arrayList=new ArrayList<>();
		ArrayList<TradeRecord> tradeRecords=new ArrayList<>();
		try {
			List list=stockDataService.getStockRecord(
					strategyPO.getStockId(), strategyPO.getStarttime(), strategyPO.getEndtime());
			for (Object object : list) {
				tradeRecords.add((TradeRecord)object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
