package web.bl.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Pb extends Strategy{
	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getPbLow()==0&&strategyPO.getPbHigh()==0) {
			return true;
		}else{
			if (tradeRecord.getPb()>=strategyPO.getPbLow()
					&&tradeRecord.getPb()<=strategyPO.getPbHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
