package businesslogic.stockmarketbl;

import java.util.ArrayList;

import ENUM.ManageState;
import ENUM.date_enum;
import ENUM.marketKline_enum;
import PO.benchmarkStatisticPO;
import VO.StockMarketVO;
import businesslogicservice.stockmarketblservice.MarketKLineBLService;
import businesslogicservice.stockmarketblservice.StockMarketBLService;
import data.stockmarketdata.BenchKLineData;
import dataservice.stockmarketdataservice.BenchKLineDataService;

public class MarketKLineBL implements MarketKLineBLService {
	BenchKLineDataService bkds = new BenchKLineData();

	@Override
	public ManageState update() {
		return bkds.update();
	}

	@Override
	public StockMarketVO getData(marketKline_enum k) {
		String[][] list;
		int size;
		int k_size = 120;
		String[][] result = new String[k_size][9];
		double[] close=new double[k_size+30];
		if (k == marketKline_enum.DayK) {
			StockMarketBLService sbs = new StockMarketBL();
			StockMarketVO sv = sbs.getStockMarket("hs300", date_enum.Year);
			list = sv.getData();
			size = list.length;
			for (int i = 0; i < close.length; i++) {
				close[i]=Double.parseDouble(list[k_size +29 - i][4]);
			}
			double sum;
			for (int i = 0; i < k_size; i++) {
				for (int j = 0; j < 6; j++) {
					result[i][j] = list[k_size - 1 - i][j];
				}
				sum=0;
				for (int j = i+25; j < i+30; j++) {
					sum+=close[j];
				}
				result[i][6]=sum/5+"";
				for (int j = i+20; j < i+25; j++) {
					sum+=close[j];
				}
				result[i][7]=sum/10+"";
				for (int j = i; j < i+20; j++) {
					sum+=close[j];
				}
				result[i][8]=sum/30+"";
			}
		} else {
			list = bkds.getStatisticData(k.toString());
			size = list.length;

			for (int i = 0; i < k_size; i++) {
				result[i] = list[size - k_size + i];
			}
		}
		StockMarketVO sv = new StockMarketVO(result);
		return sv;
	}

}
