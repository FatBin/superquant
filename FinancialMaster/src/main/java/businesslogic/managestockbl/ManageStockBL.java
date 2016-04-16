package businesslogic.managestockbl;

import java.util.ArrayList;

import data.manageStockData.ManageStockData;
import dataservice.manageStockService.manageStockDataService;
import ENUM.ManageState;
import ENUM.attentionState;
import businesslogic.factory.InitFactory;
import businesslogicservice.managestockblservice.ManageStockBLService;

public class ManageStockBL implements ManageStockBLService {
	manageStockDataService msds=new ManageStockData();
	// 添加关注的股票
	@Override
	public ManageState addStock(String id) {
		ManageState result=msds.addStock(id);
		InitFactory factory=InitFactory.getFactory();
		factory.update(id,1);
		return result;
	}
	// 删除关注的股票
	@Override
	public ManageState deleteStock(String id) {
		ManageState result=msds.deleteStock(id);
		InitFactory factory=InitFactory.getFactory();
		factory.update(id,-1);
		return result;
	}
	//判断某支股票是否被关注
	@Override
	public attentionState isAttented(String id) {
		ArrayList<String> stockList = msds.getCodeOfStock();
		for (String string : stockList) {
			if(string.equals(id)){
				return attentionState.Yes;
			}
		}
		return attentionState.No;
	}

}
