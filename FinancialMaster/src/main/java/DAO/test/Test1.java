package DAO.test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOimpl.BenchdataAccurateDaoImpl;
import DAO.DAOimpl.SimulationDaoImpl;
import DAO.DAOimpl.SimulationProfitDaoImpl;
import DAO.DAOimpl.SimulationcommissionDaoImpl;
import DAO.connection.DBconnection;
import DAO.pojo.Bench;
import DAO.pojo.BenchdataAccurate;
import DAO.pojo.Simulation;
import DAO.pojo.SimulationProfit;
import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.ContinuingTrendPO;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;
import data.BenchData.BenchdataAccurateUpdate;
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
//			String hql="delete from BenchdataAccurate where 1=1";
//			Session session=dBconnection.getSession();
//			Transaction transaction=session.beginTransaction();
//			session.createQuery(hql).executeUpdate();
//			transaction.commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			SimulationProfitDaoImpl simulationProfitDaoImpl=new SimulationProfitDaoImpl();
//			SimulationProfit simulationProfit=new SimulationProfit();
//			simulationProfit.setOperation("");
//			simulationProfit.setProfit(0);
//			simulationProfit.setState("");
//			simulationProfit.setStockId("");
//			simulationProfit.setUserId("");
//			simulationProfit.setDate(Calendar.getInstance().getTime());
//			System.out.println(simulationProfitDaoImpl.persist(simulationProfit));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			SimulationProfitDaoImpl impl=new SimulationProfitDaoImpl();
//			System.out.println(impl.getAllSimulationProfits("123").size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			BenchdataAccurateDaoImpl benchdataAccurateDaoImpl=new BenchdataAccurateDaoImpl();
//			List list=benchdataAccurateDaoImpl.getBenchdataAccurate();
//			for (Object object : list) {
//				BenchdataAccurate po=(BenchdataAccurate) object;
//				System.out.println(po.getId().getBenchId());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			Document document=Jsoup.connect("https://gupiao.baidu.com/stock/sh000001.html?from=aladingpc").get();
//			System.out.println(document.select("div[class=price s-down ]").text());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		BenchdataAccurateUpdate accurateUpdate=new BenchdataAccurateUpdate();
		BenchdataAccurateDaoImpl benchdataAccurateDaoImpl=new BenchdataAccurateDaoImpl();
		benchdataAccurateDaoImpl.clean();
	}

}
