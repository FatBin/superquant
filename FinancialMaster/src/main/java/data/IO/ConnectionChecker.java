package data.IO;

import dataservice.connection.connection;

public class ConnectionChecker implements connection{

	@Override
	public boolean checkconnection() {
		// TODO Auto-generated method stub
		String result=HttpRequest.sendGet("https://www.baidu.com/", "");
		if(result.equals("")){
			return true;
		}else{
			return false;
		}
	}

}
