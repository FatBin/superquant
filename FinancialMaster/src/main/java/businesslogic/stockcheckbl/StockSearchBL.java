package businesslogic.stockcheckbl;

import java.util.ArrayList;

import PO.codeNamePO;
import businesslogicservice.stockcheckblservice.StockSearchBLService;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

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
	//根据输入的关键字，实时更新股票代号列表
	@Override
	public String[][] getList(String key) {
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
		String[][] result=new String[size][1];
		for (String string : newList) {
			result[index][0]=string;
			index++;
		}
		return result;
	}

}
