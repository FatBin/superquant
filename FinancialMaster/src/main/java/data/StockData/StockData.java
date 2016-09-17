package data.StockData;

import java.util.ArrayList;
import java.util.List;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.pojo.Stock;
import PO.RiseStockPO;
import PO.UpToDateStockPO;
import dataservice.StockDataService.StockDataService;

public class StockData implements StockDataService{

	@Override
	public ArrayList<RiseStockPO> getRiseStock() throws Exception{
		return RiseStockUpdate.getRiseStockPOs();
	}
	
	
	public ArrayList<UpToDateStockPO> getToDateStockPOs(String exchange) throws Exception {
		return UpToDateStocksUpdate.getArrayLists(exchange);
	}
	@Override
	public List getStockRecord(String stockId, String starttime, String endtime) throws Exception {
		TradeRecordDaoProxyService tradeRecordDaoProxyService=DaoFactory.getTradeRecordDaoProxy();
		try {
			return tradeRecordDaoProxyService.getTradeRecord(stockId, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	@Override
	public List getStockInfos() throws Exception {
		StockDaoProxyService stockDaoProxyService=DaoFactory.getStockDaoProxy();
		try {
			return stockDaoProxyService.findAll();
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public UpToDateStockPO getUpToDateStockPO(String stockId) throws Exception {
		String bench="";
		if (stockId.charAt(2)=='6') {
			bench="sh";
		}else if (stockId.charAt(2)=='0') {
			bench="sz";
		}else {
			bench="cy";
		}
		ArrayList<UpToDateStockPO> arrayList=getToDateStockPOs(bench);
		for (UpToDateStockPO upToDateStockPO : arrayList) {
			if (upToDateStockPO.getStockId().equals(stockId)) {
				return upToDateStockPO;
			}
		}
		return new UpToDateStockPO();
	}


	@Override
	public boolean findById(String stockId) {
		StockDaoProxyService stockDaoProxyService=DaoFactory.getStockDaoProxy();
		try {
			Stock stock=stockDaoProxyService.findByID(stockId);
			if (stock!=null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
