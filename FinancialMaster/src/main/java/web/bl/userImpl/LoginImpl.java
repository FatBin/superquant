package web.bl.userImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.pojo.Stock;
import DAO.pojo.User;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;
import DAO.pojo.UserStrategy;
import ENUM.ManageState;
import VO.UserVO;
import data.UserData.UserData;
import data.UserData.UserStockData;
import data.UserData.UserStrategyData;
import dataservice.UserDataService.UserDataService;
import dataservice.UserDataService.UserStockDataService;
import dataservice.UserDataService.UserStrategyDataService;
import servlet.factory.InitFactoryServlet;
import web.blservice.userInfo.LoginInfo;

public class LoginImpl implements LoginInfo {
	UserDataService userData=new UserData();
	@Override
	public ManageState add(UserVO user) {				
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String addDate = format.format(cal.getTime());
		User new_user=new User(user.getUsername(),user.getPassword(),addDate);
		ManageState userState=userData.insertUser(new_user);
		if(userState==ManageState.Succeed){
			if(addUserMessage(user)==ManageState.Fail){
				userState=ManageState.Fail;
			}
		}
		System.out.println(user.getUsername()+user.getPassword()+addDate+userState);
		return userState;
	}

	@Override
	public ManageState login(UserVO user) {
		User new_user=new User();
		new_user.setUsername(user.getUsername());
		new_user.setUserpassword(user.getPassword());
		ManageState userState=userData.verifyUser(new_user);
		if(userState==ManageState.Succeed){
			if(addUserMessage(user)==ManageState.Fail){
				userState=ManageState.Fail;
			}
		}
		return userState;
	}

	private ManageState addUserMessage(UserVO user){
		UserStockDataService userStockDataService=new UserStockData();
		UserStrategyDataService userStrategyDataService=new UserStrategyData();
		try {
			ArrayList<UserStock> userStocks=userStockDataService.getUserStocks(user.getUsername());
			ArrayList<Stock> stocks=new ArrayList<Stock>();
			String id;
			for (UserStock userStock : userStocks) {
				UserStockId userStockId=userStock.getId();
				id=userStockId.getStockId();
				Stock stock=InitFactoryServlet.getStock(id);
				stocks.add(stock);
			}
			user.setStockList(stocks);
			List<String> userStrategies=userStrategyDataService.getUserStrategyNames(user.getUsername());
			ArrayList<String> list=new ArrayList<String>();
			for (String string : userStrategies) {
				list.add(string);
			}
			user.setStrategy(list);
			return ManageState.Succeed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ManageState.Fail;
	}

}
