package DAO.test;


import java.util.ArrayList;
import java.util.Calendar;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOimpl.SimulationDaoImpl;
import DAO.DAOimpl.SimulationProfitDaoImpl;
import DAO.connection.DBconnection;
import DAO.pojo.Bench;
import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import data.SimulationData.SimulationData;
import data.StockData.RecommendedData.ContinuingQuantityDown;
import data.StockData.RecommendedData.ContinuingTrendDown;
import data.StockData.RecommendedData.ContinuingTrendUp;
import data.StockData.RecommendedData.PeakDown;
import data.StockData.RecommendedData.PeakUp;
import data.StockData.RecommendedData.PriceDown;
import data.StockData.RecommendedData.PriceUp;
import data.StockData.RecommendedData.breakthroughDown;


public class Test1 {

	public static void main(String[] args) {
		
		
		
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
//		
		try {
			SimulationProfitDaoImpl simulationProfitDaoImpl=new SimulationProfitDaoImpl();
			SimulationProfit simulationProfit=new SimulationProfit();
			simulationProfit.setOperation("");
			simulationProfit.setProfit(0);
			simulationProfit.setState("");
			simulationProfit.setStockId("");
			simulationProfit.setUserId("");
			simulationProfit.setDate(Calendar.getInstance().getTime());
			System.out.println(simulationProfitDaoImpl.persist(simulationProfit));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
