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

	@Override
	public List getStrategy(String userName, String strategyName) throws Exception {
		UserStrategyDao dao=new UserStrategyDaoImpl();
		String hql="from UserStrategy u where "
				+ "u.id.userId='"+userName+"' and "
				+ "u.id.strategyName='"+strategyName+"'";
		return dao.getData(hql);
	}

	@Override
	public List getStrategyName(String userName) throws Exception {
		UserStrategyDao dao=new UserStrategyDaoImpl();
		String hql="select distinct u.id.strategyName from UserStrategy u where "
				+ "u.id.userId='"+userName+"'";
		return dao.getData(hql);
	}

	@Override
	public void deleteStrategy(String userName, String strategyName) throws Exception {
		UserStrategyDao dao=new UserStrategyDaoImpl();
			List list=getStrategy(userName, strategyName);
			for (Object object : list) {
				UserStrategy userStrategy=(UserStrategy) object;
				delete(userStrategy.getId());
			}
	}

}
