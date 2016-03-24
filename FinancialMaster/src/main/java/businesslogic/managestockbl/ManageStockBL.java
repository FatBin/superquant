package businesslogic.managestockbl;

import data.manageStockData.ManageStockData;
import dataservice.manageStockService.manageStockDataService;
import ENUM.ManageState;
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

}
