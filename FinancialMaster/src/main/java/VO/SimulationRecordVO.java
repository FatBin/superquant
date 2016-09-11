package VO;

import ENUM.Deal_enum;

public class SimulationRecordVO {

	private String id;
	private String userID;
	private String stockID;
	private String time;
	private Deal_enum deal;
	private double money;	
	
	public SimulationRecordVO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStockID() {
		return stockID;
	}
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Deal_enum getDeal() {
		return deal;
	}
	public void setDeal(Deal_enum deal) {
		this.deal = deal;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	
	
}
