package testobject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class test {
	public static void main(String[] args) {
		Bench bench=new Bench("hello","world");
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.save(bench);
		tx.commit();
		session.close();
		sessionFactory.close();
		
	}
}
