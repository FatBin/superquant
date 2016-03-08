package businesslogic.stockcheckbl;

import java.util.ArrayList;

import PO.stockStatisticPO;
import VO.StockVO;
import businesslogicservice.stockcheckblservice.StockMessageBLService;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

public class StockMessageBL implements StockMessageBLService {

	@Override
	public StockVO getStockMessage(String id,String startData, String overData) {
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
			  index++;
		}
		StockVO sv=new StockVO(list);
		return sv;
	}
}
