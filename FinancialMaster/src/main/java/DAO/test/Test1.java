package DAO.test;

import DAO.connection.DBconnection;
import data.IndustryData.IndustryData;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBconnection dBconnection=new DBconnection();
		IndustryData industryData=new IndustryData();
		try {
			industryData.getIndustry("光学光电子");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
