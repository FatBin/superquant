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
import DAO.dao.StockDao;
import DAO.pojo.Bench;
import DAO.pojo.Stock;


public class StockImpl implements StockDao{

	@Override
	public boolean insert(Stock stock) throws Exception {
		Session session=DBconnection.getSession();
		if(findByID(stock.getStockId())==null){
			session.save(stock);
			Transaction tx=session.beginTransaction();
			tx.commit();
			session.close();
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Stock findByID(String stockId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Stock.class);
		criteria.add(Restrictions.eq("benchId", benchID));
		List benchList=criteria.list();
		session.close();
		if(benchList.size()==0){
			return null;
		}else{
			return (Bench)benchList.get(0);
		}
		return null;
	}

	@Override
	public List findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
