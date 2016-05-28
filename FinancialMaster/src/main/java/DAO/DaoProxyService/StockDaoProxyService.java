package DAO.DaoProxyService;

import java.util.List;

import DAO.pojo.Stock;

public interface StockDaoProxyService {
	//insert a PO
	public boolean insert(Stock stock)throws Exception;
	
	//search a stock with its id
	public Stock findByID(String stockId)throws Exception;
	
	//get all stock data
	public List findAll()throws Exception;
	
	//get the industries
	public List getIndustries() throws Exception;
	
}
