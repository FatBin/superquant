package businesslogic.stockcheckbl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PO.codeNamePO;
import PO.stockStatisticPO;
import businesslogicservice.stockcheckblservice.StockListBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockListBL implements StockListBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();
	String list[][];
	public StockListBL(){
		StockDataService sds = new StockData();
		manageStockDataService msds=new ManageStockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());

		ArrayList<stockStatisticPO> ssPOlist;
		stockStatisticPO ssPO;
		ArrayList<String> stockList;
		stockList = msds.getCodeOfStock();

		int size=stockList.size();
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), startDay, endDay);
		} while (ssPOlist.isEmpty());

		String yesStartDay;
		do {
			cal.add(Calendar.DATE, -1);
			yesStartDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), yesStartDay, startDay);
		} while (ssPOlist.isEmpty());
		
		list = new String[size][8];
		Double close[][]=new Double[size][2];
		Double ups_and_downs;
		NumberFormat nf = NumberFormat.getPercentInstance(); 
		nf.setMinimumFractionDigits(2);// 小数点后保留几位
		  
		for (int i = 0; i < size; i++) {
			list[i][0] = stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(list[i][0], startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[i][1]=ssPO.getClose();
			list[i][1]=ssPO.getName();
			list[i][2] = ssPO.getOpen() + "";
			list[i][3] = ssPO.getHigh() + "";
			list[i][4] = ssPO.getLow() + "";
			list[i][5] = ssPO.getClose() + "";
			list[i][6] = ssPO.getVolume() + "";
			ssPOlist = sds.getStatisitcOfStock(list[i][0], yesStartDay,startDay);
			ssPO = ssPOlist.get(0);
			close[i][0]=ssPO.getClose();
			ups_and_downs=(close[i][1]-close[i][0])/close[i][0];
			list[i][7]=nf.format(ups_and_downs);
			init_list.add(list[i]);
		}
	}
	
	
	
	@Override
	public String[][] getStockList() {
		// 股票代码、开盘价、最高价、最低价、收盘价、后复权价、成交量、换手率、市盈率、市净率
		
		return list;
	}

	@Override
	public String[][] updateStockList(String key) {
		ArrayList<String[]> new_list = new ArrayList<String[]>();
		for (String[] s : init_list) {
			if (s[0].contains(key)) {
				new_list.add(s);
			}
		}
		int size = new_list.size();
		if (size == 0) {
			String[][] list = { { "-", "-", "-", "-", "-", "-" ,"-"} };
			return list;
		} else {
			String list[][] = new String[size][7];
			int index = 0;
			for (String[] strings : new_list) {
				list[index] = strings;
				index++;
			}
			return list;
		}

	}

}
