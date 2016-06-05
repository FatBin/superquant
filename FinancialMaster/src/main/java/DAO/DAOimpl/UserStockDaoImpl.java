package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.UserStockDao;
import DAO.pojo.UserStock;
import DAO.pojo.UserStockId;

public class UserStockDaoImpl implements UserStockDao{

	@Override
	public boolean insert(UserStock userStock) throws Exception {
		Session session=DBconnection.getSession();
		session.save(userStock);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public UserStock findByID(UserStockId userStockId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(UserStock.class);
		criteria.add(Restrictions.eq("id.userName", userStockId.getUserName()));
		criteria.add(Restrictions.eq("id.stockId", userStockId.getStockId()));
		List UserStockList=criteria.list();
		session.close();
		if(UserStockList.size()==0){
			return null;
		}else{
			return (UserStock)UserStockList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(UserStock.class);
		List UserStockList=criteria.list();
		session.close();
		return UserStockList;
	}

	@Override
	public boolean delete(UserStockId userStockId) throws Exception {
		Session session=DBconnection.getSession();
		Transaction tx=session.beginTransaction();
		UserStock userStock=new UserStock(userStockId);
		session.delete(userStock);
		tx.commit();
		session.close();
		return true;
	}

}
