package dataservice.UserDataService;

import java.util.ArrayList;
import java.util.List;

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
	 * get all the strategy the user use,according to the strategyName and userName
	 * it will return a list of UserStrategy
	 * !!!exception like NullPointException is potential,it must be caught
	 */
	public List getUserStrategys(String userId,String strategy) throws Exception;
	
	
	/*
	 * get all the strategyNames of the user,according to the userName
	 * it will return a list of String 
	 */
	public List getUserStrategyNames(String userName) throws Exception;
	
	/*
	 * delete the strategy according to the strategyName
	 */
	public void deleteTheWholeStrategy(String userName, String strategyName) throws Exception;
}
