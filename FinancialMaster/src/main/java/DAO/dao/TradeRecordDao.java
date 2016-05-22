package DAO.dao;

import DAO.pojo.Bench;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import java.util.List;

public interface TradeRecordDao {
	//insert a PO
	public boolean insert(TradeRecord tradeRecord)throws Exception;
	
	//search a TradeRecord with its id,in this case,it is tradeRecordId
	public TradeRecord findByID(TradeRecordId tradeRecordId)throws Exception;
	
	//get all TradeRecord data
	public List findAll()throws Exception;
}
