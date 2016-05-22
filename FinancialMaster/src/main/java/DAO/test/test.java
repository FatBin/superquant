package DAO.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DAO.DAOimpl.BenchDaoImpl;
import DAO.connection.DBconnection;
import DAO.pojo.Bench;
import DAO.pojo.User;

public class test {
	public static void main(String[] args) {
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
		DBconnection dBconnection=new DBconnection();
		BenchDaoImpl benchDaoImpl=new BenchDaoImpl();
		try {
			Bench bench=benchDaoImpl.findByID(1+"");
			System.out.println(bench.getBenchName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
