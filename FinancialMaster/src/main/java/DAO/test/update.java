package DAO.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
//		ArrayList<String> arrayList=new ArrayList<>();
		DBconnection dBconnection=new DBconnection();
		for(int year=1990;year<2017;year++){
			for(int quarter=1;quarter<5;quarter++){
				Document document=null;
				try {
					document=Jsoup.connect("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000300/type/S.phtml?year="+year+"&jidu="+quarter).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Elements elements=document.select("table[id=FundHoldSharesTable]");
					if(elements.size()!=0){
						Element element=elements.first().select("tbody").first();
						Elements tr=element.select("tr");
						tr.remove(0);
						BenchdataDaoProxy benchdataDao=DaoFactory.getBenchdataDaoProxy();
						for(int i=tr.size()-1;i>=0;i--){
							String[] temp=tr.get(i).text().split(" ");
							BenchdataId benchdataId=new BenchdataId("sh000300",temp[0]);
							Benchdata benchdata=new Benchdata(benchdataId, Double.parseDouble(temp[1]), 
									Double.parseDouble(temp[3]), Double.parseDouble(temp[2]), 
									Double.parseDouble(temp[4]), Double.parseDouble(temp[6]),Long.parseLong(temp[5]));
							try {
								benchdataDao.insert(benchdata);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
//							arrayList.add(tr.get(i).text());
//							System.out.println(tr.get(i).text());
							
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
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
		 * insert stockdrecord
		 */
		
//		DBconnection dBconnection=new DBconnection();
		Document document;
		
//		try {
//			document=Jsoup.connect("http://www.aigaogao.com/tools/history.html?s=600000").get();
//			Element element=document.select("table[style=width:100%; border-collapse: collapse;]").get(0);
//			Elements elements=element.select("tr");
//			for(Element element2:elements)
//			System.out.println(element2.text());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		String d1 = "11/12/1212";
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//		Date date = null;
//		try {
//			date = format.parse(d1);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//有异常要捕获
//		format = new SimpleDateFormat("yyyy-MM-dd");
//		String newD = format.format(date);
//		System.out.println(newD);
	}
}
