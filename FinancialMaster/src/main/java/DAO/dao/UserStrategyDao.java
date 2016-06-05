package DAO.dao;

import java.util.List;

import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;

public interface UserStrategyDao {
	//insert a PO
	public boolean insert(UserStrategy userStrategy)throws Exception;
	
	//search a bench with its id,in this case, it is UserStrategyId
	public UserStrategy findByID(UserStrategyId userStrategyId)throws Exception;
	
	//get all UserStrategy data
	public List findAll()throws Exception;
	
	//delete the UserStrategy data
	public boolean delete(UserStrategyId userStrategyId)throws Exception;
	
	//get all data according to hql
	public List getData(String hql) throws Exception;
}
