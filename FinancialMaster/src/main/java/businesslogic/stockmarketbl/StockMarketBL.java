package businesslogic.stockmarketbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ENUM.date_enum;
import PO.benchmarkPO;
import PO.benchmarkStatisticPO;
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today=format.format(cal.getTime());
		switch(date){
		case Day:
			cal.add(Calendar.DATE,-1);break;
		case Week:
			cal.add(Calendar.DATE,-7);break;
		case Month:
			cal.add(Calendar.MONTH,-1);break;
		case HalfYear:
			cal.add(Calendar.MONTH,-6);break;
		case Year:
			cal.add(Calendar.YEAR,-1);break;
		case FiveYear:
			cal.add(Calendar.YEAR,-5);break;
		case TenYear:
			cal.add(Calendar.YEAR,-10);break;
		}
		String start_day = format.format(cal.getTime());
		

		ArrayList<benchmarkStatisticPO> markdata_list=benchDataservice.getStatisticOfBenchmark(key, start_day, today);
        int size=markdata_list.size();
        String[][] list=new String[size][6];
        int index=0;
        for (benchmarkStatisticPO bsPO : markdata_list) {
			list[index][0]=bsPO.getDate();
			list[index][1]=bsPO.getOpen()+"";
			list[index][2]=bsPO.getHigh()+"";
			list[index][3]=bsPO.getLow()+"";
			list[index][4]=bsPO.getClose()+"";
			list[index][5]=bsPO.getVolume()+"";
			index++;
		}
        StockMarketVO sv=new StockMarketVO(list);
		return sv;
	}

	@Override
	public ArrayList<String> getBenchmark() {
		BenchDataService benchDataservice=new BenchData();
		benchmarkPO bp=benchDataservice.getBenchmark();
		ArrayList<String> benchmark_list=bp.getBenchmark();
		return benchmark_list;
	} 

}
