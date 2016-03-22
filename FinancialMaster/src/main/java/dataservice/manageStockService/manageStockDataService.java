package dataservice.manageStockService;

import java.util.ArrayList;

import ENUM.ManageState;
import PO.ObservedStockPO;

public interface manageStockDataService {
	//获取已被观察的股票代号
	public ArrayList<String> getCodeOfStock();
	
	//增加观察的股票,若成功，返回Succeed,反之，返回Fail，参数为代码代号
	public ManageState addStock(String code);
	
	//删除已观察的股票,若成功，返回Success,反之，返回Fail，参数为代码代号
	public ManageState deleteStock(String code);
}
