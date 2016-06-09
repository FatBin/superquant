package web.bl.StrategyHandle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.pojo.TradeRecord;
import PO.StrategyPO;

public class Time extends Strategy{

	@Override
	public boolean buyStrategy(StrategyPO strategyPO, TradeRecord tradeRecord) {
		//时间比较
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date former=format.parse(tradeRecord.getId().getDate());
				Date latter1=format.parse(strategyPO.getStarttime());
				Date latter2=format.parse(strategyPO.getEndtime());
				return (!former.after(latter2))&&(!former.before(latter1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return false;
	}

}
