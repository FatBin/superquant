package web.blservice.userInfo;

import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;

public interface UserManageInfo {

	//增加关注的股票
	public ManageState addStock(String code);
	
	//删除关注的股票
	public ManageState deleteStock(String code);
	
	//增加一个策略
	public ManageState addStrategy(StrategyVO sv);
	
	//删除一个策略
	public ManageState deleteStrategy(StrategyVO sv);
	
	//更新当前登录用户的个人信息
	public ManageState updateUser(UserVO user);
}
