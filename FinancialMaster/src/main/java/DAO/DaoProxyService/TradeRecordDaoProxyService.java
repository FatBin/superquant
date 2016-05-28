package DAO.DaoProxyService;

import java.util.List;

import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;

public interface TradeRecordDaoProxyService {
	//insert a PO
	public boolean insert(TradeRecord tradeRecord)throws Exception;
	
	//search a TradeRecord with its id,in this case,it is tradeRecordId
	public TradeRecord findByID(TradeRecordId tradeRecordId)throws Exception;
	
	//get all TradeRecord data
	public List findAll()throws Exception;
	
	//get the TradeRecord from starttime to endtime
	public List getTradeRecord(String stockId,String starttime,String endtime) throws Exception;
}
