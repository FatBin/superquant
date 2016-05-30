package DAO.DaoProxyService;

import java.util.List;

import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;

public interface UserStrategyDaoProxyService {
	//insert a PO
	public boolean insert(UserStrategy userStrategy)throws Exception;
	
	//search a bench with its id,in this case, it is UserStrategyId
	public UserStrategy findByID(UserStrategyId userStrategyId)throws Exception;
	
	//get all UserStrategy data
	public List findAll()throws Exception;
	
	//delete the UserStrategy data
	public boolean delete(UserStrategyId userStrategyId)throws Exception;
	
	//get the strategy according to the userName and strategyName
	public List getStrategy(String userName,String strategyName)throws Exception;
	
	//get the strategyName according to userName
	public List getStrategyName(String userName)throws Exception;
}
