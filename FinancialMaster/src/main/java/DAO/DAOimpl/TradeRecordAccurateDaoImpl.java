package DAO.DAOimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.connection.DBconnection;
import DAO.dao.TradeRecordAccurateDao;
import DAO.pojo.TradeRecordAccurate;

public class TradeRecordAccurateDaoImpl implements TradeRecordAccurateDao{

	@Override
	public List getTradeRecordAccurate() {
		String hql="from TradeRecordAccurate t order by t.id.date asc";
		try {
			Session session=DBconnection.getSession();
			try {
				List list=session.createQuery(hql).list();
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
			String hql="delete from TradeRecordAccurate where 1=1";
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
	public boolean persist(TradeRecordAccurate tradeRecordAccurate) {
		try {
			Session session=DBconnection.getSession();
			try {
				session.save(tradeRecordAccurate);
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
