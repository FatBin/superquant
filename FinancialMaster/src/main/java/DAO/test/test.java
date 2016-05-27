package DAO.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;

import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.DaoProxyService.IndustriesDaoProxyService;
import DAO.connection.DBconnection;
import DAO.pojo.Industries;
import DAO.pojo.IndustriesId;
import PO.benchCurrentDataPO;
import PO.industryPO;
import data.BenchData.BenchRecord;
import data.IO.HttpRequest;
import data.IndustryData.IndustryData;
import dataservice.BenchDataService.BenchRecordService;

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

//		HttpRequest httpRequest = new HttpRequest();
//		 String resultString =
//		 httpRequest.sendGet("http://quotes.money.163.com/trade/lsjysj_zhishu_399001.html",
//		 "");
//		 System.out.println(resultString);
//		Document result;
//		try {
//			result = Jsoup.connect("http://q.10jqka.com.cn/interface/stock/detail/zdf/desc/1/1/hxzp").get();
//			System.out.println(result);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

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
		
//		try {
//			Document document=Jsoup.connect("http://gupiao.baidu.com/stock/sh000300.html?from=aladingpc").get();
//			Elements elements=document.select("div[class=stock-bets]");
//			Elements elements1=elements.get(0).select("div[class=price s-down ]");
//			Elements temp=elements.get(0).select("div[class=bets-col-9]");
//			Elements elements2=temp.get(0).select("dd");
//			System.out.println(elements2.text().split(" ")[6]);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*
		 * test benchRecentDataPO
		 */
//		BenchRecordService b=new BenchRecord();
//		try {
//			benchCurrentDataPO po=b.getLastestRecord("sh000001");
//			System.out.println(po.getNow());
//			System.out.println(po.getRise_fall_price());
//			System.out.println(po.getRise_fall_percent());
//			System.out.println(po.getHigh());
//			System.out.println(po.getLow());
//			System.out.println(po.getOpen());
//			System.out.println(po.getYesterday_close());
//			System.out.println(po.getPrice());
//			System.out.println(po.getVolume());
//			System.out.println(po.getRise_company());
//			System.out.println(po.getFall_company());
//			System.out.println(po.getCompany());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/*
		 * test getIndustry
		 */
//		IndustryData industryData=new IndustryData();
//		try {
//			ArrayList<industryPO> industryPO=industryData.getIndustry("电器设备");
//			System.out.println(industryPO.get(12).getStockI());
//			System.out.println(industryPO.get(12).getCurrent_price());
//			System.out.println(industryPO.get(12).getRise_fall_price());
//			System.out.println(industryPO.get(12).getRise_fall_percent());
//			System.out.println(industryPO.get(12).getYesterday_close());
//			System.out.println(industryPO.get(12).getOpen());
//			System.out.println(industryPO.get(12).getHigh());
//			System.out.println(industryPO.get(12).getLow());
//			System.out.println(industryPO.get(12).getInflows());
//			System.out.println(industryPO.get(12).getVolume());
//			System.out.println(industryPO.get(12).getPrice());
//			System.out.println(industryPO.get(12).getTurnover());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		DBconnection dBconnection=new DBconnection();
//		IndustryData industryData=new IndustryData();
//		IndustriesDaoProxyService industriesDaoProxyService=new IndustriesDaoProxy();
//		try {
//			IndustriesId id=new IndustriesId("完世界", "2015-22-11");
//			Industries industries=new Industries(id, 0, 0, 0, 0, 0, 0, "", 0, 0);
//			industriesDaoProxyService.insert(industries);
//			System.out.println(industriesDaoProxyService.getIndustryRecord("完美世界","2012-22-12","2015-22-11"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
