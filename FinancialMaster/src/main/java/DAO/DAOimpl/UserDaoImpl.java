package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.UserDao;
import DAO.pojo.Bench;
import DAO.pojo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean insert(User user) throws Exception {
		Session session=DBconnection.getSession();
		session.save(user);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public User findByID(String userId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", userId));
		List UserList=criteria.list();
		session.close();
		if(UserList.size()==0){
			return null;
		}else{
			return (User)UserList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(User.class);
		List UserList=criteria.list();
		session.close();
		return UserList;
		
	}
}
