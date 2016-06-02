package web.bl.stockImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
			ArrayList<StockRecordVO> historyData=new ArrayList<StockRecordVO>();
			for (TradeRecord tradeRecord : records) {
				StockRecordVO stockRecordVO=new StockRecordVO(
						tradeRecord.getId().getDate(), tradeRecord.getOpen(),
						tradeRecord.getClose(), tradeRecord.getHigh(),
						tradeRecord.getLow(), tradeRecord.getAdjPrice(),
						tradeRecord.getVolume(), tradeRecord.getTurnover(),
						tradeRecord.getPe(), tradeRecord.getPb());
				historyData.add(stockRecordVO);
			}
			stockDetailVO.setUpToDateMessage(upToDateMessage);
			stockDetailVO.setHistoryData(historyData);
			
			
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

}
