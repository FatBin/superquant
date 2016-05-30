package VO;

import java.util.ArrayList;

import DAO.pojo.UserStrategy;

public class StrategyVO {

	String StrategyName;
	ArrayList<UserStrategy> userStrategies;
	
	

	public StrategyVO() {
		super();
	}

	public ArrayList<UserStrategy> getUserStrategies() {
		return userStrategies;
	}

	public void setUserStrategies(ArrayList<UserStrategy> userStrategies) {
		this.userStrategies = userStrategies;
	}

	public String getStrategyName() {
		return StrategyName;
	}

	public void setStrategyName(String strategyName) {
		StrategyName = strategyName;
	}
	
	
}
