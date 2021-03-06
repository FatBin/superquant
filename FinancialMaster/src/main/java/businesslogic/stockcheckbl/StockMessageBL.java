package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import PO.stockStatisticPO;
import VO.StockMarketVO;
import VO.StockVO;
import businesslogic.factory.InitFactory;
import businesslogic.stockmarketbl.StockMarketInfo;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

public class StockMessageBL implements StockMessageBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();// 股票不同日期数据列表
	StockVO sv;
	String id; // 当前查看的股票的id

	// 根据关键字范围筛选股票
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
		int all_size = init_list.size();
		int size = filterlist.size();
		double ratio;
		if (all_size == 0) {
			ratio = 0;
		} else {
			ratio = size * 1.0 / all_size;
		}
		String[][] list = new String[size][10];
		int index = size - 1;
		for (String[] strings : filterlist) {
			list[index] = strings;
			index--;
		}
		sv.setRatio(ratio);
		sv.setHistory_data(list);
		return sv;
	}

	// 返回所选股票的详细信息，打包成StockVO
	@Override
	public StockVO getStockMessage(String id) {
		this.id = id;
		StockDataService sds = new StockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());
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

		String yesStartDay;
		do {
			cal.add(Calendar.DATE, -1);
			yesStartDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(id, yesStartDay, startDay);
		} while (ssPOlist.isEmpty());
		ssPOlist = sds.getStatisitcOfStock(id, yesStartDay, startDay);
		ssPO = ssPOlist.get(0);
		double last_close = ssPO.getClose();// 最新前一天的收盘价
		double ups_and_downs;
		double amplitude ;
		
		if(last_close!=0){
			ups_and_downs = (close - last_close) / last_close;
			amplitude = (high - low) / last_close;
		}else{
			ups_and_downs = 0;
			amplitude = 0;
		}
	

		String[][] history_data = new String[24][10];// 历史数据

		ssPOlist = sds.getStatisitcOfStock(id);
		int size = ssPOlist.size();
		int index = size - 1;
		int k_size = 30;
		String[][] k_data = new String[size][10];// 为k线图提供历史数据
		String[][] KLine_data = new String[k_size][9];// 返回k线图
		double[] closeForKLine = new double[k_size + 30];
		double volume_avg = 0;
		for (stockStatisticPO sp : ssPOlist) {
			if (index < 24) {
				history_data[index][0] = sp.getDate();
				history_data[index][1] = sp.getOpen() + "";
				history_data[index][2] = sp.getHigh() + "";
				history_data[index][3] = sp.getLow() + "";
				history_data[index][4] = sp.getClose() + "";
				history_data[index][5] = sp.getAdj_price() + "";
				history_data[index][6] = sp.getVolume() + "";
				history_data[index][7] = sp.getTurnover() + "";
				history_data[index][8] = sp.getPe() + "";
				history_data[index][9] = sp.getPb() + "";
				init_list.add(history_data[index]);
			}
			if (index < 30) {
				volume_avg += sp.getVolume() / 30.0;
			}
			k_data[index][0] = sp.getDate();
			k_data[index][1] = sp.getOpen() + "";
			k_data[index][2] = sp.getHigh() + "";
			k_data[index][3] = sp.getLow() + "";
			k_data[index][4] = sp.getClose() + "";
			k_data[index][5] = sp.getAdj_price() + "";
			k_data[index][6] = sp.getVolume() + "";
			k_data[index][7] = sp.getTurnover() + "";
			k_data[index][8] = sp.getPe() + "";
			k_data[index][9] = sp.getPb() + "";
			index--;
		}

		double quantity_relative_ratio;
		if (volume_avg != 0) {
			quantity_relative_ratio = volume / volume_avg;
		} else {
			quantity_relative_ratio = 0;
		}
		for (int i = 0; i < closeForKLine.length; i++) {
			closeForKLine[i] = Double.parseDouble(k_data[k_size + 29 - i][4]);
		}
		double sum;
		for (int i = 0; i < k_size; i++) {
			for (int j = 0; j < 5; j++) {
				KLine_data[i][j] = k_data[k_size - 1 - i][j];
			}
			KLine_data[i][5] = k_data[k_size - 1 - i][6];
			sum = 0;
			for (int j = i + 25; j < i + 30; j++) {
				sum += closeForKLine[j];
			}
			KLine_data[i][6] = sum / 5 + "";
			for (int j = i + 20; j < i + 25; j++) {
				sum += closeForKLine[j];
			}
			KLine_data[i][7] = sum / 10 + "";
			for (int j = i; j < i + 20; j++) {
				sum += closeForKLine[j];
			}
			KLine_data[i][8] = sum / 30 + "";
		}

		InitFactory factory = InitFactory.getFactory();
		StockMarketInfo stockMarketInfo = factory.getStockMarketBL();
		StockMarketVO stockMarketVO = stockMarketInfo.getStockMarketVO();

		sv = new StockVO(name, date, open, high, low, close, adj_price, volume,
				turnover, pe, pb, ups_and_downs, stockMarketVO, amplitude,
				quantity_relative_ratio, KLine_data, history_data);
		return sv;
	}

	// 根据选择日期段更新历史数据列表列表
	@Override
	public StockVO updateStockMessage(String startData, String overData) {
		init_list.clear();
		StockDataService sds = new StockData();
		ArrayList<stockStatisticPO> ssPOlist = sds.getStatisitcOfStock(id,
				startData, overData);
		int size = ssPOlist.size();
		String[][] list = new String[size][10];
		int index = size - 1;
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
