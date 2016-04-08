package businesslogic.stockContrastbl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;
import PO.stockStatisticPO;
import businesslogicservice.stockContrastblservice.StockContrastBLService;

public class StockContrastBL implements StockContrastBLService {
	
	private Map<String, double[]> Data_MAP = new HashMap<String, double[]>();
	
	@Override
	public double[] getData(String id) {
		double[] result=null;
		for (Entry<String,double[] > entry : Data_MAP.entrySet()) {
			String number = entry.getKey();
			if(id.equals(number))
			   result = entry.getValue();
		}
		return result;
				
	}

	@Override
	public String[] getList() {
		// 股票代码、开盘价、最高价、最低价、收盘价、后复权价、成交量、换手率、市盈率、市净率
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

		cal.add(Calendar.DATE, -1);				
		String yesStartDay=format.format(cal.getTime());
		double[] data=new double[4];
		Double close[][]=new Double[size][2];
		String[] id=new String[size];
		String name;
		
		for (int i = 0; i < size; i++) {
			id[i]= stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(id[i], startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[i][1]=ssPO.getClose();
			name=ssPO.getName();
			data[1] = ssPO.getPb();
			data[2] = ssPO.getPe();
			data[3] = ssPO.getTurnover();
			ssPOlist = sds.getStatisitcOfStock(id[i], yesStartDay,startDay);
			ssPO = ssPOlist.get(0);
			close[i][0]=ssPO.getClose();
			data[0]=(close[i][1]-close[i][0])/close[i][0];
			Data_MAP.put(id[i],data);		
		}
		return id;
	}

}
