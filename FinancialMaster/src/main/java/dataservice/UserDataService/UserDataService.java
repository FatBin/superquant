package dataservice.UserDataService;

import DAO.pojo.User;
import ENUM.ManageState;

public interface UserDataService {
/*
 * this method will tell you whether your sign-up requestion is valid.
 * three positions you will meet:
 * 	  Succeed,Fail(for the UserName has been used),Others(caused by data connection problems)
 */
	public ManageState insertUser(User user);
	
	
	
/*
 * this method will return the result whether the User you search for is exist
 * attention that all the attributions must be provided
 */
	public ManageState verifyUser(User user);
}
