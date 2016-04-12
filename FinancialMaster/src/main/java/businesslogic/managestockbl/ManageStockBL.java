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
	@Override
	public ManageState addStock(String id) {
		ManageState result=msds.addStock(id);
		InitFactory factory=InitFactory.getFactory();
		factory.update(id,1);
		return result;
	}

	@Override
	public ManageState deleteStock(String id) {
		ManageState result=msds.deleteStock(id);
		InitFactory factory=InitFactory.getFactory();
		factory.update(id,-1);
		return result;
	}

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
