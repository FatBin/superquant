package businesslogic.stockmarketbl;

import java.util.ArrayList;

import data.stockmarketdata.BenchKLineData;
import dataservice.stockmarketdataservice.BenchKLineDataService;
import ENUM.ManageState;
import ENUM.marketKline_enum;
import PO.benchmarkStatisticPO;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.MarketKLineBLService;

public class MarketKLineBL implements MarketKLineBLService {
    BenchKLineDataService bkds=new BenchKLineData();
	@Override
	public ManageState update() {
		return bkds.update();
	}

	@Override
	public StockMarketVO getData(marketKline_enum k) {
		ArrayList<benchmarkStatisticPO> markdata_list=bkds.getStatisticData(k.toString());
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

}
