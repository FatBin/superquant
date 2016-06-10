package web.bl.StrategyHandle;

import java.util.List;

import DAO.pojo.UserStrategy;
import data.UserData.UserStrategyData;
import web.blservice.StrategyHandleService.GetStrategyInfo;
import dataservice.UserDataService.UserStrategyDataService;

public class GetStrategyImpl implements GetStrategyInfo {

	@Override
	public List<UserStrategy> getStrategy(String userName, String StrategyName) {
		UserStrategyDataService userStrategyDataService=new UserStrategyData();
		try {
			List<UserStrategy> result=userStrategyDataService.getUserStrategys(userName, StrategyName);
		    return result;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
