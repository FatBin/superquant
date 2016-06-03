package web.bl.stockImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;

import DAO.pojo.TradeRecord;
import ENUM.ManageState;
import PO.UpToDateStockPO;
import VO.StockDetailVO;
import VO.StockRecordVO;
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
			double[] volumes=new double[size];
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
				volumes[index]=tradeRecord.getVolume();
				historyData[index][7]=tradeRecord.getTurnover()+"";
				turnovers[index]=tradeRecord.getTurnover();
				historyData[index][8]=tradeRecord.getPe()+"";
				historyData[index][9]=tradeRecord.getPb()+"";
                index++;
			}
			
			double volumeStability=getAmendatoryStandardDevition(volumes,size);
			double priceStability=getAmendatoryStandardDevition(turnovers,size);
			double turnOver=getAvg(turnovers,size);
			System.out.println(upToDateMessage.getRise_fall());
//			String rise_fall=(upToDateMessage.getRise_fall()).split("%")[0];			
//			double ups_and_downs=Double.parseDouble(rise_fall);
			double pe=0;
			double pb=0;
			if(size>0){
				TradeRecord lastRecord=records.get(0);
				pe=lastRecord.getPe();
				pb=lastRecord.getPb();
			}
			pe=10-pe/10;
			
			
			stockDetailVO.setUpToDateMessage(upToDateMessage);
			stockDetailVO.setHistoryData(historyData);
			stockDetailVO.setVolumeStability(volumeStability);
			stockDetailVO.setPriceStability(priceStability);
			stockDetailVO.setTurnOver(turnOver);
//			stockDetailVO.setUps_and_downs(ups_and_downs);
			stockDetailVO.setPe(pe);
			stockDetailVO.setPb(pb);
			
			
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

	private double getAvg(double[] data, int n) {
		double avg = 0;

		for (int i = 0; i < n; i++) {
			avg += data[i];
		}
		avg /= n;
		
		return avg;
		
	}

}
