package VO;

import java.util.ArrayList;

import DAO.pojo.Stock;

public class UserVO {
	
	private String username;//ÓÃ»§Ãû
	private String password;//ÃÜÂë
	private ArrayList<String> strategyList;
	private ArrayList<Stock> stockList;
	
	public UserVO(){}
	
	public ArrayList<String> getStrategy() {
		return strategyList;
	}

	public void setStrategy(ArrayList<String> strategy) {
		this.strategyList = strategy;
	}

	public ArrayList<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<Stock> stockList) {
		this.stockList = stockList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
