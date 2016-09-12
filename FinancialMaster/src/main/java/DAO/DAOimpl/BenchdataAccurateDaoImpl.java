package DAO.DAOimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.BenchdataAccurateDao;
import DAO.pojo.BenchdataAccurate;

public class BenchdataAccurateDaoImpl implements BenchdataAccurateDao{

	@Override
	public List getBenchdataAccurate(String benchId) {
		String hql="from BenchdataAccurate b where b.benchId=:benchId order by b.id.date asc";
		try {
			Session session=DBconnection.getSession();
			try {
				List list=session.createQuery(hql).setString("benchId", benchId).list();
				session.close();
				return list;
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void clean() {
		try {
			Session session=DBconnection.getSession();
			try {
			String hql="delete from BenchdataAccurate where 1=1";
			Transaction transaction=session.beginTransaction();
			session.createQuery(hql).executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean persist(BenchdataAccurate benchdataAccurate) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.save(benchdataAccurate);
				Transaction transaction=session.beginTransaction();
				transaction.commit();
				session.close();
				return true;
			} catch (Exception e) {
				session.close();
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
