package web.bl.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public abstract class Strategy {

	abstract public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord);
	
}
