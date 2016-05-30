package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.UserStrategyDao;
import DAO.pojo.Bench;
import DAO.pojo.UserStock;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;

public class UserStrategyDaoImpl implements UserStrategyDao {

	@Override
	public boolean insert(UserStrategy userStrategy) throws Exception {
		Session session = DBconnection.getSession();
		session.save(userStrategy);
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public UserStrategy findByID(UserStrategyId userStrategyId) throws Exception {
		Session session = DBconnection.getSession();
		Criteria criteria = session.createCriteria(UserStrategy.class);
		criteria.add(Restrictions.eq("id.userId", userStrategyId.getUserId()));
		criteria.add(Restrictions.eq("id.stockId", userStrategyId.getStockId()));
		criteria.add(Restrictions.eq("id.strategyName", userStrategyId.getStrategyName()));
		List UserStrategyList = criteria.list();
		session.close();
		if (UserStrategyList.size() == 0) {
			return null;
		} else {
			return (UserStrategy) UserStrategyList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session = DBconnection.getSession();
		Criteria criteria = session.createCriteria(UserStrategy.class);
		List UserStrategyList = criteria.list();
		session.close();
		return UserStrategyList;
	}

	@Override
	public boolean delete(UserStrategyId userStrategyId) throws Exception {
		Session session = DBconnection.getSession();
		UserStrategy userStrategy = this.findByID(userStrategyId);
		Transaction tx = session.beginTransaction();
		session.delete(userStrategy);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List getData(String hql) throws Exception{
		Session session=DBconnection.getSession();
		Query query=session.createQuery(hql);
		return query.list();
	}
	
	

}
