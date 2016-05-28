package data.Database;

import java.util.ArrayList;
import java.util.Calendar;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;
import PO.industriesPO;
import data.IndustryData.IndustryData;

public class IndustriesUpdate {
	
	
	public void IndustriesUpdate() {
		try {
			IndustriesDaoProxy industriesDaoProxy=DaoFactory.getIndustriesDaoProxy();
			IndustryData industryData=new IndustryData();
			ArrayList<industriesPO> po=industryData.getIndustryData();
			Calendar calendar=Calendar.getInstance();
			int year=calendar.get(Calendar.YEAR);
			int month=calendar.get(Calendar.MONTH);
			int day=calendar.get(Calendar.DAY_OF_MONTH);
			for (industriesPO industriesPO : po) {
				IndustriesId id=new IndustriesId(industriesPO.getIndustry(),year+"-"+month+"-"+day);
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
				industriesDaoProxy.insert(industries);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
