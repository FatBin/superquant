package dataservice.StockDataService;

import java.util.ArrayList;

import PO.RiseStockPO;
import PO.UpToDateStockPO;

public interface StockDataService {
	/*
	 * get the data of the stock which are rising
	 */
	public ArrayList<RiseStockPO> getRiseStock() throws Exception;
	
	
	/*
	 * get the up-to-date data of all the stocks,according to the sh or sz
	 */
	public ArrayList<UpToDateStockPO> geToDateStockPOs(String exchange) throws Exception;
}
