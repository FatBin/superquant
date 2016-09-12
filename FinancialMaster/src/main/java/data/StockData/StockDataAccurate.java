package data.StockData;

import java.util.ArrayList;

import DAO.DAOimpl.TradeRecordAccurateDaoImpl;
import DAO.dao.TradeRecordAccurateDao;
import DAO.pojo.TradeRecordAccurate;
import dataservice.StockDataService.StockDataAccurateService;

public class StockDataAccurate implements StockDataAccurateService{
	TradeRecordAccurateDao TradeRecordAccurateDao;
	@Override
	public ArrayList<TradeRecordAccurate> geTradeRecordAccurates(String stockId) {
		TradeRecordAccurateDao=new TradeRecordAccurateDaoImpl();
		ArrayList<TradeRecordAccurate> arrayList=new ArrayList<>();
		if (TradeRecordAccurateDao.getTradeRecordAccurate(stockId)!=null) {
			arrayList=(ArrayList<TradeRecordAccurate>) TradeRecordAccurateDao.getTradeRecordAccurate(stockId);
		}
		return arrayList;
	}

	@Override
	public boolean clean() {
		TradeRecordAccurateDao=new TradeRecordAccurateDaoImpl();
		TradeRecordAccurateDao.clean();
		return true;
	}

	@Override
	public void persist(ArrayList<TradeRecordAccurate> arrayList) {
		TradeRecordAccurateDao=new TradeRecordAccurateDaoImpl();
		TradeRecordAccurateDao.persist(arrayList);
	}

}
