package businesslogic.connection;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import businesslogicservice.initializeblservice.initializeBLService;
import data.IO.InitData;
import dataservice.connection.InitDataService;

public class initialize implements initializeBLService,Runnable{
	
	public initialize() {
		// TODO Auto-generated constructor stub
		Thread thread=new Thread(this);
		thread.start();
	}
	//初始化数据
	@Override
	public void init() {
		// TODO Auto-generated method stub
		InitDataService initializeData=new InitData();
		initializeData.initialize();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		while (true) {
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
			String time=simpleDateFormat.format(calendar.getTime());
			if(time.equals("06:00:00")){
				init();
			}
		}
	}


}
