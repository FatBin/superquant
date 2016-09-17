package VO;

public class SimulationStockVO {

	private String id; //数据库记录id
	private String userID;
	private String time;
	private String stockID;
	private double price;  //买入价
	private long number;  //买入股数
	private double now;
	private double profitability;  //盈亏情况
	
	public SimulationStockVO() {
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	public double getNow() {
		return now;
	}

	public void setNow(double now) {
		this.now = now;
	}

	public double getProfitability() {
		return profitability;
	}

	public void setProfitability(double profitability) {
		this.profitability = profitability;
	}
	
	
	
}
