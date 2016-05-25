package web.blservice.userInfo;

import ENUM.ManageState;
import VO.UserVO;

public interface LoginInfo {
	//注册新用户.如果返回Succeed，表明注册成功，如果返回fail，表明用户名已存在
	public ManageState add(UserVO user);
	
	//登录.如果返回Succeed，表明登录成功，如果返回fail，表明用户名不存在或密码错误
	public ManageState login(UserVO user);
}
