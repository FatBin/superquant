package DAO.DAOfactory;
/*
 *in this project the strategy of factory is limited,so there is just one proxy
 */

import DAO.DaoProxy.BenchDaoProxy;
import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.DaoProxy.IndustriesDaoProxy;
import DAO.DaoProxy.StockDaoProxy;
import DAO.DaoProxy.TradeRecordDaoProxy;
import DAO.DaoProxy.UserDaoProxy;
import DAO.DaoProxy.UserStockDaoProxy;
import DAO.DaoProxy.UserStrategyDaoProxy;

public class DaoFactory {
	public static BenchDaoProxy getBenchDaoProxy(){
		return new BenchDaoProxy();
	}
	
	public static BenchdataDaoProxy getBenchdataDaoProxy(){
		return new BenchdataDaoProxy();
	}
	
	public static StockDaoProxy getStockDaoProxy(){
		return new StockDaoProxy();
	}
	
	public static TradeRecordDaoProxy getTradeRecordDaoProxy(){
		return new TradeRecordDaoProxy();
	}
	
	public static UserDaoProxy getUserDaoProxy() {
		return new UserDaoProxy();
	}
	
	public static UserStockDaoProxy getUserStockDaoProxy(){
		return new UserStockDaoProxy();
	}
	
	public static UserStrategyDaoProxy getUserStrategyDaoProxy(){
		return new UserStrategyDaoProxy();
	}
	
	public static IndustriesDaoProxy getIndustriesDaoProxy(){
		return new IndustriesDaoProxy();
	}
}
