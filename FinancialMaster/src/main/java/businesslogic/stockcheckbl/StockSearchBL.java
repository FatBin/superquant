package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;
import PO.codeNamePO;
import businesslogicservice.stockcheckblservice.StockSearchBLService;

public class StockSearchBL implements StockSearchBLService {

	ArrayList<String> totalList=new ArrayList<String>();
	public StockSearchBL(){
		 StockDataService sds = new StockData();
		 codeNamePO sz_codeName = sds.getCodeName(2015, "sz");
		 codeNamePO sh_codeName = sds.getCodeName(2015, "sh");
		 ArrayList<String> sz_list = sz_codeName.getResult();
		 ArrayList<String> sh_list = sh_codeName.getResult();
		 for (String string : sz_list) {
			totalList.add(string);
		 }
		 for (String string : sh_list) {
				totalList.add(string);
		 }
	}
	
	@Override
	public String[] getList(String key) {
		ArrayList<String> newList=new ArrayList<String>();
		for (String string : totalList) {
			if(string.contains(key)){
				newList.add(string);
			}
		}
		if(newList.isEmpty()){
			return null;
		}
		int size=newList.size();
		int index=0;
		String[] result=new String[size];
		for (String string : newList) {
			result[index]=string;
			index++;
		}
		return result;
	}

}
