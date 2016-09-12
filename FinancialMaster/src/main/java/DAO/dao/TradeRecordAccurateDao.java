package DAO.dao;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.TradeRecordAccurate;

public interface TradeRecordAccurateDao {
	public List getTradeRecordAccurate(String stockId);
	
	public void clean();
	
	public boolean persist(ArrayList<TradeRecordAccurate> arrayList);
}
