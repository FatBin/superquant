package data.UserData;

import java.util.ArrayList;
import java.util.Iterator;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.StockDaoProxy;
import DAO.DaoProxyService.UserStockDaoProxyService;
import DAO.dao.StockDao;
import DAO.dao.UserStockDao;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;
import ENUM.ManageState;
import dataservice.UserDataService.UserStockDataService;

public class UserStockData implements UserStockDataService{

	@Override
	public ManageState addObservedStock(String userId, String stockId) {
		UserStockDaoProxyService userStockDao=DaoFactory.getUserStockDaoProxy();
		try {
			UserStockId userStockId=new UserStockId(userId, stockId);
			UserStock userStock=new UserStock(userStockId);
			if (userStockDao.insert(userStock)) {
				return ManageState.Succeed;
			}else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Others;
		}
	}

	@Override
	public ManageState deleteObservedStock(String userId, String stockId) {
		UserStockDaoProxyService userStockDao=DaoFactory.getUserStockDaoProxy();
		try {
			UserStockId userStockId=new UserStockId(userId, stockId);
			if (userStockDao.delete(userStockId)) {
				return ManageState.Succeed;
			}else {
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Fail;
		}
	}

	@Override
	public ArrayList<UserStock> getUserStocks(String userId) throws Exception{
		UserStockDaoProxyService userStockDao=DaoFactory.getUserStockDaoProxy();
		ArrayList<UserStock> arrayList=new ArrayList<>();
		try {
			Iterator iterator=userStockDao.findAll().iterator();
			while (iterator.hasNext()) {
				UserStock userStock=(UserStock)iterator.next();
				if (userStock.getId().getUserName().equals(userId)) {
					arrayList.add(userStock);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return arrayList;
	}

}
