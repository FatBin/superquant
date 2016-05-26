package DAO.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.BenchDaoProxy;
import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.connection.DBconnection;
import DAO.dao.BenchdataDao;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class update {
	public static void main(String[] args) {
		/*
		 * insert stock
		 */
		ArrayList<String> arrayList=new ArrayList<>();
		DBconnection dBconnection=new DBconnection();
		for(int year=1990;year<2017;year++){
			for(int quarter=1;quarter<5;quarter++){
				Document document=null;
				try {
					document=Jsoup.connect("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/399001/type/S.phtml?year="+year+"&jidu="+quarter).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Elements elements=document.select("table[id=FundHoldSharesTable]");
				if(elements.size()!=0){
					Element element=elements.first().select("tbody").first();
					Elements tr=element.select("tr");
					tr.remove(0);
					BenchdataDaoProxy benchdataDao=DaoFactory.getBenchdataDaoProxy();
					for(int i=tr.size()-1;i>=0;i--){
						String[] temp=tr.get(i).text().split(" ");
						BenchdataId benchdataId=new BenchdataId("sz399001",temp[0]);
						Benchdata benchdata=new Benchdata(benchdataId, Double.parseDouble(temp[1]), 
								Double.parseDouble(temp[2]), Double.parseDouble(temp[3]), 
								Double.parseDouble(temp[4]), Double.parseDouble(temp[5]),Long.parseLong(temp[6]));
						try {
							benchdataDao.insert(benchdata);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						arrayList.add(tr.get(i).text());
//						System.out.println(tr.get(i).text());
						
					}

				}
			}
		}
		System.out.println("yes");
//		DBconnection dBconnection=new DBconnection();
//		StockDao stockDao=DaoFactory.getStockDaoProxy();
//		for (Element element : elements) {
//			String[] text=element.text().split(" ");
//			Stock stock=new Stock("sh"+text[1],text[2],text[4]);
//			try {
//				stockDao.insert(stock);g
//			} catch (Exception e) {
//				e.printStackTrace( );
//			}
//		}
//		System.out.println("completed");
//		System.out.println(id.size());
		
		/*
		 * insert bench
		 */
	}
}
