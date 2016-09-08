package web.blservice.stockInfo;

import PO.UpToDateStockPO;

public interface StockUpdateInfo {
	
	//更新个股的最新信息
	public UpToDateStockPO update(UpToDateStockPO upToDateMessage);

}
