package DAO.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBconnection {
	private static final String config="DAO/pojo/hibernate.cfg.xml";
	private Configuration configuration;
	private SessionFactory sessionFactory;
	private Session session;
	public DBconnection(){
		try {
			configuration=new Configuration().configure(config);
			sessionFactory=configuration.buildSessionFactory();
			session=sessionFactory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Session getSession() throws Exception{
		return session;
	}
	
	public void closeSession() throws Exception{
		if(this.session!=null){
			session.close();
			sessionFactory.close();
		}
	}
}
