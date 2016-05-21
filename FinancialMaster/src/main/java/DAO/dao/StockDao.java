package DAO.dao;

import DAO.pojo.Bench;
import DAO.pojo.Stock;
import antlr.collections.List;

public interface StockDao {
	//insert a PO
	public boolean insert(Stock stock)throws Exception;
	
	//search a stock with its id
	public Stock findByID(String stockId)throws Exception;
	
	//get all stock data
	public List findAll()throws Exception;
}
