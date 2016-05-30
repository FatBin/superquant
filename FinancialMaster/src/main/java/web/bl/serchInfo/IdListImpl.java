package web.bl.serchInfo;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.Stock;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import web.blservice.searchInfo.IdListInfo;

public class IdListImpl implements IdListInfo{

	@Override
	public ArrayList<Stock> getIdList() {
		StockDataService stockDataService=new StockData();
		ArrayList<Stock> stockList=new ArrayList<Stock>();
		try {
			List<Stock> stocks=stockDataService.getStockInfos();
			for (Stock stock : stocks) {
				stockList.add(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockList;
	}

}
