package businesslogic.managestockbl;

import java.util.ArrayList;

import data.manageStockData.ManageStockData;
import dataservice.manageStockService.manageStockDataService;
import ENUM.ManageState;
import ENUM.attentionState;
import businesslogicservice.managestockblservice.ManageStockBLService;

public class ManageStockBL implements ManageStockBLService {
	manageStockDataService msds=new ManageStockData();
	@Override
	public ManageState addStock(String id) {
		return msds.addStock(id);
	}

	@Override
	public ManageState deleteStock(String id) {
		return msds.deleteStock(id);
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
