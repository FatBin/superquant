package DAO.DAOimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.BenchDao;
import DAO.pojo.Bench;


public class BenchDaoImpl implements BenchDao{
	/*
	 * annotation refers to the BenchDao
	 */
	@Override
	public boolean insert(Bench bench) throws Exception {
		Session session=DBconnection.getSession();
			session.save(bench);
			Transaction tx=session.beginTransaction();
			tx.commit();
			session.close();
			return true;
	}

	@Override
	public Bench findByID(String benchID) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Bench.class);
		criteria.add(Restrictions.eq("benchId", benchID));
		List benchList=criteria.list();
		session.close();
		if(benchList.size()==0){
			return null;
		}else{
			return (Bench)benchList.get(0);
		}

	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Bench.class);
		List benchList=criteria.list();
		session.close();
		return benchList;
	}

}
