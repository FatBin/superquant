package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.TradeRecordDaoImpl;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.dao.TradeRecordDao;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;

public class TradeRecordDaoProxy implements TradeRecordDaoProxyService{

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

	@Override
	public List getTradeRecord(String stockId,String starttime,String endtime) throws Exception {
		TradeRecordDao tradeRecordDaoImpl=new TradeRecordDaoImpl();
		try {
			String hql="from TradeRecord t where "
					+ "t.id.stockId='"+stockId+"' and "
					+ "t.id.date>='"+starttime+"' and "
					+ "t.id.date<='"+endtime+"' order by t.id.date desc";
			return tradeRecordDaoImpl.getTradeRecord(hql);
		} catch (Exception e) {
			throw e;
		}
	}

}
