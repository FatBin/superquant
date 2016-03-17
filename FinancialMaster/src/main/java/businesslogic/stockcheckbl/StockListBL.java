package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import PO.codeNamePO;
import PO.stockStatisticPO;
import businesslogicservice.stockcheckblservice.StockListBLService;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

public class StockListBL implements StockListBLService {

	ArrayList<String[]> init_list = new ArrayList<String[]>();

	@Override
	public String[][] getStockList() {
		// 股票代码、开盘价、最高价、最低价、收盘价、后复权价、成交量、换手率、市盈率、市净率
		StockDataService sds = new StockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());
		// codeNamePO sz_codeName = sds.getCodeName(2015, "sz");
		// codeNamePO sh_codeName = sds.getCodeName(2015, "sh");
		// ArrayList<String> sz_list = sz_codeName.getResult();
		// ArrayList<String> sh_list = sz_codeName.getResult();
		// int size = sz_list.size() + sh_list.size();
		// String[][] list = new String[size][6];
		// int index = 0;
		ArrayList<stockStatisticPO> ssPOlist;
		stockStatisticPO ssPO;
		// for (String sz_s : sz_list) {
		// list[index][0] = sz_s;
		// ssPOlist = sds.getStatisitcOfStock(sz_s, yestoday, today);
		// if (!ssPOlist.isEmpty()) {
		// ssPO = ssPOlist.get(0);
		// list[index][1] = ssPO.getOpen() + "";
		// list[index][2] = ssPO.getHigh() + "";
		// list[index][3] = ssPO.getLow() + "";
		// list[index][4] = ssPO.getClose() + "";
		// list[index][5] = ssPO.getVolume() + "";
		// } else {
		// for (int i = 1; i < 6; i++) {
		// list[index][i] = "";
		// }
		// }
		// init_list.add(list[index]);
		// index++;
		// }
		// for (String sh_s : sh_list) {
		// list[index][0] = sh_s;
		// ssPOlist = sds.getStatisitcOfStock(sh_s, yestoday, today);
		// if (!ssPOlist.isEmpty()) {
		// ssPO = ssPOlist.get(0);
		// list[index][1] = ssPO.getOpen() + "";
		// list[index][2] = ssPO.getHigh() + "";
		// list[index][3] = ssPO.getLow() + "";
		// list[index][4] = ssPO.getClose() + "";
		// list[index][5] = ssPO.getVolume() + "";
		// } else {
		// for (int i = 1; i < 6; i++) {
		// list[index][i] = "";
		// }
		// }
		// init_list.add(list[index]);
		// index++;
		// }
		String[] name_list = { "sh600000", "sh600004", "sh600005", "sh600006",
				"sh600007", "sh600008", "sh600009", "sh600300", "sh600316",
				"sh600769", "sh600975", "sh600622", "sh600803", "sh600365",
				"sh600223", "sh600071", "sh603008", "sh600085", "sh600827",
				"sh600077" };
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(name_list[0], startDay, endDay);
		} while (ssPOlist.isEmpty());
		String list[][] = new String[20][6];
		for (int i = 0; i < 20; i++) {
			list[i][0] = name_list[i];
			ssPOlist = sds.getStatisitcOfStock(name_list[i], startDay, endDay);
			ssPO = ssPOlist.get(0);
			list[i][1] = ssPO.getOpen() + "";
			list[i][2] = ssPO.getHigh() + "";
			list[i][3] = ssPO.getLow() + "";
			list[i][4] = ssPO.getClose() + "";
			list[i][5] = ssPO.getVolume() + "";

			init_list.add(list[i]);
		}
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
			String[][] list = { { "-", "-", "-", "-", "-", "-" } };
			return list;
		} else {
			String list[][] = new String[size][6];
			int index = 0;
			for (String[] strings : new_list) {
				list[index] = strings;
				index++;
			}
			return list;
		}

	}

}
