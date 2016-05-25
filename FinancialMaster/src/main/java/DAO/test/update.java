package DAO.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.connection.DBconnection;
import DAO.dao.StockDao;
import DAO.pojo.Stock;
import PO.codeNamePO;
import PO.stockStatisticPO;
import data.stockcheckdata.StockData;

public class update {
	public static void main(String[] args) {
		/*
		 * insert stock
		 */
//		Document document=null;
//		try {
//			document=Jsoup.connect("http://www.shdjt.com/sh.htm").get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Elements elements=document.select("tr[height=25]");
//		DBconnection dBconnection=new DBconnection();
//		StockDao stockDao=DaoFactory.getStockDaoProxy();
//		for (Element element : elements) {
//			String[] text=element.text().split(" ");
//			Stock stock=new Stock("sh"+text[1],text[2],text[4]);
//			try {
//				stockDao.insert(stock);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("completed");
//		System.out.println(id.size());
		
		/*
		 * insert bench
		 */
	}
}
