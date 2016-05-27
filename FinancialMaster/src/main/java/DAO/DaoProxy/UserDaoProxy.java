package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.UserDaoImpl;
import DAO.DaoProxyService.UserDaoProxyService;
import DAO.dao.UserDao;
import DAO.pojo.User;

public class UserDaoProxy implements UserDaoProxyService{

	@Override
	public boolean insert(User user) throws Exception {
		UserDao userDaoImpl=new UserDaoImpl();
		try {
			if(userDaoImpl.findByID(user.getUsername())==null){
				userDaoImpl.insert(user);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public User findByID(String userId) throws Exception {
		UserDao userDaoImpl=new UserDaoImpl();
		try {
			return userDaoImpl.findByID(userId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		UserDao userDaoImpl=new UserDaoImpl();
		try {
			return userDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
