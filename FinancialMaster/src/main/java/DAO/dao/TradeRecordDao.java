package DAO.dao;

import java.util.List;

import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;

public interface TradeRecordDao {
	//insert a PO
	public boolean insert(TradeRecord tradeRecord)throws Exception;
	
	//insert a large number of PO
//	public void largeNumInsert();
	
	//search a TradeRecord with its id,in this case,it is tradeRecordId
	public TradeRecord findByID(TradeRecordId tradeRecordId)throws Exception;
	
	//get all TradeRecord data
	public List findAll()throws Exception;
	
	//get the stock date according to hql
	public List getTradeRecord(String hql) throws Exception;
}
