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
	String[][] idlist;
	String startDay;
	String endDay;
	String yesStartDay;
	ArrayList<stockStatisticPO> ssPOlist;
	stockStatisticPO ssPO;
	StockDataService sds ;
	String lastMonth;
	

	public StockContrastBL() {
		// 股票代码、开盘价、最高价、最低价、收盘价、后复权价、成交量、换手率、市盈率、市净率
		sds= new StockData();
		manageStockDataService msds = new ManageStockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		endDay = format.format(cal.getTime());

		ArrayList<String> stockList;
		stockList = msds.getCodeOfStock();

		int size = stockList.size();
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), startDay,
					endDay);
		} while (ssPOlist.isEmpty());

		
		do {
			cal.add(Calendar.DATE, -1);
			yesStartDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), yesStartDay, startDay);
		} while (ssPOlist.isEmpty());
		
		cal.add(Calendar.MONTH, -1);
		lastMonth=format.format(cal.getTime());

		Double close[][] = new Double[size][2];
		idlist = new String[size][2];
		String name;

		for (int i = 0; i < size; i++) {
			double[] data = new double[6];
			idlist[i][0] = stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(idlist[i][0], startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[i][1] = ssPO.getClose();
			name = ssPO.getName();
			data[1] = ssPO.getPb();
			data[2] = ssPO.getPe();
			data[3] = ssPO.getTurnover();
			ssPOlist = sds.getStatisitcOfStock(idlist[i][0], yesStartDay, startDay);
			ssPO = ssPOlist.get(0);
			close[i][0] = ssPO.getClose();
			data[0] = (close[i][1] - close[i][0]) / close[i][0];
			ssPOlist = sds.getStatisitcOfStock(idlist[i][0], lastMonth, endDay);
			int listSize=ssPOlist.size();
			double[] closeList=new double[listSize];
			double[] volumeList=new double[listSize];
			int index=0;
			for (stockStatisticPO sp : ssPOlist) {
				closeList[index]=sp.getClose();
				volumeList[index]=sp.getVolume();
				index++;
			}
			data[4]=getAmendatoryStandardDevition(closeList, listSize);
			data[5]=getAmendatoryStandardDevition(volumeList, listSize);
			
			Data_MAP.put(name, data);
			idlist[i][1] = name;
		}
	}

	public void update(String id,int i){
		int size = idlist.length;
		String[][] new_idlist = new String[size + i][10];
		int index = 0;
		String name="";
		if (i == -1) {
		    for (int j = 0; j < size; j++) {
		    	if(!idlist[j][0].equals(id)){
					for (int j2 = 0; j2 < 2; j2++) {
						new_idlist[index][j2]=idlist[j][j2];
					}
					index++;
				}else{
					name=idlist[j][1];
				}
			}
		    Data_MAP.remove(name);			
		} else {
			for (int j = 0; j < size; j++) {
					for (int j2 = 0; j2 < 2; j2++) {
						new_idlist[index][j2]=idlist[j][j2];
					}
					index++;						
			}
			
			double[] data = new double[6];
			double[] close=new double[2];
			new_idlist[index][0] = id;
			ssPOlist = sds.getStatisitcOfStock(id, startDay, endDay);
			ssPO = ssPOlist.get(0);
			close[1] = ssPO.getClose();
			name = ssPO.getName();
			new_idlist[index][1] = name;
			data[1] = ssPO.getPb();
			data[2] = ssPO.getPe();
			data[3] = ssPO.getTurnover();
			ssPOlist = sds.getStatisitcOfStock(id, yesStartDay, startDay);
			ssPO = ssPOlist.get(0);
			close[0] = ssPO.getClose();
			data[0] = (close[1] - close[0]) / close[0];
			ssPOlist = sds.getStatisitcOfStock(id, lastMonth, endDay);
			int listSize=ssPOlist.size();
			double[] closeList=new double[listSize];
			double[] volumeList=new double[listSize];
			index=0;
			for (stockStatisticPO sp : ssPOlist) {
				closeList[index]=sp.getClose();
				volumeList[index]=sp.getVolume();
				index++;
			}
			data[4]=getAmendatoryStandardDevition(closeList, listSize);
			data[5]=getAmendatoryStandardDevition(volumeList, listSize);
			
			Data_MAP.put(name, data);												
		}
		idlist=new_idlist;
		
	}
	
	@Override
	public double[] getData(String id) {
		double[] result = null;
		for (Entry<String, double[]> entry : Data_MAP.entrySet()) {
			String number = entry.getKey();
			if (id.equals(number))
				result = entry.getValue();
		}
		return result;

	}

	@Override
	public String[] getList() {
		int length =idlist.length;
		String nameList[]=new String[length];
		for (int i = 0; i < length; i++) {
			nameList[i]=idlist[i][1];
		}
		return nameList;
	}
    private double getAmendatoryStandardDevition(double[] data,int n){
    	double result=0;
    	double avg=0;
    	
    	for (int i = 0; i < n; i++) {
			avg+=data[i];
		}
    	avg/=n;
    	
    	for (int i = 0; i < n; i++) {
			result+=(data[i]-avg)*(data[i]-avg);
		}
    	result/=n-1;
    	result=Math.sqrt(result);
    	result/=avg;    	
    	return result;
    }
}
