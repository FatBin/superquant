package web.bl.userImpl;

import java.util.ArrayList;

import DAO.DaoProxy.UserStrategyDaoProxy;
import DAO.DaoProxyService.UserStrategyDaoProxyService;
import DAO.pojo.Stock;
import DAO.pojo.UserStrategy;
import ENUM.ManageState;
import VO.StrategyVO;
import VO.UserVO;
import data.UserData.UserStockData;
import data.UserData.UserStrategyData;
import dataservice.UserDataService.UserStockDataService;
import dataservice.UserDataService.UserStrategyDataService;
import servlet.factory.InitFactoryServlet;
import web.blservice.userInfo.UserManageInfo;

public class UserManageImpl implements UserManageInfo {

	@Override
	public ManageState addStock(UserVO user, String code) {
		UserStockDataService userStock=new UserStockData();
		ManageState result=userStock.addObservedStock(user.getUsername(), code);
		if(result==ManageState.Succeed){
		        ArrayList<Stock> stockList=user.getStockList();
		        Stock stock=InitFactoryServlet.getStock(code);
		        stockList.add(stock);
		        user.setStockList(stockList);
		}
		return result;
	}

	@Override
	public ManageState deleteStock(UserVO user, String code) {
		UserStockDataService userStock=new UserStockData();
		ManageState result=userStock.deleteObservedStock(user.getUsername(), code);
		if(result==ManageState.Succeed){
		        ArrayList<Stock> stockList=user.getStockList();
		        Stock stock=InitFactoryServlet.getStock(code);
		        stockList.remove(stock);
		        user.setStockList(stockList);
		}
		return result;
	}

	@Override
	public ManageState addStrategy(UserVO user, StrategyVO sv) {
		ArrayList<UserStrategy> userStrategies=sv.getUserStrategies();
		UserStrategyDataService userStrategyDataService=new UserStrategyData();
		ManageState result=ManageState.Succeed;
		for (UserStrategy userStrategy : userStrategies) {
			result=userStrategyDataService.addStrategy(userStrategy);
			if(result!=ManageState.Succeed){
				break;
			}
		}
		if(result==ManageState.Succeed){
			ArrayList<String> strategyList=user.getStrategy();
			strategyList.add(sv.getStrategyName());
			user.setStrategy(strategyList);
		}
		return result;
	}

	@Override
	public ManageState deleteStrategy(UserVO user, String strategyName) {
		UserStrategyDataService userStrategyDataService=new UserStrategyData();
		ManageState result=ManageState.Succeed;
		try {
			userStrategyDataService.deleteTheWholeStrategy(user.getUsername(), strategyName);
		} catch (Exception e) {
			result=ManageState.Fail;
		}

		if(result==ManageState.Succeed){
			ArrayList<String> strategyList=user.getStrategy();
			strategyList.remove(strategyName);
			user.setStrategy(strategyList);
		}
		return result;
	}

}
