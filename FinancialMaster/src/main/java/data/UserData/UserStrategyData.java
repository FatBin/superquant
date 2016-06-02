package data.UserData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.tool.hbm2ddl.Target;

import DAO.DAOfactory.DaoFactory;
import DAO.DAOimpl.UserStrategyDaoImpl;
import DAO.DaoProxy.UserStrategyDaoProxy;
import DAO.DaoProxyService.UserStrategyDaoProxyService;
import DAO.dao.UserStockDao;
import DAO.dao.UserStrategyDao;
import DAO.pojo.UserStock;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import ENUM.ManageState;
import dataservice.UserDataService.UserStrategyDataService;

public class UserStrategyData implements UserStrategyDataService {

	@Override
	public ManageState addStrategy(UserStrategy userStrategy) {
		UserStrategyDaoProxyService userStrategyDao = DaoFactory.getUserStrategyDaoProxy();
		try {
			if (userStrategyDao.insert(userStrategy)) {
				return ManageState.Succeed;
			} else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Others;
		}
	}

	@Override
	public ManageState deleteStrategy(String userId, String stockId, String strategy) {
		UserStrategyDaoProxyService userStrategyDao = DaoFactory.getUserStrategyDaoProxy();
		try {
			UserStrategyId userStrategyId = new UserStrategyId(userId, stockId, strategy);
			if (userStrategyDao.delete(userStrategyId)) {
				return ManageState.Succeed;
			} else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Fail;
		}
	}

	@Override
	public List getUserStrategys(String userId, String strategy) throws Exception {
		UserStrategyDaoProxyService userStrategyDao = DaoFactory.getUserStrategyDaoProxy();
		try {
			return userStrategyDao.getStrategy(userId, strategy);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List getUserStrategyNames(String userName) throws Exception {
		UserStrategyDaoProxyService userStrategyDao = DaoFactory.getUserStrategyDaoProxy();
		try {
			return userStrategyDao.getStrategyName(userName);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteTheWholeStrategy(String userName, String strategyName) throws Exception {
		UserStrategyDaoProxyService dao = new UserStrategyDaoProxy();
		dao.deleteStrategy(userName, strategyName);
	}

}
