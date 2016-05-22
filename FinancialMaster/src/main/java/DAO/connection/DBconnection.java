package DAO.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBconnection {
	private static final String config="DAO/pojo/hibernate.cfg.xml";
	private Configuration configuration;
	private static SessionFactory sessionFactory;
	public DBconnection(){
		try {
			configuration=new Configuration().configure(config);
			sessionFactory=configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Session getSession() throws Exception{
		Session session=sessionFactory.openSession();
		return session;
	}
	
}
