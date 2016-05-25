package DAO.DAOimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import DAO.connection.DBconnection;
import DAO.dao.TradeRecordDao;
import DAO.pojo.Bench;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;

public class TradeRecordDaoImpl implements TradeRecordDao{

	@Override
	public boolean insert(TradeRecord tradeRecord) throws Exception {
		Session session=DBconnection.getSession();
		session.save(tradeRecord);
		Transaction tx=session.beginTransaction();
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public TradeRecord findByID(TradeRecordId tradeRecordId) throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(TradeRecord.class);
		criteria.add(Restrictions.eq("id.stockId", tradeRecordId.getStockId()));
		criteria.add(Restrictions.eq("id.date", tradeRecordId.getDate()));
		List TradeRecordList=criteria.list();
		session.close();
		if(TradeRecordList.size()==0){
			return null;
		}else{
			return (TradeRecord)TradeRecordList.get(0);
		}
	}

	@Override
	public List findAll() throws Exception {
		Session session=DBconnection.getSession();
		Criteria criteria=session.createCriteria(TradeRecord.class);
		List TradeRecordList=criteria.list();
		session.close();
		return TradeRecordList;
	}

}
