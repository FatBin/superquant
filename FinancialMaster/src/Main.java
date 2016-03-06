import java.util.ArrayList;

import PO.codeNamePO;
import PO.stockStatisticPO;
import data.stockcheckdata.StockData;

public class Main {
	public static void main(String[] args){
		StockData stockData=new StockData();
//		codeNamePO codeNamePO=stockData.getCodeName(2014, "sh");
//		for(int i=0;i<codeNamePO.getResult().size();i++){
//			System.out.println(codeNamePO.getResult().get(i));
//		}
		ArrayList<stockStatisticPO> arrayList =stockData.getStatisitcOfStock("sh600000", "2014-10-10", "2015-10-10");
		for(int i=0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i).getDate()+" "+arrayList.get(i).getHigh()+" "+arrayList.get(i).getOpen()+" "+arrayList.get(i).getClose());
		}
	}
}
