package businesslogic.StrategyHandle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;
import PO.profitPO;
import businesslogicservice.StrategyHandleService.StrategyHandleService;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;


public class StrategyHandle implements StrategyHandleService{
	//计算这段时间内每天整个策略的收益
	public ArrayList<profitPO> handle(ArrayList<StrategyPO> arrayList1,ArrayList<StrategyPO>arrayList2){
		ArrayList<ArrayList<profitPO>> eachResultList=new ArrayList<>();
		ArrayList<profitPO> totalResult=new ArrayList<>();
		for(int i=0;i<arrayList1.size();i++){
			StrategyPO po1=arrayList1.get(i);
			StrategyPO po2=arrayList2.get(i);
			eachResultList.add(getEachResult(po1,po2));
		}
		
	for (ArrayList<profitPO> arrayList : eachResultList) {
		for (profitPO profitPO : arrayList) {
			boolean in=false;
			for (profitPO profitPO2 : totalResult) {
				if (profitPO2.getDate().compareTo(profitPO.getDate())==0) {
					profitPO2.setProfit(profitPO.getProfit()+profitPO2.getProfit());
					in=true;
					break;
				}
			}
			if (in==false) {
				totalResult.add(profitPO);
			}
		}
	}
	
		rank(totalResult);
		return totalResult;
	}

	//将totalResult按从早到今的顺序排序
	public void rank(ArrayList<profitPO> totalResult) {
		for (int i = 0; i < totalResult.size(); i++) {
			for (int j = 0; j < totalResult.size()-1; j++) {
				if (totalResult.get(j).getDate().compareTo(totalResult.get(j+1).getDate())>0) {
					profitPO po=totalResult.get(j);
					totalResult.set(j, totalResult.get(j+1));
					totalResult.set(j+1, po);
				}
			}
		}
	}

	//每个小策略的策略时间内每天的收益
	public ArrayList<profitPO> getEachResult(StrategyPO po1, StrategyPO po2) {
		StockDataService service=new StockData();
		ArrayList<profitPO> arrayList=new ArrayList<>();
		try {
			List list=service.getStockRecord(po1.getStockId(), po1.getStarttime(), po1.getEndtime());
			ArrayList<TradeRecord> temp=new ArrayList<>();
			ArrayList<TradeRecord> result=new ArrayList<>();
			int count=0;//记录最早能符合买条件的记录的下标
			for (Object object : list) {
				temp.add((TradeRecord)object);
			}
			for(int i=temp.size()-1;i>=0;i--){
				if (check(po1, temp.get(i))) {
					count=i;
					break;
				}
			}
			for (int i = count; i >=0 ; i--) {
				result.add(temp.get(i));
			}
			
			//买入量
			double volume=po1.getCost()/temp.get(0).getClose();
			//原本金
			double cost=po1.getCost();
			for (TradeRecord tradeRecord : result) {
				profitPO profitPO=new profitPO(tradeRecord.getId().getDate(), volume*tradeRecord.getClose()-cost);
				arrayList.add(profitPO);
				if (check(po2, tradeRecord)&&
						timeCompare(tradeRecord.getId().getDate(), po2.getStarttime(),po2.getEndtime())) {
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	//是否符合买入/卖出策略
	public boolean check(StrategyPO strategyPO,TradeRecord tradeRecord){
		boolean result=true;
		ArrayList<Strategy> strategies=StrategyMap.getStrategy();
		for (Strategy strategy : strategies) {
			if (strategy.buyStrategy(strategyPO, tradeRecord)==false) {
				result=false;
				break;
			}
		}
		return result;
	}

	//时间比较
	public boolean timeCompare(String tradeRecord,String starttime,String endtime){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date former=format.parse(tradeRecord);
			Date latter1=format.parse(starttime);
			Date latter2=format.parse(endtime);
			return former.after(latter1)||former.before(latter2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
