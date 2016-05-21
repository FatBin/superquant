package DAO.dao;

import DAO.pojo.Bench;
import DAO.pojo.User;
import antlr.collections.List;

public interface UserDao {
	//insert a PO
	public boolean insert(User user)throws Exception;
	
	//search a User with its id
	public User findByID(String userId)throws Exception;
	
	//get all User data
	public List findAll()throws Exception;
}
