package VO;

public class UserVO {
	
	private String username;//ÓÃ»§Ãû
	private String password;//ÃÜÂë
	private StrategyVO strategy;
	private String[] stockList;
	
	public void UserVO(){}
	
	public StrategyVO getStrategy() {
		return strategy;
	}

	public void setStrategy(StrategyVO strategy) {
		this.strategy = strategy;
	}

	public String[] getStockList() {
		return stockList;
	}

	public void setStockList(String[] stockList) {
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
