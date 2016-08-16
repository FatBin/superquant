package DAO.connection;
/*
 * hello world
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBconnection {
	private static final String config="DAO/pojo/hibernate.cfg.xml";
	private Configuration configuration;
	public static SessionFactory sessionFactory;
	public DBconnection(){
		try {
			configuration=new Configuration().configure(config);
			sessionFactory=configuration.buildSessionFactory();
		} catch (Exception e) {
			sessionFactory=null;
			e.printStackTrace();
		}

	}
	
	public static Session getSession() throws Exception{
		Session session=sessionFactory.openSession();
		return session;
	}
	
}
