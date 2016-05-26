package DAO.test;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import DAO.DAOimpl.BenchDaoImpl;
import DAO.DAOimpl.UserStrategyDaoImpl;
import DAO.DaoProxy.BenchDaoProxy;
import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.DaoProxy.StockDaoProxy;
import DAO.DaoProxy.TradeRecordDaoProxy;
import DAO.DaoProxy.UserDaoProxy;
import DAO.DaoProxy.UserStockDaoProxy;
import DAO.DaoProxy.UserStrategyDaoProxy;
import DAO.DaoProxyService.BenchDataDaoService;
import DAO.connection.DBconnection;
import DAO.dao.BenchDao;
import DAO.dao.BenchdataDao;
import DAO.dao.StockDao;
import DAO.dao.TradeRecordDao;
import DAO.dao.UserDao;
import DAO.dao.UserStockDao;
import DAO.dao.UserStrategyDao;
import DAO.pojo.Bench;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;
import DAO.pojo.Benchrecord;
import DAO.pojo.Stock;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import DAO.pojo.User;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import data.BenchData.BenchData;
import data.UserData.UserStockData;
import data.UserData.UserStrategyData;
import dataservice.BenchDataService.BenchDataService;

public class test {
	public static void main(String[] args) {
		/*
		 * connection test
		 */
//		Bench bench=new Bench("how","what");
//		User user=new User("2", "2", "3");
//		BenchdataId benchdataId=new BenchdataId(bench.getBenchId(),bench.getBenchName());
//		Benchdata benchdata=new Benchdata(benchdataId,bench,2,1.0,1.0,1.0,1.0,1);
//		Configuration configuration=new Configuration().configure("DAO/pojo/hibernate.cfg.xml");
//		DBconnection dBconnection=new DBconnection();
//		SessionFactory sessionFactory=configuration.buildSessionFactory();
//		Session session;
//		try {
//			session = dBconnection.getSession();
//			session.save(user);
//			Transaction tx=session.beginTransaction();
//			tx.commit();
//			session.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		session.save(bench);
//		session.save(benchdata);
		
		
		/*
		 * exception test
		 */
		DBconnection dBconnection=new DBconnection();
//		BenchDao benchDao=new BenchDaoProxy();
//		BenchdataDao benchdataDao=new BenchdataDaoProxy();
		try {
//			Bench bench=benchDaoImpl.findByID(1+"");
//			System.out.println(bench.getBenchName());
//			Bench bench=new Bench("1","3");
//			benchDaoImpl.insert(bench);
			
//			benchDao.insert(bench);
		} catch (NullPointerException e) {
//			e.printStackTrace();
			System.out.println("yes");
		}catch (ConstraintViolationException e2) {
			System.out.println("no");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
//			UserStockDao strategyDao=new UserStockDaoProxy();
//			UserStockId userStrategyId=new UserStockId("asdf", "1");
//			UserStrategy usStrategy=new UserStrategy(userStrategyId);
//			System.out.println(strategyDao.insert(usStrategy));
//			System.out.println(strategyDao.findByID(userStrategyId));
//			System.out.println(strategyDao.findAll());
//			System.out.println(strategyDao.delete(userStrategyId));
//			dBconnection.sessionFactory.close();
		} catch (Exception e) {
			System.out.println("duanwang");
			e.printStackTrace();
		}
		
		
		/*
		 * test dataservice
		 */
//		try {
//			String userId="s";
//			String stockId="que";
//			UserStrategyData userStockData=new UserStrategyData();
//			System.out.println(userStockData.addStrategy(userId, stockId));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
		
		/*
		 * test hql
		 */
		try {
//			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
//			Date date=fmt.parse("2014-13-12");
			Session session=dBconnection.getSession();
//			Benchrecord benchrecord=new Benchrecord(date);
//			session.save(benchrecord);
//			Transaction transaction=session.beginTransaction();
//			transaction.commit();
//			String hql="select a.id.date from Benchdata a where Month(Date(a.id.date))=12 order by a.id.date desc";
//			Query query=session.createQuery(hql);
//			List list=query.list();
//			System.out.println(list.get(0).getClass());
//			session.close();
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * test benchdataservice
		 */
//		BenchDataService benchDataService=new BenchData();
//		try {
//			System.out.println(benchDataService.getBench());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		/*
		 * test BenchDataDaoService
		 */
//		try {
//			BenchDataDaoService benchDataDaoService=new BenchdataDaoProxy();
//			List list=benchDataDaoService.getBenchRecord("sh000300", "2014-11-24", "2015-01-02");
//			for(int i=0;i<list.size();i++){
//				Benchdata benchdata=(Benchdata) list.get(i);
//				System.out.println(benchdata.getId().getDate());
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
		Calendar calendar=Calendar.getInstance();
//		System.out.println(calendar.get());
	}

}
