package PO;

public class industryPO {
	private String industry;
	private int company;
	private double average_price;
	private double rise_fall;
	private double volume;
	private double turnover;
	private double inflows;
	private String leaderstock;
	private double price;
	private double stock_rise_fall;
	
	public industryPO(String industry, int company, double average_price, double rise_fall, double volume,
			double turnover, double inflows, String leaderstock, double price, double stock_rise_fall) {
		super();
		this.industry = industry;
		this.company = company;
		this.average_price = average_price;
		this.rise_fall = rise_fall;
		this.volume = volume;
		this.turnover = turnover;
		this.inflows = inflows;
		this.leaderstock = leaderstock;
		this.price = price;
		this.stock_rise_fall = stock_rise_fall;
	}
	
	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public int getCompany() {
		return company;
	}


	public void setCompany(int company) {
		this.company = company;
	}


	public double getAverage_price() {
		return average_price;
	}


	public void setAverage_price(double average_price) {
		this.average_price = average_price;
	}


	public double getRise_fall() {
		return rise_fall;
	}


	public void setRise_fall(double rise_fall) {
		this.rise_fall = rise_fall;
	}


	public double getVolume() {
		return volume;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}


	public double getTurnover() {
		return turnover;
	}


	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}


	public double getInflows() {
		return inflows;
	}


	public void setInflows(double inflows) {
		this.inflows = inflows;
	}


	public String getLeaderstock() {
		return leaderstock;
	}


	public void setLeaderstock(String leaderstock) {
		this.leaderstock = leaderstock;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getStock_rise_fall() {
		return stock_rise_fall;
	}


	public void setStock_rise_fall(double stock_rise_fall) {
		this.stock_rise_fall = stock_rise_fall;
	}
	
}
