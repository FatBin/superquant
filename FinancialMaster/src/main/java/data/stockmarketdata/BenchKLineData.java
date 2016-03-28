package data.stockmarketdata;

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
	ArrayList<benchmarkStatisticPO> old_datalist;
	ArrayList<benchmarkStatisticPO> new_datalist;
	BenchDataService benchDataService = new BenchData();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public ManageState update() {
		Calendar cal = Calendar.getInstance();// 用来记录现在的日期
		cal.add(Calendar.DATE, -1);// 由于API的原因，暂时提前到昨天
		Calendar lastCal1 = Calendar.getInstance();// 用来记录上次更新到的日期
		Calendar lastCal2 = Calendar.getInstance();
		String yestoday = format.format(cal.getTime());
//		cal.add(Calendar.DATE, 1);
		try {
			ArrayList<String> datelist = FileManager
					.ReadFile("src/main/resources/Data/updateRecord.txt");
			String lastDate = datelist.get(0);
			Date d = format.parse(lastDate);
			lastCal1.setTime(d);
			lastCal2.setTime(d);
			String lastDay = format.format(lastCal1.getTime());
			if (!lastDay.equals(yestoday)) {
				// 更新周Ｋ
				updateWeek(lastCal1, cal);
				// 更新月Ｋ
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
	public ArrayList<benchmarkStatisticPO> getStatisticData(String kLine_enum) {
		ArrayList<benchmarkStatisticPO> arrayList = new ArrayList<benchmarkStatisticPO>();
		benchmarkStatisticPO bp;
		String date;
		double open, high, low, close;
		long volume;
		String path = "src/main/resources/Data/" + kLine_enum + ".txt";
		ArrayList<String> datalist = FileManager.ReadFile(path);
		for (String string : datalist) {
			String statisticData[] = string.split(";");
			date = statisticData[0];
			open = Double.parseDouble(statisticData[1]);
			high = Double.parseDouble(statisticData[2]);
			low = Double.parseDouble(statisticData[3]);
			close = Double.parseDouble(statisticData[4]);
			volume = Long.parseLong(statisticData[5]);
			bp = new benchmarkStatisticPO(date, open, high, low, close, volume);
			arrayList.add(bp);
		}
		return arrayList;
	}

	// 更新周k
	private void updateWeek(Calendar start, Calendar end) {
		old_datalist = getStatisticData("WeekK");
		double high = Double.MIN_VALUE;
		double low = Double.MAX_VALUE;
		double open = 0;
		double close = 0;
		long volume = 0;

		int dayOfWeek = start.get(Calendar.DAY_OF_WEEK)-1;
		if (dayOfWeek != 1&&dayOfWeek!=0&&dayOfWeek!=6) {
			int old_size = old_datalist.size();
			if (old_size > 0) {
				old_datalist.remove(old_size - 1);
			}
			start.add(Calendar.DATE, -(dayOfWeek - 1));
		}
		while (start.before(end)) {
			high = Double.MIN_VALUE;
			low = Double.MAX_VALUE;
			open = 0;
			close = 0;
			volume = 0;
			String startday = format.format(start.getTime());
			start.add(Calendar.DATE, 7);
			String endday = format.format(start.getTime());
			new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
					startday, endday);
			if (!new_datalist.isEmpty()) {
				int new_size = new_datalist.size();
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
				benchmarkStatisticPO new_bench = new benchmarkStatisticPO(
						startday, open, high, low, close, volume);
				old_datalist.add(new_bench);
			}
		}
		write(old_datalist, "src/main/resources/Data/WeekK.txt");
	}

	// 更新月k
	private void updateMonth(Calendar start, Calendar end) {
		old_datalist = getStatisticData("MonthK");
		double high = Double.MIN_VALUE;
		double low = Double.MAX_VALUE;
		double open = 0;
		double close = 0;
		long volume = 0;

		int dayOfMonth = start.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth != 1) {
			int old_size = old_datalist.size();
			if (old_size > 0) {
				old_datalist.remove(old_size - 1);
			}
			start.add(Calendar.DATE, -(dayOfMonth-1));
		}
		while (start.before(end)) {
			high = Double.MIN_VALUE;
			low = Double.MAX_VALUE;
			open = 0;
			close = 0;
			volume = 0;
			String startday = format.format(start.getTime());
			start.add(Calendar.MONTH, 1);
			String endday = format.format(start.getTime());
			new_datalist = benchDataService.getStatisticOfBenchmark("hs300",
					startday, endday);
			if (!new_datalist.isEmpty()) {
				int new_size = new_datalist.size();
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
				benchmarkStatisticPO new_bench = new benchmarkStatisticPO(
						startday, open, high, low, close, volume);
				old_datalist.add(new_bench);
			}
		}
		write(old_datalist, "src/main/resources/Data/MonthK.txt");
	}

	private void write(ArrayList<benchmarkStatisticPO> list, String path) {
		ArrayList<String> datalist = new ArrayList<String>();
		for (benchmarkStatisticPO PO : list) {
			String s = "";
			s += PO.getDate() + ";" + PO.getOpen() + ";" + PO.getHigh() + ";"
					+ PO.getLow() + ";" + PO.getClose() + ";" + PO.getVolume();
			datalist.add(s);
		}
		FileManager.WriteFile(datalist, path, false);
	}
}
