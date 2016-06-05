package data.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;
import DAO.test.test;
import PO.industriesPO;
import data.IndustryData.IndustryData;

public class IndustriesUpdate {
	
	public void IndustriesUpdate() {
		try {
			IndustriesDaoProxy industriesDaoProxy=DaoFactory.getIndustriesDaoProxy();
			IndustryData industryData=new IndustryData();
			ArrayList<industriesPO> po=industryData.getIndustryData();
			Calendar calendar=Calendar.getInstance();
			Date date=calendar.getTime();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String time=format.format(date);
			for (industriesPO industriesPO : po) {
				IndustriesId id=new IndustriesId(industriesPO.getIndustry(),time);
				Industries industries=new Industries(id,
						industriesPO.getCompany(),
						industriesPO.getAverage_price(),
						industriesPO.getRise_fall(),
						industriesPO.getVolume(),
						industriesPO.getTurnover(),
						industriesPO.getInflows(),
						industriesPO.getLeaderstock(),
						industriesPO.getPrice(),
						industriesPO.getStock_rise_fall());
				try {
					industriesDaoProxy.insert(industries);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
}
	
	

}
