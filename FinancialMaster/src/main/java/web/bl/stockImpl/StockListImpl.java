package web.bl.stockImpl;

import java.util.ArrayList;

import PO.RiseStockPO;
import VO.StockListVO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import web.blservice.stockInfo.StockListInfo;

public class StockListImpl implements StockListInfo {

	@Override
	public StockListVO getStockList() {
		StockDataService stockDataService=new StockData();
		ArrayList<RiseStockPO> stockPOs=new ArrayList<RiseStockPO>();
		StockListVO stockListVO=new StockListVO();		
		try {
			stockPOs=stockDataService.getRiseStock();
		} catch (Exception e) {
			e.printStackTrace();
		}
		stockListVO.setStockList(stockPOs);
		return stockListVO;
	}
}
