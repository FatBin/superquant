package web.blservice.userInfo;

import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;

public interface UserManageInfo {

	//增加关注的股票
	public ManageState addStock(UserVO user,String code);
	
	//删除关注的股票
	public ManageState deleteStock(UserVO user,String code);
	
	//增加一个策略
	public ManageState addStrategy(UserVO user,StrategyVO sv);
	
	//删除一个策略
	public ManageState deleteStrategy(UserVO user,StrategyVO sv);
}
