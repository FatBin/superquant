package businesslogic.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Price extends Strategy{

	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getPriceLow()==0&&strategyPO.getPriceLow()==0) {
			return true;
		}else{
			if (tradeRecord.getClose()>=strategyPO.getPriceLow()
					&&tradeRecord.getClose()<=strategyPO.getPriceHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}

}
