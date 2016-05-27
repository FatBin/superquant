package VO;

import java.util.ArrayList;

public class UserVO {
	
	private String username;//ÓÃ»§Ãû
	private String password;//ÃÜÂë
	private ArrayList<StrategyVO> strategyList;
	private ArrayList<String> stockList;
	
	public UserVO(){}
	
	public ArrayList<StrategyVO> getStrategy() {
		return strategyList;
	}

	public void setStrategy(ArrayList<StrategyVO> strategy) {
		this.strategyList = strategy;
	}

	public ArrayList<String> getStockList() {
		return stockList;
	}

	public void setStockList(ArrayList<String> stockList) {
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
