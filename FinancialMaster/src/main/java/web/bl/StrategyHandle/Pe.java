package web.bl.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Pe extends Strategy{
	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getPeLow()==0&&strategyPO.getPeHigh()==0) {
			return true;
		}else{
			if (tradeRecord.getPe()>=strategyPO.getPeLow()
					&&tradeRecord.getPe()<=strategyPO.getPeHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
