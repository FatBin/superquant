package data.stockmarketdata;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ENUM.ManageState;
import PO.benchmarkStatisticPO;
import data.IO.FileManager;
import dataservice.stockmarketdataservice.BenchDataService;
import dataservice.stockmarketdataservice.BenchKLineDataService;

public class BenchKLineData implements BenchKLineDataService {
	ArrayList<String> old_datalist;
	ArrayList<benchmarkStatisticPO> new_datalist;
	BenchDataService benchDataService = new BenchData();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat( "0.00");

	@Override
	public ManageState update() {
		Calendar cal = Calendar.getInstance();// 用来记录现在的日期
		cal.add(Calendar.DATE, -1);// 由于API原因，暂时更新到昨天
		Calendar lastCal1 = Calendar.getInstance();// 用来记录上次更新到的日期
		Calendar lastCal2 = Calendar.getInstance();
		String yestoday = format.format(cal.getTime());
		// cal.add(Calendar.DATE, 1);
		try {
			ArrayList<String> datelist = FileManager
					.ReadFile("src/main/resources/Data/updateRecord.txt");
			String lastDate = datelist.get(0);
			Date d = format.parse(lastDate);
			lastCal1.setTime(d);
			lastCal2.setTime(d);
			String lastDay = format.format(lastCal1.getTime());
			if (!lastDay.equals(yestoday)) {
				// 更新周k
				updateWeek(lastCal1, cal);
				// 更新月k
				updateMonth(lastCal2, cal);
				FileManager.WriteFile(yestoday,
						"src/main/resources/Data/updateRecord.txt", false);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return ManageState.Fail;
		}
		return ManageState.Succeed;
	}
	
	@Override
	public String[][] getStatisticData(String kLine_enum) {
		String path = "src/main/resources/Data/" + kLine_enum + ".txt";
		ArrayList<String> datalist = FileManager.ReadFile(path);
		int size=datalist.size();
		String[][] result=new String[size][9];
		int index=0;
		for (String strings : datalist) {
			result[index]=strings.split(";");
			index++;
		}
		return result;		
	}	

	// 更新周k
	private void updateWeek(Calendar start, Calendar end) {
		old_datalist =FileManager.ReadFile("src/main/resources/Data/WeekK.txt"); 
		double high = Double.MIN_VALUE;
		double low = Double.MAX_VALUE;
		double open = 0;
		double close = 0;
		long volume = 0;		

		start.add(Calendar.DATE, 1);
		int dayOfWeek = start.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0 ){
			start.add(Calendar.DATE, 1);
		}else if(dayOfWeek == 6){
			start.add(Calendar.DATE, 2);
		}
		else if(dayOfWeek != 1) {
			int old_size = old_datalist.size();
			if (old_size > 0) {
				old_datalist.remove(old_size - 1);
			}
			start.add(Calendar.DATE, -(dayOfWeek - 1));
		}
		while (start.before(end)) {
			String update="";
			high = Double.MIN_VALUE;
			low = Double.MAX_VALUE;
			open = 0;
			close = 0;
			volume = 0;
			String startday = format.format(start.getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTime(start.getTime());
			cal.add(Calendar.MONTH, -2);
			String two_month_ago=format.format(cal.getTime());
			start.add(Calendar.DATE, 7);
			String endday = format.format(start.getTime());
			new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
					startday, endday);
			int new_size;
			if (!new_datalist.isEmpty()) {
				new_size = new_datalist.size();
				benchmarkStatisticPO firstBench = new_datalist.get(0);
				benchmarkStatisticPO lastBench = new_datalist.get(new_size - 1);
				open = firstBench.getOpen();
				close = lastBench.getClose();
				for (benchmarkStatisticPO benchmarkStatisticPO : new_datalist) {
					if (benchmarkStatisticPO.getHigh() > high) {
						high = benchmarkStatisticPO.getHigh();
					}
					if (benchmarkStatisticPO.getLow() < low) {
						low = benchmarkStatisticPO.getLow();
					}
					volume += benchmarkStatisticPO.getVolume();
				}
				update+=startday+";"+open+";"+high+";"+low+";"+close+";"+volume;

				new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
						two_month_ago,startday);
				new_size = new_datalist.size();
				double[] closelist=new double[new_size];
				int index=0;
				for (benchmarkStatisticPO benchmarkStatisticPO : new_datalist) {
					closelist[index]=benchmarkStatisticPO.getClose();
					index++;
				}
				double sum=0;
				for (int i = new_size-5; i < new_size; i++) {
					sum+=closelist[i];
				}
				update+=";"+df.format(sum/5);
				for (int i = new_size-10; i < new_size-5; i++) {
					sum+=closelist[i];
				}
				update+=";"+df.format(sum/10);
				for (int i = new_size-30; i < new_size-10; i++) {
					sum+=closelist[i];
				}
				update+=";"+df.format(sum/30);
				old_datalist.add(update);
			}
			
		}
		FileManager.WriteFile(old_datalist, "src/main/resources/Data/WeekK.txt", false);
	}

	// 更新月k
	private void updateMonth(Calendar start, Calendar end) {
		old_datalist =FileManager.ReadFile("src/main/resources/Data/MonthK.txt"); 
		double high = Double.MIN_VALUE;
		double low = Double.MAX_VALUE;
		double open = 0;
		double close = 0;
		long volume = 0;

		
		start.add(Calendar.DATE, 1);
		int dayOfMonth = start.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth != 1) {
			int old_size = old_datalist.size();
			if (old_size > 0) {
				old_datalist.remove(old_size - 1);
			}
			start.add(Calendar.DATE, -(dayOfMonth - 1));
		}
		while (start.before(end)) {
			String update="";
			high = Double.MIN_VALUE;
			low = Double.MAX_VALUE;
			open = 0;
			close = 0;
			volume = 0;
			String startday = format.format(start.getTime());
			start.add(Calendar.MONTH, -2);
			String two_month_ago=format.format(start.getTime());
			start.add(Calendar.MONTH, 3);
			String endday = format.format(start.getTime());
			new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
					startday, endday);
			int new_size;
			if (!new_datalist.isEmpty()) {
				new_size = new_datalist.size();
				benchmarkStatisticPO firstBench = new_datalist.get(0);
				benchmarkStatisticPO lastBench = new_datalist.get(new_size - 1);
				open = firstBench.getOpen();
				close = lastBench.getClose();
				for (benchmarkStatisticPO benchmarkStatisticPO : new_datalist) {
					if (benchmarkStatisticPO.getHigh() > high) {
						high = benchmarkStatisticPO.getHigh();
					}
					if (benchmarkStatisticPO.getLow() < low) {
						low = benchmarkStatisticPO.getLow();
					}
					volume += benchmarkStatisticPO.getVolume();
				}
                update+=startday+";"+open+";"+high+";"+low+";"+close+";"+volume;

				
			}
			new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
					two_month_ago,startday);
			new_size = new_datalist.size();
			double[] closelist=new double[new_size];
			int index=0;
			for (benchmarkStatisticPO benchmarkStatisticPO : new_datalist) {
				closelist[index]=benchmarkStatisticPO.getClose();
				index++;
			}
			double sum=0;
			for (int i = new_size-5; i < new_size; i++) {
				sum+=closelist[i];
			}
			update+=";"+df.format(sum/5);
			for (int i = new_size-10; i < new_size-5; i++) {
				sum+=closelist[i];
			}
			update+=";"+df.format(sum/10);
			for (int i = new_size-30; i < new_size-10; i++) {
				sum+=closelist[i];
			}
			update+=";"+df.format(sum/30);
			old_datalist.add(update);
		}
		FileManager.WriteFile(old_datalist, "src/main/resources/Data/MonthK.txt", false);
	}

}
