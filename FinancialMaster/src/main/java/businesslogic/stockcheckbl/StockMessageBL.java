package businesslogic.stockcheckbl;

import java.util.ArrayList;

import PO.stockStatisticPO;
import VO.StockVO;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

public class StockMessageBL implements StockMessageBLService {
 
	ArrayList<String[]> init_list=new ArrayList<String[]>();
	@Override
	public StockVO getStockMessage(String id,String startData, String overData) {
		  init_list.clear();
		  StockDataService sds=new StockData();
		  ArrayList<stockStatisticPO> ssPOlist=sds.getStatisitcOfStock(id, startData, overData);
		  int size=ssPOlist.size();
		  String[][] list=new String[size][10];
		  int index=0;
		  for (stockStatisticPO sp : ssPOlist) {
			  list[index][0]=sp.getDate();
			  list[index][1]=sp.getOpen()+"";
			  list[index][2]=sp.getHigh()+"";			  
			  list[index][3]=sp.getLow()+"";
			  list[index][4]=sp.getClose()+"";			  
			  list[index][5]=sp.getAdj_price()+"";
			  list[index][6]=sp.getVolume()+"";
			  list[index][7]=sp.getTurnover()+"";			  
			  list[index][8]=sp.getPe()+"";
			  list[index][9]=sp.getPb()+"";	
			  init_list.add(list[index]);
			  index++;
		}
		StockVO sv=new StockVO(list);
		return sv;
	}

	@Override
	public StockVO filterStockMessage(int i, String low, String high) {
		double low_value,high_value;
		if(low.length()==0){
			low_value=Double.MIN_VALUE;
		}else{
			low_value=Double.parseDouble(low);
		}
		if(high.length()==0){
			high_value=Double.MAX_VALUE;
		}else{		
			high_value=Double.parseDouble(high);
		}
		ArrayList<String[]> filterlist=new ArrayList<String[]>(); 
		for (String[] strings : init_list) {
			double value=Double.parseDouble(strings[i]);
			if(value>=low_value&&value<=high_value){
				filterlist.add(strings);
			}
		}
		int size=filterlist.size();
	    String[][] list=new String[size][10];
		int index=0;
		for (String[] strings : filterlist) {
			list[index]=strings;
			index++;
		}
		StockVO sv=new StockVO(list);
		return sv;
	}
}
