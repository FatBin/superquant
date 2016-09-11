package DAO.test;


import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import DAO.DAOimpl.SimulationDaoImpl;
import DAO.connection.DBconnection;
import DAO.pojo.Bench;
import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import data.SimulationData.SimulationData;


public class Test1 {

	public static void main(String[] args) {
		
//		try {
//			Document document=Jsoup.connect("http://data.10jqka.com.cn/rank/ljqs/field/count/order/desc/page/2/ajax/1/").get();
//			System.out.println(document);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		DBconnection dBconnection=new DBconnection();
//		try {
//			String hql="delete from Simulation where 1=1";
//			Session session=dBconnection.getSession();
//			Transaction transaction=session.beginTransaction();
//			session.createQuery(hql).executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			Simulation simulation=new Simulation();
			simulation.setPrice(0);
			simulation.setStockId("");
			simulation.setTime(Calendar.getInstance().getTime());
			simulation.setUserId("");
			simulation.setVolume(0);
			SimulationDaoImpl simulationDaoImpl=new SimulationDaoImpl();
			System.out.println(simulationDaoImpl.persist(simulation));
//			System.out.println(simulationDaoImpl.getAllSimulations().size());
			SimulationData simulationData=new SimulationData();
			System.out.println(simulationData.getAllSimulations().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
