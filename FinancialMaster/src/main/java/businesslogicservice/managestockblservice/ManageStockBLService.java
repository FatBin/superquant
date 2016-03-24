package businesslogicservice.managestockblservice;

import ENUM.ManageState;

public interface ManageStockBLService {
	// 添加关注的股票
	public ManageState addStock(String id);

	// 删除关注的股票
	public ManageState deleteStock(String id);
}
