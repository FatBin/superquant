package businesslogic.loginbl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import DAO.pojo.User;
import ENUM.ManageState;
import VO.UserVO;
import businesslogicservice.loginblservice.LoginBLService;
import data.UserData.UserData;
import dataservice.UserDataService.UserDataService;

public class LoginBL implements LoginBLService {
	UserDataService userData=new UserData();
	@Override
	public ManageState add(UserVO user) {				
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String addDate = format.format(cal.getTime());
		User new_user=new User(user.getUsername(),user.getPassword(),addDate);
		ManageState userState=userData.insertUser(new_user);
		if(userState==ManageState.Succeed){
			addUserMessage(user);
		}
		return userState;
	}

	@Override
	public ManageState login(UserVO user) {
		User new_user=new User();
		new_user.setUsername(user.getUsername());
		new_user.setUserpassword(user.getPassword());
		ManageState userState=userData.verifyUser(new_user);
		if(userState==ManageState.Succeed){
			addUserMessage(user);
		}
		return userState;
	}

	private ManageState addUserMessage(UserVO user){
		return null;
	}
}
