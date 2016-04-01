package businesslogic.stockmarketbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import data.stockmarketdata.BenchKLineData;
import dataservice.stockmarketdataservice.BenchKLineDataService;
import ENUM.ManageState;
import ENUM.date_enum;
import ENUM.marketKline_enum;
import PO.benchmarkStatisticPO;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.MarketKLineBLService;
import businesslogicservice.stockmarketblservice.StockMarketBLService;

public class MarketKLineBL implements MarketKLineBLService {
    BenchKLineDataService bkds=new BenchKLineData();
	@Override
	public ManageState update() {
		return bkds.update();
	}

	@Override
	public StockMarketVO getData(marketKline_enum k) {
		String[][] list;
		int size;
		int k_size=120;
		String[][] result=new String[k_size][6];
		if(k==marketKline_enum.DayK){		
			StockMarketBLService sbs=new StockMarketBL();
			StockMarketVO sv=sbs.getStockMarket("hs300", date_enum.HalfYear);
			list=sv.getData();
			size=list.length;	
	        for (int i = 0; i < k_size; i++) {
				result[i]=list[k_size-1-i];
			}
		}else{
		ArrayList<benchmarkStatisticPO> markdata_list=bkds.getStatisticData(k.toString());
        size=markdata_list.size();
        list=new String[size][6];
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
        for (int i = 0; i < k_size; i++) {
			result[i]=list[size-k_size+i];
		}}
        StockMarketVO sv=new StockMarketVO(result);
		return sv;
	}

}
