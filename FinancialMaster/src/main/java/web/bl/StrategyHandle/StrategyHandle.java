package web.bl.StrategyHandle;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;
import PO.profitPO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import web.blservice.StrategyHandleService.StrategyHandleService;

public class StrategyHandle implements StrategyHandleService {
	// 计算这段时间内每天整个策略的收益
	public ArrayList<profitPO> handle(ArrayList<StrategyPO> arrayList1, ArrayList<StrategyPO> arrayList2) {
		ArrayList<ArrayList<profitPO>> eachResultList = new ArrayList<>();
		ArrayList<profitPO> totalResult = new ArrayList<>();
		try {
			for (int i = 0; i < arrayList1.size(); i++) {
				StrategyPO po1 = arrayList1.get(i);
				StrategyPO po2 = arrayList2.get(i);
				eachResultList.add(getEachResult(po1, po2));
			}

			for (ArrayList<profitPO> arrayList : eachResultList) {
				for (profitPO profitPO : arrayList) {
					boolean in = false;
					for (profitPO profitPO2 : totalResult) {
						if (profitPO2.getDate().compareTo(profitPO.getDate()) == 0) {
							profitPO2.setProfit(profitPO.getProfit() + profitPO2.getProfit());
							in = true;
							break;
						}
					}
					if (in == false) {
						totalResult.add(profitPO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		rank(totalResult);
		
		for(int i=0;i<totalResult.size()-1;i++){
			totalResult.get(i+1).setProfit(totalResult.get(i+1).getProfit()+totalResult.get(i).getProfit());
		}

		return totalResult;
	}

	// 将totalResult按从早到今的顺序排序
	public void rank(ArrayList<profitPO> totalResult) {
		for (int i = 0; i < totalResult.size(); i++) {
			for (int j = 0; j < totalResult.size() - 1; j++) {
				if (totalResult.get(j).getDate().compareTo(totalResult.get(j + 1).getDate()) > 0) {
					profitPO po = totalResult.get(j);
					totalResult.set(j, totalResult.get(j + 1));
					totalResult.set(j + 1, po);
				}
			}
		}
	}

	// 每个小策略的策略时间内每天的收益
	public ArrayList<profitPO> getEachResult(StrategyPO po1, StrategyPO po2) {
		StockDataService service = new StockData();
		ArrayList<profitPO> arrayList = new ArrayList<>();
		try {
			List list = service.getStockRecord(po1.getStockId(), po1.getStarttime(), po1.getEndtime());
			ArrayList<TradeRecord> temp = new ArrayList<>();
			ArrayList<TradeRecord> result = new ArrayList<>();
			System.out.println("recordsize "+list.size());
			for (Object object : list) {
				temp.add((TradeRecord) object);
			}
			for (int i = temp.size() - 1; i >= 0; i--) {
				if (check(po1, temp.get(i))) {
					result.add(temp.get(i));
					System.out.println(temp.get(i).getId().getDate());
				}
			}

			// 买入量
			double volume = 0.0001;
			try {
				volume = po1.getCost() / result.get(0).getClose();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("volume "+volume);
			// 原本金
			double cost = po1.getCost();
			System.out.println("cost "+cost);
			for (TradeRecord tradeRecord : result) {
				profitPO profitPO = new profitPO(tradeRecord.getId().getDate(), volume * tradeRecord.getClose() - cost);
				System.out.println("tradeRecord"+(volume * tradeRecord.getClose() - cost));
				arrayList.add(profitPO);
				if (check(po2, tradeRecord)&&avoid(po2)) {
					break;
				}
			}
			System.out.println("arraylist "+arrayList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	// 是否符合买入/卖出策略
	public boolean check(StrategyPO strategyPO, TradeRecord tradeRecord) {
		boolean result = true;
		ArrayList<Strategy> strategies = StrategyMap.getStrategy();
		for (Strategy strategy : strategies) {
			if (strategy.buyStrategy(strategyPO, tradeRecord) == false) {
				result = false;
			}
		}
		return result;
	}
	
	//避免卖出策略全为零时也通过
	public boolean avoid(StrategyPO po){
		if (po.getPriceLow()==0&&po.getPriceHigh()==0&&po.getVolumeLow()==0&&po.getVolumeHigh()==0
				&&po.getTurnoverLow()==0&&po.getTurnoverHigh()==0&&po.getPeLow()==0&&po.getPeHigh()==0
				&&po.getPbLow()==0&&po.getPbHigh()==0) {
			return false;
		}else {
			return true;
		}
	}
}
