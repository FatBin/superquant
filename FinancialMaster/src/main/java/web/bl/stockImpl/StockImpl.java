package web.bl.stockImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import DAO.pojo.TradeRecord;
import ENUM.ManageState;
import PO.UpToDateStockPO;
import VO.Analyze_BasicItemsVO;
import VO.StockDetailVO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import web.blservice.stockInfo.StockInfo;

public class StockImpl implements StockInfo {

	@Override
	public StockDetailVO getStock(String code) {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String endtime=format.format(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		String starttime=format.format(calendar.getTime());
		
		StockDataService stockDataService=new StockData();
		StockDetailVO stockDetailVO=new StockDetailVO();
		
		try {
			UpToDateStockPO upToDateMessage=stockDataService.getUpToDateStockPO(code);
			List<TradeRecord> records=stockDataService.getStockRecord(code, starttime, endtime);			
			int size=records.size();
			
			String[][] historyData=new String[size][10];
			double[] closes=new double[size];
			double[] turnovers=new double[size];

			
			
			
//			日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
			int index=0;
			for (TradeRecord tradeRecord : records) {
				historyData[index][0]=tradeRecord.getId().getDate();				
				historyData[index][1]=tradeRecord.getOpen()+"";
				historyData[index][2]=tradeRecord.getClose()+"";
				closes[index]=tradeRecord.getClose();
				historyData[index][3]=tradeRecord.getHigh()+"";
				historyData[index][4]=tradeRecord.getLow()+"";
				historyData[index][5]=tradeRecord.getAdjPrice()+"";
				historyData[index][6]=tradeRecord.getVolume()+"";
				historyData[index][7]=tradeRecord.getTurnover()+"";
				turnovers[index]=tradeRecord.getTurnover();
				historyData[index][8]=tradeRecord.getPe()+"";
				historyData[index][9]=tradeRecord.getPb()+"";
				System.out.println("date:"+historyData[index][0]+"pe"+historyData[index][8]+"pb"+historyData[index][9]);
                index++;
			}
			
			
			//计算基本项
			int m=size,n=size;
			if(size>10){
				n=10;
			}
			if(size>30){
				m=30;
			}

			double priceStability=1-getAmendatoryStandardDevition(turnovers,m);
			System.out.println(upToDateMessage.getRise_fall());
			double turnOver=getAvg(turnovers,n);
			double ups_and_downs=0;
			double quantity_relative_ratio=0;
			if(upToDateMessage.getRise_fall()!=null){
				String rise_fall=(upToDateMessage.getRise_fall()).split("%")[0];
				ups_and_downs=Double.parseDouble(rise_fall);
				quantity_relative_ratio=upToDateMessage.getQuantity_relative_ratio();
			}

			double pe=0;
			double pb=0;
			if(size>0){
				TradeRecord lastRecord=records.get(0);
				pe=lastRecord.getPe();
				pb=lastRecord.getPb();
			}
			
			Analyze_BasicItemsVO analyze_BasicItemsVO=new Analyze_BasicItemsVO();
			analyze_BasicItemsVO.setQuantity_relative_ratio(quantity_relative_ratio);			
			analyze_BasicItemsVO.setPriceStability(priceStability);
			analyze_BasicItemsVO.setUps_and_downs(ups_and_downs);
			analyze_BasicItemsVO.setTurnOver(turnOver);
			analyze_BasicItemsVO.setPe(pe);
			analyze_BasicItemsVO.setPb(pb);

			
			stockDetailVO.setUpToDateMessage(upToDateMessage);
			stockDetailVO.setHistoryData(historyData);			
			stockDetailVO.setAnalyze_BasicItemsVO(analyze_BasicItemsVO);
		
			analyze(stockDetailVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockDetailVO;
	}

	@Override
	public ManageState updateHistoryData(StockDetailVO sv, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState filterHistoryData(StockDetailVO sv, int item, double lowerLimit, double upperLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 得到数组修正后的标准差
	private double getAmendatoryStandardDevition(double[] data, int n) {
		double result = 0;
		double avg = getAvg(data, n);

		for (int i = 0; i < n; i++) {
			result += (data[i] - avg) * (data[i] - avg);
		}
		result /= n - 1;
		result = Math.sqrt(result);
		result /= avg;
		return result;
	}

	//得到平均值
	private double getAvg(double[] data, int n) {
		double avg = 0;

		for (int i = 0; i < n; i++) {
			avg += data[i];
		}
		avg /= n;
		
		return avg;
		
	}
	
	private void analyze(StockDetailVO stockDetailVO) {
		
	}

}
