package businesslogic.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Pe extends Strategy{
	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getPbLow()==0&&strategyPO.getPeHigh()==0) {
			return true;
		}else{
			if (tradeRecord.getClose()>=strategyPO.getPeLow()
					&&tradeRecord.getClose()<=strategyPO.getPeHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
