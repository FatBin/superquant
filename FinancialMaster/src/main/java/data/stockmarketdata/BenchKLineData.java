package data.stockmarketdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ENUM.ManageState;
import PO.benchmarkStatisticPO;
import data.IO.FileManager;
import dataservice.stockmarketdataservice.BenchKLineDataService;

public class BenchKLineData implements BenchKLineDataService {

	@Override
	public ManageState update() {
		Calendar   cal   =   Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today=format.format(cal.getTime());
		int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth=cal.get(Calendar.DAY_OF_MONTH);
		int monthOfYear=cal.get(Calendar.MONTH);
//		switch(date){
//		case Day:
//			cal.add(Calendar.DATE,-1);break;
//		case Week:
//			cal.add(Calendar.DATE,-7);break;
//		case Month:
//			cal.add(Calendar.MONTH,-1);break;
//		case HalfYear:
//			cal.add(Calendar.MONTH,-6);break;
//		case Year:
//			cal.add(Calendar.YEAR,-1);break;
//		case FiveYear:
//			cal.add(Calendar.YEAR,-5);break;
//		case TenYear:
//			cal.add(Calendar.YEAR,-10);break;
//		}
//　　　 更新周Ｋ
//		更新双周Ｋ
//		更新月Ｋ
//		更新三月Ｋ
//		更新半年Ｋ
//		更新年Ｋ
		return null;
	}

	@Override
	public ArrayList<benchmarkStatisticPO> getStatisticData(String kLine_enum) {
		ArrayList<benchmarkStatisticPO> arrayList=new ArrayList<benchmarkStatisticPO>();
		benchmarkStatisticPO bp;
		String date;
		double open,high,low,close;
		long volume;
		String path="src/main/resources/Data/"+kLine_enum+".txt";
		ArrayList<String> datalist=FileManager.ReadFile(path);
		for (String string : datalist) {
			String statisticData[]=string.split(";");
			date=statisticData[0];
			open=Double.parseDouble(statisticData[1]);
			high=Double.parseDouble(statisticData[2]);
			low=Double.parseDouble(statisticData[3]);
			close=Double.parseDouble(statisticData[4]);
			volume=Long.parseLong(statisticData[5]);			
			bp=new benchmarkStatisticPO(date, open, high, low, close, volume);
			arrayList.add(bp);			
		}
		return arrayList;
	}

}
