package web.bl.userImpl;

import java.util.ArrayList;

import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;
import data.UserData.UserStockData;
import dataservice.UserDataService.UserStockDataService;
import web.blservice.userInfo.UserManageInfo;

public class UserManageImpl implements UserManageInfo {

	@Override
	public ManageState addStock(UserVO user, String code) {
		UserStockDataService userStock=new UserStockData();
		ManageState result=userStock.addObservedStock(user.getUsername(), code);
		if(result==ManageState.Succeed){
		        ArrayList<String> stockList=user.getStockList();
		}
		return result;
	}

	@Override
	public ManageState deleteStock(UserVO user, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState addStrategy(UserVO user, StrategyVO sv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState deleteStrategy(UserVO user, StrategyVO sv) {
		// TODO Auto-generated method stub
		return null;
	}

}
