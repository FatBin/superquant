package dataservice.UserDataService;

import java.util.ArrayList;

import DAO.pojo.UserStock;
import DAO.pojo.UserStrategy;
import ENUM.ManageState;

public interface UserStrategyDataService {
	/*
	 * add the strategy the user want to use to list
	 * three position you will meet:
	 * 		Succeed
	 * 		Fail: the strategy has been added
	 * 		Others
	 */
	public ManageState addStrategy(UserStrategy userStrategy);
	
	
	
	/*
	 * delete the strategy the user don't want to use temporarily
	 * two position you will meet:
	 * 		Succeed
	 * 		Fail
	 */
	public ManageState deleteStrategy(String userId,String stockId,String strategy);
	
	
	
	
	/*
	 * get all the strategy the user use
	 * !!!exception like NullPointException is potential,it must be caught
	 */
	public ArrayList<UserStrategy> getUserStrategys(String userId) throws Exception;
}
