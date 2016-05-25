package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.UserStockDaoImpl;
import DAO.dao.UserStockDao;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;

public class UserStockDaoProxy implements UserStockDao{

	@Override
	public boolean insert(UserStock userStock) throws Exception {
		UserStockDao userStockDaoImpl=new UserStockDaoImpl();
		try {
			if(userStockDaoImpl.findByID(userStock.getId())==null){
				userStockDaoImpl.insert(userStock);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public UserStock findByID(UserStockId userStockId) throws Exception {
		UserStockDao userStockDaoImpl=new UserStockDaoImpl();
		try {
			return userStockDaoImpl.findByID(userStockId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		UserStockDao userStockDaoImpl=new UserStockDaoImpl();
		try {
			return userStockDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean delete(UserStockId userStockId) throws Exception {
		UserStockDao userStockDaoImpl=new UserStockDaoImpl();
		try {
			if (userStockDaoImpl.findByID(userStockId)!=null) {
				userStockDaoImpl.delete(userStockId);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
