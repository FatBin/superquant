package dataservice.UserDataService;

import DAO.pojo.User;
import ENUM.UserState;

public interface UserDataService {
/*
 * this method will tell you whether your sign-up requestion is valid.
 * three positions you will meet:
 * 	  Exist,NotExist,Others(caused by data connection problems)
 */
	public UserState insertUser(User user);
	
	
	
/*
 * this method will return the result whether the User you search for is exist
 * attention that all the attributions must be provided
 */
	public UserState searchUser(User user);
}
