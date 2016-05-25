package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.BenchDaoImpl;
import DAO.DAOimpl.TradeRecordDaoImpl;
import DAO.dao.BenchDao;
import DAO.dao.TradeRecordDao;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;

public class TradeRecordDaoProxy implements TradeRecordDao{

	@Override
	public boolean insert(TradeRecord tradeRecord) throws Exception {
		TradeRecordDao tradeRecordDaoImpl=new TradeRecordDaoImpl();
		try {
			if(tradeRecordDaoImpl.findByID(tradeRecord.getId())==null){
				tradeRecordDaoImpl.insert(tradeRecord);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		} 
	}

	@Override
	public TradeRecord findByID(TradeRecordId tradeRecordId) throws Exception {
		TradeRecordDao tradeRecordDaoImpl=new TradeRecordDaoImpl();
		try {
			return tradeRecordDaoImpl.findByID(tradeRecordId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		TradeRecordDao tradeRecordDaoImpl=new TradeRecordDaoImpl();
		try {
			return tradeRecordDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
