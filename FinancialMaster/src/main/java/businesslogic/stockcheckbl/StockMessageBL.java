package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import PO.stockStatisticPO;
import VO.StockVO;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockMessageBL implements StockMessageBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();
	StockVO sv;
	String id;

	@Override
	public StockVO filterStockMessage(int i, String low, String high) {
		double low_value, high_value;
		if (low.equals("输入下限") || low.length() == 0) {
			low_value = Double.MIN_VALUE;
		} else {
			low_value = Double.parseDouble(low);
		}
		if (high.equals("输入上限") || high.length() == 0) {
			high_value = Double.MAX_VALUE;
		} else {
			high_value = Double.parseDouble(high);
		}
		ArrayList<String[]> filterlist = new ArrayList<String[]>();
		for (String[] strings : init_list) {
			double value = Double.parseDouble(strings[i]);
			if (value >= low_value && value <= high_value) {
				filterlist.add(strings);
			}
		}
		int size = filterlist.size();
		String[][] list = new String[size][10];
		int index = size-1;
		for (String[] strings : filterlist) {
			list[index] = strings;
			index--;
		}
		sv.setHistory_data(list);
		return sv;
	}

	@Override
	public StockVO getStockMessage(String id) {
		this.id = id;
		StockDataService sds = new StockData();
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());
		cal2.add(Calendar.MONTH, -2);
		String lastMonth = format.format(cal2.getTime());
		ArrayList<stockStatisticPO> ssPOlist;
		stockStatisticPO ssPO;
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(id, startDay, endDay);
		} while (ssPOlist.isEmpty());

		ssPO = ssPOlist.get(0);		
		String name = ssPO.getName();// 股票名
		String date = ssPO.getDate();// 日期
		double open = ssPO.getOpen();// 开盘价
		double high = ssPO.getHigh();// 最高价
		double low = ssPO.getLow();// 最低价
		double close = ssPO.getClose();// 收盘价
		double adj_price = ssPO.getAdj_price();// 后复权价
		int volume = ssPO.getVolume();// 成交量
		double turnover = ssPO.getTurnover();// 换手率
		double pe = ssPO.getPb();// 市盈率
		double pb = ssPO.getPe();// 市净率

		cal.add(Calendar.DATE, -1);				
		String yesStartDay=format.format(cal.getTime());
		ssPOlist = sds.getStatisitcOfStock(id, yesStartDay,startDay);
		ssPO = ssPOlist.get(0);	
		double lase_close = ssPO.getClose();// 最新前一天的收盘价
		Double ups_and_downs=(close-lase_close)/lase_close;
						
		ssPOlist = sds.getStatisitcOfStock(id, lastMonth, endDay);
		int size = ssPOlist.size();
		String[][] list = new String[size][10];// 历史数据
		int index = size-1;
		for (stockStatisticPO sp : ssPOlist) {
			list[index][0] = sp.getDate();
			list[index][1] = sp.getOpen() + "";
			list[index][2] = sp.getHigh() + "";
			list[index][3] = sp.getLow() + "";
			list[index][4] = sp.getClose() + "";
			list[index][5] = sp.getAdj_price() + "";
			list[index][6] = sp.getVolume() + "";
			list[index][7] = sp.getTurnover() + "";
			list[index][8] = sp.getPe() + "";
			list[index][9] = sp.getPb() + "";
			init_list.add(list[index]);
			index--;
		}
		sv = new StockVO(name, date, open, high, low, close, adj_price,
				volume, turnover, pe, pb,ups_and_downs, list);

		return sv;
	}

	@Override
	public StockVO updateStockMessage(String startData, String overData) {
		init_list.clear();
		StockDataService sds = new StockData();
		ArrayList<stockStatisticPO> ssPOlist = sds.getStatisitcOfStock(id,
				startData, overData);
		int size = ssPOlist.size();
		String[][] list = new String[size][10];
		int index = size-1;
		for (stockStatisticPO sp : ssPOlist) {
			list[index][0] = sp.getDate();
			list[index][1] = sp.getOpen() + "";
			list[index][2] = sp.getHigh() + "";
			list[index][3] = sp.getLow() + "";
			list[index][4] = sp.getClose() + "";
			list[index][5] = sp.getAdj_price() + "";
			list[index][6] = sp.getVolume() + "";
			list[index][7] = sp.getTurnover() + "";
			list[index][8] = sp.getPe() + "";
			list[index][9] = sp.getPb() + "";
			init_list.add(list[index]);
			index--;
		}
		sv.setHistory_data(list);
		return sv;
	}
}
