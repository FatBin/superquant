import java.util.ArrayList;

import PO.benchmarkStatisticPO;
import PO.codeNamePO;
import PO.stockStatisticPO;
import data.stockcheckdata.StockData;
import data.stockmarketdata.BenchData;

public class Main {
	public static void main(String[] args){
		StockData stockData=new StockData();
		codeNamePO codeNamePO=stockData.getCodeName(2015, "sh");
//		for(int i=0;i<codeNamePO.getResult().size();i++){
//			System.out.println(codeNamePO.getResult().get(i));
//		}
//		ArrayList<stockStatisticPO> arrayList =stockData.getStatisitcOfStock("sh600000", "2016-03-01", "2016-03-02");
//		for(int i=0;i<arrayList.size();i++){
//			System.out.println(arrayList.get(i).getDate()+arrayList.get(i).getTurnover());
//		}
//		BenchData benchData=new BenchData();
//		FieldData fieldData=new FieldData();
//		fieldStatisticPO f=fieldData.getStatisticOfField();
//		System.out.print(f.getOpen()+" "+f.getHigh()+" "+f.getLow()+" "+f.getClose()+" "+f.getAdj_price()+" "+f.getVolume()+" "+f.getTurnover()+" "+f.getPb()+" "+f.getPe());
//		stockData.getCodeName(201, "sh");
		BenchData benchData=new BenchData();
		ArrayList<benchmarkStatisticPO> arrayList=benchData.getStatisticOfBenchmark("hs300", "2015-01-01", "2015-12-30");
	}
}
