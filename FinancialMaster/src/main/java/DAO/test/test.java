package DAO.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DAOimpl.StockDaoImpl;
import DAO.DAOimpl.TradeRecordDaoImpl;
import DAO.DAOimpl.UserStrategyDaoImpl;
import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.DaoProxy.UserStrategyDaoProxy;
import DAO.DaoProxyService.IndustriesDaoProxyService;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.DaoProxyService.UserStrategyDaoProxyService;
import DAO.connection.DBconnection;
import DAO.dao.TradeRecordDao;
import DAO.dao.UserStrategyDao;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import PO.RiseStockPO;
import PO.UpToDateStockPO;
import PO.benchCurrentDataPO;
import PO.codeNamePO;
import PO.industriesPO;
import PO.industryPO;
import PO.stockStatisticPO;
import data.BenchData.BenchData;
import data.BenchData.BenchRecord;
import data.IO.HttpRequest;
import data.IndustryData.IndustryData;
import data.StockData.StockData;
import dataservice.BenchDataService.BenchRecordService;
import dataservice.IndustryDataService.IndustryDataService;
import dataservice.StockDataService.StockDataService;
import presentation.stockcheckui.selectPanel;

public class test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*
		 * connection test
		 */
		// Bench bench=new Bench("how","what");
		// User user=new User("2", "2", "3");
		// BenchdataId benchdataId=new
		// BenchdataId(bench.getBenchId(),bench.getBenchName());
		// Benchdata benchdata=new
		// Benchdata(benchdataId,bench,2,1.0,1.0,1.0,1.0,1);
		// Configuration configuration=new
		// Configuration().configure("DAO/pojo/hibernate.cfg.xml");
		// DBconnection dBconnection=new DBconnection();
		// SessionFactory sessionFactory=configuration.buildSessionFactory();
		// Session session;
		// try {
		// session = dBconnection.getSession();
		// session.save(user);
		// Transaction tx=session.beginTransaction();
		// tx.commit();
		// session.close();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// session.save(bench);
		// session.save(benchdata);

		/*
		 * exception test
		 */
		// DBconnection dBconnection=new DBconnection();
		// BenchDao benchDao=new BenchDaoProxy();
		// BenchdataDao benchdataDao=new BenchdataDaoProxy();
		try {
			// Bench bench=benchDaoImpl.findByID(1+"");
			// System.out.println(bench.getBenchName());
			// Bench bench=new Bench("1","3");
			// benchDaoImpl.insert(bench);

			// benchDao.insert(bench);
		} catch (NullPointerException e) {
			// e.printStackTrace();
			System.out.println("yes");
		} catch (ConstraintViolationException e2) {
			System.out.println("no");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// UserStockDao strategyDao=new UserStockDaoProxy();
			// UserStockId userStrategyId=new UserStockId("asdf", "1");
			// UserStrategy usStrategy=new UserStrategy(userStrategyId);
			// System.out.println(strategyDao.insert(usStrategy));
			// System.out.println(strategyDao.findByID(userStrategyId));
			// System.out.println(strategyDao.findAll());
			// System.out.println(strategyDao.delete(userStrategyId));
			// dBconnection.sessionFactory.close();
		} catch (Exception e) {
			System.out.println("duanwang");
			e.printStackTrace();
		}

		/*
		 * test dataservice
		 */
		// try {
		// String userId="s";
		// String stockId="que";
		// UserStrategyData userStockData=new UserStrategyData();
		// System.out.println(userStockData.addStrategy(userId, stockId));
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		//

		/*
		 * test hql
		 */
		try {
			// DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
			// Date date=fmt.parse("2014-13-12");
			// Session session=dBconnection.getSession();
			// Benchrecord benchrecord=new Benchrecord(date);
			// session.save(benchrecord);
			// Transaction transaction=session.beginTransaction();
			// transaction.commit();
			// String hql="select a.id.date from Benchdata a where
			// Month(Date(a.id.date))=12 order by a.id.date desc";
			// Query query=session.createQuery(hql);
			// List list=query.list();
			// System.out.println(list.get(0).getClass());
			// session.close();
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * test benchdataservice
		 */
		// BenchDataService benchDataService=new BenchData();
		// try {
		// System.out.println(benchDataService.getBench());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		/*
		 * test BenchDataDaoService
		 */
		// try {
		// BenchDataDaoService benchDataDaoService=new BenchdataDaoProxy();
		// List list=benchDataDaoService.getBenchRecord("sh000300",
		// "2014-11-24", "2015-01-02");
		// for(int i=0;i<list.size();i++){
		// Benchdata benchdata=(Benchdata) list.get(i);
		// System.out.println(benchdata.getId().getDate());
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		/*
		 * test date()
		 */
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");//设置日期格式
		// System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		//
		// Calendar calendar=Calendar.getInstance();
		// System.out.println(calendar.get());

		/*
		 * change code to gbk
		 */
		String t = "\u957f\u6625\u71c3\u6c14";
		// String utf8 = new String(t.getBytes( "UTF-8"));
		// System.out.println(utf8);
		// String unicode = new String(utf8.getBytes(),"UTF-8");
		// System.out.println(unicode);
		// String gbk = new String(t.getBytes("GBK"));
		// System.out.println(gbk);

		// HttpRequest httpRequest = new HttpRequest();
		// String resultString =
		// httpRequest.sendGet("http://quotes.money.163.com/trade/lsjysj_zhishu_399001.html",
		// "");
		// System.out.println(resultString);
		// Document result;
		// try {
		// result =
		// Jsoup.connect("http://q.10jqka.com.cn/interface/stock/detail/zdf/desc/1/1/hxzp").get();
		// System.out.println(result);
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// JSONObject jsonObject = new JSONObject(resultString);
		// JSONArray jsonArray = jsonObject.getJSONArray("data");
		// String[] strings = JSONObject.getNames(jsonArray.getJSONObject(0));
		// for (int i = 0; i < strings.length; i++) {
		// JSONObject jsonObject2 = jsonArray.getJSONObject(0);
		// System.out.println(jsonObject2);
		// }

		/*
		 * test industrydataservice
		 */
		// IndustryData industryData=new IndustryData();
		// ArrayList<industryPO> arrayList=industryData.getIndustryData();
		// for(industryPO industryPO: arrayList){
		// System.out.print(industryPO.getIndustry()+" ");
		// System.out.print(industryPO.getCompany()+" ");
		// System.out.print(industryPO.getAverage_price()+" ");
		// System.out.print(industryPO.getRise_fall()+" ");
		// System.out.print(industryPO.getVolume()+" ");
		// System.out.print(industryPO.getTurnover()+" ");
		// System.out.print(industryPO.getInflows()+" ");
		// System.out.print(industryPO.getLeaderstock()+" ");
		// System.out.print(industryPO.getPrice()+" ");
		// System.out.println(industryPO.getStock_rise_fall()+" ");
		// }

		/*
		 * test new userstrategy
		 */
		// UserStrategyData strategyDao=new UserStrategyData();
		try {
			// System.out.println(strategyDao.addStrategy("23", "cs", "cao",
			// "-2-31"
			// , "13-2-1", 12, 0, 0, "", "", ""));
			// System.out.println(strategyDao.getUserStrategys("123").size());
			// System.out.println(strategyDao.deleteStrategy("23", "cs",
			// "cao"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * test firstletterfill
		 */

		// try {
		// Document
		// document=Jsoup.connect("http://gupiao.baidu.com/stock/sh000300.html?from=aladingpc").get();
		// Elements elements=document.select("div[class=stock-bets]");
		// Elements elements1=elements.get(0).select("div[class=price s-down
		// ]");
		// Elements temp=elements.get(0).select("div[class=bets-col-9]");
		// Elements elements2=temp.get(0).select("dd");
		// System.out.println(elements2.text().split(" ")[6]);
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		/*
		 * test benchRecentDataPO
		 */
		// BenchRecordService b=new BenchRecord();
		// try {
		// benchCurrentDataPO po=b.getLastestRecord("sh000001");
		// System.out.println(po.getNow());
		// System.out.println(po.getRise_fall_price());
		// System.out.println(po.getRise_fall_percent());
		// System.out.println(po.getHigh());
		// System.out.println(po.getLow());
		// System.out.println(po.getOpen());
		// System.out.println(po.getYesterday_close());
		// System.out.println(po.getPrice());
		// System.out.println(po.getVolume());
		// System.out.println(po.getRise_company());
		// System.out.println(po.getFall_company());
		// System.out.println(po.getCompany());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		/*
		 * test getIndustry
		 */
		// IndustryData industryData=new IndustryData();
		// try {
		// ArrayList<industryPO> industryPO=industryData.getIndustry("电器设备");
		// System.out.println(industryPO.get(12).getStockI());
		// System.out.println(industryPO.get(12).getCurrent_price());
		// System.out.println(industryPO.get(12).getRise_fall_price());
		// System.out.println(industryPO.get(12).getRise_fall_percent());
		// System.out.println(industryPO.get(12).getYesterday_close());
		// System.out.println(industryPO.get(12).getOpen());
		// System.out.println(industryPO.get(12).getHigh());
		// System.out.println(industryPO.get(12).getLow());
		// System.out.println(industryPO.get(12).getInflows());
		// System.out.println(industryPO.get(12).getVolume());
		// System.out.println(industryPO.get(12).getPrice());
		// System.out.println(industryPO.get(12).getTurnover());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		/*
		 * test industrydata
		 */
		// DBconnection dBconnection=new DBconnection();
		IndustryData industryData = new IndustryData();
		// IndustriesDaoProxyService industriesDaoProxyService=new
		// IndustriesDaoProxy();
		// try {
		// IndustriesId id=new IndustriesId("完世界", "2015-22-11");
		// Industries industries=new Industries(id, 0, 0, 0, 0, 0, 0, "", 0, 0);
		// industriesDaoProxyService.insert(industries);
		// System.out.println(industriesDaoProxyService.getIndustryRecord("完美世界","2012-22-12","2015-22-11"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		/*
		 * test stockdataservice
		 */
		// try {
		// StockData stockData=new StockData();
		// ArrayList<RiseStockPO> stockPOs=stockData.getRiseStock();
		// int i=1;
		// for (RiseStockPO riseStockPO : stockPOs) {
		// System.out.print(i+" ");
		// System.out.print(riseStockPO.getStockId());
		// System.out.print(riseStockPO.getStockName());
		// System.out.print(riseStockPO.getNow());
		// System.out.print(riseStockPO.getHigh());
		// System.out.print(riseStockPO.getLow());
		// System.out.print(riseStockPO.getRise_days());
		// System.out.print(riseStockPO.getRise_fall());
		// System.out.print(riseStockPO.getTotal_turnover());
		// System.out.println(riseStockPO.getIndustry());
		// i++;
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Document result;
		// try {
		// result = Jsoup.connect("http://www.shdjt.com/sh.htm").get();
		// Elements elements=result.select("tr[height=25]");
		// for (Element element : elements) {
		// System.out.println(element.text());
		// }
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		 StockData stockData=new StockData();
		 try {
		 ArrayList<UpToDateStockPO>
		 arrayList=stockData.getToDateStockPOs("sh");
		 for (UpToDateStockPO upToDateStockPO : arrayList) {
		 System.out.print(upToDateStockPO.getStockId()+" ");
		 System.out.print(upToDateStockPO.getStockName()+" ");
		 System.out.print(upToDateStockPO.getIndustry()+" ");
		 System.out.print(upToDateStockPO.getNow()+" ");
		 System.out.print(upToDateStockPO.getRise_fall()+" ");
		 System.out.print(upToDateStockPO.getDdx()+" ");
		 System.out.print(upToDateStockPO.getDdy()+" ");
		 System.out.print(upToDateStockPO.getDdz()+" ");
		 System.out.print(upToDateStockPO.getPositive()+" ");
		 System.out.print(upToDateStockPO.getTongchilv()+" ");
		 System.out.print(upToDateStockPO.getExtraLargePurchase()+" ");
		 System.out.print(upToDateStockPO.getExtraLargeSell()+" ");
		 System.out.print(upToDateStockPO.getLargePurchase()+" ");
		 System.out.print(upToDateStockPO.getLargeSell()+" ");
		 System.out.print(upToDateStockPO.getMediumPurchase()+" ");
		 System.out.print(upToDateStockPO.getMediumSell()+" ");
		 System.out.print(upToDateStockPO.getSmallPurchase()+" ");
		 System.out.print(upToDateStockPO.getSmallSell()+" ");
		 System.out.print(upToDateStockPO.getTurnover()+" ");
		 System.out.println(upToDateStockPO.getQuantity_relative_ratio());
		 }
		 } catch (Exception e) {
		 e.printStackTrace();
		 }

		// DBconnection dBconnection=new DBconnection();
		// StockData stockData=new StockData();
		// try {
		// List list=stockData.getStockRecord("sh200302", "2011-07-01",
		// "2014-11-31");
		// System.out.println(list.size());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// DBconnection dBconnection=new DBconnection();
		// StockDaoProxyService service=DaoFactory.getStockDaoProxy();
		// try {
		// List list=service.getIndustries();
		// for (Object object : list) {
		// System.out.println((String)object);
		// }
		// System.out.println(list.size());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		/*
		 * assistant's api
		 */
		// String
		// result=HttpRequest.sendGet("http://121.41.106.89:8010/api/stock/" +
		// "sh600000", "start=" + "2014-01-01"
		// + "&end=" + "2015-05-28" +
		// "&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb");
		// JSONObject jsonObject=new JSONObject(result);
		// JSONObject jsonObject2=jsonObject.getJSONObject("data");
		// JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
		// for (Object object : jsonArray) {
		// JSONObject jsonObject3=(JSONObject) object;
		// System.out.print(jsonObject3.getString("date")+" ");
		// System.out.print(jsonObject3.getDouble("open")+" ");
		// System.out.print(jsonObject3.getDouble("close")+" ");
		// System.out.print(jsonObject3.getDouble("high")+" ");
		// System.out.print(jsonObject3.getDouble("low")+" ");
		// System.out.print(jsonObject3.getDouble("adj_price")+" ");
		// System.out.print(jsonObject3.getDouble("volume")+" ");
		// System.out.print(jsonObject3.getDouble("turnover")+" ");
		// System.out.print(jsonObject3.getDouble("pe_ttm")+" ");
		// System.out.println(jsonObject3.getDouble("pb"));
		// }
		// System.out.println(result);

		/*
		 * 
		 */
		// String url="jdbc:mysql://115.159.122.196:3306/superquant";
		// String userName="root";
		// String password="141250089";
		//
		// IndustryDataService industryDataService = new IndustryData();
		// long a=System.currentTimeMillis();
		// try {
		// ArrayList<industriesPO> arrayList =
		// industryDataService.getIndustryData();
		// System.out.println("获取所有行业阶段end");
		// int idcount=0;
		// for (industriesPO industriesPO : arrayList) {
		// System.out.println("开启事务阶段end");
		//
		// ArrayList<industryPO> arrayList2 =
		// industryData.getIndustry(industriesPO.getIndustry());
		// System.out.println("获取具体行业所有公司阶段end");
		// for (industryPO industryPO : arrayList2) {
		// idcount++;
		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// Connection conn =
		// DriverManager.getConnection(url+"?useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true",
		// userName, password);
		// conn.setAutoCommit(false);
		// String sql = "insert into trade_record"
		// + "(stockID,date,open,close,high,low,adj_price,volume,turnover,pe,pb)
		// "
		// + "values(?,?,?,?,?,?,?,?,?,?,?)";
		// PreparedStatement prest = conn.prepareStatement(sql);
		//
		// String id=industryPO.getStockI();
		//
		// try {
		// String exchange="";
		// if (industryPO.getStockI().charAt(0)=='6') {
		// exchange="sh";
		// id="sh"+id;
		// }else {
		// exchange="sz";
		// id="sz"+id;
		// }
		//
		// String result = HttpRequest.sendGet(
		// "http://121.41.106.89:8010/api/stock/" +
		// exchange+industryPO.getStockI(),
		// "start=" + "1990-01-01" + "&end=" + "2016-05-28"
		// + "&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb");
		// System.out.println("获取公司历史数据阶段end");
		// JSONObject jsonObject = new JSONObject(result);
		// JSONObject jsonObject2 = jsonObject.getJSONObject("data");
		// JSONArray jsonArray = jsonObject2.getJSONArray("trading_info");
		// System.out.println(jsonArray.length()+ " "+ idcount+ "
		// "+industryPO.getStockI());
		// for (Object object2 : jsonArray) {
		//
		// try {
		// JSONObject jsonObject3=(JSONObject) object2;
		// prest.setString(1, id);
		// prest.setString(2, jsonObject3.getString("date"));
		// prest.setDouble(3, jsonObject3.getDouble("open"));
		// prest.setDouble(4, jsonObject3.getDouble("close"));
		// prest.setDouble(5, jsonObject3.getDouble("high"));
		// prest.setDouble(6, jsonObject3.getDouble("low"));
		// prest.setDouble(7, jsonObject3.getDouble("adj_price"));
		// prest.setLong(8, jsonObject3.getLong("volume"));
		// prest.setDouble(9, jsonObject3.getDouble("turnover"));
		// prest.setDouble(10, jsonObject3.getDouble("pb"));
		// prest.setDouble(11, jsonObject3.getDouble("pb"));
		//
		// prest.addBatch();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// prest.executeBatch();
		// prest.clearBatch();
		// conn.commit();
		// System.out.println("储存公司历史数据阶段end");
		// } catch (Exception e) {
		// System.out.println("已存储");
		// }
		//
		// }
		//
		// System.out.println("第5阶段end");
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// long b=System.currentTimeMillis();
		// System.out.println("MySql非批量插入10万条记录用时"+ (b-a)+" ms");

		/*
		 * test the head of the stock 0/3/6
		 */
		// StockData stockData=new StockData();
		// codeNamePO codeNamePO=stockData.getCodeName(2015, "sz");
		// ArrayList<String> arrayList=codeNamePO.getResult();
		// for (String string : arrayList) {
		// System.out.println(string);
		// }

		/*
		 * test getUpToDateStockPO()
		 */
		// StockData stockData=new StockData();
		// try {
		// ArrayList<RiseStockPO > arrayList=stockData.getRiseStock();
		// for (RiseStockPO riseStockPO : arrayList) {
		// System.out.println(riseStockPO.getStockId()+"
		// "+riseStockPO.getStockName());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		/*
		 * test UserStrategyData
		 */
		// DBconnection dBconnection=new DBconnection();
		// UserStrategyDaoProxyService service=new UserStrategyDaoProxy();
		// try {
		// service.deleteStrategy("fatchao", "bianshou");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// BenchRecord benchRecord=new BenchRecord();
		// try {
		// benchCurrentDataPO po=benchRecord.getLastestRecord("sz399001");
		// System.out.println(po.getStatus());
		// System.out.println(po.getTime());
		// System.out.println(po.getNow());
		// System.out.println(po.getRise_fall_price());
		// System.out.println(po.getRise_fall_percent());
		// System.out.println(po.getHigh());
		// System.out.println(po.getLow());
		// System.out.println(po.getOpen());
		// System.out.println(po.getYesterday_close());
		// System.out.println(po.getPrice());
		// System.out.println(po.getVolume());
		// System.out.println(po.getRise_company());
		// System.out.println(po.getFall_company());
		// System.out.println(po.getCompany());
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// data.stockcheckdata.StockData stockData=new
		// data.stockcheckdata.StockData();
		//
		// ArrayList<stockStatisticPO>
		// arrayList=stockData.getStatisitcOfStock("sh600000","2015-01-01","2015-02-01");
		// for (stockStatisticPO stockStatisticPO : arrayList) {
		// System.out.println(stockStatisticPO.getName());
		// }
		// String string=new
		// String(data.stockcheckdata.StockData.sendGet("http://hq.sinajs.cn/list="+"sh600000"+",",
		// ""));
		// System.out.println(string.split("\"")[1].split(",")[0]);

		/*
		 * insert the stocks in the minority
		 */
//		String url = "jdbc:mysql://115.159.122.196:3306/superquant";
//		String userName = "root";
//		String password = "141250089";
//		DBconnection dBconnection = new DBconnection();
//		TradeRecordDaoImpl tradeRecordDao = new TradeRecordDaoImpl();
//		StockDaoImpl stockDaoImpl = new StockDaoImpl();
//		String hql = "select s.stockId from Stock s where s.stockId not in "
//				+ "(select distinct t.id.stockId from TradeRecord t)";
//		try {
//			List list = stockDaoImpl.getStockData(hql);
//			System.out.println("已经获取所有未存股票");
//			int count=0;
//			for (Object object : list) {
//				count++;
//				try {
//					Class.forName("com.mysql.jdbc.Driver");
//					Connection conn = DriverManager.getConnection(
//							url + "?useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true", userName,
//							password);
//					conn.setAutoCommit(false);
//					String sql = "insert into trade_record"
//							+ "(stockID,date,open,close,high,low,adj_price,volume,turnover,pe,pb) "
//							+ "values(?,?,?,?,?,?,?,?,?,?,?)";
//					PreparedStatement prest = conn.prepareStatement(sql);
//					String stockId = (String) object;
//					try {
//						Document document = Jsoup
//								.connect("http://www.aigaogao.com/tools/history.html?s=" + stockId.substring(2)).get();
//						Elements elements = document.select("table[class=data]").get(0).select("tr");
//						for (int i = 1; i < elements.size() - 1; i++) {
//							Element element = elements.get(i);
//							String[] temp = element.text().split(" ");
//
//							
//							//转换日期格式
//							String d = "";
//							SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
//							Date date = format1.parse(temp[0]);
//							SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//							d = format2.format(date);
//							//转换成交量格式
//							String volume=temp[5].replace(",", "")+"00";
//							
//							prest.setString(1, stockId);
//							prest.setString(2, d);
//							prest.setDouble(3, Double.parseDouble(temp[1]));
//							prest.setDouble(4, Double.parseDouble(temp[4]));
//							prest.setDouble(5, Double.parseDouble(temp[2]));
//							prest.setDouble(6, Double.parseDouble(temp[3]));
//							prest.setDouble(7, 0);
//							prest.setLong(8, Long.parseLong(volume));
//							prest.setDouble(9, 0);
//							prest.setDouble(10, 0);
//							prest.setDouble(11, 0);
//							
//							prest.addBatch();
////							System.out.print(stockId+" ");
////							System.out.print(d+" ");
////							System.out.print(Double.parseDouble(temp[1])+" ");
////							System.out.print(Double.parseDouble(temp[4])+" ");
////							System.out.print(Double.parseDouble(temp[2])+" ");
////							System.out.print(Double.parseDouble(temp[3])+" ");
////							System.out.print(0+" ");
////							System.out.print(Long.parseLong(volume)+" ");
////							System.out.print(0+" ");
////							System.out.print(0+" ");
////							System.out.println(0+" ");
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//						System.out.println("无此股票数据");
//					}
//
//					prest.executeBatch();
//					prest.clearBatch();
//					conn.commit();
//					System.out.println(stockId + "存储成功 "+count);
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println((String) object + "存储失败");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		

	}
}
