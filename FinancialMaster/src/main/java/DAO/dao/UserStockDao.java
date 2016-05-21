package DAO.dao;

import DAO.pojo.Bench;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;
import antlr.collections.List;

public interface UserStockDao {
	//insert a PO
	public boolean insert(UserStock userStock)throws Exception;
	
	//search a UserStock with its id,in this case, it is UserStockid
	public UserStock findByID(UserStockId userStockId)throws Exception;
	
	//get all UserStock data
	public List findAll()throws Exception;
}
