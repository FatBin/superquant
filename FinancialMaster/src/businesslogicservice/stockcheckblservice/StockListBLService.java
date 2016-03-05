package businesslogicservice.stockcheckblservice;

public interface StockListBLService {

	// 返回预选股票的列表，用二维数组展示
	public String[][] getStockList();

	// 返回筛选过后的列表
	public String[][] updateStockList(String key);
}
