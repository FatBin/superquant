package DAO.DaoProxy;

import java.util.List;

import DAO.DAOimpl.StockDaoImpl;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.dao.StockDao;
import DAO.pojo.Stock;

public class StockDaoProxy implements StockDaoProxyService{

	@Override
	public boolean insert(Stock stock) throws Exception {
		StockDao stockDaoImpl=new StockDaoImpl();
		try {
			if (stockDaoImpl.findByID(stock.getStockId())==null) {
				stockDaoImpl.insert(stock);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Stock findByID(String stockId) throws Exception {
		StockDao stockDaoImpl=new StockDaoImpl();
		try {
			return stockDaoImpl.findByID(stockId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List findAll() throws Exception {
		StockDao stockDaoImpl=new StockDaoImpl();
		try {
			return stockDaoImpl.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

}
