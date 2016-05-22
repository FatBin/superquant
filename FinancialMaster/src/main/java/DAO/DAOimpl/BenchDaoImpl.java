package DAO.DAOimpl;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.BenchDao;
import DAO.pojo.Bench;
import antlr.collections.List;


public class BenchDaoImpl implements BenchDao{
	
	@Override
	public boolean insert(Bench bench) throws Exception {
		Session session=DBconnection.getSession();
		if(findByID(bench.getBenchId())==null){
			session.save(bench);
			Transaction tx=session.beginTransaction();
			tx.commit();
			return true;
		}else{
			return false;
		}

	}

	@Override
	public Bench findByID(String benchID) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Bench.class);
		criteria.add(Restrictions.eq("benchId", benchID));
		ArrayList benchList=(ArrayList) criteria.list();
		if(benchList.size()==0){
			return null;
		}else{
			return (Bench)benchList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
