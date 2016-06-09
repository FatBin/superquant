package businesslogic.StrategyHandle;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Volume extends Strategy{
	@Override
	public boolean buyStrategy(StrategyPO strategyPO,TradeRecord tradeRecord) {
		if (strategyPO.getVolumeLow()==0&&strategyPO.getVolumeHigh()==0) {
			return true;
		}else{
			if (tradeRecord.getVolume()>=strategyPO.getVolumeLow()
					&&tradeRecord.getVolume()<=strategyPO.getVolumeHigh()) {
				return true;
			}else {
				return false;
			}
		}
	}
}
