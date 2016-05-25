package DAO.DAOimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.BenchdataDao;
import DAO.pojo.Bench;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class BenchdataDaoImpl implements BenchdataDao{

	@Override
	public boolean insert(Benchdata benchdata) throws Exception {
		Session session=DBconnection.getSession();
		session.save(benchdata);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public Benchdata findByID(BenchdataId benchdataId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Benchdata.class);
		criteria.add(Restrictions.eq("id.benchId", benchdataId.getBenchId()));
		criteria.add(Restrictions.eq("id.date", benchdataId.getDate()));
		List benchdataList=criteria.list();
		session.close();
		if(benchdataList.size()==0){
			return null;
		}else{
			return (Benchdata)benchdataList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Benchdata.class);
		List benchdataList=criteria.list();
		session.close();
		return benchdataList;
	}

}
