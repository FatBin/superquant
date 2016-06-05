package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.StockDao;
import DAO.pojo.Stock;


public class StockDaoImpl implements StockDao{

	@Override
	public boolean insert(Stock stock) throws Exception {
		Session session=DBconnection.getSession();
		session.save(stock);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public Stock findByID(String stockId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Stock.class);
		criteria.add(Restrictions.eq("stockId", stockId));
		List stockList=criteria.list();
		session.close();
		if(stockList.size()==0){
			return null;
		}else{
			return (Stock)stockList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(Stock.class);
		List StockList=criteria.list();
		session.close();
		return StockList;
	}

	@Override
	public List getStockData(String hql) throws Exception {
		Session session=DBconnection.getSession();
		Query query=session.createQuery(hql);
		List list=query.list();
		session.close();
		return list;
	}
}
