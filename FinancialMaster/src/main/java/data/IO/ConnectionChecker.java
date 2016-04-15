package data.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import dataservice.connection.connection;

public class ConnectionChecker implements connection{

	@Override
	public boolean checkconnection() {
		// TODO Auto-generated method stub
        try{
            URL myurl = new URL("http://www.baidu.cn");
            URLConnection myurlcon = myurl.openConnection();
            myurlcon.setConnectTimeout(1000);
            myurlcon.setReadTimeout(1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(myurlcon.getInputStream(),"UTF-8"));
            return false;
        } catch (IOException e) {
          e.printStackTrace();
          return true;
        }
 	}

}
