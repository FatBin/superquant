package DAO.dao;

import java.util.List;

import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;

public interface UserStockDao {
	//insert a PO
	public boolean insert(UserStock userStock)throws Exception;
	
	//search a UserStock with its id,in this case, it is UserStockid
	public UserStock findByID(UserStockId userStockId)throws Exception;
	
	//get all UserStock data
	public List findAll()throws Exception;
	
	//delete the UserStock data
	public boolean delete(UserStockId userStockId)throws Exception;
}
