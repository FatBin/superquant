package businesslogic.stockcheckbl;

import businesslogicservice.stockcheckblservice.StockListBLService;

public class StockListBL implements StockListBLService {

	
	@Override
	public String[][] getStockList() {
//		股票代码、开盘价、最高价、收盘价、最低价、交易量、交易金额
		String[][] test={{"600000","27.65","33.66","31.23","26.55","10240000","1000000000"}};
		return null;
	}

	@Override
	public String[][] updateStockList(String key) {

		return null;
	}

}
