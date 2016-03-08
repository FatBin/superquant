package businesslogic.stockmarketbl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ENUM.date_enum;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import data.stockmarketdata.BenchData;
import dataservice.stockmarketdataservice.BenchDataService;

public class StockMarketBL implements StockMarketBLService {

	@Override
	public StockMarketVO getStockMarket(String key, date_enum date) {
		BenchDataService benchDataservice=new BenchData();
		//得到日期
		Calendar   cal   =   Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		String today=format.format(cal.getTime());
		switch(date){
		case Day:
			cal.add(Calendar.DATE,-1);break;
		}
		cal.add(Calendar.MONTH,-1);
		String start_day = format.format(cal.getTime());
		return null;
	} 

}
