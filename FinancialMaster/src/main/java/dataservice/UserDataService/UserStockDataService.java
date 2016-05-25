package dataservice.UserDataService;

import java.util.ArrayList;

import DAO.pojo.UserStock;
import ENUM.ManageState;

public interface UserStockDataService {
	/*
	 * add the stock the user want to observe to list
	 * three position you will meet:
	 * 		Succeed
	 * 		Fail: the stock has been added
	 * 		Others
	 */
	public ManageState addObservedStock(String userId,String stockId);
	
	
	
	/*
	 * delete the stock the user don't want to observe temporarily
	 * two position you will meet:
	 * 		Succeed
	 * 		Fail
	 */
	public ManageState deleteObservedStock(String userId,String stockId);
	
	
	
	
	/*
	 * get all the stock the user observe
	 * !!!exception like NullPointException is potential,it must be caught
	 */
	public ArrayList<UserStock> getUserStocks(String userId) throws Exception;
}
