package businesslogic.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Turnover extends Strategy{
	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getTurnoverLow()==0&&strategyPO.getTurnoverHigh()==0) {
			return true;
		}else{
			if (tradeRecord.getTurnover()>=strategyPO.getTurnoverLow()
					&&tradeRecord.getTurnover()<=strategyPO.getTurnoverHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
