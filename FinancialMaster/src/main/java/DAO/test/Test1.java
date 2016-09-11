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
		
		try {
//			Document document=Jsoup.connect("http://data.10jqka.com.cn/rank/cxg/board/4/field/stockcode/order/asc/page/1/ajax/1/").get();
//			Elements elements=document.select("tbody").select("tr");
//			for (Element element : elements) {
//				System.out.println(element.text());
//			}
			PriceDown breakthrough=new PriceDown();
			ArrayList<PricePO> arrayList=breakthrough.getDatas();
			System.out.println("yes");
			System.out.println(arrayList.size());
			for (PricePO breakthroughPO : arrayList) {
				System.out.println(breakthroughPO.getStockName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		DBconnection dBconnection=new DBconnection();
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
//		try {
//			SimulationProfitDaoImpl simulationProfitDaoImpl=new SimulationProfitDaoImpl();
//			System.out.println(simulationProfitDaoImpl.getAmontOfProfits());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
