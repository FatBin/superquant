package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.UserStrategyDaoImpl;
import DAO.DaoProxyService.UserStrategyDaoProxyService;
import DAO.dao.UserStrategyDao;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;

public class UserStrategyDaoProxy implements UserStrategyDaoProxyService{

	@Override
	public boolean insert(UserStrategy userStrategy) throws Exception {
		UserStrategyDao userStrategyDaoImpl=new UserStrategyDaoImpl();
		try {
			if(userStrategyDaoImpl.findByID(userStrategy.getId())==null){
				userStrategyDaoImpl.insert(userStrategy);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public UserStrategy findByID(UserStrategyId userStrategyId) throws Exception {
		UserStrategyDao userStrategyDaoImpl=new UserStrategyDaoImpl();
		try {
			return userStrategyDaoImpl.findByID(userStrategyId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		UserStrategyDao userStrategyDaoImpl=new UserStrategyDaoImpl();
		try {
			return userStrategyDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean delete(UserStrategyId userStrategyId) throws Exception {
		UserStrategyDao userStrategyDaoImpl=new UserStrategyDaoImpl();
		try {
			if (userStrategyDaoImpl.findByID(userStrategyId)!=null) {
				userStrategyDaoImpl.delete(userStrategyId);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
