package businesslogicservice.managestockblservice;

import ENUM.ManageState;
import ENUM.attentionState;

public interface ManageStockBLService {
	
	//判断某支股票是否被关注
	public attentionState isAttented(String id);
	
	// 添加关注的股票
	public ManageState addStock(String id);

	// 删除关注的股票
	public ManageState deleteStock(String id);
}
