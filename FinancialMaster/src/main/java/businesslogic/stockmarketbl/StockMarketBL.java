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

public class StockMarketBL implements StockMarketBLService, StockMarketInfo {

	StockMarketVO sv;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	BenchDataService benchDataservice = new BenchData();
	ArrayList<benchmarkStatisticPO> markdata_list;

	public StockMarketBL() {
		Calendar cal = Calendar.getInstance();
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());
		double volume;
		double close;
		double ups_and_downs;
		double changeRange;
		double[] closelist = new double[2];

		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			markdata_list = benchDataservice.getStatisticOfBenchmark("hs300",
					startDay, endDay);
		} while (markdata_list.isEmpty());

		String[][] list = new String[1][6];
		benchmarkStatisticPO bsPO = markdata_list.get(0);
		list[0][0] = bsPO.getDate();
		list[0][1] = bsPO.getOpen() + "";
		list[0][2] = bsPO.getHigh() + "";
		list[0][3] = bsPO.getLow() + "";
		list[0][4] = bsPO.getClose() + "";
		list[0][5] = bsPO.getVolume() + "";
		closelist[1] = bsPO.getClose();
		volume = bsPO.getVolume();
		close = bsPO.getClose();
		String yesStartDay;
		do {
			cal.add(Calendar.DATE, -1);
			yesStartDay = format.format(cal.getTime());
			markdata_list = benchDataservice.getStatisticOfBenchmark("hs300",
					yesStartDay, startDay);
		} while (markdata_list.isEmpty());
		bsPO = markdata_list.get(0);
		closelist[0] = bsPO.getClose();

		changeRange = closelist[1] - closelist[0];
		ups_and_downs = changeRange / closelist[0];
		sv = new StockMarketVO(list, volume, changeRange, ups_and_downs, close);

	}

	// 根据大盘指数和时间（枚举类）返回当前的大盘信息（顺序是时间、开盘价、最高价、最低价、收盘价）
	@Override
	public StockMarketVO getStockMarket(String key, date_enum date) {

		// 得到日期
		Calendar cal = Calendar.getInstance();
		String today = format.format(cal.getTime());
		switch (date) {
		case Day:
			cal.add(Calendar.DATE, -1);
			break;
		case Week:
			cal.add(Calendar.DATE, -7);
			break;
		case Month:
			cal.add(Calendar.MONTH, -1);
			break;
		case HalfYear:
			cal.add(Calendar.MONTH, -6);
			break;
		case Year:
			cal.add(Calendar.YEAR, -1);
			break;
		case FiveYear:
			cal.add(Calendar.YEAR, -5);
			break;
		case TenYear:
			cal.add(Calendar.YEAR, -10);
			break;
		}
		String start_day = format.format(cal.getTime());

		ArrayList<benchmarkStatisticPO> markdata_list = benchDataservice
				.getStatisticOfBenchmark(key, start_day, today);
		int size = markdata_list.size();
		String[][] list = new String[size][6];
		int index = size - 1;
		for (benchmarkStatisticPO bsPO : markdata_list) {
			list[index][0] = bsPO.getDate();
			list[index][1] = bsPO.getOpen() + "";
			list[index][2] = bsPO.getHigh() + "";
			list[index][3] = bsPO.getLow() + "";
			list[index][4] = bsPO.getClose() + "";
			list[index][5] = bsPO.getVolume()/1000000 + "";
			index--;
		}
		sv.setData(list);
		return sv;
	}

	// 得到所有可用的大盘指数，目前只有沪深300指数(hs300)
	@Override
	public ArrayList<String> getBenchmark() {
		BenchDataService benchDataservice = new BenchData();
		benchmarkPO bp = benchDataservice.getBenchmark();
		ArrayList<String> benchmark_list = bp.getBenchmark();
		return benchmark_list;
	}

	// 得到大盘最新价（最近的收盘价），涨跌额，涨跌幅
	@Override
	public StockMarketVO getStockMarketVO() {
		return sv;
	}

}
