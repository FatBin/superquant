package web.blservice.StrategyHandleService;

import java.util.List;

import DAO.pojo.UserStrategy;

public interface GetStrategyInfo {

	//根据用户名和策略名，得到对应才策略信息
	public List<UserStrategy> getStrategy(String userName,String StrategyName);
	
}
