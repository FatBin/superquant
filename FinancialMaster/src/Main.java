import PO.fieldStatisticPO;
import data.FieldData.FieldData;
import data.stockcheckdata.StockData;
import data.stockmarketdata.BenchData;

public class Main {
	public static void main(String[] args){
		StockData stockData=new StockData();
//		codeNamePO codeNamePO=stockData.getCodeName(2014, "sh");
//		for(int i=0;i<codeNamePO.getResult().size();i++){
//			System.out.println(codeNamePO.getResult().get(i));
//		}
//		ArrayList<stockStatisticPO> arrayList =stockData.getStatisitcOfStock("sh600000", "2014-10-10", "2015-10-10");

		BenchData benchData=new BenchData();
		FieldData fieldData=new FieldData();
		fieldStatisticPO f=fieldData.getStatisticOfField();
		System.out.print(f.getOpen()+" "+f.getHigh()+" "+f.getLow()+" "+f.getClose()+" "+f.getAdj_price()+" "+f.getVolume()+" "+f.getTurnover()+" "+f.getPb()+" "+f.getPe());
	}
}
