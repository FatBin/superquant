package dataservice.StockDataService;

import java.util.ArrayList;
import java.util.List;

import PO.RiseStockPO;
import PO.UpToDateStockPO;

public interface StockDataService {
	/*
	 * get the data of the stock which are rising
	 */
	public ArrayList<RiseStockPO> getRiseStock() throws Exception;
	
	
	/*
	 * get the up-to-date data of the stock,according stockId
	 */
	public UpToDateStockPO getUpToDateStockPO(String stockId) throws Exception;
	
	/*
	 * get the TradeRecord from the starttime to the endtime
	 */
	public List getStockRecord(String stockId,String starttime,String endtime) throws Exception;
	
	
	/*
	 * return the list contain the Dao.POJO.Stock
	 */
	public List getStockInfos() throws Exception;
}
