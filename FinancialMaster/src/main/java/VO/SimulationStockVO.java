package VO;

public class SimulationStockVO {

	private String id;
	private String userID;
	private String time;
	private String stockID;
	private double price;
	private long number;
	
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
	
	
	
}
