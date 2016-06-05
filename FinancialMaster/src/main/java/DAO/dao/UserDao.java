package DAO.dao;

import java.util.List;

import DAO.pojo.User;

public interface UserDao {
	//insert a PO
	public boolean insert(User user)throws Exception;
	
	//search a User with its id
	public User findByID(String userId)throws Exception;
	
	//get all User data
	public List findAll()throws Exception;
	
}
