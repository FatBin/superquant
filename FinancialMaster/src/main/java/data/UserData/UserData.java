package data.UserData;

import DAO.DAOfactory.DaoFactory;
import DAO.dao.UserDao;
import DAO.pojo.User;
import ENUM.ManageState;
import ENUM.UserState;
import dataservice.UserDataService.UserDataService;

public class UserData implements UserDataService{
	/*
	 * annotation refers to the UserDataService
	 */
	@Override
	public ManageState insertUser(User user) {
		UserDao userDao=DaoFactory.getUserDaoProxy();
		try {
			if (userDao.insert(user)) {
				return ManageState.Succeed;
			}else{
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Others;
		}
	}
	
	
	
	/*
	 * annotation refers to the UserDataService
	 */
	@Override
	public ManageState verifyUser(User user) {
		UserDao userDao=DaoFactory.getUserDaoProxy();
		try {
			User temp=userDao.findByID(user.getUsername());
			if(temp!=null){
				if(temp.getUsername()==user.getUsername()){
					return ManageState.Succeed;
				}else{
					return ManageState.Fail;
				}
			}else{
				return ManageState.Fail;
			}
		} catch (Exception e) {
			return ManageState.Others;
		}
	}


	

	
}
