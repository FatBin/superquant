package DAO.test;


import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.pojo.Bench;
import DAO.pojo.Simulation;


public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBconnection dBconnection=new DBconnection();
//		IndustryData industryData=new IndustryData();
//		try {
//			industryData.getIndustry("光学光电子");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			Session session=dBconnection.getSession();
			Simulation simulation=new Simulation();
			simulation.setId(1);
			simulation.setPrice(100);
			simulation.setStockId("213");
			simulation.setTime(Calendar.getInstance().getTime());
			simulation.setUserId("123");
//			Bench bench=new Bench();
//			bench.setBenchId("123");
//			bench.setBenchName("123123");
			Transaction transaction=session.beginTransaction();
			session.save(simulation);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
