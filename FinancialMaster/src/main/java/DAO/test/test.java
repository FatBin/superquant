package DAO.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import DAO.DAOimpl.UserStrategyDaoImpl;
import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.DaoProxyService.IndustriesDaoProxyService;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.DaoProxyService.UserStrategyDaoProxyService;
import DAO.connection.DBconnection;
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
import PO.industriesPO;
import PO.industryPO;
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

		// StockData stockData=new StockData();
		// try {
		// ArrayList<UpToDateStockPO>
		// arrayList=stockData.geToDateStockPOs("sh");
		// for (UpToDateStockPO upToDateStockPO : arrayList) {
		// System.out.print(upToDateStockPO.getStockId()+" ");
		// System.out.print(upToDateStockPO.getStockName()+" ");
		// System.out.print(upToDateStockPO.getIndustry()+" ");
		// System.out.print(upToDateStockPO.getNow()+" ");
		// System.out.print(upToDateStockPO.getRise_fall()+" ");
		// System.out.print(upToDateStockPO.getDdx()+" ");
		// System.out.print(upToDateStockPO.getDdy()+" ");
		// System.out.print(upToDateStockPO.getDdz()+" ");
		// System.out.print(upToDateStockPO.getPositive()+" ");
		// System.out.print(upToDateStockPO.getTongchilv()+" ");
		// System.out.print(upToDateStockPO.getExtraLargePurchase()+" ");
		// System.out.print(upToDateStockPO.getExtraLargeSell()+" ");
		// System.out.print(upToDateStockPO.getLargePurchase()+" ");
		// System.out.print(upToDateStockPO.getLargeSell()+" ");
		// System.out.print(upToDateStockPO.getMediumPurchase()+" ");
		// System.out.print(upToDateStockPO.getMediumSell()+" ");
		// System.out.print(upToDateStockPO.getSmallPurchase()+" ");
		// System.out.print(upToDateStockPO.getSmallSell()+" ");
		// System.out.print(upToDateStockPO.getTurnover()+" ");
		// System.out.println(upToDateStockPO.getQuantity_relative_ratio());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

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
		DBconnection dBconnection=new DBconnection();
		IndustryDataService industryDataService = new IndustryData();
		TradeRecordDaoProxyService tradeRecordDaoProxyService = DaoFactory.getTradeRecordDaoProxy();
		try {
			ArrayList<industriesPO> arrayList = industryDataService.getIndustryData();
			System.out.println("第1阶段end");
			int idcount=0;
			for (industriesPO industriesPO : arrayList) {
				ArrayList<industryPO> arrayList2 = industryData.getIndustry(industriesPO.getIndustry());
				System.out.println("第2阶段end");
				for (industryPO industryPO : arrayList2) {
					Session session=dBconnection.getSession();
					idcount++;
					try {
						String exchange="";
						if (industryPO.getStockI().charAt(0)=='6') {
							exchange="sh";
						}else {
							exchange="sz";
						}
						
						String result = HttpRequest.sendGet(
								"http://121.41.106.89:8010/api/stock/" + exchange+industryPO.getStockI(),
								"start=" + "1990-01-01" + "&end=" + "2016-05-28"
										+ "&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb");
						System.out.println("第3阶段end");
						JSONObject jsonObject = new JSONObject(result);
						JSONObject jsonObject2 = jsonObject.getJSONObject("data");
						JSONArray jsonArray = jsonObject2.getJSONArray("trading_info");
						System.out.println(jsonArray.length()+ " "+ idcount+ " "+industryPO.getStockI());
						for (Object object2 : jsonArray) {
							try {
								JSONObject jsonObject3=(JSONObject) object2;
								TradeRecordId id=new TradeRecordId(industryPO.getStockI(), jsonObject3.getString("date"));
								TradeRecord tradeRecord=new TradeRecord(
										id, 
										jsonObject3.getDouble("open"), 
										jsonObject3.getDouble("close"), 
										jsonObject3.getDouble("high"), 
										jsonObject3.getDouble("low"), 
										jsonObject3.getDouble("adj_price"), 
										jsonObject3.getLong("volume"), 
										jsonObject3.getDouble("turnover"), 
										jsonObject3.getDouble("pe_ttm"), 
										jsonObject3.getDouble("pb"));
										session.save(tradeRecord);
//								tradeRecordDaoProxyService.insert(tradeRecord);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						System.out.println("第4阶段end");
					} catch (Exception e) {
						e.printStackTrace();
					}
					Transaction tx=session.beginTransaction();
					tx.commit();
					session.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			UserStockId userStockId=new UserStockId("vax","fas");
//			UserStock userStock=new UserStock(userStockId);
//			String hql="insert into UserStock(id.userName,id.stockId) values "
//					+ "("+userStock.getId().getUserName()+","+userStock.getId().getStockId()+")";
//			Session session=dBconnection.getSession();
//			session.createCriteria("insert into UserStock(id)");
//			dBconnection.getSession().createSQLQuery(hql).executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
