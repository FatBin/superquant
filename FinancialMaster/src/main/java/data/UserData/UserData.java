package data.UserData;

import DAO.pojo.User;
import ENUM.UserState;
import dataservice.UserDataService.UserDataService;

public class UserData implements UserDataService{
/*
 * annotation refers to the UserDataService
 */
	@Override
	public UserState insertUser(User user) {
		try {
			return null;
		} catch (Exception e) {
			return UserState.Others;
		}
	}

	
/*
 * annotation refers to the UserDataService
 */
	@Override
	public UserState searchUser(User user) {
		try {
			return null;
		} catch (Exception e) {
			return UserState.Others;
		}
	}
	
}
