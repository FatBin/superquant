package web.bl.StrategyHandle;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.TradeRecord;
import PO.profitPO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import web.blservice.StrategyHandleService.perfectStrategyService;

public class PerfectStrategyHandle implements perfectStrategyService{

	@Override
	public ArrayList<profitPO> getProfit(String stockId, String startTime, String endTime, double cost) {
		StockDataService stockData=new StockData();
		ArrayList<profitPO> result=new ArrayList<>();
		ArrayList<TradeRecord> temp1=new ArrayList<>();
		List list=null;
		
		try {
			list=stockData.getStockRecord(stockId, startTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		temp1=(ArrayList<TradeRecord>)list;
		
		ArrayList<TradeRecord> temp2=new ArrayList<>();
		
		for (int i = temp1.size()-1; i >=0; i--) {
			temp2.add(temp1.get(i));
		}
		
		result=calculate(temp2,cost);
		return result;
	}

	private ArrayList<profitPO> calculate(ArrayList<TradeRecord> temp2,double cost) {
		//买入量
		double volume=0;
		
		//买卖状态，已买入为true
		boolean buy=false;
		
		//初始资金
		double origin=cost;
		
		ArrayList<profitPO> arrayList=new ArrayList<>();
		try {
			if (temp2.size()<=0) {
			}else if (temp2.size()==1) {
				profitPO po=new profitPO(temp2.get(0).getId().getDate(), 0);
				arrayList.add(po);
			}else{
				profitPO po=new profitPO(temp2.get(0).getId().getDate(), 0);
				arrayList.add(po);
				for (int i = 1; i < temp2.size(); i++) {
					if (temp2.get(i).getClose()>=temp2.get(i-1).getClose()) {
						if (buy==false) {
							volume=cost/temp2.get(i-1).getClose();
							cost=0;
							buy=true;
						}
							profitPO profitPO=new profitPO(temp2.get(i).getId().getDate(), volume*temp2.get(i).getClose()-origin);
							arrayList.add(profitPO);
					}else {
						if (buy==true) {
							cost=volume*temp2.get(i-1).getClose();
							volume=0;
							buy=false;
						}
						profitPO profitPO=new profitPO(temp2.get(i).getId().getDate(),arrayList.get(arrayList.size()-1).getProfit());
						arrayList.add(profitPO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

}
