package businesslogicservice.stockcheckblservice;

import java.util.ArrayList;

public interface StockSearchBLService {

	//根据输入的关键字，实时更新股票代号列表
	public String[] getList(String key);
}
