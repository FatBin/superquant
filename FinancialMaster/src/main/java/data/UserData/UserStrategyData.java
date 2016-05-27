package data.UserData;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.tool.hbm2ddl.Target;

import DAO.DAOfactory.DaoFactory;
import DAO.dao.UserStockDao;
import DAO.dao.UserStrategyDao;
import DAO.pojo.UserStock;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import ENUM.ManageState;
import dataservice.UserDataService.UserStrategyDataService;

public class UserStrategyData implements UserStrategyDataService{

	@Override
	public ManageState addStrategy(String userId,String stockId, String strategy,String starttime,
			String endtime,int cost,int frequency,double weight,String buystrategy,String sellstrategy,String otherstrategy) {
		UserStrategyDao userStrategyDao=DaoFactory.getUserStrategyDaoProxy();
		try {
			UserStrategyId userStrategyId=new UserStrategyId(userId,stockId,strategy);
			UserStrategy userStrategy=new UserStrategy(userStrategyId,starttime,
					endtime,cost,frequency,weight,buystrategy,sellstrategy,otherstrategy);
			if (userStrategyDao.insert(userStrategy)) {
				return ManageState.Succeed;
			}else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Others;
		}
	}

	@Override
	public ManageState deleteStrategy(String userId, String stockId,String strategy) {
		UserStrategyDao userStrategyDao=DaoFactory.getUserStrategyDaoProxy();
		try {
			UserStrategyId userStrategyId=new UserStrategyId(userId, stockId,strategy);
			if (userStrategyDao.delete(userStrategyId)) {
				return ManageState.Succeed;
			}else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Fail;
		}
	}

	@Override
	public ArrayList<UserStrategy> getUserStrategys(String userId) throws Exception{
		UserStrategyDao userStrategyDao=DaoFactory.getUserStrategyDaoProxy();
		ArrayList<UserStrategy> arrayList=new ArrayList<>();
		try {
			Iterator iterator=userStrategyDao.findAll().iterator();
			while (iterator.hasNext()) {
				UserStrategy userStrategy=(UserStrategy)iterator.next();
				if (userStrategy.getId().getUserId().equals(userId)) {
					arrayList.add(userStrategy);
				}
			}
			return arrayList;
		} catch (Exception e) {
			throw e;
		}

	}

}
